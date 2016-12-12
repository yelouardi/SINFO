package org.sinfo.controller;

import java.util.List;
import org.sinfo.annotation.Loggable;
import org.sinfo.annotation.Loggable.level;
import org.sinfo.entity.Topic;
import org.sinfo.entity.TopicPage;
import org.sinfo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
	
/**
 * @author yelouardi
 * Topic Controller RS
 */
@RestController
@RequestMapping("/topics")
@Api(value = "topics")
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	/** Get All Topics
	 * @return
	 */
	@Loggable(calss=TopicController.class, message = "Web Request HTTP RS =>> Get All Topics ", type = level.INFO)
	@RequestMapping(method = RequestMethod.GET)	
	@ApiOperation(value = "Returns List Topic ", notes = "Returns a complete list topic", response = Topic.class ,responseContainer = "List")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval list topics", response = Topic.class,responseContainer = "List"),
	    @ApiResponse(code = 404, message = "List Topics  does not exist"),
	    @ApiResponse(code = 500, message = "Internal server error")}
	)
	public List<Topic> getToipsList(){
		return topicService.getListTopic();
	}
	
	
	/** Get Topics By Code Tag
	 * @param codeTag
	 * @return
	 */
	@Loggable(calss=TopicController.class, message = "Web Request HTTP RS =>> Get Topics By Code Tag ", type = level.INFO)
	@RequestMapping(method = RequestMethod.GET, value = "/{codeTag}")
	@ApiOperation(value = "Returns List Topic By Code Tag", notes = "Returns a complete list topic By Code Tag", response = Topic.class,responseContainer = "List")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrievalcode tag ", response = Topic.class, responseContainer = "List"),
	    @ApiResponse(code = 404, message = "List Topics with code tag does not exist"),
	    @ApiResponse(code = 500, message = "Internal server error")}
	)
	public List<Topic> getToipsList(@PathVariable Long codeTag){
		return topicService.getListTopicByTag(codeTag);
	}
	
	/** Get One Topic By Code
	 * @param codeTopic
	 * @return
	 */
	@Loggable(calss=TopicController.class, message = "Web Request HTTP RS =>> Get One Topic By Code ", type = level.INFO)
	@RequestMapping(method = RequestMethod.GET, value = "/more/{codeTopic}")
	@ApiOperation(value = "Returns  Topic ", notes = "Returns a complete list topic by code ", response = Topic.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval of code topic", response = Topic.class),
	    @ApiResponse(code = 404, message = "List Topics with code topic does not exist"),
	    @ApiResponse(code = 500, message = "Internal server error")}
	)
	public Topic getToipc(@PathVariable Long codeTopic){
		return topicService.getTopicById(codeTopic);
	}
	

	/** Get Topics By Page and Size
	 * @param page
	 * @param size
	 * @return
	 */
	@Loggable(calss=TopicController.class, message = "Web Request HTTP RS =>> Get Topics By Page and Size ", type = level.INFO)
	@RequestMapping(method = RequestMethod.GET, value = "/{page}/{size}")
	@ApiOperation(value = "Returns List Topic ", notes = "Returns a complete list topic", response = TopicPage.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful retrieval list topic of page and size ", response = TopicPage.class),
	    @ApiResponse(code = 404, message = "List Topics does not exist"),
	    @ApiResponse(code = 500, message = "Internal server error")}
	)
	public TopicPage getToipcByPage(@PathVariable int page,@PathVariable int size){
		PageRequest pageRequest = new PageRequest(page, size);
		TopicPage topicPage=new TopicPage();
		Page<Topic> findAllPageable = topicService.findAllPageable(pageRequest);
		topicPage.setNumber(findAllPageable.getNumber());
		topicPage.setNumberOfElements(findAllPageable.getNumberOfElements());
		topicPage.setSize(findAllPageable.getSize());
		topicPage.setTotalElements(findAllPageable.getTotalElements());
		topicPage.setTotalPages(findAllPageable.getTotalPages());
		topicPage.setTopics(findAllPageable.getContent());
		return topicPage;
	}
}