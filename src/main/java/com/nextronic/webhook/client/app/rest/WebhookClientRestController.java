package com.nextronic.webhook.client.app.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nextronic.webhook.client.app.domain.Message;
import com.nextronic.webhook.client.app.service.WebhookClientService;
import com.nextronic.webhook.client.app.util.LoggingUtils;

import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@AllArgsConstructor
@RestController
@RequestMapping("/wh")
@CrossOrigin("*")
@CommonsLog
public class WebhookClientRestController {
     private WebhookClientService webHookClientService;
     
     @PostMapping("/send")
     public ResponseEntity<String> send(@RequestParam("url") String url,@RequestBody Message message) throws JsonProcessingException {
    	log.info(LoggingUtils.getStartMessage());
    	String result = webHookClientService.sendMessage(url,message);
    	log.info(LoggingUtils.getEndMessage());
		return ResponseEntity.ok(result);
     }
}
