package com.nextronic.webhook.client.app.brocker.listener;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.nextronic.webhook.client.app.brocker.config.MessagingBrockerConfig;
import com.nextronic.webhook.client.app.util.LoggingUtils;

import lombok.extern.apachecommons.CommonsLog;

@Component
@CommonsLog
public class WebHookMessageListener {
	
	
	@RabbitListener(queues = MessagingBrockerConfig.WEBHOOK_QUEUE_NAME)
	public void onMessage(Map<String,Object> payload) throws Exception {
		
		log.info(LoggingUtils.getMessage(payload));
	    
	}
	
	
	
}