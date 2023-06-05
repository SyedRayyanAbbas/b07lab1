public class Polynomial
{
    double[] polycoefficients;

    Polynomial()
    {
        polycoefficients = new double[1];
        polycoefficients[0] = 0;
    }

    Polynomial(double[] coefficients)
    {
        int coefflength = coefficients.length;
        polycoefficients = new double[coefflength];

        for (int i = 0; i < coefflength; i++) {
            polycoefficients[i] = coefficients[i];
        }
    }

    public Polynomial add(Polynomial other)
    {
        int shorter_length;
        int longer_length;


        if (polycoefficients.length < other.polycoefficients.length)
        {
            longer_length = other.polycoefficients.length;
            shorter_length = polycoefficients.length;
        }

        else
        {
            longer_length = polycoefficients.length;
            shorter_length = other.polycoefficients.length;
        }

        double sum[] = new double [longer_length];

        for (int i = 0; i < shorter_length; i++)
        {
            sum[i] = polycoefficients[i] + other.polycoefficients[i];
        }
        if (longer_length == other.polycoefficients.length)
        {
            for (int j = shorter_length; j < longer_length; j++)
            {
                sum[j] = other.polycoefficients[j];
            }
        }
        else
        {
            for (int j = shorter_length; j < longer_length; j++)
            {
                sum[j] = polycoefficients[j];
            }
        }
        Polynomial result = new Polynomial(sum);
        return result;
    }

    public double evaluate (double i)
    {
        double sum = polycoefficients[0];
        for (int j = 1; j < polycoefficients.length; j++)
        {
            sum += polycoefficients[j] * Math.pow(i, j);
        }
        return sum;
    }
    public boolean hasRoot(double x)
    {
        return evaluate(x) == 0.0;

    }
}