package com.foo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants(innerTypeName = "F")
public class BaseCommunicationEntity<T extends BaseCommunicationMessage> extends BaseEntity {
	protected T message;
	
	@NoArgsConstructor
	public static class F extends BaseEntity.F {
		
	}
}
