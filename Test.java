package laboratorium.lista3;

import laboratorium.lista3.exceptions.EmptyQueueException;
import laboratorium.lista3.exceptions.EmptyStackException;
import laboratorium.lista3.exceptions.FullQueueException;
import laboratorium.lista3.generics.GenericArrayStack;
import laboratorium.lista3.generics.GenericLinkedQueue;
import laboratorium.lista3.generics.GenericQueue;
import laboratorium.lista3.generics.GenericStack;

/**
 * 
 * @author Kacper
 *
 */
public class Test
{
	public static String testQueueTC(int enqueue, int dequeue, int queueSize)
	{
		GenericQueue<Vehicle> queue = new GenericLinkedQueue<>(queueSize);
		for (int i = 0; i < enqueue; i++)
		{
			try
			{
				queue.enqueue(new Vehicle(i + 1, 1990 + i));
			}
			catch (FullQueueException e)
			{
				System.out.println("Queue is full!");
			}
		}
		for (int i = 0; i < dequeue; i++)
		{
			try
			{
				queue.dequeue();
			}
			catch (EmptyQueueException e)
			{
				System.out.println("Queue is empty!");
			}
		}
		return queue.toString();
	}

	public static String testStackTC(int push, int pop, int stackSize)
	{
		GenericStack<Vehicle> stack = new GenericArrayStack<>(stackSize);
		for (int i = 0; i < push; i++)
			stack.push(new Vehicle(i + 1, 1990 + i));
		for (int i = 0; i < pop; i++)
		{
			try
			{
				stack.pop();
			}
			catch (EmptyStackException e)
			{
				System.out.println("Stack is empty!");
			}
		}
		return stack.toString();
	}

	public static void main(String[] args)
	{
		System.out.println(testStackTC(3, 1, 3));
		System.out.println(testQueueTC(3, 1, 3));
	}
}
