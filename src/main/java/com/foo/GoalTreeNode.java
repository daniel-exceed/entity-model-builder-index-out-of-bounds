package com.foo;

//import com.exceed.entities.user.LeadAttribute;
//import com.exceed.entities.user.LeadStatus;
//import com.exceed.models.ConversationFieldModel;
//import com.exceed.services.constants.TemplateNameConstants;
//import com.exceed.util.DateUtils;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonTypeInfo;

import dev.morphia.annotations.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amotzhoshen on 11/01/2017.
 */
@Data
@Entity
public class GoalTreeNode<T extends BaseGoalData> {
	protected String id;
	private BaseGoalData data;
	private String name;
	private ConversationFieldModel.FieldType fieldType;
//	private NodeActivation nodeActivation = new NodeActivation(); // Temporary for user-conversations
//	private List<NodeActivation> nodeActivations = new ArrayList<>(); // determines where this node is active
	private BaseGoalData dashboardData; // goal data used for dashboard goals
	private String saveGoalDataToUserAs;
	private boolean endNode = false; // this node ends the conversation once it's met
	private boolean nonBlocking;
	private String moduleId;
//	private LeadAttribute saveLeadDataAs;
	private boolean updatingCrm = false;
	private boolean mandatory;
	private boolean limitedRetries;
	private Integer maxRetries;
	private Integer retryCount = 0;
	private boolean skipped;
	private String displayName;
	protected String switchedId;
//	private LeadStatus leadStatus;

	public GoalTreeNode() {
//		id = DateUtils.newObjectId().toString();
	}

	public GoalTreeNode(String name) {
		this();
		this.name = name;
	}

	public GoalTreeNode(String id, String name, ConversationFieldModel.FieldType fieldType) {
		this.id = id;
		this.name = name;
		this.setFieldType(fieldType);
	}

	public GoalTreeNode(T data, String name) {
		this(name);
		this.data = data;
	}

	public GoalTreeNode(T data, String name, boolean saveDataToUserAsNodeName) {
		this(data, name);
		if (saveDataToUserAsNodeName) {
			this.saveGoalDataToUserAs = name;
		}
	}

	public GoalTreeNode(T data, String name, String saveDataToUserAs) {
		this(data, name);
		this.saveGoalDataToUserAs = saveDataToUserAs;
	}


	@SuppressWarnings("unchecked")
	public T getData() {
		return (T) this.data;
	}

}
