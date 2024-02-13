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
     * Demonstrates the use of both Individual constructors and the getFitness() method
     * @param args: command line arguments (ignored)
     */
    public static void main(String[] args){
        Individual sam = new Individual(8, 5);
        Individual fred = new Individual(8, 5);

        Individual shawty = new Individual(sam, fred, 16, 0.1);

        System.out.println("Sam chromosome: " + sam);
        System.out.println("Fred chromosome: " + fred);
        System.out.println("Shawty chromosome: " + shawty);

        System.out.println(sam.getFitness());
        System.out.println(fred.getFitness());
        System.out.println(shawty.getFitness());
    }
    
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
//    private boolean doesMutate(float m){
//        float randomNum = ThreadLocalRandom.current().nextInt(0, 1);
//        return randomNum < m;
//    }

    private boolean doesMutate(double m){
        double randomNum = Math.random();
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
    public Individual(int c_0, int g) {             //int n???
        chromosome = new ArrayList<>();

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
        chromosome = new ArrayList<>();

        String prefix = parent1.toString().substring(0, (int)(Math.random()*parent1.toString().length()));
        String suffix = parent2.toString().substring((int)(Math.random()*parent2.toString().length()-1));
        String fin = prefix.concat(suffix);
        boolean mutate;

        if(fin.length() > c_max){
            fin = fin.substring(0, c_max);
        }

        String[] strSplit = fin.split("");
        for(int i = 0; i < strSplit.length; i++){
            mutate = doesMutate(m);
//            System.out.println(mutate);
            chromosome.add(i, strSplit[i].charAt(0));
            if(mutate){
                chromosome.set(i, randomLetter(5));
            }
        }

    }

    /**
     * Calculates the fitness score of each chromosome
     * @return The fitness score as an int
     */
    public int getFitness() {
        int fitScore = 0;

        if(chromosome.size() % 2 == 1){
            fitScore += 1;
        }

        for(int i = 0; i < chromosome.size()/2; i++){
            if (chromosome.get(i).equals((chromosome.get((chromosome.size()-1)-i)))){
                fitScore += 1;
            } else {
                fitScore -= 1;
            }
        }

        for(int i = 0; i < chromosome.size()-1; i++){
            if(chromosome.get(i).equals((chromosome.get(i+1)))){
                fitScore -= 1;
            }
        }
        return fitScore;
    }
    
}
