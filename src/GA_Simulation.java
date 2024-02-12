//import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Comparator;

public class GA_Simulation {
    int n;
    int k;
    int r;
    int c_0;
    int c_max;
    double m;
    int g;

    public GA_Simulation(int n, int k, int r, int c_0, int c_max, double m, int g){
        this.n = n;
        this.k = k;
        this.r = r;
        this.c_0 = c_0;
        this.c_max = c_max;
        this.m = m;
        this.g = g;
    }

    /**
     * Sorts population by fitness score, best first
     * @param pop: ArrayList of Individuals in the current generation
     * returns an ArrayList of Individuals sorted by fitness
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

    /**
     * Initializes a population of n individuals
     * @return population: an ArrayList of the initialized population
     */
    public ArrayList<Individual> init(){
        ArrayList<Individual> population = new ArrayList<>();
        while(population.size() < n){
            population.add(new Individual(c_0, g));
        }
        return population;
    }

    /**
     * Sorts the population by fitness score, then makes a new generation using only the gene pool of the fittest k individuals
     * @param pop
     * @return pop: Arraylist of the new generation of individuals
     */
    public ArrayList<Individual> evolve(ArrayList<Individual> pop){
        rankPopulation(pop);
        Individual[] winners = new Individual[k];

        for(int i = 0; i < k; i++){
            winners[i] = pop.get(i);
        }

        for(int i = 0; i < pop.size(); i++){
            pop.set(i, new Individual(winners[(int)(Math.random()*winners.length)], winners[(int)(Math.random()*winners.length)], c_max, m));
            rankPopulation(pop);
        }
        return pop;
    }

    /**
     * Prints out the fitness score of the fittest adn kth inidivuals and their chromosomes
     * @param pop
     */
    public void describeGeneration(ArrayList<Individual> pop){
        System.out.println("|  fittest individual: " + pop.get(0).getFitness());
        System.out.println("|                      " + pop.get(0).toString());
        System.out.println("|  kth individual:     " + pop.get(k).getFitness());
        System.out.println("|                      " + pop.get(k).toString() + "\n------------------------------------\n\n");

    }

    /**
     * Method that runs the simulation (calls the methods written in the class)
     * Calls init() to initialize the population, then evolves the population and describes it.
     * Then, evolves the population for r number of generations
     */
    public void run(){
        int generation = 1;
        System.out.println(generation + " ---------------------------------");
        ArrayList<Individual> population = init();
        rankPopulation(population);
        describeGeneration(population);

        while (generation < r){
            generation++;
            System.out.println(generation + " ---------------------------------");
            population = evolve(population);
            describeGeneration(population);
        }
    }

    public static void main(String[] args){
        GA_Simulation sim = new GA_Simulation(100, 15, 100, 8, 20, 0.1, 5);
        sim.run();
    }
}