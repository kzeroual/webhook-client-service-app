package com.nextronic.webhook.client.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nextronic.webhook.client.app.domain.Message;
import com.nextronic.webhook.client.app.domain.Webhook;
import com.nextronic.webhook.client.app.util.LoggingUtils;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class WebhookClientService {
	@Autowired
	private RestTemplate restTemplate; 
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private WebhookService webhookService;
	
	public String sendMessage(String webhookUrl,Message message) throws JsonProcessingException {
		log.info(LoggingUtils.getMessage(webhookUrl));
		log.info(LoggingUtils.getMessage(message));
		Webhook webhook = webhookService.findWebhookByUrl(webhookUrl);
		log.info(LoggingUtils.getMessage(webhook));
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String messageJson = objectMapper.writeValueAsString(message);
        HttpEntity<String> entity = new HttpEntity<String>(messageJson, headers);
        String result = restTemplate.exchange(webhook.getUrl(), HttpMethod.POST, entity, String.class).getBody();
        log.info(LoggingUtils.getMessage(result));
        return result;
	}
	
}
