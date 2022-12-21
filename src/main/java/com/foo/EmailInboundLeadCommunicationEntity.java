package com.foo;

import lombok.Data;

@Data
//@Indexes({@Index(fields = {@Field(F.receivedDate), @Field("message.bouncedDetails")}, options = @IndexOptions(name = "receivedDate_bouncedDetails", sparse = true, background = true)),
//	@Index(fields = @Field(value = "message.sentTimeStamp", type = IndexType.DESC), options = @IndexOptions(sparse = true, background = true))})
public class EmailInboundLeadCommunicationEntity extends InboundLeadCommunicationEntity<EmailCommunicationMessage> {
	private String mailgunEventId;
	
	public EmailInboundLeadCommunicationEntity() {
		super();
//		setSenderPlatform(SenderPlatform.EMAIL);
	}
}
