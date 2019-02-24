/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JGenetic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author mendoza
 */
public class Acceptance {
    
    Resources resources;
    private final int min;
    private final int max;
    
    public Acceptance () {
        this.resources = Resources.getSingletonInstance();
        this.min = Config.getMinRandomVector();
        this.max = Config.getMaxRandomVector();
    }
    
    public void total(Chromosome[] cromosomaChildren) {
        
        int mayConflicValue[] = new int[cromosomaChildren.length];
        int mayConflicPos[] = new int[cromosomaChildren.length];
 
        for (int i = 0; i < cromosomaChildren.length; i++) {
            mayConflicValue[i] = Config.getCommunity().get(0).getAptitude();
        }
        
        System.out.println("------------------------------------------ */*/ Acceptance Total (Result) */*/ ------------------------------------------");
        
        for (int i = 0; i < cromosomaChildren.length; i++) {
            
            for (Chromosome cromosomaTemp : Config.getCommunity()) {
                
                if (cromosomaTemp.getAptitude()> mayConflicValue[i]) {
                    
                    mayConflicValue[i] = cromosomaTemp.getAptitude();
                    
                    mayConflicPos[i] = Config.getCommunity().indexOf(cromosomaTemp);
                }
            }
            System.out.println("Parents eliminate in the position " + mayConflicPos[i] + " conflic (" + Config.getCommunity().get(mayConflicPos[i]).getAptitude()+ ")" + " - > " + Arrays.toString(Config.getCommunity().get(mayConflicPos[i]).getChromosomeVector()));
            Config.getCommunity().remove(mayConflicPos[i]);
        }
        
        Config.getCommunity().addAll(Arrays.asList(cromosomaChildren));    

        for (int i = 0; i < cromosomaChildren.length; i++) {
            System.out.println("Acceptance Chield "+i+" : " + Arrays.toString(cromosomaChildren[i].getChromosomeVector()));
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        
    }
    
    public void improvement (Chromosome[] cromosomaChildren) {
        
        int mayConflicValue[] = new int[cromosomaChildren.length];
        int mayConflicPos[] = new int[cromosomaChildren.length];
        int menorConflicValue[] = new int[cromosomaChildren.length];
        int menorConflicPos[] = new int[cromosomaChildren.length];
        
        Chromosome cromosomaParentsTemp[] = new Chromosome[cromosomaChildren.length];
        
        ArrayList<Chromosome> communityImprovementTemp = new ArrayList<>();
        
        System.out.println("------------------------------------------ */*/ Acceptance improvement (Result) */*/ ------------------------------------------");
        
        for (int i = 0; i < cromosomaChildren.length; i++) {
            
            mayConflicValue[i] = Config.getCommunity().get(0).getAptitude();
            
            for (Chromosome poblacionTemp : Config.getCommunity()) {
                
                if (poblacionTemp.getAptitude()> mayConflicValue[i]) {
                    
                    mayConflicValue[i] = poblacionTemp.getAptitude();
                    mayConflicPos[i] = Config.getCommunity().indexOf(poblacionTemp);
                }
            }
            cromosomaParentsTemp[i] = Config.getCommunity().get(mayConflicPos[i]);
            Config.getCommunity().remove(mayConflicPos[i]);
        }
        
        for (int i = 0; i < cromosomaParentsTemp.length; i++) {
            System.out.println("Parent " + mayConflicPos[i] + " Aptitude ( " + cromosomaParentsTemp[i].getAptitude()+ " ) -> " + Arrays.toString(cromosomaParentsTemp[i].getChromosomeVector()));
        }
        
        for (int i = 0; i < cromosomaChildren.length; i++) {
            System.out.println("Child " + i +"  Aptitude ( " + cromosomaChildren[i].getAptitude()+ " ) -> " + Arrays.toString(cromosomaChildren[i].getChromosomeVector()));
        }
        
        
        for (int i = 0; i < cromosomaChildren.length; i++) {
            communityImprovementTemp.add(cromosomaChildren[i]);
            communityImprovementTemp.add(cromosomaParentsTemp[i]);
        }
        
        communityImprovementTemp.forEach((c) -> {
            System.out.println("*-* Participate in the search for the best Aptitude ( " + c.getAptitude() + " ) -> " + Arrays.toString(c.getChromosomeVector()));
        });
        
        for (int i = 0; i < cromosomaChildren.length; i++) {
            
            menorConflicValue[i] = communityImprovementTemp.get(0).getAptitude();
            
            for (Chromosome cromosoma : communityImprovementTemp) {
                
                if (cromosoma.getAptitude()< menorConflicValue[i]) {
                    
                    menorConflicValue[i] = cromosoma.getAptitude();
                    menorConflicPos[i] = communityImprovementTemp.indexOf(cromosoma);
                }
            }
            System.out.println(" ✔️ Go to the next generation the best person " + i + " Aptitude ( " + communityImprovementTemp.get(menorConflicPos[i]).getAptitude()+ " ) -> " + Arrays.toString(communityImprovementTemp.get(menorConflicPos[i]).getChromosomeVector()));
            
            Config.getCommunity().add(communityImprovementTemp.get(menorConflicPos[i]));
            communityImprovementTemp.remove(menorConflicPos[i]);
            
        }
                
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public void improvementWithParents (Chromosome[] cromosomaChildren, int[] posParents) {
        
        int menorConflicValue[] = new int[cromosomaChildren.length];
        int menorConflicPos[] = new int[cromosomaChildren.length];
        
        Chromosome cromosomaParentsTemp[] = new Chromosome[posParents.length];
        
        ArrayList<Chromosome> communityImprovementTemp = new ArrayList<>();
        
        System.out.println("------------------------------------------ */*/ Acceptance improvement (Result) */*/ ------------------------------------------");


        for (int i = 0; i < posParents.length; i++) {
            cromosomaParentsTemp[i] = Config.getCommunity().get(posParents[i]);
        }
        
        for (Chromosome cromosoma : cromosomaParentsTemp) {
            Config.getCommunity().remove(cromosoma);
        }
        
        for (int i = 0; i < cromosomaParentsTemp.length; i++) {
            System.out.println("Parent " + posParents[i] + " Aptitude ( " + cromosomaParentsTemp[i].getAptitude()+ " ) -> " + Arrays.toString(cromosomaParentsTemp[i].getChromosomeVector()));
        }
        
        for (int i = 0; i < cromosomaChildren.length; i++) {
            System.out.println("Child " + i +"  Aptitude ( " + cromosomaChildren[i].getAptitude()+ " ) -> " + Arrays.toString(cromosomaChildren[i].getChromosomeVector()));
        }
        
        
        communityImprovementTemp.addAll(Arrays.asList(cromosomaChildren));
        communityImprovementTemp.addAll(Arrays.asList(cromosomaParentsTemp));
        
        communityImprovementTemp.forEach((c) -> {
            System.out.println("*-* Participate in the search for the best Aptitude ( " + c.getAptitude() + " ) -> " + Arrays.toString(c.getChromosomeVector()));
        });
        
        for (int i = 0; i < cromosomaChildren.length; i++) {
            
            menorConflicValue[i] = communityImprovementTemp.get(0).getAptitude();
            
            for (Chromosome cromosoma : communityImprovementTemp) {
                
                if (cromosoma.getAptitude()< menorConflicValue[i]) {
                    
                    menorConflicValue[i] = cromosoma.getAptitude();
                    menorConflicPos[i] = communityImprovementTemp.indexOf(cromosoma);
                }
            }
            System.out.println(" ✔️ Go to the next generation the best person " + i + " Aptitude ( " + communityImprovementTemp.get(menorConflicPos[i]).getAptitude()+ " ) -> " + Arrays.toString(communityImprovementTemp.get(menorConflicPos[i]).getChromosomeVector()));
            
            Config.getCommunity().add(communityImprovementTemp.get(menorConflicPos[i]));
            communityImprovementTemp.remove(menorConflicPos[i]);
            
        }
        
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
    }
    
    
    public void tournament (Chromosome[] cromosomaChildren, int numParticipants) {
        
        int menorConflicValue[] = new int[numParticipants];
        int menorConflicPos[] = new int[numParticipants];
        
        int parentsRandomPos[] = new int[numParticipants];
        Chromosome parentsRandomTemp[] = new Chromosome[numParticipants];
        
        ArrayList<Chromosome> communityTournamentTemp = new ArrayList<>();
        
        System.out.println("------------------------------------------ */*/ Acceptance tournament (Result) */*/ ------------------------------------------");

        
        for (int i = 0; i < numParticipants; i++) {
            
            parentsRandomPos[i] = resources.getRandom(0, Config.getCommunity().size() - 1);
            
            parentsRandomTemp[i] = Config.getCommunity().get(parentsRandomPos[i]);

            Config.getCommunity().remove(parentsRandomPos[i]);           
        }
        
        for (int i = 0; i < parentsRandomTemp.length; i++) {
            System.out.println("Parent " + parentsRandomPos[i] + " Aptitude ( " + parentsRandomTemp[i].getAptitude()+ " ) -> " + Arrays.toString(parentsRandomTemp[i].getChromosomeVector()));
        }
        
        for (int i = 0; i < cromosomaChildren.length; i++) {
            System.out.println("Child " + i +"  Aptitude ( " + cromosomaChildren[i].getAptitude()+ " ) -> " + Arrays.toString(cromosomaChildren[i].getChromosomeVector()));
        }
        
        communityTournamentTemp.addAll(Arrays.asList(parentsRandomTemp));
        
        communityTournamentTemp.addAll(Arrays.asList(cromosomaChildren));
        
        communityTournamentTemp.forEach((c) -> {
            System.out.println("*-* Participate in the search for the best Aptitude ( " + c.getAptitude() + " ) -> " + Arrays.toString(c.getChromosomeVector()));
        });
        
        for (int i = 0; i < numParticipants; i++) {
            
            menorConflicValue[i] = communityTournamentTemp.get(0).getAptitude();
            
            for (Chromosome cromosoma : communityTournamentTemp) {
                
                if (cromosoma.getAptitude()< menorConflicValue[i]) {
                    
                    menorConflicValue[i] = cromosoma.getAptitude();
                    menorConflicPos[i] = communityTournamentTemp.indexOf(cromosoma);
                }
            }
            
            System.out.println(" ✔️ Go to the next generation the best person " + i + " Aptitude ( " + communityTournamentTemp.get(menorConflicPos[i]).getAptitude()+ " ) -> " + Arrays.toString(communityTournamentTemp.get(menorConflicPos[i]).getChromosomeVector()));
            
            Config.getCommunity().add(communityTournamentTemp.get(menorConflicPos[i]));            
            communityTournamentTemp.remove(menorConflicPos[i]);
            
        }
                
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public void tournament (Chromosome[] cromosomaChildren) {
    
        int numParticipants = 2;
        tournament(cromosomaChildren, numParticipants);
        
    }
    
}
