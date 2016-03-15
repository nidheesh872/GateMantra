package com.gatemantra.utils;

import java.io.Serializable;

public class Question {

private int id;	
private String question;
private String answer;
private String choice1;
private String choice2;
private String choice3;
private String choice4;
private String explanation;
private String subject;




public Question(int id, String question, String answer, String choice1,
		String choice2, String choice3, String choice4, String explanation,
		String subject) {
	super();
	this.id = id;
	this.question = question;
	this.answer = answer;
	this.choice1 = choice1;
	this.choice2 = choice2;
	this.choice3 = choice3;
	this.choice4 = choice4;
	this.explanation = explanation;
	this.subject = subject;
}
public String getExplanation() {
	return explanation;
}
public void setExplanation(String explanation) {
	this.explanation = explanation;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public Question() {
	super();
}
public Question(String question, String answer) {
	super();
	this.question = question;
	this.answer = answer;
}
public Question(int id, String question, String answer) {
	super();
	this.id = id;
	this.question = question;
	this.answer = answer;
}
public Question(int id, String question, String answer, String choice1,
		String choice2, String choice3, String choice4) {
	super();
	this.id = id;
	this.question = question;
	this.answer = answer;
	this.choice1 = choice1;
	this.choice2 = choice2;
	this.choice3 = choice3;
	this.choice4 = choice4;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getQuestion() {
	return question;
}
public void setQuestion(String question) {
	this.question = question;
}
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}
public String getChoice1() {
	return choice1;
}
public void setChoice1(String choice1) {
	this.choice1 = choice1;
}
public String getChoice2() {
	return choice2;
}
public void setChoice2(String choice2) {
	this.choice2 = choice2;
}
public String getChoice3() {
	return choice3;
}
public void setChoice3(String choice3) {
	this.choice3 = choice3;
}
public String getChoice4() {
	return choice4;
}
public void setChoice4(String choice4) {
	this.choice4 = choice4;
}


}
