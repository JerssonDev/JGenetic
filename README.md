# JGenetic 🧬 

## About
---
🧬 `JGenetic` is a library of [Genetic Algorithms](https://en.wikipedia.org/wiki/Genetic_algorithm) written in Java, all you have to do is program the logic of initiation, aptitude for the population and let the library do the rest.
Is developed with the IDE [Apache Netbeans 10](https://netbeans.apache.org/download/nb100/nb100.html) and [Openjdk 11](https://openjdk.java.net/projects/jdk/11/)

## Methods
---
1. Selection:
    - Roulette
    - Ranking
    - Elitist
    - Deterministic Tournament

2. Crossing:
    - One point
    - Two points
    - Uniform

3. Mutation:
    - Inversion of genes
    - Change of order
    - Modification of genes


4. Acceptance:
    - Total
    -  Improvement
    -  Tournament

## Configuration
For the configuration we will take as an example the puzzle solution

|   5   |   1   |   3   |
| :---: | :---: | :---: |
|   7   |   6   |   4   |
|   8   |   0   |   2   |

We know that the board is a square of 3x3 so we already have the width, now we transform the matrix to a vector which will be the following: `[5,1,3,7,6,4,8,0,2]`.
The vector is of length: `9`.
The gene is included in the range of values from: `0 to 8`.
Now that we have abstracted the problem we are going to set the values to the configuration in the following example:

```java
Config.setWidthBoard(3);        // Board width
Config.setWidthVector(9);       // Chromosome vector width
Config.setWidthCommunity(100);  // Size of the community
Config.setWidthChildren(2);     // Number of children > 0 | The default parents are 2
Config.setMinRandomVector(0);   // Random minimum for vector length
Config.setMaxRandomVector(8);   // Random maximum for vector length
Config.setMinRandomGenes(0);    // Random minimum for the value of the gene
Config.setMaxRandomGenes(8);    // Random maximum for the value of the gene
```

## License

MIT