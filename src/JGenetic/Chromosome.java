/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JGenetic;

/**
 *
 * @author mendoza
 */
public class Chromosome {

    private int [] chromosomeVector; 
    private int aptitude;
    private boolean selected;
    private boolean solution;
    
    public Chromosome () {
    
        this.chromosomeVector = new int [Config.getWidthVector()];
        this.aptitude = 0;
        this.selected = false;
        this.solution = false;

    }

    public int[] getChromosomeVector() {
        return chromosomeVector;
    }

    public void setChromosomeVector(int[] chromosomeVector) {
        this.chromosomeVector = chromosomeVector;
    }
    
    public int getValueChromosomeVector(int indice) {
        return this.chromosomeVector[indice];
    }

    public void setValueChromosomeVector(int indice, int value) {
        this.chromosomeVector[indice] = value;
    }

    public int getAptitude() {
        return aptitude;
    }

    public void setAptitude(int aptitude) {
        this.aptitude = aptitude;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSolution() {
        return solution;
    }

    public void setSolution(boolean solution) {
        this.solution = solution;
    }
    
    
    
}
