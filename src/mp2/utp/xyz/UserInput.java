package mp2.utp.xyz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput
{
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public int inputCheck(int min, int max)
	{
		int i = 0;

		try
		{
			i = Integer.parseInt(reader.readLine());
			while (i < min || i > max)
			{
				System.out.println("Seleccion Incorrecta");
				i = Integer.parseInt(reader.readLine());
			}
		}
		catch (IOException e)
		{
			System.out.println("INPUT ERROR!");
		}

		return i;
	}

	public double inputCheckDouble(int max)
	{
		double i = 0;

		try
		{
			i = Double.parseDouble(reader.readLine());
			while (i <= 0 || i > max)
			{
				System.out.println("Seleccion Incorrecta");
				i = Double.parseDouble(reader.readLine());
			}
		}
		catch (IOException e)
		{
			System.out.println("INPUT ERROR!");
		}

		return i;
	}

	public String inputString()
	{
		String i = null;

		try
		{
			i = reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("INPUT ERROR!");
		}

		return i;
	}
}