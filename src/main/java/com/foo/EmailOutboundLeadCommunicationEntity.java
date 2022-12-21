package com.foo;

import org.bson.types.ObjectId;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
//@Indexes({
//		@Index(fields = {@Field(senderType), @Field(F.userId), @Field(outboundStatus), @Field(F.playbookId), @Field(F.scheduledTimestamp), @Field(value= F.id, type = DESC)},
//				options = @IndexOptions(name = "getOutCommsByUserAndStatus", background = true, partialFilter = "{\"outboundStatus\" : {\"$exists\" : true}}")),
//
//		@Index(fields = {@Field(senderType), @Field(botId), @Field(F.scheduledTimestamp), @Field(outboundStatus), @Field(F.playbookInfo + "." + UserEntity.PlaybookInfo.F.playbookId), @Field("message.from.address"),
//				@Field(OutboundLeadCommunicationEntity.F.message + "." + EmailCommunicationMessage.F.templateType),
//				@Field(OutboundLeadCommunicationEntity.F.playbookInfo + "." + UserEntity.PlaybookInfo.F.sendIntoImmediately),
//				@Field(F.fuSendHoursZoned), @Field(F.currHolidayEnd), @Field(F.lastEmailSentTimestamp), @Field(F.markedToBeSentImmediately), @Field(F.markedToBeDiscarded),
//				@Field(value = F.sequencePriority, type = DESC), @Field(value = F.priority, type = DESC)},
//				options = @IndexOptions(name = SEND_LEAD_COMMS_INDEX, background = true, partialFilter = "{\"outboundStatus\" : {\"$exists\" : true}}")),
//
//		@Index(fields = {@Field(senderType), @Field(botId), @Field(F.playbookId), @Field(outboundStatus), @Field(F.senderPlatform), @Field("message.sentTimeStamp"), @Field(F.userId)},
//				options = @IndexOptions(sparse = true, background = true, name = "outboundStatus_playbookId")),
//
//		@Index(fields = {@Field(senderType), @Field(outboundStatus), @Field(F.userId), @Field(value = F.id, type = DESC)}, options = @IndexOptions(sparse = true, background = true, name = "latestSentIndex")),
////		@Index(fields = {@Field(senderType), @Field("message.messageId"), @Field("message.mimeMessageId"), @Field(userRepliedWithOptOut), @Field(repliedTime), @Field(outboundStatus)},
////				options = @IndexOptions(name = EmailOutboundLeadCommunicationEntity.LEADCOMMS_BY_MESSAGE_ID_OUTBOUND, background = true, partialFilter = "{\"outboundStatus\" : {\"$exists\" : true}}")),
////
////		@Index(fields = {@Field(senderType), @Field("message.messageId"), @Field("message.mimeMessageId")},
////				options = @IndexOptions(name = EmailOutboundLeadCommunicationEntity.LEADCOMMS_BY_MESSAGE_ID_INBOUND, background = true, partialFilter = "{\"inboundStatus\" : {\"$exists\" : true}}")),
////
////		@Index(fields = {@Field(senderType), @Field("message.messageId")},
////				options = @IndexOptions(name = EmailOutboundLeadCommunicationEntity.LEADCOMMS_BY_MESSAGE_ID_OUTBOUND_WEBHOOK, background = true, partialFilter = "{\"outboundStatus\" : {\"$exists\" : true}}")),
//
//		@Index(fields = {@Field(senderType), @Field("message.messageId")}, options = @IndexOptions(background = true)),
//		@Index(fields = {@Field(senderType), @Field("message.mimeMessageId")}, options = @IndexOptions(background = true)),
//})
public class EmailOutboundLeadCommunicationEntity extends OutboundLeadCommunicationEntity<EmailCommunicationMessage> {
	public static final String SEND_LEAD_COMMS_INDEX = "sendLeadCommunicationMessagesIndex";
//	public static final String LEADCOMMS_BY_MESSAGE_ID_INBOUND = "leadcommByMessageIdInbound";
//	public static final String LEADCOMMS_BY_MESSAGE_ID_OUTBOUND = "leadcommByMessageIdOutbound";
//	public static final String LEADCOMMS_BY_MESSAGE_ID_OUTBOUND_WEBHOOK = "leadcommByMessageIdOutboundWebhook";

	private int priority;
	private boolean sequencePriority;
	private ObjectId virtualAssistantId;

	public EmailOutboundLeadCommunicationEntity() {
		super();
	}

	// sequence priority comes first. delay others.
	private int getFinalPriority() {
		if (!this.sequencePriority) {
			return (this.priority + 5);
		}

		return this.priority;
	}

	@Override
	public int compareTo(BaseEntity entity) {
		if (entity instanceof EmailOutboundLeadCommunicationEntity) {
			return Integer.compare(this.getFinalPriority(), ((EmailOutboundLeadCommunicationEntity) entity).getFinalPriority());
		}

		return super.compareTo(entity);
	}

	public static class F extends OutboundLeadCommunicationEntity.F {
		public static final java.lang.String priority = "priority";
		public static final java.lang.String sequencePriority = "sequencePriority";
	}
}
