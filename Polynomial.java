public class Polynomial 
{
	
	double polynomial_coefficients[];
	
	Polynomial () 
	{
		polynomial_coefficients = new double [1];
		polynomial_coefficients[0] = 0; 
	}
	Polynomial (double coefficients[])
	{
		int length = coefficients.length;
		polynomial_coefficients = new double [length];
		for (int i = 0; i < length; i++)
		{
			polynomial_coefficients [i] = coefficients [i];
		}
		
	}
	
	public Polynomial add(Polynomial other)
	{
		int shorter_length; 
		int longer_length;
		
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
		
		double sum[] = new double [longer_length];
		for (int i = 0; i < shorter_length; i++)
			{
				sum[i] = polynomial_coefficients[i] + other.polynomial_coefficients[i];
			}
		for (int j = shorter_length; j < longer_length; j++)
			{
				sum[j] = other.polynomial_coefficients[j];
			}
		
		Polynomial result = new Polynomial(sum);
		return result;
	}
	public double evaluate (double i)
	{
		double sum = 0.0;
		for (int j = 0; j < polynomial_coefficients.length; j++)
		{
			sum += polynomial_coefficients[j] * Math.pow(i, j);
		}
		return sum;
	}
	
	public boolean hasRoot(double x)
	{
		if (evaluate(x) == 0.0);
		{
			return true;
		}
	}
}