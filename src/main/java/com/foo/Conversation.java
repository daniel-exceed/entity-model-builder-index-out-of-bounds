package com.foo;

import dev.morphia.annotations.Entity;
import lombok.Data;

@Data
@Entity("CONVERSATION")
public class Conversation extends BaseEntity{

	private NodeMap nodeMap;
}
