package com.foo;

import java.util.List;

import com.foo.InboundLeadCommunicationEntity.F;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Field;
import dev.morphia.annotations.Index;
import dev.morphia.annotations.IndexOptions;
import dev.morphia.annotations.Indexes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

/**
 * Created by amotzhoshen on 01/11/2017.
 */
@FieldNameConstants
//@TrackedForETL(value = "INBOUND_LEAD_COMMUNICATION", clusteringFields = {F.botId, F.playbookId}, hasLogicalDelete = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Indexes(@Index(fields = @Field(F.inboundStatus), options = @IndexOptions(sparse = true, background = true)))
public class InboundLeadCommunicationEntity<T extends BaseCommunicationMessage> extends LeadCommunicationEntity<T> {

//	private InboundStatus inboundStatus;
	private Double sentiment;
//	private NlpLog nlpLog = new NlpLog();
	private long receivedDate;
	private boolean postQualification = false;
	private NattyResponse nattyResponse;
	private boolean aiActivated = false;

	public void setPostQualification(boolean postQualification) {
		this.postQualification = postQualification;

		if (message instanceof BaseTemplateCommunicationMessage) {
			((BaseTemplateCommunicationMessage) message).setPostQualification(postQualification);
		}
	}

	public boolean isAiActivated() {
		if (message instanceof BaseTemplateCommunicationMessage) {
			return  ((BaseTemplateCommunicationMessage) message).isAiActivated();
		}

		return this.aiActivated;
	}

	@NoArgsConstructor
	public static class F extends LeadCommunicationEntity.F {
		public static final java.lang.String inboundStatus = "inboundStatus";
		public static final java.lang.String sentiment = "sentiment";
		public static final java.lang.String nlpLog = "nlpLog";
		public static final java.lang.String receivedDate = "receivedDate";
	}

	@Data
	@Entity
	@NoArgsConstructor
	@AllArgsConstructor
	public static class NattyResponse {
		private String timezoneName;
		private List<Long> timestamps;
	}
}
