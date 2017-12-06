import edu.princeton.cs.algs4.StdRandom;
import java.util.*;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item[] a;
   private int N;


   public RandomizedQueue()                 // construct an empty randomized queue
   {
   	a = (Item[]) new Object[2];
   }


   public boolean isEmpty()                 // is the randomized queue empty?
   {
   	return N==0;
   }


   public int size()                        // return the number of items on the randomized queue
   {
   	return N;
   }


   public void enqueue(Item item)           // add the item
   {
   	if(item==null)
   	{
   		throw new IllegalArgumentException();
   	}

   	if(N==a.length)
   	{
   		resize(2*a.length);
   	}

   	a[N++]=item;
   }


   private void resize(int capacity)
   {
   	Item[] temp = (Item[]) new Object[capacity];
   	for(int i=0; i< N;i++)
   	{
   		temp[i] = a[i];
   	}
   	a = temp;
   }


   private void checkNotEmpty()
   {
   	if(isEmpty())
   	{
   		throw new NoSuchElementException();
   	}
   }


   public Item dequeue()                    // remove and return a random item
   {
   	checkNotEmpty();
   	int index = StdRandom.uniform(N);
   	Item item = a[index];
   	a[index] = a[N-1];
      N--;
   	if (N > 0 && N == a.length/4) 
   		{
   			resize(a.length/2);
   		}
      return item;
   }


   public Item sample()                     // return a random item (but do not remove it)
   {
   	checkNotEmpty();
   	int index = StdRandom.uniform(N);
   	return a[index];
   }


   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   {
   	return new RandomizedQueueIterator();
   }

   private class RandomizedQueueIterator implements Iterator<Item>
   {
   	private int i = N;
   	private Item[] r;

   	public RandomizedQueueIterator()
   	{
   		r = (Item[]) new Object[N];
   		for(int j=0; j< N;j++)
   			{
   				r[j] = a[j];
   			}
   			StdRandom.shuffle(r);
   	}

   	public boolean hasNext()
   	{
   		return i >0;
   	}
   	public void remove()
   	{
   		throw new UnsupportedOperationException();
   	}

   	public Item next()
   	{
   		return r[--i];
   	}
   }
   // public static void main(String[] args)   // unit testing (optional)
}