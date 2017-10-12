package ua.goit.java8.project5.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;

import java.io.IOException;

/**
 * Created by t.oleksiv on 22/09/2017.
 */
public class MyObjectMapper {

    public MyObjectMapper(){
        initObjectMapper();
    }

    private void initObjectMapper() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private JacksonObjectMapper jacksonObjectMapper
                    = new JacksonObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
