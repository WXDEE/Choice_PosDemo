package com.choice.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebMvc
@EnableWebSocket
public class WSConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{
	 @Override
     public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

         //前台 可以使用websocket环境
         registry.addHandler(new WSHandler(),"/websocket").setAllowedOrigins("*")
         .addInterceptors(new HandshakeInterceptor());


       
     }


     // websocket 处理类
     @Bean
     public WebSocketHandler myWebSocketHandler(){
         return new WSHandler();
     }

}
