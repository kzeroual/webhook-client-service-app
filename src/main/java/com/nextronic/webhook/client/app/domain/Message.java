package com.nextronic.webhook.client.app.domain;


import lombok.*;
import java.io.Serializable;
	
@Data	
@AllArgsConstructor	
@NoArgsConstructor
public class Message implements Serializable {
  private String text;
}