package com.example.demo.data;



public class QuestionEntity {



	private String answer;
	private String notes;






	public QuestionEntity() {}



	public QuestionEntity(String answer, String notes) {
		super();
		this.answer = answer;
		this.notes = notes;
	}



	public String getAnswer() {
		return answer;
	}



	public void setAnswer(String answer) {
		this.answer = answer;
	}



	public String getNotes() {
		return notes;
	}



	public void setNotes(String notes) {
		this.notes = notes;
	}



	@Override
	public String toString() {
		return "QuestionEntity [answer=" + answer + ", notes=" + notes + "]";
	}


	
	
	

}
