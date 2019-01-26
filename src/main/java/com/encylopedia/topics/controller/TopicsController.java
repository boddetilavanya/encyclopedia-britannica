package com.encylopedia.topics.controller;

import java.net.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.encylopedia.topics.exception.TopicsErrorResponseVO;
import com.encylopedia.topics.exception.TopicsServiceException;
import com.encylopedia.topics.vo.GetTopicsVO;

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
@Api(value = "eb", produces = "application/xml", protocols = "https")
@RestController
@RequestMapping(value = "eb")
@EnableAutoConfiguration
@ComponentScan
public class TopicsController {

	private static final Logger logger = LoggerFactory.getLogger(TopicsController.class);

	
	@ApiOperation(value = "Retrieves the topic details. ", notes = "Retrieves the topic details for the given topicId")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = " Topic Details Not Found", response = TopicsErrorResponseVO.class),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = " Bad Request", response = TopicsErrorResponseVO.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = " Service Invocation Error", response = TopicsErrorResponseVO.class)
	})
	@ApiImplicitParams({

			@ApiImplicitParam(name = "X-EB-APP_INFO", required = true, dataType = "string", paramType = "header", value = "API consumer information pipe delimeted", example = "{App Name}|{Channel Id}|{Channel Name}|{RequestUUID}|{UserId}") 
			})
	@RequestMapping(value = "/topic", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<GetTopicsVO> getTopic(@ApiParam(name="topicId",value="Topic Id for which the topic details are retrieved",required=true) @RequestParam("topic_id") String topicId, HttpServletRequest request) throws TopicsServiceException{
		logger.info("Beginning of getTopic");
		if(logger.isDebugEnabled()) {
			logger.debug("ConsumerInfo: {}, TopicId: {}",request.getHeader("X-EB-APP_INFO"), topicId);
		}
		
		return null;

	}
	
	@ApiOperation(value = "Retrieves all the matching topic details. ", notes = "Retrieves all the matching topic details for the given className")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = " Topic Details Not Found", response = TopicsErrorResponseVO.class),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = " Bad Request", response = TopicsErrorResponseVO.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = " Service Invocation Error", response = TopicsErrorResponseVO.class)
	})
	@ApiImplicitParams({

			@ApiImplicitParam(name = "X-EB-APP_INFO", required = true, dataType = "string", paramType = "header", value = "API consumer information pipe delimeted", example = "{App Name}|{Channel Id}|{Channel Name}|{RequestUUID}|{UserId}") 
			})
	@RequestMapping(value = "/class", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<GetTopicsVO> getTopicByClassName(@ApiParam(name="className",value="Class Name for which all the matching topic details are retrieved",required=true) @RequestParam("class-name") String topicId, HttpServletRequest request) throws TopicsServiceException{
		logger.info("Beginning of getTopicByClassName");
		if(logger.isDebugEnabled()) {
			logger.debug("ConsumerInfo: {}, ClassName: {}",request.getHeader("X-EB-APP_INFO"), topicId);
		}
		
		return null;

	}
	
	
	@ApiOperation(value = "Retrieves all the topic details. ", notes = "Retrieves all the topic details")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = " Topic Details Not Found", response = TopicsErrorResponseVO.class),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = " Bad Request", response = TopicsErrorResponseVO.class),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = " Service Invocation Error", response = TopicsErrorResponseVO.class)
	})
	@ApiImplicitParams({

			@ApiImplicitParam(name = "X-EB-APP_INFO", required = true, dataType = "string", paramType = "header", value = "API consumer information pipe delimeted", example = "{App Name}|{Channel Id}|{Channel Name}|{RequestUUID}|{UserId}") 
			})
	@RequestMapping(value = "/all/topic", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<GetTopicsVO> getAllTopics(HttpServletRequest request) throws TopicsServiceException{
		logger.info("Beginning of getAllTopics , ConsumerInfo: {}",request.getHeader("X-EB-APP_INFO"));
		if(logger.isDebugEnabled()) {
			logger.debug("ConsumerInfo: {}",request.getHeader("X-EB-APP_INFO"));
		}
		
		return null;

	}

}
