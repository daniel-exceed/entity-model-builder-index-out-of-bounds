package com.foo;

import java.util.EnumMap;

import org.bson.types.ObjectId;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity @EqualsAndHashCode (callSuper=true)
public abstract class BaseModule<T extends BaseGoalData> extends GoalTreeNode<T> {
	@Transient 
	private NodeMap nodeMap;
	protected EnumMap<MessageErrorCode, ChatComponent[][]> errorMessageList;
	
	public BaseModule() {
		createNodeMap();
	}
	
	public void createNodeMap() {
		this.nodeMap = new NodeMap(this);
	}
	
	public void markNodes() {
		super.setModuleId(super.getId());
		
		if (nodeMap != null) {
			for (NodeMap.NodeRoutes node : nodeMap.allNodeRoutes()) {
				node.getNode().setModuleId(super.getId());
			}
		}
	}
	
	public abstract void createStructure();
	
	public abstract void injectData(ObjectId botId, ObjectId playbookId, NodeMap convNodeMap);
	
	public void addErrorMessages(MessageErrorCode code, ChatComponent... messages) {
		if (errorMessageList == null) {
			errorMessageList = new EnumMap<>(MessageErrorCode.class);
		}
		
		errorMessageList.put(code, new ChatComponent[][] {messages});
	}
	
	public void postStructure(ConversationFieldModel field, ObjectId playbookId) {
		
	}
}
