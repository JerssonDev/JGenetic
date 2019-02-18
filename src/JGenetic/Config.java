/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JGenetic;

import java.util.ArrayList;

/**
 *
 * @author mendoza
 */
public class Config {
    
    private static int widthBoard = 3;

    private static int widthCommunity = 100;
    
    private static int widthParents = 2;
    
    private static int widthChildren = 2;
    
    private static int widthVector = 9;
    
    private static int minRandomVector = 0;
    
    private static int maxRandomVector = 9;
    
    private static int minRandomGenes = 0;
    
    private static int maxRandomGenes = 9;
    
    private static ArrayList<Chromosome> community = new ArrayList<>();

    public static int getWidthBoard() {
        return widthBoard;
    }

    public static void setWidthBoard(int widthBoard) {
        Config.widthBoard = widthBoard;
    }

    public static int getMinRandomGenes() {
        return minRandomGenes;
    }

    public static void setMinRandomGenes(int minRandomGenes) {
        Config.minRandomGenes = minRandomGenes;
    }

    public static int getMaxRandomGenes() {
        return maxRandomGenes;
    }

    public static void setMaxRandomGenes(int maxRandomGenes) {
        Config.maxRandomGenes = maxRandomGenes;
    }

    public static int getMinRandomVector() {
        return minRandomVector;
    }

    public static void setMinRandomVector(int minRandomVector) {
        Config.minRandomVector = minRandomVector;
    }

    public static int getMaxRandomVector() {
        return maxRandomVector;
    }

    public static void setMaxRandomVector(int maxRandomVector) {
        Config.maxRandomVector = maxRandomVector;
    }

    public static int getWidthCommunity() {
        return widthCommunity;
    }

    public static void setWidthCommunity(int widthCommunity) {
        Config.widthCommunity = widthCommunity;
    }

    public static int getWidthParents() {
        return widthParents;
    }

    private static void setWidthParents(int widthParents) {
        Config.widthParents = widthParents;
    }

    public static int getWidthChildren() {
        return widthChildren;
    }

    public static int getWidthVector() {
        return widthVector;
    }

    public static void setWidthVector(int widthVector) {
        Config.widthVector = widthVector;
    }

    public static void setWidthChildren(int widthChildren) {
        Config.widthChildren = widthChildren;
    }

    public static ArrayList<Chromosome> getCommunity() {
        return community;
    }

    public static void setCommunity(ArrayList<Chromosome> community) {
        Config.community = community;
    }  
    
    
}
