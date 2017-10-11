package ua.goit.java8.project5.reports;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import ua.goit.java8.project5.Main;
import ua.goit.java8.project5.tools.FileUtils;
import ua.goit.java8.project5.tools.Key;
import ua.goit.java8.project5.youtube.entities.channels.ChannelsResponse;

import java.io.IOException;

public class HTTPRequestChannelComments {
    private static final String SEARCH_LINK = "https://www.googleapis.com/youtube/v3/commentThreads";
    // ключ вантажимо з файлу
    //private static final String MY_KEY = "AIzaSyDwu_AH-9_PNHCKIiIzJ-uqXGwNWOfAURw";
    private static int countOfAllComments;
    private FileUtils fileUtils = new FileUtils();

    public int start(String channelid)  {

        countOfAllComments = 0;
        HttpResponse<ChannelsResponse> response = null;
        try {
            response = Unirest.get(SEARCH_LINK)
                    .queryString("part", "snippet,replies")
                    .queryString("allThreadsRelatedToChannelId", channelid)
                    .queryString("maxResults", "100")
                    .queryString("key", Key.myKey)
                    .asObject(ChannelsResponse.class);

            countOfAllComments = countOfAllComments + response.getBody().getPageInfo().getTotalResults();

            if (response.getBody().getPageInfo().getTotalResults() >= 100) {
                readPages(response.getBody().nextPageToken,channelid);
            }

            // якщо в налаштуваннях увімкнуто опцію використання кешу, то
            // зберігаєм результат в JSON форматі у кеш для майбутнього використання
            if (Main.settingsSet.getUseCache()){
                String json = JSON.toJSONString(countOfAllComments);
                try {
                    fileUtils.writeToFile(json, getJSONPath(channelid));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return countOfAllComments;
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        System.out.println("HTTPRequestChannelComments eror");
        return -1;
    }

    private static void readPages(String nextPageToken, String channelid) throws UnirestException {

        HttpResponse<ChannelsResponse> response = Unirest.get(SEARCH_LINK)
                .queryString("part", "snippet,replies")
                .queryString("pageToken", nextPageToken)
                .queryString("allThreadsRelatedToChannelId",channelid)
                .queryString("maxResults", "100")
                .queryString("key", Key.myKey)
                .asObject(ChannelsResponse.class);


        countOfAllComments = countOfAllComments + response.getBody().getPageInfo().getTotalResults();

        if (response.getBody().getPageInfo().getTotalResults() < 100) {
            return;
        } else {
            readPages(response.getBody().nextPageToken,channelid);
        }
    }

    // отримання результатів запиту з попередньо збереженого JSON (якщо він існує)
    public int startFromJSON(String channelid) throws UnirestException, IOException {
        int result = 0;
        // якщо збережено JSON локально, то тягнем з нього
        if (!fileUtils.fileExists(getJSONPath(channelid))){
            result = start(channelid);
            // якщо локально збереженого JSON неіснує то тягнем через HTTP
        } else {
            String json;
            json = fileUtils.readFromFile(getJSONPath(channelid));
            result = (int)JSON.parse(json);
        }

        return result;
    }

    private String getJSONPath(String id){
        return Main.settingsSet.getPathToCache() + "\\" + id + "_comments.json";
    }
}