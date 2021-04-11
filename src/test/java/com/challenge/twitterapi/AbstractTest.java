package com.challenge.twitterapi;

import java.io.IOException;

import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Abstract class used to create web application context and define methods
 * to convert the JSON string into Java object
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TwitterApiApplication.class)
@WebAppConfiguration
public abstract class AbstractTest {
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    /**
     * Create web application context
     */
    protected void setUp() {
        System.out.println("webApplicationContext: "+ webApplicationContext);
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Convert JSON String to specified class
     * @param json JSON string
     * @param clazz Class to convert
     * @param <T> Any type of object
     * @return Object with JSON data
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    /**
     * Convert JSON String to specified collection type
     * @param json JSON String
     * @param collectionType Collection type to convert
     * @param <T> Any type of object
     * @return Object with JSON data
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    protected <T> T mapFromJson(String json, CollectionType collectionType)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, collectionType);
    }
}