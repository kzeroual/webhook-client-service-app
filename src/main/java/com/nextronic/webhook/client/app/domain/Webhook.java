package com.nextronic.webhook.client.app.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="webhooks")
public class Webhook {
   @Id
   private String id;
   private String provider;
   @Indexed
   private String url;
   @CreatedDate
   private Long creationDate;
   @LastModifiedDate
   private Long lastUpdate;
}
