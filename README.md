# JGenetic 🧬 

## About

🧬 `JGenetic` is a library of [Genetic Algorithms](https://en.wikipedia.org/wiki/Genetic_algorithm) written in Java, all you have to do is program the logic of initiation, aptitude / fitness for the population and let the library do the rest.
Is developed with the IDE [Apache Netbeans 10](https://netbeans.apache.org/download/nb100/nb100.html) and [Openjdk 11](https://openjdk.java.net/projects/jdk/11/)

## Methods

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

Problem:

|   5   |   1   |   3   |
| :---: | :---: | :---: |
|   7   |   6   |   4   |
|   8   |   0   |   2   |

Solution:

|   0   |   1   |   2   |
| :---: | :---: | :---: |
|   3   |   4   |   5   |
|   6   |   7   |   8   |


To find the solution we know that the board is a square of 3x3, so we already have the width, now we transform the matrix into a vector: `[5,1,3,7,6,4,8,0,2]`.

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

## Logic of the problem 'Puzzle'

With `JGenetic` you only have to worry about the logic of initiation, calculation of aptitude / fitness and let the library do the rest, as an example we will take the puzzle game, the logic of initiation and calculation of fitnes to the following:

For the initiation random we will invest the positions.

For the calculation of the fitness we will compare the index of the vector with the value.

```java
public class Logic {
    
    Resources resource = Resources.getSingletonInstance();   
    int min, max;
    
    public Logic () {

        min = Config.getMinRandomGenes();
        max = Config.getMaxRandomGenes();
    }
    
    public int[] initVector () {
        
        int chromosomeVector[] = new int [Config.getWidthVector()];
        int num1,num2,aux;

        for (int i = 0; i < chromosomeVector.length; i++) {
            chromosomeVector[i] = i;         
        }

        for (int i = 0; i < resource.getRandom(4, max); i++) {
            num1 = resource.getRandom(min,max);
            num2 = resource.getExclusiveRandom(min,max,num1);

            aux = chromosomeVector[num1];
            chromosomeVector[num1] = chromosomeVector[num2];
            chromosomeVector[num2] = aux;
        }
        return chromosomeVector;
    }
    
    public int Fitness (int vector[]) {
    
        int numConflicts = 0;
        
        for (int i = 0; i < vector.length; i++) {
            if(i != vector[i]) numConflicts++;
        }

        return numConflicts;        
    }
    
    public void initCommunity () {
        
        ArrayList<Chromosome> CommunityTemp = new ArrayList<>();

        for (int i = 0; i < Config.getWidthCommunity(); i++) {

            Chromosome newCromosoma = new Chromosome();

            newCromosoma.setChromosomeVector(initVector());
 
            CommunityTemp.add(newCromosoma);

        }
        
        Config.setCommunity(CommunityTemp);
        calFitnessTotalCommunity(); 
    }
    
    public void calFitnessTotalCommunity () {
    
        Config.getCommunity().forEach((c) -> {       
            c.setAptitude(Fitness(c.getChromosomeVector()));
        });
        
    }
    
    public void drawMatriz (int[] vector) {
    
        System.out.println("********************/ / PUZZLE / /********************\n");
        
        int matriz[][] = new int [Config.getWidthBoard()][Config.getWidthBoard()];        
        int count = 0;
        
        for (int i = 0; i < Config.getWidthBoard(); i++) {  
            for (int j = 0; j < Config.getWidthBoard(); j++) {
                matriz[i][j] = vector[j+count];
            }
            count+=Config.getWidthBoard();
        }
        
        for (int i = 0; i < Config.getWidthBoard(); i++) {
            System.out.print("\t\t");
            for (int j = 0; j < Config.getWidthBoard(); j++) { 
                System.out.print(matriz[i][j]+"\t");
            }
            System.out.println("\n");
        }
        System.out.println("**********************************************************");
    }
}
```
## Genetic of the problem 'Puzzle'

Already having the logic we only worry about selecting the methods of selection, crossing, mutation and acceptance that most suit us as we will see in the following example:

```java

public class Genetic {
    

    JGenetic jgenetic;      // JGenetic
    Resources resources;    // JGenetic
    
    int generation = 0, children = 0, posParents[];     // Variables
    Logic logic; // Class Logic
    
    Chromosome[] chromChildren; // vectors for reproduction
    Chromosome[] resCrossing;   // vectors for reproduction
    Chromosome[] resMutacion;   // vectors for reproduction
    
    public Genetic() {       
        
        this.resources = Resources.getSingletonInstance();
        this.jgenetic = new JGenetic();
        
        this.logic = new  Logic();
        this.posParents = new int[Config.getWidthParents()];
        this.chromChildren = new Chromosome[Config.getWidthChildren()];
        this.resCrossing = new Chromosome[Config.getWidthChildren()];
        this.resMutacion = new Chromosome[Config.getWidthChildren()];       
    }
    
    public void geneticAlgorithm() {

        boolean isFinal = false;
        
        logic.initCommunity();

        while (!isFinal) {
            
            for (int i = 0; i < Config.getWidthChildren(); i++) {
                chromChildren[i] = new Chromosome();
            }
            
            logic.calFitnessTotalCommunity(); // Calculate Fitness
            
            if (resources.findSolution()) isFinal = true; // there's a solution?

            if (!isFinal) {

                jgenetic.SelectionTournamentDeterministic();    //Selection
                
                posParents[0] = resources.selectedParent1();                // Parent one 
                posParents[1] = resources.selectedParent2(posParents[0]);   // Parent two 

                resCrossing = jgenetic.CrossoverOnePoint(posParents, chromChildren);    // Crossing

                resMutacion = jgenetic.MutationInvertGenes(resCrossing);    // Mutation

                // Calculate fitness for children
                for (Chromosome auxMutacionHijo : resMutacion) {
                    Logica auxiliar = new Logica();
                    auxMutacionHijo.setAptitude(auxiliar.Fitness(auxMutacionHijo.getChromosomeVector()));
                }

                jgenetic.AcceptanceTournament(resMutacion);     // Acceptance

                resources.prepareNextGeneration();  // I prepare the community for the next generation
                
                children += Config.getWidthChildren();
                generation++;
            }
        }
        
        resources.drawResultFinal();                    // shows the final chromosome
        resources.drawInfoFinal(generation, children);  // shows the final information
        logic.drawMatriz(resources.getVectorFinal());   // shows the final vector transformed to matriz
    }
}
```

## Execution of the problem 'Puzzle'

Now we set the configuration values, instantiate the object and execute its function `geneticAlgorithm`.

```java
public class JGeneticPuzzle {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Config.setWidthBoard(3);        // Board width
        Config.setWidthVector(9);       // Chromosome vector width
        Config.setWidthCommunity(100);  // Size of the community
        Config.setWidthChildren(2);     // Number of children > 0 | The default parents are 2
        Config.setMinRandomVector(0);   // Random minimum for vector length
        Config.setMaxRandomVector(8);   // Random maximum for vector length
        Config.setMinRandomGenes(0);    // Random minimum for the value of the gene
        Config.setMaxRandomGenes(8);    // Random maximum for the value of the gene
             
        Genetic genetic = new Genetic();
        genetic.geneticAlgorithm();     // Execution of the Genetic Algorithm
    }
}
```

## License

MIT
