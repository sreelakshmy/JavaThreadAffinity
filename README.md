JavaThreadAffinity
==================

JavaThreadAffinity

a) Running the code without Eclipse :

Step:1

    Clone the repository in your local machine and download the jna.jar file (from internet).
    After cloning, you can find the files in side the path JavaThreadAffinity/src/com/threads/

Step:2

    Run the C program using the following command,
    
          $ gcc -fPIC -o libctest.so -shared ctest.c

    Alternatively, you can run
          $ ./compile.sh
          
b) Running the code in Eclipse :

Step 1:

    Clone the repository and import the project files inside your workspace.
    
Step 2:

    Download the jna.jar file from the internet.
    
Step 3:

    Change the buildpath and classpath to point to jna.jar

Step 4:
      
      Build and run the following files to see thread affinity in action
:-
      ThreadAffinityBasicUser.java
      ThreadAffinityMultiUser.java
      FactorialThreadAffinity.java
