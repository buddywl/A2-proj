import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.lang.Math;
//test
public class Individual {

    /**
     * Chromosome stores the individual's genetic data as an ArrayList of characters
     * Each character represents a gene
     */
    ArrayList<Character> chromosome;
    
    /**
     * Chooses a letter at random, in the range from A to the number of states indicated
     * @param num_letters The number of letters available to choose from (number of possible states)
     * @return The letter as a Character
     */
    private Character randomLetter(int num_letters) {
        return Character.valueOf((char)(65+ThreadLocalRandom.current().nextInt(num_letters)));
      }
    
    /** 
     * Method to determine whether a given gene will mutate based on the parameter ***m***
     * @param m the mutation rate
     * @return true if a number randomly chosen between 0 and 1 is less than ***m***, else false
    */
    private Boolean doesMutate(float m){
        float randomNum = ThreadLocalRandom.current().nextInt(0, 1);
        return randomNum < m;
    }

    /**
     * Expresses the individual's chromosome as a String, for display purposes
     * @return The chromosome as a String
     */
    public String toString() {
        StringBuilder builder = new StringBuilder(chromosome.size());
        for(Character ch: chromosome) {
          builder.append(ch);
        }
        return builder.toString();
      }

    /** 
     * Initial constructor to generate initial population members
     * @param c_0 The initial chromosome size
     * @param g The number of letters available to choose from
     */
    public Individual(int c_0, int g, int n) {
        chromosome = new ArrayList<Character>();

        for(int i = 0; i < c_0; i++){
            chromosome.add(randomLetter(g));
        }
    }


    /**
      * Second constructor to create parents and offspring chromosomes
      * @param parent1 The first parent chromosome
      * @param parent2 The second parent chromosome
      * @param c_max The maximum chromosome size
      * @param m The chances per round of mutation in each gene
      */
    public Individual(Individual parent1, Individual parent2, int c_max, double m) {
      // fill in
        chromosome = new ArrayList<Character>();

        String prefix = parent1.toString().substring(0, (int)(Math.random()*parent1.toString().length()));
        String suffix = parent2.toString().substring((int)(Math.random()*parent2.toString().length()));

        String fin = prefix + suffix;

        if(fin.length() > c_max){
            fin = fin.substring(0, c_max);
        }

        String[] strSplit = fin.split("");
        for(int i = 0; i < strSplit.length; i++){
            chromosome.add(i, strSplit[i].charAt(0));
        }
    }

    /**
     * Calculates the fitness score of each chromosome
     * @return The fitness score as an int
     */
    public int getFitness() {
        // fill in
        // remove the return below and write your own
        return 0;
    }

    public static void main(String[] args){
        Individual sam = new Individual(8, 5, 1);
        Individual fred = new Individual(8, 5, 1);

        Individual shawty = new Individual(sam, fred, 8, 2);

        System.out.println(sam.toString());
        System.out.println(fred.toString());
        System.out.println(shawty.toString());
    }
    
}
