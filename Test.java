package laboratorium.lista3;

import laboratorium.lista3.generics.GenericArrayStack;
import laboratorium.lista3.generics.GenericLinkedQueue;

public class Test
{
	public static void trollTestQueue()
	{
		LinkedQueue queue = new LinkedQueue(3);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		queue.enqueue(5);
		queue.enqueue(6);
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		queue.enqueue(5);
		System.out.println(queue.dequeue());
	}

	public static void trollTestGenericQueue()
	{
		GenericLinkedQueue<Vehicle> queue = new GenericLinkedQueue<>(3);
		queue.enqueue(new Vehicle(1, 2000));
		queue.enqueue(new Vehicle(2, 2000));
		queue.enqueue(new Vehicle(3, 2000));
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		queue.enqueue(new Vehicle(4, 2000));
		queue.enqueue(new Vehicle(5, 2000));
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		queue.enqueue(new Vehicle(6, 2000));
		System.out.println(queue.dequeue());
	}

	public static void trollTestStack()
	{
		ArrayStack stack = new ArrayStack(3);
		System.out.println("Size: " + stack.size() + "|Top: " + stack.getTop());
		stack.push(1);
		System.out.println("Pushed: " + 1 + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		stack.push(2);
		System.out.println("Pushed: " + 2 + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		System.out.println("Zdjêto: " + stack.pop() + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		System.out.println("Zdjêto: " + stack.pop() + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		stack.push(3);
		System.out.println("Pushed: " + 3 + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		stack.push(4);
		System.out.println("Pushed: " + 4 + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		stack.push(5);
		System.out.println("Pushed: " + 5 + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		System.out.println("Zdjêto: " + stack.pop() + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		System.out.println("Zdjêto: " + stack.pop() + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		System.out.println("Zdjêto: " + stack.pop() + "|Size: " + stack.size() + "|Top: " + stack.getTop());
	}

	public static void trollTestGenericStack()
	{
		GenericArrayStack<Integer> stack = new GenericArrayStack<>(3);
		System.out.println("Size: " + stack.size() + "|Top: " + stack.getTop());
		stack.push(1);
		System.out.println("Pushed: " + 1 + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		stack.push(2);
		System.out.println("Pushed: " + 2 + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		System.out.println("Zdjêto: " + stack.pop() + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		System.out.println("Zdjêto: " + stack.pop() + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		stack.push(3);
		System.out.println("Pushed: " + 3 + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		stack.push(4);
		System.out.println("Pushed: " + 4 + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		stack.push(5);
		System.out.println("Pushed: " + 5 + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		System.out.println("Zdjêto: " + stack.pop() + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		System.out.println("Zdjêto: " + stack.pop() + "|Size: " + stack.size() + "|Top: " + stack.getTop());
		System.out.println("Zdjêto: " + stack.pop() + "|Size: " + stack.size() + "|Top: " + stack.getTop());
	}

	public static void main(String[] args)
	{
		trollTestGenericQueue();
	}

}
