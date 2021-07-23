package com.example.demo.rest.boundaries;

public class QuestionBoundary {
	
	
	
	private String answer;
	private String notes;
	
	
	public QuestionBoundary() {}
	
	
	
	
	
	public QuestionBoundary(String answer, String notes) {
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
		return "QuestionBoundary [answer=" + answer + ", notes=" + notes + "]";
	}
	
	
	
	

}
