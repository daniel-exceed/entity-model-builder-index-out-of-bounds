package com.foo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

import org.bson.types.ObjectId;

import dev.morphia.annotations.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

/**
 * Created by amotzhoshen on 01/11/2017.
 */
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OutboundLeadCommunicationEntity<T extends BaseCommunicationMessage> extends LeadCommunicationEntity<T> {

	private long scheduledTimestamp;
	private T originalMessage;
	private Long openedTime;
	private Long lastOpenedTime;
	private Long repliedTime;
	private Long meetingScheduledTime;
	private int openedCount;
	@Getter
	private OutboundStatus outboundStatus;
	private OutboundStatus statusBeforeError;
	private ObjectId originatedFromInboundId;
	// keeps history of actions on the message
	private List<AdminData> adminDataList;
	private List<String> requestedFieldsIds;
	private Double leadScore;
	private Boolean userRepliedWithOptOut;
	private Long optedOutTime;
	private Integer followupHoursOfInActivity = null;
	private int outboundStatusOrder;
	private LinkInfo clickInfo;
	private List<LinkInfo> clickHistory = new ArrayList<>();
	private List<SuggestedSlotInfo> meetingSchedulerClicksHistory = new ArrayList<>();
	private boolean alreadyRendered = false;
	private Boolean userDroppedOffChat;
	private Long dropOffTime;

	// email and sms schedule props
	private List<Integer> fuSendHoursZoned;
	private Long currHolidayEnd;
	private String timezoneName;
	private Long lastEmailSentTimestamp;

	// email and sms triggers
	private String triggeredInboundText;
	private String triggeredInboundTextShorten;

	@Deprecated
	protected List<String> errors;

	private boolean markedToBeSentImmediately = false;
	private boolean markedToBeDiscarded = false;

	@Override
	public int compareTo(BaseEntity entity) {
		if (entity instanceof OutboundLeadCommunicationEntity<?>) {
			return Long.compare(this.scheduledTimestamp, ((OutboundLeadCommunicationEntity<?>) entity).scheduledTimestamp);
		}

		return super.compareTo(entity);
	}

	public void incOpenedCount() {
		this.openedCount++;
	}

	public void setOutboundStatus(OutboundStatus status) {
		if (status == OutboundStatus.ERROR && this.outboundStatus != OutboundStatus.ERROR) {
			this.statusBeforeError = this.outboundStatus;
		}
		
		outboundStatus = status;
		outboundStatusOrder = status.ordinal();
	}

	public void setOptedOutTime(Long optedOutTime) {
		this.userRepliedWithOptOut = (Objects.isNull(optedOutTime) ? null : true);
		this.optedOutTime = optedOutTime;
	}

	public void setDropOffTime(Long dropOffTime) {
		this.userDroppedOffChat = (Objects.isNull(dropOffTime) ? null : true);
		this.dropOffTime = dropOffTime;
	}

	public TimeZone getTimezone() {
		return TimeZone.getTimeZone(timezoneName);
	}

	@Data
	@Entity
	@NoArgsConstructor
	public static class AdminData {
		private String repName;
		private Long operationTimestamp;
		private ObjectId repId;
		private OutboundStatus status;
	}

	public enum OutboundStatus {
		WAITING_FOR_LANDLORD_APPROVAL,
		WAITING_FOR_APPROVAL,
		APPROVED,
		APPROVED_NO_OP,
		@Deprecated
		QUEUED,
		SENT,
		REJECTED,    // for rep
		DISCARDED,    // for system
		ERROR
	}

	@Data
	@Entity
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PauseStatus {
		private OutboundStatus status;
		private long timestamp;
	}

	@Data
	@Entity
	@NoArgsConstructor
	@AllArgsConstructor
	@FieldNameConstants
	public static class LinkInfo {
		private String url;
		private long timestamp;
	}

	@Data
	@Entity
	@NoArgsConstructor
	@AllArgsConstructor
	@FieldNameConstants
	public static class SuggestedSlotInfo {
		private Long slotStartTime;
		private long timestamp;
	}

	@NoArgsConstructor
	public static class F extends LeadCommunicationEntity.F {
		public static final java.lang.String scheduledTimestamp = "scheduledTimestamp";
		public static final java.lang.String originalMessage = "originalMessage";
		public static final java.lang.String openedTime = "openedTime";
		public static final java.lang.String repliedTime = "repliedTime";
		public static final java.lang.String meetingScheduledTime = "meetingScheduledTime";
		public static final java.lang.String openedCount = "openedCount";
		public static final java.lang.String outboundStatus = "outboundStatus";
		public static final java.lang.String originatedFromInboundId = "originatedFromInboundId";
		public static final java.lang.String adminDataList = "adminDataList";
		public static final java.lang.String requestedFieldsIds = "requestedFieldsIds";
		public static final java.lang.String leadStatusInfo = "leadStatusInfo";
		public static final java.lang.String leadScore = "leadScore";
		public static final java.lang.String playbookInfo = "playbookInfo";
		public static final java.lang.String userRepliedWithOptOut = "userRepliedWithOptOut";
		public static final java.lang.String followupHoursOfInActivity = "followupHoursOfInActivity";
		public static final java.lang.String outboundStatusOrder = "outboundStatusOrder";
		public static final java.lang.String clickInfo = "clickInfo";
		public static final java.lang.String clickHistory = "clickHistory";
		public static final java.lang.String meetingSchedulerClicksHistory = "meetingSchedulerClicksHistory";
		public static final java.lang.String fuSendHoursZoned = "fuSendHoursZoned";
		public static final java.lang.String currHolidayEnd = "currHolidayEnd";
		public static final java.lang.String timezoneName = "timezoneName";
		public static final java.lang.String lastEmailSentTimestamp = "lastEmailSentTimestamp";
		public static final java.lang.String markedToBeDiscarded = "markedToBeDiscarded";
	}
}
