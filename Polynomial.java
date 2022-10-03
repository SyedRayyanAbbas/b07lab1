import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial 
{
	
	double polynomial_coefficients[];
	int polynomial_exponents []; 
	
	Polynomial () 
	{
		polynomial_coefficients = new double [1];
		polynomial_coefficients[0] = 0; 
		
		polynomial_exponents = new int [1];
		polynomial_exponents[0] = 0;
		
	}
	Polynomial (double coefficients[], int exponents[])
	{
		int length = coefficients.length;
		polynomial_coefficients = new double [length];
		for (int i = 0; i < length; i++)
		{
			polynomial_coefficients [i] = coefficients [i];
		}
		
		int length2 = exponents.length;
		polynomial_exponents = new int [length2];
		for (int i = 0; i < length2; i++)
		{
			polynomial_exponents [i] = exponents [i];
		}
	}
	
	public Polynomial add(Polynomial other)
	{
		int shorter_length; 
		int longer_length;
		int expshorter_length; 
		int explonger_length;
		
		if (polynomial_coefficients.length < other.polynomial_coefficients.length)
		{
			longer_length = other.polynomial_coefficients.length;
			shorter_length = polynomial_coefficients.length;
		}
		
		else
		{
			longer_length = polynomial_coefficients.length;
			shorter_length = other.polynomial_coefficients.length;
		}
		
		if (polynomial_exponents.length < other.polynomial_exponents.length)
		{
			explonger_length = other.polynomial_exponents.length;
			expshorter_length = polynomial_exponents.length;
		}
		
		else
		{
			explonger_length = polynomial_exponents.length;
			expshorter_length = other.polynomial_exponents.length;
		}
		
		double sum[] = new double [longer_length];
		int sum2[] = new int [explonger_length];
		
		for (int i = 0; i < shorter_length; i++)
			{
				sum[i] = polynomial_coefficients[i] + other.polynomial_coefficients[i];
			}
		if (longer_length == other.polynomial_coefficients.length)
		{
			for (int j = shorter_length; j < longer_length; j++)
			{
				sum[j] = other.polynomial_coefficients[j];
			}
		}
		else
		{
			for (int j = shorter_length; j < longer_length; j++)
			{
				sum[j] = polynomial_coefficients[j];
			}
		}
		
			
		for (int i = 0; i < expshorter_length; i++)
		{
			sum2[i] = polynomial_exponents[i] + other.polynomial_exponents[i];
		}
		if (explonger_length == other.polynomial_exponents.length)
		{
			for (int j = expshorter_length; j < explonger_length; j++)
			{
				sum2[j] = other.polynomial_exponents[j];
			}
		}
		else
		{
			for (int j = expshorter_length; j < explonger_length; j++)
			{
				sum2[j] = polynomial_exponents[j];
			}
		}
		Polynomial result = new Polynomial(sum, sum2);
		return result;
	}
	public double evaluate (double i)
	{
		double sum = 0.0;
		for (int j = 0; j < polynomial_coefficients.length; j++)
		{
			sum += polynomial_coefficients[j] * Math.pow(i, polynomial_exponents[j]);
		}
		return sum;
	}
	
	public boolean hasRoot(double x)
	{
		return evaluate(x) == 0.0;
		
	}
	
	public Polynomial multiply(Polynomial other)
	{
		int shorter_length; 
		int longer_length;
		int expshorter_length; 
		int explonger_length;
		
		if (polynomial_coefficients.length < other.polynomial_coefficients.length)
		{
			longer_length = other.polynomial_coefficients.length;
			shorter_length = polynomial_coefficients.length;
		}
		
		else
		{
			longer_length = polynomial_coefficients.length;
			shorter_length = other.polynomial_coefficients.length;
		}
		
		if (polynomial_exponents.length < other.polynomial_exponents.length)
		{
			explonger_length = other.polynomial_exponents.length;
			expshorter_length = polynomial_exponents.length;
		}
		
		else
		{
			explonger_length = polynomial_exponents.length;
			expshorter_length = other.polynomial_exponents.length;
		}
		
		int product[] = new int [explonger_length * expshorter_length];
		double product2[] = new double [longer_length * shorter_length];
		
		for (int i = 0; i < expshorter_length; i++)
		{
			for (int j = 0; j < explonger_length; j++)
			{
				product[i + j] = other.polynomial_exponents[i] + polynomial_exponents[j];
			}
		}
		
		for (int i = 0; i < shorter_length; i++)
		{
			for (int j = 0; j < longer_length; j++)
			{
				product2[i + j] = other.polynomial_coefficients[i] * polynomial_coefficients[j];
			}
		}
		
		Polynomial result = new Polynomial(product2, product);
		return result;
	}
	
	public Polynomial(File polFile) {
		BufferedReader reader;
		try 
		{
			reader = new BufferedReader(new FileReader(polFile));
			String line = reader.readLine();
			int a = 0;
			int b = 0;
			int lengthofline = line.length();
			int exponents []  = new int[lengthofline];
			double coefficients [] = new double[lengthofline];
			boolean power = false;
			boolean negative = false;
			for(char c: line.toCharArray()) 
			{
				if(c == '+')
				{
					continue;
				}
				else if(c == '-') 
				{
					negative = true;
				}
				else if(c == 'x') 
				{
					power = true;
				}
				else if(power) 
				{
					exponents[b] = Integer.parseInt(Character.toString(c));
					b++;
					power = false;
				}
				else 
				{
					double integerholder = Double.parseDouble(Character.toString(c));
					if(negative) {
						integerholder = -1*integerholder;
						negative = false;
					}
					
					coefficients[a] = integerholder;
					a++;
				}
			}
			polynomial_coefficients = new double[a];
			polynomial_exponents  = new int[b + 1];
			for(int i = 0; i < a; i++) 
			{
				polynomial_coefficients[i] = coefficients[i];
			}
			polynomial_exponents[0] = 0;
			for(int i = 0; i < b; i++) 
			{
				polynomial_exponents[i + 1] = exponents[i];
			}
			reader.close();
		}
		
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		
		
	}
	
	public void saveToFile(String fileName) 
	{
		FileWriter writer;
		try 
		{
			writer = new FileWriter(fileName);
	
			for(int i = 0; i < polynomial_coefficients.length; i++)
			{
				if(i > 0 && polynomial_coefficients[i] > 0)
				{
					writer.write("+");
				}
				writer.write((int)polynomial_coefficients[i] + "");
				if(i > 0) 
				{
					writer.write("x");
					writer.write(polynomial_exponents[i] + "");
				}
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}