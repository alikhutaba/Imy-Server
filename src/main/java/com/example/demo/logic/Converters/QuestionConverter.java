package com.example.demo.logic.Converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.data.QuestionEntity;
import com.example.demo.rest.boundaries.QuestionBoundary;

@Component
public class QuestionConverter {




	@Autowired
	public QuestionConverter() {
		super();
	}




	public QuestionBoundary fromEntity(QuestionEntity questionEntity) {

		try {

			return new QuestionBoundary(
					questionEntity.getAnswer(),
					questionEntity.getNotes()
					);



		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert Question from Entity to Boundary: "+ e.getMessage());
		}

	}




	public QuestionEntity toEntity(QuestionBoundary questionBoundary) {



		try {

			return new QuestionEntity(
					questionBoundary.getAnswer(),
					questionBoundary.getNotes()
					);


		} catch (Exception e) {
			throw new UnvalidConverterException("Coulde not convert Question from Boundary to Entity: "+ e.getMessage());
		}	



	}


}
