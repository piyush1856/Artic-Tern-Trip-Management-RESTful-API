package com.articTern.service;

import java.util.List;

import com.articTern.dtoes.FeedBackDTO;
import com.articTern.exceptions.FeedbackException;
import com.articTern.model.Feedback;

public interface FeedbackService {
	
	public String addFeedback(Feedback feedback,String key) throws FeedbackException;
	
	public FeedBackDTO findFeedbackByCustomerId(Integer customerId,String key) throws FeedbackException;
	
	public FeedBackDTO findFeedbackByfeedbackId(Integer feedbackId, String key) throws FeedbackException;
	
	public List<FeedBackDTO> viewAllfeedback(String key) throws FeedbackException;

}
