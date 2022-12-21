package com.foo;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@Entity
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
public class BaseTemplateCommunicationMessage extends BaseCommunicationMessage {
    private String messageId;
    private ObjectId originatedFromTemplateId;
    private ObjectId originatedFromTemplateGroupId; // For FUs only
    private String templateName;
    private List<Exception> exceptions = new ArrayList<>();
    private EditDetails editDetails;
    private EditDetails approveDetails;
    private List<TemplateEdit> repTemplateEdits;
    private boolean postQualification = false;
    private boolean aiActivated = false;
    @Transient
    ObjectId pregeneratedCommId;

    public BaseTemplateCommunicationMessage() {
        super();
    }

    public BaseTemplateCommunicationMessage(String messageId) {
        this();
        this.messageId = messageId;
    }

    @Data
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EditDetails {
        private ObjectId adminId;
        private long timestamp;
    }

    @Data
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TemplateEdit extends EditDetails {
        String value;
        String originalValue;

    }

    @NoArgsConstructor
    public static class F extends BaseCommunicationMessage.F {
        public static final java.lang.String messageId = "messageId";
        public static final java.lang.String originatedFromTemplateId = "originatedFromTemplateId";
        public static final java.lang.String originatedFromTemplateGroupId = "originatedFromTemplateGroupId";
        public static final java.lang.String templateName = "templateName";
        public static final java.lang.String templateType = "templateType";
        public static final java.lang.String provider = "provider";
        public static final java.lang.String messageComponents = "messageComponents";
        public static final java.lang.String editDetails = "editDetails";
        public static final java.lang.String repTemplateEdits = "repTemplateEdits";
    }
}
