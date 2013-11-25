package com.threads;

import com.threads.ThreadAffinityException;
import com.sun.jna.Library;
import com.sun.jna.Native;

// ThreadAffinity class for managing thread priority
public class ThreadAffinity {
	public CTest ctest;

	// ThreadAffinity constructor
	public ThreadAffinity() {
		ctest = (CTest) Native.loadLibrary("ctest", CTest.class);
	}

	// Interface for CTest
    public interface CTest extends Library {
        public void helloFromC(long i);
        public int _setaffinity(int size, int[] a);
        public int _setaffinity_process(int pid, int size, int[] a);
        public int _getaffinity(int tid, int cpusetsize);
        public int _getcpu();
        public int _getpid();
        public int _gettid();
    }

    // Method to process the return value
    private void process_retval(int retval) throws Exception {
    	if ( retval == 0 ) { }
        	// System.out.println("DEBUG: Operation successful");
    	else if ( retval == 1 )
    		throw new ThreadAffinityException("Supplied memory address was invalid");
    	else if ( retval == 2 )
    		throw new ThreadAffinityException("cpusetsize is smaller than the size of the affinity mask used by the kernel.");
    	else if ( retval == 3 )
    		throw new ThreadAffinityException("The calling process does not have appropriate privileges. The caller needs an effective user ID equal to the real user ID or effective user ID of the process identified by pid, or it must possess the CAP_SYS_NICE capability.");
    	else if ( retval == 4 )
    		throw new ThreadAffinityException("The process whose ID is pid could not be found.");
    }

    // Method to get the current CPU
    public int sched_getcpu() throws Exception {
    	int retval = ctest._getcpu();
    	return retval;
    }

    // Method to get the current process id
    public int _getpid() throws Exception {
    	int retval = ctest._getpid();
    	return retval;
    }
    
    public int _gettid() throws Exception {
    	int retval = ctest._gettid();
    	return retval;
    }

    // Method to set the affinity of a process
    public void setaffinity(int size, int[] a) throws Exception {
    	int retval = ctest._setaffinity(size, a);
    	process_retval(retval);
    }
    
    public void setaffinity_process(int pid, int size, int[] a) throws Exception {
    	int retval = ctest._setaffinity_process(pid, size, a);
    	process_retval(retval);
    }

    // Method to get the affinity of a process
    public void getaffinity(int tid, int size, int[] a) throws Exception {
    	int retval = ctest._getaffinity(tid, size);
    	process_retval(retval);
    }
    
    static public void main(String argv[]) throws Exception {

    }
}