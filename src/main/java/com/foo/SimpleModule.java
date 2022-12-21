package com.foo;

import java.util.Arrays;
import java.util.List;

//import org.apache.commons.collections4.CollectionUtils;
import org.bson.types.ObjectId;

//import com.exceed.entities.chat.ChatComponent;
//import com.exceed.entities.conversation.goal.BaseGoalData;

import dev.morphia.annotations.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public abstract class SimpleModule<T extends BaseGoalData> extends BaseModule<T> {
	private List<List<ChatComponent>> messages;
	
	@Override
	public void injectData(ObjectId botId, ObjectId playbookId, NodeMap convNodeMap) {
//		if (CollectionUtils.isNotEmpty(messages)) {
//			this.getData().setChatComponents(messages);
//		}
	}
	
	public void setFlatMessages(ChatComponent... messages) {
		this.messages = Arrays.asList(Arrays.asList(messages));
	}
	
	public void addMessagesToVariations(ChatComponent... messages) {
		if (messages == null) {
			setFlatMessages(messages);
		} else {
			for (List<ChatComponent> variation : this.messages) {
				variation.addAll(Arrays.asList(messages));
			}
		}
	}
}