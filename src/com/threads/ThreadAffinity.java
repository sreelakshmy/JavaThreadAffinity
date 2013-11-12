package com.threads;

import com.threads.ThreadAffinityException;
import com.sun.jna.Library;
import com.sun.jna.Native;

public class ThreadAffinity {
	public CTest ctest;

	public ThreadAffinity() {
		System.out.println(System.getProperty("user.dir"));
		ctest = (CTest) Native.loadLibrary("ctest", CTest.class);
	}

    public interface CTest extends Library {
        public void helloFromC(long i);
        public int _setaffinity(int tid, int size, int[] a);
        public int _getaffinity(int tid, int cpusetsize);
        public int _getcpu();
    }

    private void process_retval(int retval) throws Exception {
    	if ( retval == 0 )
        	System.out.println("Operation successful");
    	else if ( retval == 1 )
    		throw new ThreadAffinityException("Supplied memory address was invalid");
    	else if ( retval == 2 )
    		throw new ThreadAffinityException("cpusetsize is smaller than the size of the affinity mask used by the kernel.");
    	else if ( retval == 3 )
    		throw new ThreadAffinityException("The calling process does not have appropriate privileges. The caller needs an effective user ID equal to the real user ID or effective user ID of the process identified by pid, or it must possess the CAP_SYS_NICE capability.");
    	else if ( retval == 4 )
    		throw new ThreadAffinityException("The process whose ID is pid could not be found.");
    }

    public void sched_getcpu() throws Exception {
    	int retval = ctest._getcpu();
    	process_retval(retval);
    }
    
    public void setaffinity(int tid, int size, int[] a) throws Exception {
    	int retval = ctest._setaffinity(tid, size, a);
    	process_retval(retval);
    }

    public void getaffinity(int tid, int size, int[] a) throws Exception {
    	int retval = ctest._getaffinity(tid, size);
    	process_retval(retval);
    }
    
    // #TODO : how to throw only ThreadAffinityException
    static public void main(String argv[]) throws Exception {
    	ThreadAffinity t = new ThreadAffinity();
    	int a[] = {0,1,2};
    	int b[] = new int[8];
    	t.setaffinity(2536, a.length, a);
    	t.getaffinity(2536, b.length, b);
    	for (int i = 0; i < b.length; i++) System.out.println(b[i]);
    	t.sched_getcpu();
    }
}