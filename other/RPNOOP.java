package laboratorium.lista3.other;

import java.util.Stack;

public class RPNOOP
{
	private String formula;
	private String[] elements;
	Stack<Double> stack;

	public RPNOOP(String formula)
	{
		this.formula = formula;
		stack = new Stack<>();
	}

	private void splitFormula()
	{
		formula = formula.trim();
		formula = formula.replaceAll("\\s+", " ");
		elements = formula.split(" ");
	}

	public double solve()
	{
		splitFormula();
		int i = 0;
		while (i < elements.length)
		{
			while (elements[i].charAt(0) > 47 && elements[i].charAt(0) < 58)
			{
				double q = Double.parseDouble(elements[i]);
				stack.push(q);
				i++;
			}
			switch (elements[i++].charAt(0))
			{
				case '+':
				{
					double q = stack.pop();
					double w = stack.pop();
					stack.push(w + q);
					break;
				}
				case '-':
				{
					double q = stack.pop();
					double w = stack.pop();
					stack.push(w - q);
					break;
				}
				case '*':
				{
					double q = stack.pop();
					double w = stack.pop();
					stack.push(w * q);
					break;
				}
				case '/':
				{
					double q = stack.pop();
					double w = stack.pop();
					stack.push(w / q);
					break;
				}
				case '^':
				{
					double q = stack.pop();
					double w = stack.pop();
					stack.push(Math.pow(w, q));
					break;
				}
				case 'l':
				{
					double q = stack.pop();
					stack.push(Math.log10(q));
					break;
				}
				default:
					break;
			}
		}
		return stack.pop();
	}

	public String getFormula()
	{
		return formula;
	}

	public void setFormula(String formula)
	{
		this.formula = formula;
	}

	public String toString()
	{
		return formula;
	}

	public static void main(String[] args)
	{
		String formula = "12 2 3 4 * 10 5 / + * +";
		// String formula = "1000 l";
		// String formula = "7 3 + 5 2 - 2 ^ *";
		// String formula = "5 620 + 1 2 / ^";
		// String formula = "2 3 + 5 *";
		// String formula = "77 3 + 5 2 - *";
		RPNOOP onp = new RPNOOP(formula);
		System.out.println(onp.solve());
	}
}
