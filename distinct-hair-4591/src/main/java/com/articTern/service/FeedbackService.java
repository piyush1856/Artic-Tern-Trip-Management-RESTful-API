package com.articTern.service;

import com.articTern.exceptions.FeedbackException;
import com.articTern.model.Feedback;

public interface FeedbackService {
	
	public String addFeedback(Feedback feedback,String key) throws FeedbackException;
	
	public Feedback findFeedbackByCustomerId(Integer customerId,String key) throws FeedbackException;

}
