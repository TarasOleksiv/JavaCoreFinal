package ua.goit.java8.project5.reports;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import ua.goit.java8.project5.Main;
import ua.goit.java8.project5.extra.FileUtils;
import ua.goit.java8.project5.youtube.entities.channels.ChannelsResponse;

import java.io.IOException;

/**
 * Created by Taras on 29.09.2017.
 */
public class HTTPRequest {
    private static final String SEARCH_LINK = "https://www.googleapis.com/youtube/v3/channels";
    private static final String MY_KEY = "AIzaSyDwu_AH-9_PNHCKIiIzJ-uqXGwNWOfAURw";
    private FileUtils fileUtils = new FileUtils();

    // отримання результатів запиту через HTTP
    public ChannelsResponse getHTTPResponse(String id) throws UnirestException {
        //id = "UC_x5XG1OV2P6uZZ5FSM9Ttw";
        HttpResponse<ChannelsResponse> response = Unirest.get(SEARCH_LINK)
                .queryString("part", "snippet,statistics,invideoPromotion")
                .queryString("id", id)
                .queryString("key", MY_KEY)
                .asObject(ChannelsResponse.class);
        ChannelsResponse result = response.getBody();

        // якщо в налаштуваннях увімкнуто опцію використання кешу, то
        // зберігаєм результат в JSON форматі у кеш для майбутнього використання
        if (Main.settingsSet.getUseCache()){
            String json = JSON.toJSONString(result);
            try {
                fileUtils.writeToFile(json, getJSONPath(id));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    // отримання результатів запиту з попередньо збереженого JSON (якщо він існує)
    public ChannelsResponse getJSONResponse(String id) throws UnirestException, IOException {
        ChannelsResponse result = null;
        // якщо збережено JSON локально, то тягнем з нього
        if (!fileUtils.fileExists(getJSONPath(id))){
            result = getHTTPResponse(id);
        // якщо локально збереженого JSON неіснує то тягнем через HTTP
        } else {
            String json;
            json = fileUtils.readFromFile(getJSONPath(id));
            result = JSON.parseObject(json,ChannelsResponse.class);
        }

        return result;
    }

    private String getJSONPath(String id){
        return Main.settingsSet.getPathToCache() + "\\" + id + ".json";
    }

}
