package com.opera.survway.corporation.model.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResearchQuestion {
	private int questionNo;
	private int questionFormNo;
	private String rquestionContext;
	private int researchNo;
	private String mediaExist;
	private String questionVideoLink;
	private String mediaExplain;
	private int questionOrder;
	
	private ArrayList<ResearchChoice> requestChoiceList;

	public ResearchQuestion() {}

	public ResearchQuestion(int questionNo, int questionFormNo, String rquestionContext, int researchNo,
			String mediaExist, String questionVideoLink, String mediaExplain, int questionOrder,
			ArrayList<ResearchChoice> requestChoiceList) {
		super();
		this.questionNo = questionNo;
		this.questionFormNo = questionFormNo;
		this.rquestionContext = rquestionContext;
		this.researchNo = researchNo;
		this.mediaExist = mediaExist;
		this.questionVideoLink = questionVideoLink;
		this.mediaExplain = mediaExplain;
		this.questionOrder = questionOrder;
		this.requestChoiceList = requestChoiceList;
	}

	public int getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}

	public int getQuestionFormNo() {
		return questionFormNo;
	}

	public void setQuestionFormNo(int questionFormNo) {
		this.questionFormNo = questionFormNo;
	}

	public String getRquestionContext() {
		return rquestionContext;
	}

	public void setRquestionContext(String rquestionContext) {
		this.rquestionContext = rquestionContext;
	}

	public int getResearchNo() {
		return researchNo;
	}

	public void setResearchNo(int researchNo) {
		this.researchNo = researchNo;
	}

	public String getMediaExist() {
		return mediaExist;
	}

	public void setMediaExist(String mediaExist) {
		this.mediaExist = mediaExist;
	}

	public String getQuestionVideoLink() {
		return questionVideoLink;
	}

	public void setQuestionVideoLink(String questionVideoLink) {
		this.questionVideoLink = questionVideoLink;
	}

	public String getMediaExplain() {
		return mediaExplain;
	}

	public void setMediaExplain(String mediaExplain) {
		this.mediaExplain = mediaExplain;
	}

	public int getQuestionOrder() {
		return questionOrder;
	}

	public void setQuestionOrder(int questionOrder) {
		this.questionOrder = questionOrder;
	}

	public ArrayList<ResearchChoice> getRequestChoiceList() {
		return requestChoiceList;
	}

	public void setRequestChoiceList(ArrayList<ResearchChoice> requestChoiceList) {
		this.requestChoiceList = requestChoiceList;
	}

	@Override
	public String toString() {
		return "ResearchQuestion [questionNo=" + questionNo + ", questionFormNo=" + questionFormNo
				+ ", rquestionContext=" + rquestionContext + ", researchNo=" + researchNo + ", mediaExist=" + mediaExist
				+ ", questionVideoLink=" + questionVideoLink + ", mediaExplain=" + mediaExplain + ", questionOrder="
				+ questionOrder + ", requestChoiceList=" + requestChoiceList + "]\n";
	}

}