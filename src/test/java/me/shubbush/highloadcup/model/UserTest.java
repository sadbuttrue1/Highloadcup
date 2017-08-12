package me.shubbush.highloadcup.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;

import java.time.Instant;
import static org.junit.Assert.*;

/**
 * @author shubanev.a
 */
public class UserTest {

    @Test
    public void testJsonSerialize() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        User user = User.builder()
                .firstName("FirstName")
                .lastName("LastName")
                .birthDate(Instant.now())
                .id(1)
                .build();

        System.out.println(objectMapper.writeValueAsString(user));
    }

}