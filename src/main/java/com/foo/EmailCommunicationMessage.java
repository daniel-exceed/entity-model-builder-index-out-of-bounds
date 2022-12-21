package com.foo;

import java.util.List;
import java.util.Map;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

/**
 * Created by amotzhoshen on 18/10/2017.
 */
@Data
@Entity
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
@ToString
public class EmailCommunicationMessage extends BaseTemplateCommunicationMessage {

//	protected InternetAddress from;
//	protected List<InternetAddress> to;
//	protected List<InternetAddress> cc;
//	protected List<InternetAddress> bcc;

    private String fullMessageHtml;
    private String fullMessagePlain;
    private String subject;
//    private List<InternetAddress> replyTo;
//    @SkipExtraction
    private String threadTopMessageHtml;
//    @SkipExtraction
    private String threadTailMessageHtml;
    private String threadId;
//    private BouncedEmailDetails bouncedDetails;
    private List<String> inReplyTo;
    private List<String> references;
    private String mimeMessageId;
//    @SkipExtraction
    private String delimiterLine;
    @Transient
    private Map<String, String> attachedImagesCidToLink;
//    @SkipExtraction
    private String linkUnsubscribe;
//    private EmailSendingOption sendingOption;
//    @SkipExtraction
    private String callToActionText;
//    @SkipExtraction
    private String callToActionLink;
    private String referral;

    public EmailCommunicationMessage() {
        super();
//        setFrom(new InternetAddress());
    }
    
//    @JsonIgnore
//	public List<InternetAddress> getRecepients() {
//		List<InternetAddress> list = new ArrayList<>(to);
//
//		if (cc != null) {
//			list.addAll(cc);
//		}
//
//		if (bcc != null) {
//			list.addAll(bcc);
//		}
//
//		return list;
//	}

    public static class F extends BaseTemplateCommunicationMessage.F {
        public static final java.lang.String fullMessageHtml = "fullMessageHtml";
        public static final java.lang.String fullMessagePlain = "fullMessagePlain";
        public static final java.lang.String subject = "subject";
        public static final java.lang.String replyTo = "replyTo";
        public static final java.lang.String threadTopMessageHtml = "threadTopMessageHtml";
        public static final java.lang.String threadTailMessageHtml = "threadTailMessageHtml";
        public static final java.lang.String threadId = "threadId";
        public static final java.lang.String bouncedDetails = "bouncedDetails";
        public static final java.lang.String inReplyTo = "inReplyTo";
        public static final java.lang.String references = "references";
        public static final java.lang.String mimeMessageId = "mimeMessageId";
        public static final java.lang.String delimiterLine = "delimiterLine";
    }
}
