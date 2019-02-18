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
public class JGenetic {
    
    private final Selection selection;
    private final Crossover crossover;
    private final Mutation mutation;
    private final Acceptance acceptance;
    

    public JGenetic() {

        this.selection = new Selection();
        this.crossover = new Crossover();
        this.mutation = new Mutation();
        this.acceptance = new Acceptance();
        
    }
    
    //--------------------------------------- SELECTION --------------------------------
    
    public void SelectionElitist () {
        
        selection.elitist();
        
    }
    
    public void SelectionRoulette () {
        
        selection.roulette();
        
    }
    
    public void SelectionTournamentDeterministic () {
        
        selection.tournamentDeterministic();
        
    }
    
    public void SelectionTournamentDeterministic (int numParticipants) {
        
        selection.tournamentDeterministic(numParticipants);
        
    }
    
    public void SelectionRanking () {
        
        selection.ranking();
        
    }
    
    //--------------------------------------- CROSSOVER --------------------------------
 
            
    public Chromosome[] CrossoverOnePoint (int[] posParents, Chromosome[] cromosomaChildren) {
        
        return crossover.onePoint(posParents, cromosomaChildren);
        
    }
    
    public Chromosome[] CrossoverTwoPoints(int posParents[], Chromosome cromosomaChildren[]) {
        
        return crossover.twoPoints(posParents, cromosomaChildren);
        
    }
    
    public Chromosome[] Crossoveruniform (int posParents[], Chromosome cromosomaChildren[]) {
        
        return crossover.uniform(posParents, cromosomaChildren);
        
    }
    
    //--------------------------------------- MUTATION --------------------------------
    
    public Chromosome[] MutationInvertGenes (Chromosome[] cromosomaChildren) {
        
        return mutation.invertGenes(cromosomaChildren);
        
    }
    
    public Chromosome[] MutationExchangeOrder (Chromosome[] cromosomaChildren) {
        
        return mutation.exchangeOrder(cromosomaChildren);
        
    }
    
    public Chromosome[] MutationModificationGenes(Chromosome[] cromosomaChildren, int numModifications) {
        
        return mutation.modificationGenes(cromosomaChildren,numModifications);
        
    }
    
    public Chromosome[] MutationModificationGenes(Chromosome[] cromosomaChildren) {

        return mutation.modificationGenes(cromosomaChildren);
        
    }
    
    //--------------------------------------- ACCEPTANCE --------------------------------
    
    public void AcceptanceTotal(Chromosome[] cromosomaChildren) {

        acceptance.total(cromosomaChildren);
        
    }
    
    public void AcceptanceImprovement (Chromosome[] cromosomaChildren) {

        acceptance.improvement(cromosomaChildren);
        
    }
    
    public void AcceptanceImprovementWithParents (Chromosome[] cromosomaChildren, int[] posParents) {

        acceptance.improvementWithParents(cromosomaChildren,posParents);
        
    }
    
    public void AcceptanceTournament (Chromosome[] cromosomaChildren, int numParticipants) {

        acceptance.tournament(cromosomaChildren,numParticipants);
        
    }
    
    public void AcceptanceTournament (Chromosome[] cromosomaChildren) {

        acceptance.tournament(cromosomaChildren);
        
    }   

    
}
