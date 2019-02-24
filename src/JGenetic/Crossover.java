/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JGenetic;

import java.util.Arrays;

/**
 *
 * @author mendoza
 */
public class Crossover {
    
    Resources resources;
    private final int min;
    private final int max;
    
    public Crossover () {
        this.resources = Resources.getSingletonInstance();
        this.min = Config.getMinRandomVector();
        this.max = Config.getMaxRandomVector();
    }
    
    
    public Chromosome[] onePoint (int[] posParents, Chromosome[] cromosomaChildren) {

        Chromosome parent1 = new Chromosome();
        Chromosome parent2 = new Chromosome();
        int pointCrossover;

        System.out.println("------------------------------------------ */*/ Crossover in onePoint (Result) */*/ ------------------------------------------");
        
        for (int i = 0; i < cromosomaChildren.length; i++) {

            pointCrossover = resources.getRandom(min, max);

            if (i % 2 == 0) {
                parent1 = Config.getCommunity().get(posParents[0]);
                parent2 = Config.getCommunity().get(posParents[1]);
            } else {
                parent1 = Config.getCommunity().get(posParents[1]);
                parent2 = Config.getCommunity().get(posParents[0]);
            }

            for (int j = 0; j < Config.getWidthVector(); j++) {
                if (j <= pointCrossover) {
                    cromosomaChildren[i].setValueChromosomeVector(j, parent1.getValueChromosomeVector(j));
                } else {
                    cromosomaChildren[i].setValueChromosomeVector(j, parent2.getValueChromosomeVector(j));
                }
            }
       
        }
        
        System.out.println("Parent 1 : " + Arrays.toString(parent1.getChromosomeVector()));
        System.out.println("Parent 2 : " + Arrays.toString(parent2.getChromosomeVector())); 
        
        for (int i = 0; i < cromosomaChildren.length; i++) {
            System.out.println("Child "+i+" : " + Arrays.toString(cromosomaChildren[i].getChromosomeVector()));
        }
        
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");

        return cromosomaChildren;
    }

    public Chromosome[] twoPoints(int posParents[], Chromosome cromosomaChildren[]) {

        Chromosome parent1 = new Chromosome();
        Chromosome parent2 = new Chromosome();
        int point1, point2, auxPoint;
        
        System.out.println("------------------------------------------ */*/ Crossover in twoPoint (Result) */*/ ------------------------------------------");

        for (int i = 0; i < cromosomaChildren.length; i++) {

            point1 = resources.getRandom(min, max);
            point2 = resources.getExclusiveRandom(min, max, point1);

            if (point2 < point1) {
                auxPoint = point1;
                point1 = point2;
                point2 = auxPoint;
            }

            if (i % 2 == 0) {
                parent1 = Config.getCommunity().get(posParents[0]);
                parent2 = Config.getCommunity().get(posParents[1]);
            } else {
                parent1 = Config.getCommunity().get(posParents[1]);
                parent2 = Config.getCommunity().get(posParents[0]);
            }

            for (int j = 0; j < Config.getWidthVector(); j++) {

                if (j <= point1) {
                    cromosomaChildren[i].setValueChromosomeVector(j, parent1.getValueChromosomeVector(j));
                } else if (j >= point2) {
                    cromosomaChildren[i].setValueChromosomeVector(j, parent1.getValueChromosomeVector(j));
                } else {
                    cromosomaChildren[i].setValueChromosomeVector(j, parent2.getValueChromosomeVector(j));
                }

            } 
        }
        
        System.out.println("Parent 1 : " + Arrays.toString(parent1.getChromosomeVector()));
        System.out.println("Parent 2 : " + Arrays.toString(parent2.getChromosomeVector()));
        
        for (int i = 0; i < cromosomaChildren.length; i++) {
            System.out.println("Child "+i+" : " + Arrays.toString(cromosomaChildren[i].getChromosomeVector()));
        }
        
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        
        return cromosomaChildren;
    }

    public Chromosome[] uniform (int posParents[], Chromosome cromosomaChildren[]) {

        Chromosome parent1 = new Chromosome();
        Chromosome parent2 = new Chromosome();
        
        System.out.println("------------------------------------------ */*/ Crossover in uniform (Result) */*/ ------------------------------------------");

        for (int i = 0; i < cromosomaChildren.length; i++) {
            
            if (i % 2 == 0) {
                parent1 = Config.getCommunity().get(posParents[0]);
                parent2 = Config.getCommunity().get(posParents[1]);
            } else {
                parent1 = Config.getCommunity().get(posParents[1]);
                parent2 = Config.getCommunity().get(posParents[0]);
            }

            for (int j = 0; j < Config.getWidthVector(); j++) {

                if (j % 2 == 0) {
                    cromosomaChildren[i].setValueChromosomeVector(j, parent1.getValueChromosomeVector(j));
                } else {
                    cromosomaChildren[i].setValueChromosomeVector(j, parent2.getValueChromosomeVector(j));
                }
            }
        }
        
        System.out.println("Parent 1 : " + Arrays.toString(parent1.getChromosomeVector()));
        System.out.println("Parent 2 : " + Arrays.toString(parent2.getChromosomeVector()));

        for (int i = 0; i < cromosomaChildren.length; i++) {
            System.out.println("Child "+i+" : " + Arrays.toString(cromosomaChildren[i].getChromosomeVector()));
        }
        
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        
        return cromosomaChildren;
    }
    
    
}
