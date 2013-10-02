package com.threads;

public class ThreadAffinityException extends Exception {
	
	public ThreadAffinityException() {
		
	}
	
	public ThreadAffinityException(String message) throws Exception {
		super(message);
	}
}
