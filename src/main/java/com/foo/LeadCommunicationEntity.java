package com.foo;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;

import org.bson.types.ObjectId;

import dev.morphia.annotations.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import com.foo.LeadCommunicationEntity.F;
/**
 * Created by amotzhoshen on 01/11/2017.
 */
@FieldNameConstants(innerTypeName = "F")
@Entity(value="LEAD_COMMUNICATION")
@Data
@NoArgsConstructor
@Indexes({@Index(fields = {@Field(F.userId), @Field(F.playbookId)}),
	@Index(fields = {@Field(F.userId)})})
public abstract class LeadCommunicationEntity<T extends BaseCommunicationMessage> extends BaseCommunicationEntity<T>{

	private ObjectId userId;
	private String userImageUrl;
	private ObjectId playbookId;
	private Set<String> missingAnswers;

	public static final Comparator<LeadCommunicationEntity<?>> leadCommComp = Comparator.nullsFirst(new LeadCommunicationComparator());

	public int compareTo(LeadCommunicationEntity<?> o) {
		return leadCommComp.compare(this, o);
	}

	private static final class LeadCommunicationComparator implements Comparator<LeadCommunicationEntity<?>> {
		@Override
		public int compare(LeadCommunicationEntity<?> leadCommunication1, LeadCommunicationEntity<?> leadCommunication2) {
			return 0;
		}
	}
	
	@NoArgsConstructor
	public static class F extends BaseCommunicationEntity.F{
		public static final java.lang.String userId = "userId";
		public static final java.lang.String userImageUrl = "userImageUrl";
		public static final java.lang.String playbookId = "playbookId";
	}
}
