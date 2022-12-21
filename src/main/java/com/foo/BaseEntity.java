package com.foo;

import org.bson.types.ObjectId;

import dev.morphia.annotations.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants(innerTypeName = "F")
public class BaseEntity  implements Comparable<BaseEntity>{
	
	@Id
	protected ObjectId id;

	@Override
	public int compareTo(BaseEntity o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@NoArgsConstructor
	public static class F {
		public static final String id = "_id";
		public static final String className = "className";
	}
}
