package com.articTern.service;

import java.util.List;

import com.articTern.exceptions.FeedbackException;
import com.articTern.model.Feedback;

public interface FeedbackService {
	
	public String addFeedback(Feedback feedback,String key) throws FeedbackException;
	
	public String findFeedbackByCustomerId(Integer customerId,String key) throws FeedbackException;
	
	public String findFeedbackByfeedbackId(Integer feedbackId, String key) throws FeedbackException;
	
	public List<String> viewAllfeedback(String key) throws FeedbackException;

}
