#define _GNU_SOURCE
#include <stdio.h>
#include <errno.h>
#include <sched.h>

int _setaffinity(int pid, int n, int *masks) {
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
