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

    private int [] VectorCromosoma; 
    private int aptitude;
    private boolean selected;
    private boolean solution;
    
    public Chromosome () {
    
        this.VectorCromosoma = new int [Config.getWidthVector()];
        this.aptitude = 0;
        this.selected = false;
        this.solution = false;

    }

    public int[] getVectorCromosoma() {
        return VectorCromosoma;
    }

    public void setVectorCromosoma(int[] VectorCromosoma) {
        this.VectorCromosoma = VectorCromosoma;
    }
    
    public int getValueVectorCromosoma(int indice) {
        return this.VectorCromosoma[indice];
    }

    public void setValueVectorCromosoma(int indice, int valor) {
        this.VectorCromosoma[indice] = valor;
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
