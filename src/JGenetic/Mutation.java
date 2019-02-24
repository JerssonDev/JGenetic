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
public class Mutation {
    
    Resources resources;
    private final int min;
    private final int max;
    private final int minInvertGenes;
    private final int maxInvertGenes;
    
    public Mutation () {
        
        this.resources = Resources.getSingletonInstance();
        this.min = Config.getMinRandomVector();
        this.max = Config.getMaxRandomVector();
        this.minInvertGenes = Config.getMinRandomGenes();
        this.maxInvertGenes = Config.getMaxRandomGenes();
    }
    
    public Chromosome[] invertGenes (Chromosome[] cromosomaChildren) {

        int pointInvert, value, valueTemp;
        
        System.out.println("------------------------------------------ */*/ Mutation invertGenes (Result) */*/ ------------------------------------------");
        
        for (Chromosome cromosomaChild : cromosomaChildren) {
            
            pointInvert = resources.getRandom(min, max);
            
            valueTemp = cromosomaChild.getValueChromosomeVector(pointInvert);
            
            value = resources.getExclusiveRandom(minInvertGenes, maxInvertGenes,valueTemp);
            
            cromosomaChild.setValueChromosomeVector(pointInvert, value);
        }
        
        for (int i = 0; i < cromosomaChildren.length; i++) {
            System.out.println("Mutation in Child "+i+" : " + Arrays.toString(cromosomaChildren[i].getChromosomeVector()));
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        
        return cromosomaChildren;
    }

    public Chromosome[] exchangeOrder (Chromosome[] cromosomaChildren) {

        int exchangePoint1, exchangePoint2, exchangeValue1, exchangeValue2;

        System.out.println("------------------------------------------ */*/ Mutation exchangeOrder (Result) */*/ ------------------------------------------");
        
        for (Chromosome cromosomaChild : cromosomaChildren) {
            
            exchangePoint1 = resources.getRandom(min, max);
            exchangePoint2 = resources.getExclusiveRandom(min, max, exchangePoint1);
            
            exchangeValue1 = cromosomaChild.getValueChromosomeVector(exchangePoint1);
            exchangeValue2 = cromosomaChild.getValueChromosomeVector(exchangePoint2);
            
            cromosomaChild.setValueChromosomeVector(exchangePoint1, exchangeValue2);
            cromosomaChild.setValueChromosomeVector(exchangePoint2, exchangeValue1);
        }
        
        for (int i = 0; i < cromosomaChildren.length; i++) {
            System.out.println("Mutation in Child "+i+" : " + Arrays.toString(cromosomaChildren[i].getChromosomeVector()));
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");

        return cromosomaChildren;
    }

    public Chromosome[] modificationGenes(Chromosome[] cromosomaChildren, int numModifications) {

        int exchangePoint, exchangeValue, newValue;
        int sumGen = 1, resGen = -1;

        System.out.println("------------------------------------------ */*/ Mutation modificationGenes (Result) */*/ ------------------------------------------");

        for (Chromosome cromosomaChild : cromosomaChildren) {
            
            for (int j = 0; j < numModifications; j++) {
                
                exchangePoint = resources.getRandom(min, max);
                exchangeValue = cromosomaChild.getValueChromosomeVector(exchangePoint);
                
                if (exchangeValue % 2 == 0) {
                    
                    newValue = exchangeValue + sumGen;
                    cromosomaChild.setValueChromosomeVector(exchangePoint, newValue);
                    
                } else {
                    
                    newValue = exchangeValue + resGen;
                    cromosomaChild.setValueChromosomeVector(exchangePoint, newValue);
                    
                }
            }
        }

        for (int i = 0; i < cromosomaChildren.length; i++) {
            System.out.println("Mutation in Child "+i+" : " + Arrays.toString(cromosomaChildren[i].getChromosomeVector()));
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");

        return cromosomaChildren;
    }
    
    public Chromosome[] modificationGenes(Chromosome[] cromosomaChildren) {

        int numModifications = 2;

        return modificationGenes(cromosomaChildren,numModifications);
    }
    
    
}
