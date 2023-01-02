package com.foo;

import java.util.EnumSet;

import org.bson.types.ObjectId;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity
public class MyEntity {
	@Id
	public ObjectId id;
	
	public EnumSet<TheEnum> enumSet;
	
	public enum TheEnum {
		ONE, TWO
	}
}
