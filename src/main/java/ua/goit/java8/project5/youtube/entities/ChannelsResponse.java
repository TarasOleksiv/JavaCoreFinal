package ua.goit.java8.project5.youtube.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by t.oleksiv on 28/09/2017.
 */

/*
Информация о каждом канале:
            id - string  The ID that YouTube uses to uniquely identify the channel.
            1.	Имя канала - snippet.title - string. The channel's title.
            2.	Дата создания канала - snippet.publishedAt - datetime. The date and time that the channel was created. The value is specified in ISO 8601 (YYYY-MM-DDThh:mm:ss.sZ) format.
            3.	Кол-во подписчиков - statistics.subscriberCount - unsigned long. The number of subscribers that the channel has.
            4.	Кол-во видео на канале - statistics.videoCount - unsigned long. The number of videos uploaded to the channel.
            5.	Кол-во просмотров всех видео - statistics.viewCount - unsigned long. The number of times the channel has been viewed.
            6.	Кол-во комментариев - statistics.commentCount - unsigned long. The number of comments for the channel.
*/

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelsResponse {
    public List<Channel> items;
}
