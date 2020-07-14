package mp2.utp.xyz;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Manager
{
	private UserInput userInput;
	private Oficina oficina;

	private LocalDate fechaInicio;
	private LocalDate fechaHoy;

	DateTimeFormatter formatoFecha;
	DateTimeFormatter formatoFechaM;

	Manager()
	{
		formatoFecha = DateTimeFormatter.ofPattern("d-M-yyyy");
		formatoFechaM = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		userInput = new UserInput();
		oficina = new Oficina(initValues());
		oficina.setCantidades(initCantidades());
		setFecha();
		fechaHoy = fechaInicio;
	}

	boolean initValues()
	{
		System.out.println("Desea editar los valores default?");
		System.out.println("1. Si | 2. No");
		return userInput.inputCheck(1, 2) == 1;
	}

	boolean initCantidades()
	{
		System.out.println("Desea editar las cantidades default?");
		System.out.println("1. Si | 2. No");
		return userInput.inputCheck(1, 2) == 1;
	}

	void setFecha()
	{
		System.out.println("Introduzca la Fecha de Inicio (d-M-yyyy)");
		//TODO BACK RETURN
		try
		{
			fechaInicio = LocalDate.parse("1-1-2020", formatoFecha);
			//fechaInicio = LocalDate.parse(userInput.inputString(), formatoFecha);
		}
		catch (DateTimeParseException | NullPointerException e)
		{
			System.out.println("INVALID FORMAT!");
		}
	}

	void mainLoop()
	{
		int selecc;

		System.out.printf("Fecha de inicio: %s\n", fechaInicio.format(formatoFechaM));

		for (int i = 0; i < 11; i++)
		{
			System.out.printf("Dia #%d - Fecha de Hoy: %s\n", i + 1, fechaHoy.format(formatoFechaM));
			oficina.printMenu(i == 10);

			do
			{
				selecc = userInput.inputCheck(0, 10);
				switch (selecc)
				{
					case 1:
						oficina.printExistenciaPorProducto();
						break;
					case 2:
						oficina.printProductosVencenUnMes(fechaHoy);
						break;
					case 3:
						oficina.printProductorPorProveedores();
						break;
					case 4:
						oficina.printProductoReorden();
						break;
					case 5:
						oficina.printProductoSinExistencia();
						break;
					case 6:
						oficina.printProductoVencido(fechaHoy);
						break;
					case 7:
						oficina.printProductoBroken();
						break;
					case 8:
						//Solve stage, entregar
						break;
					case 9:
						//Solver stage, recibe
						break;
					case 10:
						oficina.printMenu(i == 10);
						break;
				}
			} while (selecc != 0);

			fechaHoy = fechaHoy.plusDays(1);
		}
	}
}
