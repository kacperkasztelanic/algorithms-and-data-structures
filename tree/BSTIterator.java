package laboratorium.lista7.tree;

import java.util.Stack;

public final class BSTIterator<E> implements Iterator<E>
{
	private Stack<BSTNode<E>> stack = new Stack<>();
	private BinarySearchTree<E> bst;
	private TraversalMode mode;

	public BSTIterator(BinarySearchTree<E> bst, TraversalMode mode)
	{
		this.bst = bst;
		this.mode = mode;
	}

	public void first()
	{
		switch (mode)
		{
			case INORDER:
				inOrderFirst();
				break;
			case PREORDER:
				preOrderFirst();
				break;
			case POSTORDER:
				postOrderFirst();
				break;
		}
	}

	private void inOrderFirst()
	{
		stack.clear();
		pushLeftChildren(bst.root);
	}

	private void preOrderFirst()
	{
		stack.clear();
		if (bst.root != null)
			stack.push(bst.root);
	}

	private void postOrderFirst()
	{
		stack.clear();
		findNextLeaf(bst.root);
	}

	private void findNextLeaf(BSTNode<E> current)
	{
		while (current != null)
		{
			stack.push(current);
			if (current.left != null)
				current = current.left;
			else
				current = current.right;
		}
	}

	public void next()
	{
		switch (mode)
		{
			case INORDER:
				inOrderNext();
				break;
			case POSTORDER:
				postOrderNext();
				break;
			case PREORDER:
				preOrderNext();
				break;
		}
	}

	private void pushLeftChildren(BSTNode<E> current)
	{
		while (current != null)
		{
			stack.push(current);
			current = current.left;
		}
	}

	private void inOrderNext()
	{
		pushLeftChildren(stack.pop().right);
	}

	private void postOrderNext()
	{
		BSTNode<E> temp = stack.pop();
		if (!stack.isEmpty())
		{
			BSTNode<E> top = stack.peek();
			if (temp == top.left)
				findNextLeaf(top.right);
		}
	}

	private void preOrderNext()
	{
		if (stack.peek().left != null)
			stack.push(stack.peek().left);
		else
		{
			BSTNode<E> temp = stack.pop();
			while (temp.right == null && !stack.isEmpty())
				temp = stack.pop();
			if (temp.right != null)
				stack.push(temp.right);
		}
	}

	public E current()
	{
		return stack.peek().value;
	}

	public boolean isDone()
	{
		return stack.isEmpty();
	}
}
