#define _GNU_SOURCE
#include <stdio.h>
#include <errno.h>
#include <sched.h>

int _setaffinity_process(int pid, int n, int *masks) {
  int i;
  int retval=0;

  printf("DEBUG: Making call to sched_setaffinity with arguments pid=%d\n", pid);
  cpu_set_t  mask;

  CPU_ZERO(&mask);
  for(i=0;i<n;i++)
    CPU_SET(masks[i], &mask);

  retval = sched_setaffinity(pid, sizeof(cpu_set_t), &mask);
  if ( retval == 0 )
    return 0;

  if (errno == EFAULT)
    return 1;
  else if (errno == EINVAL)
    return 2;
  else if (errno == EPERM)
    return 3;
  else if (errno == ESRCH)
    return 4;
}

// http://stackoverflow.com/questions/20030070/java-processes-vs-threads-for-thread-affinity/20030694?noredirect=1#20030694
// http://stackoverflow.com/questions/6372102/what-is-the-difference-between-pthread-self-and-gettid-which-one-should-i-u?rq=1
int _setaffinity(int n, int *masks) {
  int i;
  int retval=0;

  /*printf("DEBUG: Making call to sched_setaffinity with arguments pid=%d\n", pid);*/
  cpu_set_t  mask;

  CPU_ZERO(&mask);
  for(i=0;i<n;i++)
    CPU_SET(masks[i], &mask);

  /*pthread_t tid = pthread_self();*/
  /*retval = pthread_setaffinity_np(tid, sizeof(cpu_set_t), &mask);*/
  retval = pthread_setaffinity_np(pthread_self(), sizeof(cpu_set_t), &mask);
  if ( retval == 0 )
    return 0;

  if (errno == EFAULT)
    return 1;
  else if (errno == EINVAL)
    return 2;
  else if (errno == EPERM)
    return 3;
  else if (errno == ESRCH)
    return 4;
}


int _getaffinity(int pid, int n, int *masks) {
  int i;
  int retval;
  int current_index = 0;

  printf("Making call to sched_getaffinity with arguments (pid=%d)", pid);
  cpu_set_t mask;
  CPU_ZERO(&mask);
  retval = sched_getaffinity(pid, sizeof(cpu_set_t), &mask);
/*
  for ( i = 0; i < 4; i++ ) {
    if ( CPU_ISSET(i, &mask) ) {
      masks[current_index] = i;
      current_index += 1;
    }
  }
*/
 /* for ( i = 0; i < n; i++ ) {
    masks[i] = i;
  }

masks[9] = 1;
  if ( retval == 0 )
    return 0;

  if (errno == EFAULT)
    return 1;
  else if (errno == EINVAL)
    return 2;
  else if (errno == EPERM)
    return 3;
  else if (errno == ESRCH)
    return 4;
*/
  return 0;
}

int _getcpu() {
  return sched_getcpu();
}

int _getpid() {
  return getpid();
}

unsigned int _gettid() {
  unsigned int x = (unsigned int)pthread_self();
  printf("From C we have %d", x);
  return (unsigned int)pthread_self();
}
