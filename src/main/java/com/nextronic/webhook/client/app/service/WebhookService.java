package com.nextronic.webhook.client.app.service;



import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextronic.webhook.client.app.dao.WebhookRepository;
import com.nextronic.webhook.client.app.domain.Webhook;
import com.nextronic.webhook.client.app.util.LoggingUtils;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class WebhookService {
	@Autowired
    private WebhookRepository repository;
    
	public Webhook addWebhook(Webhook entity) {
		log.info(LoggingUtils.getMessage(entity));
		Webhook result =	repository.save(entity);
		log.info(LoggingUtils.getMessage(result));
		return result;
	}
	
	public Webhook updateWebhook(String id, Webhook entity) {
		log.info(LoggingUtils.getMessage(entity));
		log.info(LoggingUtils.getMessage(id));
		Webhook result = repository.findById(id).flatMap(existingWebhook -> {
				existingWebhook.setUrl(entity.getUrl());
				existingWebhook.setProvider(entity.getProvider());
				Webhook rs = repository.save(existingWebhook);
				return Optional.of(rs);
		}).get();
		log.info(LoggingUtils.getMessage(result));
		return result;
	}
	
	public Webhook findWebhookById(String id) {
		log.info(LoggingUtils.getMessage(id));
		Webhook result = repository.findById(id).get();
		log.info(LoggingUtils.getMessage(result));
		return result;
	}
	
	public Webhook findWebhookByUrl(String url) {
		log.info(LoggingUtils.getMessage(url));
		Webhook result = repository.findByUrl(url);
		log.info(LoggingUtils.getMessage(result));
		return result;
	}
	
	public Collection<Webhook> getAllWebhooks() {
		Collection<Webhook> result = repository.findAll();
		log.info(LoggingUtils.getMessage(result));
		return result;
	}

	

	public void deleteWebhookUrlById(String webhookUrl_id) {
		log.info(LoggingUtils.getMessage(webhookUrl_id));
		 repository.findById(webhookUrl_id)
		 .ifPresent(existingWebhookUrl -> repository.deleteById(webhookUrl_id));
	}
	
}
