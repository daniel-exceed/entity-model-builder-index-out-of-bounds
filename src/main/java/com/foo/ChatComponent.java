package com.foo;

import dev.morphia.annotations.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public abstract class ChatComponent {
	@NonNull
	private MessagingTypes messageType;
	// Optional node info for component node origin in WebchatCommunicationMessage
	private String id;
	private String nodeId;
	private String nodeName;
	private ConversationFieldModel.FieldType nodeType;
}
