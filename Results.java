package laboratorium.lista5;

public class Results
{
	private double[] times;
	private double average;

	public Results(double[] times, double average)
	{
		this.times = times;
		this.average = average;
	}

	public double[] getTimes()
	{
		return times;
	}

	public void setTimes(double[] times)
	{
		this.times = times;
	}

	public double getAverage()
	{
		return average;
	}

	public void setAverage(double average)
	{
		this.average = average;
	}

	public String toString()
	{
		return Double.toString(average);
	}
}
