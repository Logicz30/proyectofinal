package mp2.utp.xyz;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Manager
{
	private UserInput userInput;
	private Oficina oficina;

	private LocalDate fechaInicio;
	private LocalDate fechaHoy;

	DateTimeFormatter formatoFecha;

	Manager()
	{
		formatoFecha = DateTimeFormatter.ofPattern("d-M-yyyy");
		userInput = new UserInput();
		oficina = new Oficina(initValues());
		setFecha();
		fechaHoy = fechaInicio;
	}

	boolean initValues()
	{
		System.out.println("Desea editar los valores default?");
		System.out.println("1. Si | 2. No");
		//TODO BACK RETURN
		//return userInput.inputCheck(1, 2) == 1;
		return false;
	}

	void setFecha()
	{
		System.out.println("Introduzca la Fecha de Inicio (d-M-yyyy)");
		//TODO BACK RETURN
		//fechaInicio = LocalDate.parse(userInput.inputString(), formatoFecha);
		fechaInicio = LocalDate.parse("4-1-2020", formatoFecha);
	}

	void mainLoop()
	{
		int selecc;

		System.out.printf("Fecha de inicio: %s\n", fechaInicio.format(formatoFecha));
		oficina.printMenu();

		for (int i = 0; i < 10; i++)
		{
			selecc = userInput.inputCheck(0, 12);

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
				case 12:
					oficina.printMenu();
			}
		}
	}
}
