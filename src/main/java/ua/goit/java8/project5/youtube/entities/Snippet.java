package ua.goit.java8.project5.youtube.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by t.oleksiv on 28/09/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Snippet {
    public String title;
    public Date publishedAt;
}
