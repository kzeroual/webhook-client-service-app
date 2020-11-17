package com.nextronic.webhook.client.app.brocker.config;



import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory; 
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;


@Configuration
public class MessagingBrockerConfig {
	    
	public static final String EXCHANGE_NAME = "actionneur-exchange";
    
    public static final String DEAD_LETTER_EXCHANGE_NAME = "actionneur-dead-letter-exchange";
    
    public static final String WEBHOOK_QUEUE_NAME = "webhookQueue";
    
    public static final String DEAD_LETTER_WEBHOOK_QUEUE_NAME = "webhookDLQueue";
	    
	    @Bean
	    public ConnectionFactory connectionFactory() {
	        return new CachingConnectionFactory("localhost");
	    }

	    @Bean
	    public RabbitTemplate rabbitTemplate() {
	    	final RabbitTemplate  rabbitTemplate = new RabbitTemplate(connectionFactory());
	    	 rabbitTemplate.setMessageConverter(converter());
	        return rabbitTemplate;
	    }

	    @Bean
	    public MessageConverter converter() {
	        return new Jackson2JsonMessageConverter();
	    }
	    
	    @Bean
	    Queue emailsQueue() {
	        return QueueBuilder.nonDurable(WEBHOOK_QUEUE_NAME)
	                .withArgument("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE_NAME)
	                .withArgument("x-dead-letter-routing-key", DEAD_LETTER_WEBHOOK_QUEUE_NAME)
	                .build();
	    }
	  
}
