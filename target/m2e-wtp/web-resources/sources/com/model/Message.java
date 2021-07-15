package com.model;

public class Message {
	
	private String approveMessage = "We were super excited to hear that your adoption has been approved and finalized. "
			+ "We’re looking forward to meeting the new member of your family soon. We’re wishing you the best with your new child. ";
	private String approveMessage1 = " Coming date for getting child is: ";
	private String applyingMessage = "Thank you for Applying adoption it's a  pleasure to have you! thank you for waiting.";
	private String nextStepMessage= "Your request is well checked and you can procced to another step. other step is for choosing child depend for your"
			+ "criteria and we are thank you for helping the rwandan children. keep on that. we are waiting for request.";
	
	
	
	public String getNextStepMessage() {
		return nextStepMessage;
	}
	public void setNextStepMessage(String nextStepMessage) {
		this.nextStepMessage = nextStepMessage;
	}
	public String getApproveMessage() {
		return approveMessage;
	}
	public void setApproveMessage(String approveMessage) {
		this.approveMessage = approveMessage;
	}
	
	public String getApproveMessage1() {
		return approveMessage1;
	}
	public void setApproveMessage1(String approveMessage1) {
		this.approveMessage1 = approveMessage1;
	}
	public String getApplyingMessage() {
		return applyingMessage;
	}
	public void setApplyingMessage(String applyingMessage) {
		this.applyingMessage = applyingMessage;
	}
	
}
