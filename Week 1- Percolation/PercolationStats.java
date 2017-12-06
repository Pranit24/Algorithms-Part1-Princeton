import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats{
   private double[] trial;
   private int T;

   public PercolationStats(int N, int T)  { // perform trials independent experiments on an n-by-n grid
         if( N <=0 || T <=0){
            throw new IllegalArgumentException("N or T is less or equal to 0");
         }

         this.T = T;
         int size = N * N;
         trial = new double[T];
         for(int i =0; i<T ; i++){
            Percolation percolation = new Percolation(N);

            double opened = 0; // ratio of number of sites open to the total size N*N
            while(!percolation.percolates()){
               int row = StdRandom.uniform(N) + 1;
               int col = StdRandom.uniform(N) + 1;
               if(!percolation.isOpen(row, col)){
                  opened++;
                  percolation.open(row,col);
               }
            }
            trial[i] = opened / size;
         }
         

   }  
   public double mean(){   // sample mean of percolation threshold
         return StdStats.mean(trial);
   }                          
   public double stddev(){  // sample standard deviation of percolation threshold
         return StdStats.stddev(trial);
   }                       
   public double confidenceLo() {   // low  endpoint of 95% confidence interval
         return StdStats.mean(trial) - 1.96*StdStats.stddev(trial)/Math.sqrt(T);
   }                 
   public double confidenceHi()  {
      return StdStats.mean(trial)+ 1.96*StdStats.stddev(trial)/Math.sqrt(T);
   }


   public static void main(String[] args) {
      int N = Integer.parseInt(args[0]);
      int T = Integer.parseInt(args[1]);

      PercolationStats  percStats = new PercolationStats(N, T);
      System.out.println(percStats.mean());
      System.out.println(percStats.stddev());
      System.out.println(percStats.confidenceLo());
      System.out.println(percStats.confidenceHi());
      
   }
}