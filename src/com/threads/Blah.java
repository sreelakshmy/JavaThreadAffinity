package com.threads;

public class Blah {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MessagePrinter m1 = new MessagePrinter("asdf", 10);
		MessagePrinter m2 = new MessagePrinter("asdf", 50);

		m1.start();
		m2.start();
	}

}

class MessagePrinter extends Thread {
	  private final String message;
	  private final long interval;
	  public MessagePrinter(String msg, long interval) {
	    this.message = msg;
	    this.interval = interval;
	  }
	  public void run() {
			ThreadAffinity t = new ThreadAffinity();
	    	int a[] = {6};
	    	int b[] = new int[8];
	    	try {
				t.setaffinity(10, a.length, a);
				System.out.println("The current process id is - " + t._getpid());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    	//for (int i = 0; i < b.length; i++) System.out.println(b[i]);
	    	try {
				System.out.println("The CPU id is - " + t.sched_getcpu());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	  }
	}