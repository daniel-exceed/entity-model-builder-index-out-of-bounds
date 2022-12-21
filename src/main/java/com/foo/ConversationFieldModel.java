package com.foo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import dev.morphia.annotations.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ConversationFieldModel {
	private String id;
	private String fieldName;
	private FieldType type;
	private boolean mandatory;
	private boolean updatingCrm;
	private List<TrackedText> variantOfQuestions;
	private List<TrackedText> quickReplies;
	private List<String> countriesCodes;
	private List<LeadCaptureFieldCondition> conditions;
	private String pixel;
	private String mediaUrl;
	private boolean workEmail;

	public enum FieldType {
		TEXT,
		EMAIL,
		PHONE,
		NUMBER,
		NAME,
		ADDRESS,
		MEETING_SCHEDULER,
		COUNTRY,
		CONFIRMATION,
		MEDIA,
		ANSWERS,
		SUPPORT
	}

	public ConversationFieldModel(String id, String fieldName, FieldType type, List<LeadCaptureFieldCondition> conditions, String customerSupportMessage) {
		this.id = id;
		this.fieldName = fieldName;
		this.type = type;
		this.conditions = conditions;
		this.variantOfQuestions = Arrays.asList(new TrackedText("", customerSupportMessage));
		this.quickReplies = new ArrayList<>();
	}

	@Data
	@Entity
	@AllArgsConstructor
	@NoArgsConstructor
	public static class LeadCaptureFieldCondition {
		private String selectedFieldId;
		private String selectedFieldCondition;
		private List<String> selectedFieldConditionValues = new ArrayList<>();
	}

	@Data
	@Entity
	@AllArgsConstructor
	@NoArgsConstructor
	public static class TrackedText {
		private String id;
		private String text;
	}
}
