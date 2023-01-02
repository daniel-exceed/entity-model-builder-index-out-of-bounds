package com.foo;

import java.util.Collection;
import java.util.EnumSet;

import org.bson.codecs.Codec;
import org.bson.codecs.pojo.PropertyCodecRegistry;
import org.bson.codecs.pojo.TypeWithTypeParameters;

import com.google.auto.service.AutoService;

import dev.morphia.mapping.codec.CollectionCodec;
import dev.morphia.mapping.codec.EnumCodec;
import dev.morphia.mapping.codec.MorphiaPropertyCodecProvider;


@AutoService(MorphiaPropertyCodecProvider.class)
public class MyCodecProvider extends MorphiaPropertyCodecProvider{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T> Codec<T> get(TypeWithTypeParameters<T> type, PropertyCodecRegistry registry) {
		if (type.getType().equals(EnumSet.class)) {
			return new EnumSetCodec(type.getType(), registry.get(type.getTypeParameters().get(0)));
		}
		return null;
	}

	public static class EnumSetCodec<T> extends CollectionCodec<T> {

		protected EnumSetCodec(Class<Collection<T>> encoderClass, Codec<T> codec) {
			super(encoderClass, codec);
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		protected Collection<T> getInstance() {
			return EnumSet.noneOf(((EnumCodec) getCodec()).getEncoderClass());
		}
		
	}
}
