package com.foo;

import dev.morphia.annotations.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class BaseCommunicationMessage {
	private String text = "";
	
	@NoArgsConstructor
	public static class F {
		public static final java.lang.String text = "text";
		public static final java.lang.String attachments = "attachments";
		public static final java.lang.String sentTimeStamp = "sentTimeStamp";
	}
}
