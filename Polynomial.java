import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Polynomial
{
    double[] polycoefficients;
    int[] polyexp;

    Polynomial()
    {
        polycoefficients = new double[1];
        polycoefficients[0] = 0;

        polyexp = new int[1];
        polyexp[0] = 0;
    }

    Polynomial(double[] coefficients, int[] exponents)
    {
        int coefflength = coefficients.length;
        polycoefficients = new double[coefflength];

        int expolength = exponents.length;
        polyexp = new int[expolength];

        for (int i = 0; i < coefflength; i++)
        {
            polycoefficients[i] = coefficients[i];
        }

        for (int j = 0; j < expolength; j++)
        {
            polyexp[j] = exponents[j];
        }
    }

    public Polynomial add(Polynomial other)
    {
        int shorter_length;
        int longer_length;
        int explonger;
        int expshorter;


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

        if (polyexp.length < other.polyexp.length)
        {
            explonger = other.polyexp.length;
            expshorter = polyexp.length;
        }

        else
        {
            explonger = polyexp.length;
            expshorter = other.polyexp.length;
        }

        double sum[] = new double [longer_length];
        int sum2[] = new int [explonger];

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

        for (int i = 0; i < expshorter; i++)
        {
            sum2[i] = polyexp[i] + other.polyexp[i];
        }
        if (explonger == other.polyexp.length)
        {
            for (int j = expshorter; j < explonger; j++)
            {
                sum2[j] = other.polyexp[j];
            }
        }
        else
        {
            for (int j = expshorter; j < explonger; j++)
            {
                sum2[j] = polyexp[j];
            }
        }


        Polynomial result = new Polynomial(sum, sum2);
        return result;
    }


    public double evaluate (double i)
    {
        double sum = 0.0;
        for (int j = 0; j < polycoefficients.length; j++)
        {
            sum += polycoefficients[j] * Math.pow(i, polyexp[j]);
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
        int explonger;
        int expshorter;


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

        if (polyexp.length < other.polyexp.length)
        {
            explonger = other.polyexp.length;
            expshorter = polyexp.length;
        }

        else
        {
            explonger = polyexp.length;
            expshorter = other.polyexp.length;
        }

        double product[] = new double [longer_length * shorter_length];
        int product2[] = new int [explonger * expshorter];
        int x = 0;
        int y = 0;

        for (int i = 0; i < expshorter; i++)
        {
            for (int j = 0; j < explonger; j++)
            {
                product2[x] = other.polyexp[i] + polyexp[j];
                x++;
            }
        }

        for (int i = 0; i < shorter_length; i++)
        {
            for (int j = 0; j < longer_length; j++)
            {
                product[y] = other.polycoefficients[i] * polycoefficients[j];
                y++;
            }
        }

        Map<Integer, Double> liketerms = new HashMap<>();
        for(int i = 0; i < product2.length; i++)
        {
            if(liketerms.containsKey(product2[i]))
            {
                liketerms.put(product2[i], liketerms.get(product2[i]) + product[i]);
            }
            else
            {
                liketerms.put(product2[i], product[i]);
            }
        }

        int length = liketerms.size();
        int [] finalexp  = new int [length];
        double [] finalcoeff  = new double [length];

        final int[] i = {0};
        liketerms.forEach((key, value) -> {
            finalexp[i[0]] = key;
            finalcoeff[i[0]] = value;
            i[0]++;
        });

        Polynomial result = new Polynomial(finalcoeff, finalexp);
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
            polycoefficients = new double[a];
            polyexp  = new int[b + 1];
            for(int i = 0; i < a; i++)
            {
                polycoefficients[i] = coefficients[i];
            }
            polyexp[0] = 0;
            for(int i = 0; i < b; i++)
            {
                polyexp[i + 1] = exponents[i];
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

            for(int i = 0; i < polycoefficients.length; i++)
            {
                if(i > 0 && polycoefficients[i] > 0)
                {
                    writer.write("+");
                }
                writer.write((int)polycoefficients[i] + "");
                if(i > 0)
                {
                    writer.write("x");
                    writer.write(polyexp[i] + "");
                }
            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
