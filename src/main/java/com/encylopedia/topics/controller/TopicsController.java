package com.encylopedia.topics.controller;

import java.net.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.encylopedia.topics.exception.TopicsErrorResponseVO;
import com.encylopedia.topics.exception.TopicsServiceException;
import com.encylopedia.topics.service.TopicsService;
import com.encylopedia.topics.vo.Topics;
import com.encylopedia.topics.vo.UrlPublish;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author lboddeti
 *
 */
@Controller
@Import(ObjectMapper.class)
@Api(value = "eb", produces = "application/xml", protocols = "https")
@RestController
@RequestMapping(value = "/eb")
@EnableAutoConfiguration
@ComponentScan
public class TopicsController {

	private static final Logger logger = LoggerFactory.getLogger(TopicsController.class);

	@Autowired
	private TopicsService topicsService;

	@Autowired
	ObjectMapper ob;

	/*
	 * Method to retrieve all the topic details for the given topic_id .
	 * 
	 * @param topicId
	 * 
	 * @return Topics
	 * 
	 * @throws TopicsServiceException
	 * 
	 */
	@ApiOperation(value = "Retrieves the topic details. ", notes = "Retrieves the topic details for the given topicId")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = " Topic Details Not Found", response = TopicsErrorResponseVO.class),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = " Bad Request", response = TopicsErrorResponseVO.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = " Service Invocation Error", response = TopicsErrorResponseVO.class) })
	@ApiImplicitParams({

			@ApiImplicitParam(name = "X-EB-APP-INFO", required = true, dataType = "string", paramType = "header", value = "API consumer information pipe delimeted", example = "{App Name}|{Channel Id}|{Channel Name}|{RequestUUID}|{UserId}") })
	@RequestMapping(value = "/topic", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.TEXT_XML_VALUE })
	public ResponseEntity<UrlPublish> getTopic(
			@ApiParam(name = "topicId", value = "Topic Id for which the topic details are retrieved", required = true) @RequestParam("topic_id") Integer topicId,
			HttpServletRequest request) throws TopicsServiceException {
		logger.info("Beginning of getTopic");
		logger.info("ConsumerInfo: {}, TopicId: {}", request.getHeader("X-EB-APP_INFO"), topicId);
		ResponseEntity<UrlPublish> responseEntity = null;
		validateGetTopicRequest(topicId);
		UrlPublish topicVO = topicsService.getTopic(topicId);
		if (topicVO != null) {

			responseEntity = new ResponseEntity<>(topicVO, HttpStatus.OK);
		}
		return responseEntity;

	}

	/*
	 * Method to validate GetTopic Request.
	 * 
	 * @param topicId
	 * 
	 * @throws TopicsServiceException
	 * 
	 */
	private void validateGetTopicRequest(Integer topicId) throws TopicsServiceException {
		if (topicId == null) {
			throw new TopicsServiceException("ValidationError", "E001", "Missing mandatory Field");
		}

	}

	/*
	 * Method to retrieve all the matching topic details for the given class name .
	 * 
	 * @param className
	 * 
	 * @return Topics
	 * 
	 * @throws TopicsServiceException
	 * 
	 */
	@ApiOperation(value = "Retrieves all the matching topic details. ", notes = "Retrieves all the matching topic details for the given className")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = " Topic Details Not Found", response = TopicsErrorResponseVO.class),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = " Bad Request", response = TopicsErrorResponseVO.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = " Service Invocation Error", response = TopicsErrorResponseVO.class) })
	@ApiImplicitParams({

			@ApiImplicitParam(name = "X-EB-APP-INFO", required = true, dataType = "string", paramType = "header", value = "API consumer information pipe delimeted", example = "{App Name}|{Channel Id}|{Channel Name}|{RequestUUID}|{UserId}") })
	@RequestMapping(value = "/class", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Topics> getTopicsByClassName(
			@ApiParam(name = "className", value = "Class Name for which all the matching topic details are retrieved", required = true) @RequestParam("class-name") String className,
			HttpServletRequest request) throws TopicsServiceException {
		logger.info("Beginning of getTopicsByClassName");
		logger.info("ConsumerInfo: {}, ClassName: {}", request.getHeader("X-EB-APP_INFO"), className);
		ResponseEntity<Topics> responseEntity = null;
		validateGetTopicsByClassNameRequest(className);
		Topics topics = topicsService.getTopicsByClassName(className);
		if (topics != null) {

			responseEntity = new ResponseEntity<>(topics, HttpStatus.OK);
		}
		return responseEntity;

	}

	/*
	 * Method to retrieve all the topic details.
	 * 
	 * @return Topics
	 * 
	 * @throws TopicsServiceException
	 * 
	 */
	@ApiOperation(value = "Retrieves all the topic details. ", notes = "Retrieves all the topic details")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = " Topic Details Not Found", response = TopicsErrorResponseVO.class),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = " Bad Request", response = TopicsErrorResponseVO.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = " Service Invocation Error", response = TopicsErrorResponseVO.class) })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "X-EB-APP-INFO", required = true, dataType = "string", paramType = "header", value = "API consumer information pipe delimeted", example = "{App Name}|{Channel Id}|{Channel Name}|{RequestUUID}|{UserId}") })
	@RequestMapping(value = "/all/topic", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Topics> getAllTopics(HttpServletRequest request) throws TopicsServiceException {
		logger.info("Beginning of getAllTopics , ConsumerInfo: {}", request.getHeader("X-EB-APP_INFO"));
		ResponseEntity<Topics> responseEntity = null;
		Topics topics = topicsService.getAllTopics();
		if (topics != null) {

			responseEntity = new ResponseEntity<>(topics, HttpStatus.OK);
		}
		return responseEntity;

	}

	/*
	 * Method to validate GetTopicsByClassName Request.
	 * 
	 * @param className
	 * 
	 * @throws TopicsServiceException
	 * 
	 */
	private void validateGetTopicsByClassNameRequest(String className) throws TopicsServiceException {
		if (className == null || className.isEmpty()) {
			throw new TopicsServiceException("ValidationError", "E001", "Missing mandatory Field");
		}

	}

	/*
	 * Method to handle exceptionsof the TopicsApplication
	 * 
	 * @param topicsServiceException
	 * 
	 * @return ResponseEntity<TopicsErrorResponseVO>
	 * 
	 */

	@ExceptionHandler(value = { TopicsServiceException.class })
	public ResponseEntity<TopicsErrorResponseVO> topicsExceptionhandler(Exception topicsServiceException) {
		logger.info("Beginning of topicsExceptionhandler");
		TopicsErrorResponseVO errorVO = new TopicsErrorResponseVO();
		errorVO.setErrorCode(((TopicsServiceException) topicsServiceException).getExceptionCode());
		errorVO.setErrorDescription(((TopicsServiceException) topicsServiceException).getMessage());
		ResponseEntity<TopicsErrorResponseVO> resp = new ResponseEntity<>(errorVO, HttpStatus.BAD_REQUEST);
		logger.info("End of topicsExceptionhandler");
		return resp;

	}

}
