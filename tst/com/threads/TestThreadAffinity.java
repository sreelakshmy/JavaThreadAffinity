package com.threads;

import static org.junit.Assert.*;
import com.threads.ThreadAffinity;
import org.junit.Test;

public class TestThreadAffinity {
	@Test
	public void test() throws Exception {
    	ThreadAffinity t = new ThreadAffinity();
    	int cores[] = {2};
    	t.setaffinity_thread(0, cores.length, cores);    	
    	System.out.println("Hello " + t.sched_getcpu());
    	System.out.println("The current process id is " + t._getpid());
	}
}