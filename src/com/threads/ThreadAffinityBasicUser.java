package com.threads;

public class ThreadAffinityBasicUser implements Runnable{
        int[] processors;
        
        public ThreadAffinityBasicUser(int[] processors) {
                this.processors = processors;
        }
    public void run() {
                ThreadAffinity t = new ThreadAffinity();
                try {
                                t.setaffinity_process(0, this.processors.length, this.processors);
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
            Thread t1 = new Thread(new ThreadAffinityBasicUser(p1), "My Thread");
            Thread t2 = new Thread(new ThreadAffinityBasicUser(p2), "My Thread");

            /*
* To start a particular thread, use
* void start() method of Thread class.
*
* Please note that, after creation of a thread it will not start
* running until we call start method.
*/
           
            t1.start();      t1.join();
            t2.start();
      
            t2.join();
              
    }
}