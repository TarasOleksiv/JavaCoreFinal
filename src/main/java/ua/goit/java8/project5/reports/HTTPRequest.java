package ua.goit.java8.project5.reports;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import ua.goit.java8.project5.youtube.entities.ChannelsResponse;

/**
 * Created by Taras on 29.09.2017.
 */
public class HTTPRequest {
    private static final String SEARCH_LINK = "https://www.googleapis.com/youtube/v3/channels";
    private static final String MY_KEY = "AIzaSyDwu_AH-9_PNHCKIiIzJ-uqXGwNWOfAURw";

    // отримання результатів запиту
    public ChannelsResponse getResponse(String id) throws UnirestException {
        //id = "UC_x5XG1OV2P6uZZ5FSM9Ttw";
        HttpResponse<ChannelsResponse> response = Unirest.get(SEARCH_LINK)
                .queryString("part", "snippet,statistics")
                .queryString("id", id)
                .queryString("key", MY_KEY)
                .asObject(ChannelsResponse.class);
        return response.getBody();
    }
}
