package com.foo;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

//import com.exceed.components.goal.validators.CriteriaOperator;
//import com.exceed.components.goal.validators.ValidatorType;
//import com.exceed.entities.chat.ChatComponent;
//import com.exceed.entities.user.UserEntity;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonTypeInfo;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

/**
 * Created by amotzhoshen on 12/01/2017.
 */
@NoArgsConstructor
@Data
@Entity
public abstract class BaseGoalData {
//	private ValidatorType validatorType;
	private String rawStringData;
//	private UserInputSource rawStringSource;
//	private CriteriaOperator criteriaOperator;
	private String criterionVal;
	private List<List<ChatComponent>> chatComponents;
	private EnumMap<MessageErrorCode, ChatComponent[][]> errorMessageList;
//	private EnumMap<PrefaceMessageCode, ChatComponent[][]> prefaceMessageList;
	private MessageErrorCode activeError;
//	private List<ChatHistory> inputHistory;
	private List<String> equivalentActions;
	private List<String> equivalentEntities;
	private List<List<ChatComponent>> feedbackChatComponents; // used to feedback the user after he has finished this node
	private boolean locker = false;
	private List<List<ChatComponent>> lockingChatComponents; // how to tell the user that we are initiating a new conversation
	@Transient
	private Object cachedConcreteValue;
	@Transient
//	private UserEntity user;
	private Boolean preProcess;
	private String inputId;

	protected boolean valid;
	// Nasty hack for nasty java
	@Transient
	private String clazz = getClass().getName();

	public void setFlatChatComponents(ChatComponent... components) {
		chatComponents = Arrays.asList(Arrays.asList(components));
	}

	public void setFlatFeedbackChatComponents(ChatComponent... components) {
		feedbackChatComponents = Arrays.asList(Arrays.asList(components));
	}

	public void addErrorMessages(MessageErrorCode code, ChatComponent... messages) {
		if (errorMessageList == null) {
			errorMessageList = new EnumMap<>(MessageErrorCode.class);
		}

		errorMessageList.put(code, new ChatComponent[][] { messages });
	}


	public MessageErrorCode getActiveError() {
		if (activeError == null) {
			return MessageErrorCode.VALIDATION_ERROR;
		}

		return activeError;
	}

	public void resetData(BaseGoalData preDef) {
		this.rawStringData = preDef.getRawStringData();
		this.activeError = preDef.getActiveError();
		this.chatComponents = preDef.getChatComponents();
		this.valid = false;
	}

	public void setRegexs(String... regexes) {
		this.criterionVal = String.join(";;", regexes);
	}

	public String[] getRegexs() {
		if (StringUtils.isEmpty(criterionVal)) {
			return new String[]{};
		}

		return criterionVal.split(";;");
	}

	public void setErrorMessagesList(Map<MessageErrorCode, ChatComponent> map) {
		errorMessageList = new EnumMap<>(MessageErrorCode.class);

		for (val key : map.keySet()) {
			errorMessageList.put(key, new ChatComponent[][] {{map.get(key)}});
		}
	}
}
