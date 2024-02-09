import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Comparator;

public class GA_Simulation {
    int n;
    int k;
    int r;
    int c_0;
    int c_max;
    float m;
    int g;

    public GA_Simulation(int n, int k, int r, int c_0, int c_max, float m, int g){
        this.n = n;
        this.k = k;
        this.r = r;
        this. c_0 = c_0;
        this.c_max = c_max;
        this.m = m;
        this.g = g;
    }

    /** Sorts population by fitness score, best first 
     * @param pop: ArrayList of Individuals in the current generation
     * @return: An ArrayList of Individuals sorted by fitness
    */
    public void rankPopulation(ArrayList<Individual> pop) {
        // sort population by fitness
        Comparator<Individual> ranker = new Comparator<>() {
          // this order will sort higher scores at the front
          public int compare(Individual c1, Individual c2) {
            return (int)Math.signum(c2.getFitness()-c1.getFitness());
          }
        };
        pop.sort(ranker); 
    }

    public void init(int n){

    }
}