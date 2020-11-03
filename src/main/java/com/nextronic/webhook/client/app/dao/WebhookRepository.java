package com.nextronic.webhook.client.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nextronic.webhook.client.app.domain.Webhook;

@Repository
public interface WebhookRepository extends MongoRepository<Webhook, String> {
	Webhook findByUrl(String url);
}
