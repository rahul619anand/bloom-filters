# Bloom Filters

Bloom Filters offers a one side containment test of elements in a set .
It offers two results :
    
    1. A Definite NO - stating an elemnet is definitely not in set
    2. A Maybe YES  - stating element is possibly in set , with some false positives
    


### Pros    

1. Requires very little space as it stores the elements hash is represented in  binary form.  

### Cons 

1. Small False Positives 
2. No deletion of data (i.e. inserted elements info) supported.
3. Cannot store associated objects.

Here, I have used Guava's Bloom Filter for testing, 
which automatically tunes the number of hash functions 
based on expectedInsertions and the accepted false positive probability.


### Sample Output
    
    testing bloom filter ... 
    
    Expected insertions : 1000000
    Accepted False positives probability = 0.01 approx.
    Setting up bloom filter ... 
    Insertions done: 1000000
    
    Testing for all inserted elements ...
    Expected positives : 1000000
    Real positives : 1000000
    
    Testing for all non-inserted elements ...
    Expected false positives , i.e. (fpp * expectedInsertions) approx. : 10000.0
    Real false positives : 10030
  