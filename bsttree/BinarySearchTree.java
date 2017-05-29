package laboratorium.lista7.bsttree;

import java.util.Comparator;

public class BinarySearchTree<T>
{
	protected BSTNode<T> root;
	private final Comparator<? super T> comparator;
	private StringBuilder sb = new StringBuilder();

	public BinarySearchTree()
	{
		this.comparator = null;
	}

	public BinarySearchTree(Comparator<? super T> comparator)
	{
		this.comparator = comparator;
	}

	@SuppressWarnings("unchecked")
	private int compare(T t1, T t2)
	{
		if (comparator != null)
			return comparator.compare(t1, t2);
		else if (t1 instanceof Comparable<?>)
			return ((Comparable<T>) t1).compareTo(t2);
		else
			throw new IllegalArgumentException(
					"Variable type must implement Comparable interface or instance of Comparator class must be provided.");
	}

	public T find(T value)
	{
		BSTNode<T> node = search(value);
		return node != null ? node.value : null;
	}

	private BSTNode<T> search(T value)
	{
		BSTNode<T> node = root;
		int cmp = 0;
		while (node != null && (cmp = compare(value, node.value)) != 0)
			node = cmp < 0 ? node.left : node.right;
		return node;
	}

	public void insert(T value)
	{
		root = insert(value, root);
	}

	private BSTNode<T> insert(T value, BSTNode<T> node)
	{
		if (node == null)
			node = new BSTNode<>(value);
		else
		{
			int cmp = compare(value, node.value);
			if (cmp < 0)
				node.left = insert(value, node.left);
			else if (cmp > 0)
				node.right = insert(value, node.right);
		}
		return node;
	}

	public void delete(T value)
	{
		root = delete(value, root);
	}

	private BSTNode<T> delete(T value, BSTNode<T> node)
	{
		if (node == null)
			// throw new ItemNotFoundException(value.toString());
			return null;
		else
		{
			int cmp = compare(value, node.value);
			if (cmp < 0)
				node.left = delete(value, node.left);
			else if (cmp > 0)
				node.right = delete(value, node.right);
			else if (node.left != null && node.right != null)
				node.right = detachMin(node.right, node);
			else
				node = (node.left != null) ? node.left : node.right;
		}
		return node;
	}

	private BSTNode<T> detachMin(BSTNode<T> t, BSTNode<T> del)
	{
		if (t.left != null)
			t.left = detachMin(t.left, del);
		else
		{
			del.value = t.value;
			t = t.right;
		}
		return t;
	}

	public String toString()
	{
		sb.setLength(0);
		sb.append('[');
		traverseAscending(root);
		if (sb.length() >= 3)
			sb.setLength(sb.length() - 2);
		sb.append(']');
		return sb.toString();
	}

	private void traverseAscending(BSTNode<T> root)
	{
		if (root != null)
		{
			traverseAscending(root.left);
			sb.append(root.value).append(", ");
			traverseAscending(root.right);
		}
	}

	public Iterator<T> inOrderIterator()
	{
		return new BSTIterator<T>(this, TraversalMode.INORDER);
	}

	public Iterator<T> preOrderIterator()
	{
		return new BSTIterator<T>(this, TraversalMode.PREORDER);
	}

	public Iterator<T> postOrderIterator()
	{
		return new BSTIterator<T>(this, TraversalMode.POSTORDER);
	}
}
