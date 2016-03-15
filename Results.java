package laboratorium.lista3;

/**
 * 
 * @author Kacper
 *
 */
public class Results
{
	private String formula;
	private double result;

	public Results(String formula, double result)
	{
		this.formula = formula;
		this.result = result;
	}

	public String getFormula()
	{
		return formula;
	}

	public void setFormula(String formula)
	{
		this.formula = formula;
	}

	public double getResult()
	{
		return result;
	}

	public void setResult(double result)
	{
		this.result = result;
	}

	public String toString()
	{
		return String.format("Formula: %-30s Solution: %.3f", formula, result);
	}
}
