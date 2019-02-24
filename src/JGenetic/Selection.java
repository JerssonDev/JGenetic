/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JGenetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


/**
 *
 * @author mendoza
 */
public class Selection {
    
    Resources resourses;
    
    public Selection () {
        this.resourses = Resources.getSingletonInstance();
    }
    
    public void elitist () {

        Config.getCommunity().sort((Comparator.comparing(Chromosome::getAptitude)));
        
        for (int i = 0; i < Config.getWidthParents(); i++) {
            
            Config.getCommunity().get(i).setSelected(true);
        }
        
        System.out.println("------------------------------------------ */*/ Selection elitist (Result) */*/ ------------------------------------------");
        
        System.out.println("Selected Parent " + 0 + " ;");
        System.out.println("Selected Parent " + 1 + " ;");
        
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public void roulette () {
    
        int sumFitness = 0, selected;
        
        int posSelected[] = new int[Config.getWidthParents()];
        
        int cumulativeFitness[] = new int[Config.getWidthCommunity()];

        for (int i = 0; i < Config.getCommunity().size(); i++) {
            
            sumFitness += Config.getCommunity().get(i).getAptitude();
            
            cumulativeFitness[i] = sumFitness;  
        }

        
        for (int i = 0; i < Config.getWidthParents(); i++) {
            
            selected = resourses.getRandom(cumulativeFitness[0], sumFitness);
            
            for (int j = 0; j < Config.getCommunity().size(); j++) {
                
                if (cumulativeFitness[j] > selected) {
                    
                    posSelected[i] = j;
                    break;
                }
            }
        }

        System.out.println("------------------------------------------ */*/ Selection roulette (Result) */*/ ------------------------------------------");
        
        for (int i = 0; i < Config.getWidthParents(); i++) {
            
            Config.getCommunity().get(posSelected[i]).setSelected(true);
            
            System.out.println("Selected Parent " + posSelected[i] + "; cumulative Fitness : " + cumulativeFitness[posSelected[i]]);
        }
        
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");

        
    }
    
    
    
    public void tournamentDeterministic () {

       int numParticipants = 2;
        tournamentDeterministic(numParticipants);

    }
    
    public void ranking () {

        Config.getCommunity().sort((Comparator.comparing(Chromosome::getAptitude)));
        
        Config.getCommunity().get(0).setSelected(true);
        
        Config.getCommunity().get(Config.getCommunity().size() - 1).setSelected(true);
        
        System.out.println("------------------------------------------ */*/ Selection ranking (Result) */*/ ------------------------------------------");
        
        System.out.println("Selected Parent " + 0 + " ;");
        System.out.println("Selected Parent " + 99 + " ;");
        
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");

    }
    
    
    
    public void tournamentDeterministic (int numParticipants) {

        int random;
        
        int menorConflicValue[] = new int[Config.getWidthParents()];
        
        int menorConflicPos[] = new int[Config.getWidthParents()];
        
        Chromosome cromosomaParentsSelected[] = new Chromosome[Config.getWidthParents()];
        
        Chromosome cromosomaParentsTemp[] = new Chromosome[numParticipants];
        
        ArrayList<Chromosome> communityDeterministicTemp = new ArrayList<>();

        System.out.println("------------------------------------------ */*/ Selection tournament deterministic (Result) */*/ ------------------------------------------");
        
        for (int i = 0; i < numParticipants; i++) {
            
            random = resourses.getRandom(0, Config.getCommunity().size() - 1);
            
            cromosomaParentsTemp[i] = Config.getCommunity().get(random);
            
            Config.getCommunity().remove(random);
        }
        
        communityDeterministicTemp.addAll(Arrays.asList(cromosomaParentsTemp));
            
        communityDeterministicTemp.forEach((c) -> {
            System.out.println("*-* Participate in the search for the best Aptitude ( " + c.getAptitude() + " ) -> " + Arrays.toString(c.getChromosomeVector()));
        });
        
        for (int i = 0; i < Config.getWidthParents(); i++) {
            
            menorConflicValue[i] = communityDeterministicTemp.get(0).getAptitude();
            
            for (Chromosome cromosoma : communityDeterministicTemp) {
                
                if (cromosoma.getAptitude()< menorConflicValue[i]) {
                    
                    menorConflicValue[i] = cromosoma.getAptitude();
                    menorConflicPos[i] = communityDeterministicTemp.indexOf(cromosoma);
                }
            }
            
            System.out.println(" ✔️ Selecter for Parents " + i + " Aptitude ( " + communityDeterministicTemp.get(menorConflicPos[i]).getAptitude()+ " ) -> " + Arrays.toString(communityDeterministicTemp.get(menorConflicPos[i]).getChromosomeVector()));
            
            cromosomaParentsSelected[i] = communityDeterministicTemp.get(menorConflicPos[i]);            
            communityDeterministicTemp.remove(menorConflicPos[i]);
        }
        
        for (Chromosome parentsSelected : cromosomaParentsSelected) {
            parentsSelected.setSelected(true);
            communityDeterministicTemp.add(parentsSelected);
        }
        
        Config.getCommunity().addAll(communityDeterministicTemp);
        
        communityDeterministicTemp.forEach((c) -> {
            System.out.println("++ Re-Enter ( " + c.getAptitude() + " ) -> " + Arrays.toString(c.getChromosomeVector()));
        });
        
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
    }
 
    
}
