package com.example.demo.data;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class QuestionsToJsonStringConveter implements AttributeConverter<QuestionEntity, String>{


	private ObjectMapper jackson;

	public QuestionsToJsonStringConveter() {
		this.jackson = new ObjectMapper();
	}


	@Override
	public String convertToDatabaseColumn(QuestionEntity attribute) {
		try {
			return this.jackson
					.writeValueAsString(attribute);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public QuestionEntity convertToEntityAttribute(String dbData) {
		try {
			return this.jackson
					.readValue(dbData, QuestionEntity.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
