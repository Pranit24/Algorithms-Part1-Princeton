import java.util.*;

public class Deque<Item> implements Iterable<Item>
{
   private Node first, last;
   private int size = 0;


   private class Node{ 
   	Item item;
   	Node next;
   	Node prev;
   }

   public Deque()                           // construct an empty deque
   {}


   public boolean isEmpty()                 // is the deque empty?
   {
   	return size==0;
   }


   private void checkItemNotNull(Item item)
   {
   	if(item == null)
   	{
   		throw new IllegalArgumentException();
   	}
   }
   public int size()                        // return the number of items on the deque
   {
   	return size;
   }


   public void addFirst(Item item)          // add the item to the front
   {
   	checkItemNotNull(item);
   	Node oldFirst = first;
   	first = new Node();
   	first.item = item;
   	first.next = oldFirst;
   	if(size>0)
   	{
   		oldFirst.prev = first;
   	}
   	else
   	{
   		last = first;
   	}
   	size++;
   }


   public void addLast(Item item)           // add the item to the end
   {
   	checkItemNotNull(item);
   	Node oldLast = last;
   	last = new Node();
   	last.item = item;
   	last.prev = oldLast;
   	if(size>0)
   	{
   		oldLast.next = last;
   	}
   	else
   	{
   		first = last;
   	}
      size++;
   }

   private void checkForElement()
   {
   	if(isEmpty())
   	{
   		throw new NoSuchElementException();
   	}
   }

   public Item removeFirst()                // remove and return the item from the front
   {
   	checkForElement();
   	Item item = first.item;
   	if(size > 1)
   	{
   	first = first.next;
   	first.prev = null;	
   	}
   	else
   	{
   		first = null;
   		last = null;
   	}
   	size--;
   	return item;
   }


   public Item removeLast()                 // remove and return the item from the end
   {
   	checkForElement();
   	Item item = last.item;
   	if(size > 1)
   	{
   	last = last.prev;
   	last.next = null;
   	}
   	else
   	{
   		first = null;
   		last = null;
   	}
   	size--;
   	return item;
   }


   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {
   		return new ListIterator();
   }

   private class ListIterator implements Iterator<Item>
   {
   	private Node current = first;

   	public boolean hasNext()
   	{
   		return current != null;
   	}


   	public void remove()
   	{
   		throw new UnsupportedOperationException();	
   	}

   	public Item next()
   	{
   		if(current==null)
   		{
   			throw new NoSuchElementException();
   		}
   		Item item = current.item;
   		current = current.next;
   		return item;
   	}
   }

}