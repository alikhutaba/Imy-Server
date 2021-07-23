
package com.example.demo.data;

import java.util.List;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


public class ListToJsonLocationConveter implements AttributeConverter<List<InjectionLocation>, String> {
	private ObjectMapper jackson;

	public ListToJsonLocationConveter() {
		this.jackson = new ObjectMapper();
	}

	@Override
	public String convertToDatabaseColumn(List<InjectionLocation> attribute) {

		
		try {
			return this.jackson
					.writeValueAsString(attribute);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<InjectionLocation> convertToEntityAttribute(String dbData) {

		try {
			return this.jackson
//					.readValue(dbData, InjectionLocation[].class);
					.readValue(dbData, new TypeReference<List<InjectionLocation>>() {});

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
