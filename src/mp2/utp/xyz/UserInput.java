package mp2.utp.xyz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput
{
	private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public int inputCheck(int min, int max)
	{
		boolean valid;
		int i = 0;

		do
		{
			valid = true;
			try
			{
				i = Integer.parseInt(reader.readLine());
				while (i < min || i > max)
				{
					System.out.println("Seleccion Incorrecta");
					i = Integer.parseInt(reader.readLine());
				}
			}
			catch (IOException | NumberFormatException e)
			{
				valid = false;
				System.out.println("INT INPUT ERROR!");
			}

		} while (!valid);

		return i;
	}

	public String inputString()
	{
		boolean valid;
		String i = null;

		do
		{
			valid = true;
			try
			{
				i = reader.readLine();
			}
			catch (IOException e)
			{
				valid = false;
				System.out.println("STRING INPUT ERROR!");
			}
		} while (!valid);

		return i;
	}
}