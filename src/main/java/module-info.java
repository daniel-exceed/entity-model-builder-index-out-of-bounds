module morphia.reproducer {
	exports com.foo;

	requires bottlerocket;
	requires com.google.auto.service;
	requires java.semver;
	requires junit;
	requires morphia.core;
	requires org.jetbrains.annotations;
	requires org.mongodb.bson;
	requires org.mongodb.driver.sync.client;
	
	uses dev.morphia.mapping.codec.MorphiaPropertyCodecProvider;
}