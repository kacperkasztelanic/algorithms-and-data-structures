package laboratorium.lista3.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import laboratorium.lista2.Iterator;
import laboratorium.lista2.List;
import laboratorium.lista3.Results;
import laboratorium.lista3.exceptions.EmptyStackException;

public class RPNCalculatorMyDataStructures
{
	private String file;
	private String resultsFile;
	private List formulas;

	public RPNCalculatorMyDataStructures()
	{
		formulas = new ArrayList();
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
		List incorrectFormulas = new ArrayList();
		List results = new ArrayList();
		Iterator it = formulas.iterator();
		it.first();
		while (!it.isDone())
		{
			try
			{
				results.add(RPNMyStack.solve((String) it.current()));
				it.next();
			}
			catch (EmptyStackException | NumberFormatException e)
			{
				System.err.println("Incorrect formula format: " + (String) it.current());
				incorrectFormulas.add((String) it.current());
				it.next();
			}
		}

		StringBuilder report = new StringBuilder();
		it = results.iterator();
		it.first();
		while (!it.isDone())
		{
			report.append(((Results) it.current()).toString());
			report.append(System.lineSeparator());
			it.next();
		}
		if (!incorrectFormulas.isEmpty())
		{
			report.append("Could not evaluate the following formulas:");
			report.append(System.lineSeparator());
			it = incorrectFormulas.iterator();
			it.first();
			while (!it.isDone())
			{
				report.append(it.current());
				report.append(System.lineSeparator());
				it.next();
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
		RPNCalculatorMyDataStructures calc = new RPNCalculatorMyDataStructures();
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
					System.out.println(RPNMyStack.solve(args[0]).getResult());
				}
				catch (EmptyStackException | NumberFormatException e)
				{
					System.err.println("Incorrect formula format: " + args[0]);
				}
				return;
			}
			else
			{
				System.out.println("Nothing to evaluate. Please specify the file with the formulas. Help: java "
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
