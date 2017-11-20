package com.choice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.TextMessage;

import com.choice.filter.WSHandler;

@Controller
public class PageController {
	
	/*private WSHandler wsHandler = new WSHandler();*/
	
	@RequestMapping("/")
	public String index(){
		/*wsHandler.sendMessageToUsers(new TextMessage("进入首页"));*/
		return "index";
	}

}
