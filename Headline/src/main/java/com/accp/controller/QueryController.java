package com.accp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.domain.Consumer;
import com.accp.domain.News;
import com.accp.domain.PageBean;
import com.accp.domain.Talk;
import com.accp.service.ConsumerService;
import com.accp.service.NewsService;
import com.accp.service.TalkService;

@Controller
@ResponseBody
@RequestMapping("/query/")
public class QueryController {
	
	@Autowired
	TalkService talkservice;
	@Autowired
	NewsService newsService;
	@Autowired
	ConsumerService consumerService;
	
	@RequestMapping("talks")
	public PageBean<Talk> talks(int nid,int page){
		Consumer consumer=new Consumer();
		consumer.setId(1);
		return talkservice.query(nid, page,consumer);
	}
	
	@RequestMapping("news")
	public News news(int id) {
		Consumer consumer=new Consumer();
		consumer.setId(1);
		return newsService.queryById(id,consumer);
	}
	
	@RequestMapping("login")
	public Consumer login(Consumer consumer,HttpSession session) {
		try {
			Thread.sleep(800);			
		} catch (Exception e) {
			// TODO: handle exception
		}
		consumer=consumerService.login(consumer);
		session.setAttribute("user", consumer);
		return consumer;
	}
	
	@RequestMapping("queryConsumerById")
	public Consumer queryConsumerById(Integer id ) {
		return consumerService.queryByAid(id);
	}
}
