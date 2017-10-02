package ua.goit.java8.project5.youtube.entities.channels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Taras on 02.10.2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class InvideoPromotion {
    public List<Item> items;
}
