package laboratorium.lista7.other.visualisation;

import java.util.LinkedList;
import java.util.Queue;

public class Tree<T extends Comparable<T>>
{

	private Node<T> root;
	private int size;

	public Tree()
	{
		root = null;
		size = 0;
	}

	public boolean find(T data)
	{
		Node<T> current = root;
		int n;
		while (current != null)
		{
			n = data.compareTo(current.data);
			if (n == 0)
			{
				return true;
			}
			else if (n < 0)
			{
				current = current.left;
			}
			else
			{
				current = current.right;
			}
		}
		return false;
	}

	public boolean delete(T data)
	{
		Node<T> parent = root;
		Node<T> current = root;
		boolean isLeftChild = false;
		int n;
		while ((n = data.compareTo(current.data)) != 0)
		{
			parent = current;
			if (n > 0)
			{
				isLeftChild = true;
				current = current.left;
			}
			else
			{
				isLeftChild = false;
				current = current.right;
			}
			if (current == null)
			{
				return false;
			}
		}
		if (current.left == null && current.right == null)
		{
			if (current == root)
			{
				root = null;
			}
			if (isLeftChild)
			{
				parent.left = null;
			}
			else
			{
				parent.right = null;
			}
		}
		else if (current.right == null)
		{
			if (current == root)
			{
				root = current.left;
			}
			else if (isLeftChild)
			{
				parent.left = current.left;
			}
			else
			{
				parent.right = current.left;
			}
		}
		else if (current.left == null)
		{
			if (current == root)
			{
				root = current.right;
			}
			else if (isLeftChild)
			{
				parent.left = current.right;
			}
			else
			{
				parent.right = current.right;
			}
		}
		else if (current.left != null && current.right != null)
		{
			Node<T> successor = getSuccessor(current);
			if (current == root)
			{
				root = successor;
			}
			else if (isLeftChild)
			{
				parent.left = successor;
			}
			else
			{
				parent.right = successor;
			}
			successor.left = current.left;
		}
		size--;
		return true;
	}

	public Node<T> getSuccessor(Node<T> deleteNode)
	{
		Node<T> successsor = null;
		Node<T> successsorParent = null;
		Node<T> current = deleteNode.right;
		while (current != null)
		{
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		if (successsor != deleteNode.right)
		{
			successsorParent.left = successsor.right;
			successsor.right = deleteNode.right;
		}
		return successsor;
	}

	public void insert(T data)
	{
		Node<T> newNode = new Node<>(data);
		if (root == null)
		{
			root = newNode;
			size++;
			return;
		}
		Node<T> current = root;
		Node<T> parent;
		int n;
		boolean insert = true;
		while (insert)
		{
			n = data.compareTo(current.data);
			parent = current;
			if (n == 0)
			{
				return;
			}
			else if (n < 0)
			{
				current = current.left;
				if (current == null)
				{
					parent.left = newNode;
					insert = false;
				}
			}
			else
			{
				current = current.right;
				if (current == null)
				{
					parent.right = newNode;
					insert = false;
				}
			}
		}
		size++;
	}

	public int size()
	{
		return size;
	}

	private void display(Node<T> root, StringBuilder stb)
	{
		if (root != null)
		{
			display(root.left, stb);
			stb.append("\n  - ").append(root.data);
			display(root.right, stb);
		}
	}

	public String toString()
	{
		StringBuilder stb = new StringBuilder("Tree, size - ").append(size).append(": ");
		display(root, stb);
		return stb.append("\n").toString();
	}

	private void preOrder(Node<T> node)
	{
		if (node != null)
		{
			System.out.print(node.data);
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	public void preOrder()
	{
		preOrder(root);
	}

	private void inOrder(Node<T> node)
	{
		if (node != null)
		{
			inOrder(node.left);
			System.out.print(node.data);
			inOrder(node.right);
		}
	}

	public void inOrder()
	{
		inOrder(root);
	}

	private void postOrder(Node<T> node)
	{
		if (node != null)
		{
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node.data);
		}
	}

	public void postOrder()
	{
		postOrder(root);
	}

	public void levelOrder()
	{
		Queue<Node<T>> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty())
		{
			Node<T> node = queue.poll();
			System.out.print(node.data);
			if (node.left != null)
				queue.offer(node.left);
			if (node.right != null)
				queue.offer(node.right);
		}
	}

	public void print()
	{
		BTreePrinter.printNode(root);
	}

	public void DSW()
	{
		if (null != root)
		{
			createBackbone();// effectively: createBackbone( root)
			createPerfectBST();// effectively: createPerfectBST( root)
		}
	}

	/**
	 * Time complexity: O(n)
	 */
	private void createBackbone()
	{
		Node<T> grandParent = null;
		Node<T> parent = root;
		Node<T> leftChild;

		while (null != parent)
		{
			leftChild = parent.left;
			if (null != leftChild)
			{
				grandParent = rotateRight(grandParent, parent, leftChild);
				parent = leftChild;
			}
			else
			{
				grandParent = parent;
				parent = parent.right;
			}
		}
	}

	/************************************************************************
	 * Before After Gr Gr \ \ Par Ch / \ / \ Ch Z X Par / \ / \ X Y Y Z
	 ***********************************************************************/
	private Node<T> rotateRight(Node<T> grandParent, Node<T> parent, Node<T> leftChild)
	{
		if (null != grandParent)
		{
			grandParent.right = leftChild;
		}
		else
		{
			root = leftChild;
		}
		parent.left = leftChild.right;
		leftChild.right = parent;
		return grandParent;
	}

	/**
	 * Time complexity: O(n)
	 */
	private void createPerfectBST()
	{
		int n = 0;
		for (Node<T> tmp = root; null != tmp; tmp = tmp.right)
		{
			n++;
		}
		// m = 2^floor[lg(n+1)]-1, ie the greatest power of 2 less than n: minus
		// 1
		int m = greatestPowerOf2LessThanN(n + 1) - 1;
		makeRotations(n - m);

		while (m > 1)
		{
			makeRotations(m /= 2);
		}
	}

	/**
	 * Time complexity: log(n)
	 */
	private int greatestPowerOf2LessThanN(int n)
	{
		int x = MSB(n);// MSB
		return (1 << x);// 2^x
	}

	/**
	 * Time complexity: log(n) return the index of most significant set bit:
	 * index of least significant bit is 0
	 */
	public int MSB(int n)
	{
		int ndx = 0;
		while (1 < n)
		{
			n = (n >> 1);
			ndx++;
		}
		return ndx;
	}

	private void makeRotations(int bound)
	{
		Node<T> grandParent = null;
		Node<T> parent = root;
		Node<T> child = root.right;
		for (; bound > 0; bound--)
		{
			try
			{
				if (null != child)
				{
					rotateLeft(grandParent, parent, child);
					grandParent = child;
					parent = grandParent.right;
					child = parent.right;
				}
				else
				{
					break;
				}
			}
			catch (NullPointerException convenient)
			{
				break;
			}
		}
	}

	private void rotateLeft(Node<T> grandParent, Node<T> parent, Node<T> rightChild)
	{
		if (null != grandParent)
		{
			grandParent.right = rightChild;
		}
		else
		{
			root = rightChild;
		}
		parent.right = rightChild.left;
		rightChild.left = parent;
	}

	static class Node<T>
	{
		T data;
		Node<T> left;
		Node<T> right;

		public Node(T data)
		{
			this.data = data;
			left = null;
			right = null;
		}
	}
}