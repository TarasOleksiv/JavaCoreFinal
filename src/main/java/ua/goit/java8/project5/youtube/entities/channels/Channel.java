package ua.goit.java8.project5.youtube.entities.channels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by t.oleksiv on 28/09/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {
    public String id;
    public Snippet snippet;
    public Statistics statistics;
    public long commentsCount;
}
