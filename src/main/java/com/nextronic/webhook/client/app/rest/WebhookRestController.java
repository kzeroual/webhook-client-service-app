package com.nextronic.webhook.client.app.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nextronic.webhook.client.app.domain.Webhook ;
import com.nextronic.webhook.client.app.service.WebhookService;
import com.nextronic.webhook.client.app.util.LoggingUtils;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@RequestMapping("/webhooks")
@CrossOrigin("*")
@CommonsLog
public class WebhookRestController {

	@Autowired
	private WebhookService webhookService;
	
	@GetMapping
	public ResponseEntity<Collection<Webhook>> getAll() {
		log.info(LoggingUtils.getStartMessage());
		Collection<Webhook> result = webhookService.getAllWebhooks();
		log.info(LoggingUtils.getEndMessage());
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Webhook> getById(@PathVariable("id") String id) {
		log.info(LoggingUtils.getStartMessage());
		Webhook result = webhookService.findWebhookById(id);
		log.info(LoggingUtils.getEndMessage());
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/url")
	public ResponseEntity<Webhook> getByUrl(@RequestParam("name") String url) {
		log.info(LoggingUtils.getStartMessage());
		Webhook result = webhookService.findWebhookByUrl(url);
		log.info(LoggingUtils.getEndMessage());
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<Webhook> create(@RequestBody Webhook webhook) {
		log.info(LoggingUtils.getStartMessage());
		Webhook result = webhookService.addWebhook(webhook);
		log.info(LoggingUtils.getEndMessage());
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Webhook> update(@PathVariable("id") String id,@RequestBody Webhook webhook) {
		log.info(LoggingUtils.getStartMessage());
		Webhook result = webhookService.updateWebhook(id, webhook);
		log.info(LoggingUtils.getEndMessage());
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		log.info(LoggingUtils.getStartMessage());
		 webhookService.deleteWebhookUrlById(id);
		log.info(LoggingUtils.getEndMessage());
		return ResponseEntity.ok().build();
	}
	
}
