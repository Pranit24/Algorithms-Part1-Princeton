import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.*;

public class Permutation{
	public static void main(String[] args) {
		RandomizedQueue<String> str = new RandomizedQueue<String>();
		while(!StdIn.isEmpty())
		{
			str.enqueue(StdIn.readString());
		}

		int k = Integer.parseInt(args[0]);
		for(int i = 0; i<k;i++){
			StdOut.println(str.dequeue());
		}
		
	}
}