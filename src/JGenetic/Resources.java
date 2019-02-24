/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JGenetic;

import java.util.Arrays;
import java.util.Random;
/**
 *
 * @author mendoza
 */
public class Resources {
    
    private final Random random;
    
    private  static Resources resources;

    private Resources () {
        random = new Random();
    }
    
    public String getClassName () {
    
        return this.getClass().getName();
        
    }
    
    public static Resources getSingletonInstance() {
        
        if (resources == null){
            resources = new Resources();
        }        
        return resources;
    }
    
    
    public int getRandom(int min, int max) {
        return (int) Math.round((max - min) * random.nextDouble() + min);
    }


    public int getExclusiveRandom(int min,int max, int numIgnore) {
        
        boolean finish = false;  
        int rand = 0;
        
        while (!finish) {
            rand = (int) Math.round((max - min) * random.nextDouble() + min);
            if (rand != numIgnore) {
                finish = true;
            }
        }

        return rand;
    }
    
    public boolean findSolution(){
         
        for (int i = 0; i < Config.getCommunity().size(); i++) {
                
            if (Config.getCommunity().get(i).getAptitude()== 0) {
                Config.getCommunity().get(i).setSolution(true);
                return true;
            }
        }
        
        return false;
         
    }
    

    public int selectedParent1() {
        
        int posParent = 0;
        Chromosome cromosoma;

        for (int i = 0; i < Config.getCommunity().size(); i++) {
            
            cromosoma = Config.getCommunity().get(i);
            
            if (cromosoma.isSelected()) {
                
                posParent = i;
                break;
            }
        }
        return posParent;
    }


    public int selectedParent2(int parent1) {
        
        int posParent = 0;
        Chromosome cromosoma;
        
        for (int i = 0; i < Config.getCommunity().size(); i++) {
            
            cromosoma = Config.getCommunity().get(i);
            
            if (i != parent1) {
                
                if (cromosoma.isSelected()) {
                    
                    posParent = i;
                    break;
                }
            }
        }
        return posParent;
    }
    
    public void prepareNextGeneration() {
        // Restaura estado de cromosoma
        Config.getCommunity().forEach((c) -> {
            c.setSelected(false);
        });
        
    }
    
    public void drawResultFinal () {
    
        Config.getCommunity().stream().filter((c) -> (c.getAptitude()== 0)).forEach((c) -> {
            System.out.println("\t  --------------------");
            System.out.println("\t | Chromosome Solution |");
            System.out.println("\t  --------------------\n");   
            System.out.println( "\t" + Arrays.toString(c.getChromosomeVector()) + "\n");          
        });
        
    }
    
    public void drawInfoFinal (int generation,int children) {
    
        System.out.println("  ---------------------------------------");
        System.out.println(" | \t\t Resolved \t\t |");    
        System.out.println(" | \t Total de Generation = " + generation + " \t |");
        System.out.println(" | \t Total de Children = " + children + " \t\t |");
        System.out.println(" | \t Total de Community = " + Config.getCommunity().size() + " \t |");
        System.out.println("  ---------------------------------------\n");
        
    }
    
    public int[] getVectorFinal () {

        for (Chromosome c : Config.getCommunity()) {
            if (c.getAptitude() == 0) {
                return c.getChromosomeVector();
            }
        }
        
        return null;
    }
    
}
