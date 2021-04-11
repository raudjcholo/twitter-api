package com.challenge.twitterapi;

import com.challenge.twitterapi.dto.HashtagDto;
import com.challenge.twitterapi.dto.TweetDto;
import com.challenge.twitterapi.service.TweetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Tests class
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TwitterApiApplicationTests extends AbstractTest {

	@Autowired
	TweetService tweetService;

	/**
	 * Create web application context and create test data
	 */
	@BeforeAll
	public void setUp() {
		super.setUp();

		TweetDto tweet = new TweetDto(new Long("1381245065889189892"), "test1", "test tweet #hashtag", null);
		TweetDto tweet2 = new TweetDto(new Long("1381245065889189893"), "test2", "test tweet #hashtag2", null);
		TweetDto tweet3 = new TweetDto(new Long("1381245065889189894"), "test1", "test tweet #hashtag", null);
		tweet.setValidation(true);
		tweetService.save(tweet);
		tweetService.save(tweet2);
		tweetService.save(tweet3);
	}

	/**
	 * Test that verifies that the tweet reading API method returns the 3 previously inserted tweets
	 * @throws Exception
     */
	@Test
	void getTweets() throws Exception {

		String uri = "/tweets";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		TweetDto[] tweetList = super.mapFromJson(content, TweetDto[].class);
		assertTrue(tweetList.length == 3);
	}

	/**
	 * Test that verifies that the validation of a tweet works correctly
	 * @throws Exception
     */
	@Test
	void validateTweet() throws Exception{

		String uri = "/tweet/validate/1381245065889189892";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		TweetDto tweet = super.mapFromJson(content, TweetDto.class);
		assertEquals(true, tweet.getValidation());

	}

	/**
	 * Test that verifies that the validated tweets of a user are returned correctly
	 * @throws Exception
     */
	@Test
	void findTweetsByUser() throws Exception{

		String uri = "/tweets/test1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		TweetDto[] tweetList = super.mapFromJson(content, TweetDto[].class);
		assertTrue(tweetList.length == 1);

	}

	/**
	 * Test that verifies that the most used hashtag is correct, as well as its number of repetitions
	 * @throws Exception
     */
	@Test
	void findHashtags() throws Exception{

		String uri = "/hashtags/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		CollectionType javaType = mapper.getTypeFactory()
				.constructCollectionType(Collection.class, HashtagDto.class);
		Collection<HashtagDto> hashtagList = super.mapFromJson(content, javaType);
		assertTrue(hashtagList.size() == 1);
		assertEquals("#hashtag",hashtagList.iterator().next().getKey());
		assertEquals(new Long(2),hashtagList.iterator().next().getReplays());

	}
}
