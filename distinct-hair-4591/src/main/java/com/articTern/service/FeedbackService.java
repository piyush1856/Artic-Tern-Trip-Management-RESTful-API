package com.articTern.service;

import com.articTern.exceptions.FeedbackException;
import com.articTern.model.Feedback;

public interface FeedbackService {
	
	public Feedback addFeedback(Feedback feedback,String key) throws FeedbackException;

}
