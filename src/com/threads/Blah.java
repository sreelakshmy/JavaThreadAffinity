package com.threads;

public class Blah implements Runnable{
	int[] processors;
	
	public Blah(int[] processors) {
		this.processors = processors;
	}
    public void run() {
            System.out.println("[+] Getting the current thread id");
        	Thread currThread = Thread.currentThread();
        	int threadId = (int) currThread.getId();
        	System.out.println("[+] The current thread id is " + threadId); 
        	
        	System.out.println("[+] Setting up an instance of ThreadAffinity");
        	ThreadAffinity t = new ThreadAffinity();
        	try {
            	System.out.println("[+] Actual threadid could be " + t._gettid());
				t.setaffinity(t._gettid(), this.processors.length, this.processors);
	        	System.out.println("[+] The current CPU is " + t.sched_getcpu());
			} catch (Exception e) {
				e.printStackTrace();
			}
    }
   
    public static void main(String[] args) throws InterruptedException {
           
            /*
             * To create new thread, use
             * Thread(Runnable thread, String threadName)
             * constructor.
             *
             */
    		int[] p1 = {0, 1};
    		int[] p2 = {2, 3};
            Thread t1 = new Thread(new Blah(p1), "My Thread");
            Thread t2 = new Thread(new Blah(p2), "My Thread");

            /*
             * To start a particular thread, use
             * void start() method of Thread class.
             *
             * Please note that, after creation of a thread it will not start
             * running until we call start method.
             */
           
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        	Thread currThread = Thread.currentThread();
        	int threadId = (int) currThread.getId();
        	System.out.println("[+] The main thread id is " + threadId);     
    }
}