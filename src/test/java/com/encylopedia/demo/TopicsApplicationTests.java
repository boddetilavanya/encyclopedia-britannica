package com.encylopedia.demo;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.encylopedia.topics.TopicsApplication;
import com.encylopedia.topics.dao.TopicRowMapper;
import com.encylopedia.topics.dto.TopicDTO;
import com.encylopedia.topics.service.TopicsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TopicsApplication.class)
@AutoConfigureMockMvc
public class TopicsApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	TopicsServiceImpl topicsService;

	@MockBean
	@Qualifier("namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Test
	public void testGetTopic_validValues() throws Exception {
		TopicDTO topicDTO = createTopicDTO(4144,"sports","acrobatic-skiing-Year-In-Review-1997");
		given(this.namedParameterJdbcTemplate.queryForObject(
				eq("select TOPIC_ID,CLASS_NAME,TITLE from TOPIC where TOPIC_ID= :topicId"),
				any(SqlParameterSource.class), any(TopicRowMapper.class)))
						.willReturn(topicDTO);

		MvcResult result = this.mockMvc
				.perform(get("/eb/topic?topic_id=4144").header("X-EB-APP_INFO", "abc|def|hij|12345|lboddeti")
						.contentType(MediaType.APPLICATION_XML_VALUE).accept(MediaType.APPLICATION_XML_VALUE))
				.andExpect(status().is(200)).andReturn();
		System.out.println("Result is " + result.getResponse().getContentAsString());
	}


	private TopicDTO createTopicDTO(Integer topicId,String className, String title) {
		TopicDTO topicDTO=new TopicDTO();
		topicDTO.setTopicId(topicId);
		topicDTO.setUrlClass(className);
		topicDTO.setUrlTitle(title);
		return topicDTO;
	}
	
	private List<TopicDTO> createTopicDTOList() {
		List<TopicDTO> topicDTOList=new ArrayList<>();
		
		topicDTOList.add(createTopicDTO(4144,"sports","acrobatic-skiing-Year-In-Review-1997"));
		topicDTOList.add(createTopicDTO(4143,"sports","acrobatic-skiing"));
		return topicDTOList;
	}
	private List<TopicDTO> createTopicsDTO() {
		List<TopicDTO> topicDTOList=new ArrayList<>();
		
		topicDTOList.add(createTopicDTO(4144,"sports","acrobatic-skiing-Year-In-Review-1997"));
		topicDTOList.add(createTopicDTO(4143,"sports","acrobatic-skiing"));
		topicDTOList.add(createTopicDTO(4143,"science","ablation-cluster-preparation"));
		return topicDTOList;
	}
	@Test
	public void testGetTopicsByClassName_ValidValues() throws Exception {
		given(this.namedParameterJdbcTemplate.query(
				eq("select TOPIC_ID,CLASS_NAME,TITLE from TOPIC where CLASS_NAME= :className"),
				any(SqlParameterSource.class), any(TopicRowMapper.class)))
						.willReturn(createTopicDTOList());

		MvcResult result = this.mockMvc
				.perform(get("/eb/class?class-name=sports").header("X-EB-APP_INFO", "abc|def|hij|12345|lboddeti")
						.contentType(MediaType.APPLICATION_XML_VALUE).accept(MediaType.APPLICATION_XML_VALUE))
				.andExpect(status().is(200)).andReturn();
		System.out.println("Result is " + result.getResponse().getContentAsString());
	}
	@Test
	public void testGetTopics_ValidValues() throws Exception {
		given(this.namedParameterJdbcTemplate.query(
				eq("select TOPIC_ID,CLASS_NAME,TITLE from TOPIC"),
				any(SqlParameterSource.class), any(TopicRowMapper.class)))
						.willReturn(createTopicsDTO());

		MvcResult result = this.mockMvc
				.perform(get("/eb/all/topic").header("X-EB-APP_INFO", "abc|def|hij|12345|lboddeti")
						.contentType(MediaType.APPLICATION_XML_VALUE).accept(MediaType.APPLICATION_XML_VALUE))
				.andExpect(status().is(200)).andReturn();
		System.out.println("Result is " + result.getResponse().getContentAsString());
	}

	@Test
	public void testGetTopic_EmptyResultSet() throws Exception {
		given(this.namedParameterJdbcTemplate.queryForObject(
				eq("select TOPIC_ID,CLASS_NAME,TITLE from TOPIC where TOPIC_ID= :topicId"),
				any(SqlParameterSource.class), any(TopicRowMapper.class)))
						.willThrow(new EmptyResultDataAccessException(1));

		MvcResult result = this.mockMvc
				.perform(get("/eb/topic?topic_id=4144").header("X-EB-APP_INFO", "abc|def|hij|12345|lboddeti")
						.contentType(MediaType.APPLICATION_XML_VALUE).accept(MediaType.APPLICATION_XML_VALUE))
				.andExpect(status().is(200)).andReturn();
		System.out.println("Result is " + result.getResponse().getContentAsString());
	}

	@Test
	public void testGetTopic_missingMandatoryfields() throws Exception {
		given(this.namedParameterJdbcTemplate.queryForObject(
				eq("select TOPIC_ID,CLASS_NAME,TITLE from TOPIC where TOPIC_ID= :topicId"),
				any(SqlParameterSource.class), any(TopicRowMapper.class)))
						.willThrow(new EmptyResultDataAccessException(1));

		MvcResult result = this.mockMvc
				.perform(get("/eb/topic?topic_id=").header("X-EB-APP_INFO", "abc|def|hij|12345|lboddeti")
						.contentType(MediaType.APPLICATION_XML_VALUE).accept(MediaType.APPLICATION_XML_VALUE))
				.andExpect(status().is(400)).andReturn();
		System.out.println("Result is " + result.getResponse().getContentAsString());
	}

	@Test
	public void testGetTopicsByClassName_EmptyResultSet() throws Exception {
		given(this.namedParameterJdbcTemplate.query(
				eq("select TOPIC_ID,CLASS_NAME,TITLE from TOPIC where CLASS_NAME= :className"),
				any(SqlParameterSource.class), any(TopicRowMapper.class)))
						.willThrow(new EmptyResultDataAccessException(1));

		MvcResult result = this.mockMvc
				.perform(get("/eb/class?class-name=social").header("X-EB-APP_INFO", "abc|def|hij|12345|lboddeti")
						.contentType(MediaType.APPLICATION_XML_VALUE).accept(MediaType.APPLICATION_XML_VALUE))
				.andExpect(status().is(400)).andReturn();
		System.out.println("Result is " + result.getResponse().getContentAsString());
	}

	@Test
	public void testGetTopicsByClassName_missingMandatoryfields() throws Exception {

		MvcResult result = this.mockMvc
				.perform(get("/eb/class?class-name=").header("X-EB-APP_INFO", "abc|def|hij|12345|lboddeti")
						.contentType(MediaType.APPLICATION_XML_VALUE).accept(MediaType.APPLICATION_XML_VALUE))
				.andExpect(status().is(400)).andReturn();
		System.out.println("Result is " + result.getResponse().getContentAsString());
	}

	@Test
	public void testGetTopics_EmptyResultSet() throws Exception {
		given(this.namedParameterJdbcTemplate.query(eq("select TOPIC_ID,CLASS_NAME,TITLE from TOPIC"),
				any(TopicRowMapper.class))).willThrow(new EmptyResultDataAccessException(1));

		MvcResult result = this.mockMvc
				.perform(get("/eb/all/topic").header("X-EB-APP_INFO", "abc|def|hij|12345|lboddeti")
						.contentType(MediaType.APPLICATION_XML_VALUE).accept(MediaType.APPLICATION_XML_VALUE))
				.andExpect(status().is(400)).andReturn();
		System.out.println("Result is " + result.getResponse().getContentAsString());
	}

}
