package com.threads;

import static org.junit.Assert.*;
import com.threads.ThreadAffinity;
import org.junit.Test;

public class TestThreadAffinity {
	@Test
	public void test() throws Exception {
    	ThreadAffinity t = new ThreadAffinity();
    	t.setaffinity(2536, 11);
    	t.getaffinity(2536, 11);
	}
}
