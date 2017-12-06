import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{
	private boolean perc[][];
	private int N;
	private WeightedQuickUnionUF weightedUnion;
	private int num;

	public Percolation(int N){
		if(N <=0){
			throw new IllegalArgumentException();
		}
		this.N = N;
		perc = new boolean[N+1][N+1];
		weightedUnion = new WeightedQuickUnionUF(1 + N*N + 1);

		for(int i =0; i<=N; i++){// Connect the first row to 0 and last row to N*N+1
			weightedUnion.union(0,i);

			weightedUnion.union( N * N -i +1, N * N +1);
		}
	}

	private void validate(int i, int j){ // make sure that i and j are in bound of N
		if (i < 1 || i > N){
            throw new IllegalArgumentException("row index i out of bounds");
		}
        if (j < 1 || j > N){
            throw new IllegalArgumentException("column index j out of bounds");
        }
	}

	private int xyto1D(int i, int j){
		return (i-1)*N + j; // 
	}

	public boolean isOpen(int i, int j){ //check if i and j are connected
		validate(i,j);
		return perc[i][j];
	}

	private void connectTop(int i, int j){
		if (i == 1){return;} //FIrst is 1 not 0

		if (isOpen(i-1, j)){

			weightedUnion.union(xyto1D(i,j),xyto1D(i-1,j));
		}
	}

	private void connectRight(int i, int j){
		if (j == N){return;} 

		if (isOpen(i, j+1)){

			weightedUnion.union(xyto1D(i,j),xyto1D(i,j+1));
		}
	}

	private void connectLeft(int i, int j){
		if (j == 1){return;}

		if (isOpen(i, j-1)){

			weightedUnion.union(xyto1D(i,j),xyto1D(i,j-1));
		}
	}

	private void connectBottom(int i, int j){
		if (i == N){return;} //last row already connected

		if (isOpen(i+1, j)){

			weightedUnion.union(xyto1D(i,j),xyto1D(i+1,j));
		}
	}

 	public     int numberOfOpenSites(){
 		return num;
 	}

	public void open(int i , int j){
		validate(i,j);
		if (isOpen(i,j)){
			return;
		}
		num++;
		perc[i][j] = true;	
		connectBottom(i, j);
		connectTop(i, j);
		connectRight(i, j);
		connectLeft(i, j);
	}

	public boolean isFull(int i, int j){
		validate(i,j);
		if (!isOpen(i, j)) {
            return false;
        }
		
		int site = xyto1D(i,j);
		return weightedUnion.connected(0,site);
	}

	public boolean percolates(){
		return weightedUnion.connected(0, N * N + 1);
	}
}		
	