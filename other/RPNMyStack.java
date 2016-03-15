package laboratorium.lista3.other;

import laboratorium.lista3.Results;
import laboratorium.lista3.generics.GenericArrayStack;
import laboratorium.lista3.generics.GenericStack;

public class RPNMyStack
{
	private RPNMyStack()
	{
	}

	public static Results solve(String formula)
	{
		formula = formula.trim();
		formula = formula.replaceAll("\\s+", " ");
		String[] elements = formula.split(" ");
		GenericStack<Double> stack = new GenericArrayStack<>();
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
				case 's':
				{
					double q = stack.pop();
					stack.push(Math.sqrt(q));
					break;
				}
				default:
					break;
			}
		}
		return new Results(formula, stack.pop());
	}
}
