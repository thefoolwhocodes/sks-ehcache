# ehcache
- This program intends to demonstrate the usage of ehcache.
- It also shows a glimse of how various tiers of the ehcache can be layered.
- This code also demonstrates the thread safety aspect of ehcache.

# Usage
- Download the maven project and run as a Java application.

# Tweakable parameters
- Thread count: Number of threads working on the random work can be increased/decreased.
- Random work: The dimension of Random work can be increased/decreased. A decrease in the dimension will mean that at a given time, there is a increased possibility of more threads hitting the same work.

# Conclusion
- Ehcache looks good enough to work in multithreaded environment.
