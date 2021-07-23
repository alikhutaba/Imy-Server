package com.example.demo.data;

import java.util.List;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ArrayToJsonStringConveter implements AttributeConverter<List<Double>, String> {
	private ObjectMapper jackson;

	public ArrayToJsonStringConveter() {
		this.jackson = new ObjectMapper();
	}

	@Override
	public String convertToDatabaseColumn(List<Double> attribute) {

		
		try {
			return this.jackson
					.writeValueAsString(attribute);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List convertToEntityAttribute(String dbData) {

		try {
			return this.jackson
					.readValue(dbData, List.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
