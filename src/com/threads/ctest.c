#include <stdio.h>
#include <errno.h>

void helloFromC(long x) {
    printf("Hello from C! = %ld\n", x);
}


int _setaffinity(int pid, int cpusetsize) {
  printf("Making call to sched_setaffinity with arguments (pid=%d, cpusetsize=%d)\n",
         pid, cpusetsize);
  /*
   * returns 0 if success, else returns -1 with ERRNO set
   */
  int retval=0;
  if ( retval == 0 )
    return 0;

  if (errno == EFAULT) {
    return 1;
  }
  else if (errno == EINVAL) {
    return 2;
  }
  else if (errno == EPERM) {
    return 3;
  }
  else if (errno == ESRCH) {
    return 4;
  }
}

int _getaffinity(int pid, int cpusetsize) {
  printf("Making call to sched_getaffinity with arguments (pid=%d, cpusetsize=%d)\n",
         pid, cpusetsize);
  return 0;
}
