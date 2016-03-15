package laboratorium.lista3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * 
 * @author Kacper
 *
 */
public class RPNCalculator
{
	private String file;
	private String resultsFile;
	private List<String> formulas;

	public RPNCalculator()
	{
		formulas = new ArrayList<>();
	}

	private void generateResultsFileName()
	{
		if (file.contains("."))
			resultsFile = file.substring(0, file.lastIndexOf('.')) + "-results" + file.substring(file.lastIndexOf('.'));
		else
		{
			resultsFile = file + "-results";
		}
	}

	private boolean readFromFile()
	{
		try (BufferedReader br = new BufferedReader(new FileReader(file)))
		{
			String line;
			while ((line = br.readLine()) != null)
				formulas.add(line);
			return true;
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Could not find file: " + file);
			return false;
		}
		catch (IOException e)
		{
			System.err.println("Error reading file: " + file);
			return false;
		}
	}

	private boolean saveToFile(String text)
	{
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(resultsFile)))
		{
			bw.write(text.toString());
			return true;
		}
		catch (IOException e)
		{
			System.err.println("Error writing to file: " + resultsFile);
			return false;
		}
	}

	private void display(String text)
	{
		System.out.println(text);
	}

	public String solveFormulas()
	{
		List<String> incorrectFormulas = new ArrayList<>();
		List<Results> results = new ArrayList<>();
		Iterator<String> it = formulas.iterator();
		while (it.hasNext())
		{
			String formula = it.next();
			try
			{
				results.add(RPN.solve(formula));
			}
			catch (EmptyStackException | NumberFormatException e)
			{
				System.err.println("Incorrect formula format: " + formula);
				incorrectFormulas.add(formula);
			}
		}

		StringBuilder report = new StringBuilder();
		Iterator<Results> it2 = results.iterator();
		while (it2.hasNext())
		{
			report.append(it2.next().toString());
			report.append(System.lineSeparator());
		}
		if (!incorrectFormulas.isEmpty())
		{
			report.append("Could not evaluate the following formulas:");
			report.append(System.lineSeparator());
			it = incorrectFormulas.iterator();
			while (it.hasNext())
			{
				report.append(it.next());
				report.append(System.lineSeparator());
			}
			report.setLength(report.length() - 1);
		}
		this.saveToFile(report.toString());
		return report.toString();
	}

	public String getFile()
	{
		return file;
	}

	public void setFile(String file)
	{
		this.file = file;
	}

	public String getResultsFile()
	{
		return resultsFile;
	}

	public void setResultsFile(String resultsFile)
	{
		this.resultsFile = resultsFile;
	}

	public static void main(String[] args)
	{
		RPNCalculator calc = new RPNCalculator();
		if (args.length > 0 && args[0].equals("-h"))
		{
			System.out.println("To calculate, type: java " + calc.getClass().getName().replaceAll("\\.", "/")
					+ " <formula>" + System.lineSeparator()
					+ "For batch mode: java -Dinput=<inputFilePath> [-Doutput=<outputFilePath>] [-Dsave=y/n] "
					+ calc.getClass().getName().replaceAll("\\.", "/"));
			return;
		}
		Properties props = System.getProperties();
		if (!props.containsKey("input"))
		{
			if (args.length == 1)
			{
				try
				{
					System.out.println(RPN.solve(args[0]).getResult());
				}
				catch (EmptyStackException | NumberFormatException e)
				{
					System.err.println("Incorrect formula format: " + args[0]);
				}
				return;
			}
			else
			{
				System.out.println(
						"Nothing to evaluate. Please specify the formula or file containing formulas. Help: java "
								+ calc.getClass().getName().replaceAll("\\.", "/") + " -h");
				return;
			}
		}
		else
			calc.setFile(props.getProperty("input"));
		if (!props.containsKey("output"))
			calc.generateResultsFileName();
		else
			calc.setResultsFile(props.getProperty("output"));
		boolean fileOk = calc.readFromFile();
		if (fileOk)
		{
			String report = calc.solveFormulas();
			System.out.println("Formulas from file: " + calc.getFile());
			calc.display(report);
			if (props.containsKey("save") && props.getProperty("save").equals("y"))
				if (calc.saveToFile(report))
					System.out.println("Results saved to: " + calc.getResultsFile());
		}
	}
}
