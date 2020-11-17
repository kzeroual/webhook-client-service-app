package com.nextronic.webhook.client.app.brocker.listener;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.nextronic.webhook.client.app.brocker.config.MessagingBrockerConfig;
import com.nextronic.webhook.client.app.domain.Message;
import com.nextronic.webhook.client.app.service.WebhookClientService;
import com.nextronic.webhook.client.app.util.LoggingUtils;

import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@AllArgsConstructor
@Component
@CommonsLog
public class WebHookMessageListener {
	private WebhookClientService webhookClientService;
	
	private final String webhookUrl = "https://hooks.slack.com/services/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";
	
	
	
	@RabbitListener(queues = MessagingBrockerConfig.WEBHOOK_QUEUE_NAME)
	public void onMessage(Map<String,Object> payload) throws Exception {
		log.info(LoggingUtils.getMessage(payload)); 
		Message m = new Message();
		
		m.setText("This a message send by webhook service for alert");
		
		log.info(LoggingUtils.getMessage(m)); 
		webhookClientService.sendContent(webhookUrl, m);
	}
	
	
	
}