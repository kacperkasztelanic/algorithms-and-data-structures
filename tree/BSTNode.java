package laboratorium.lista7.tree;

public class BSTNode<T>
{
	protected T value;
	protected BSTNode<T> left;
	protected BSTNode<T> right;

	public BSTNode(T value)
	{
		this.value = value;
		left = null;
		right = null;
	}

	public T getValue()
	{
		return value;
	}

	public void setValue(T value)
	{
		this.value = value;
	}

	public BSTNode<T> getLeft()
	{
		return left;
	}

	public void setLeft(BSTNode<T> left)
	{
		this.left = left;
	}

	public BSTNode<T> getRight()
	{
		return right;
	}

	public void setRight(BSTNode<T> right)
	{
		this.right = right;
	}

	public String toString()
	{
		return value.toString();
	}
}
