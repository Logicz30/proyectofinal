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
			oficina.setExpired(fechaHoy);
			oficina.printMenu(i == 10);

			do
			{
				selecc = userInput.inputCheck(0, 10);
				switch (selecc)
				{
					case 1:
						if (i == 10)
						{
							//LIST
							oficina.printListaRecibido();
						}
						else
						{
							//ADD
							System.out.println("ID del producto entregado:");
							int id = userInput.inputCheck(1, 10);

							if (oficina.getProductoExpired(id - 1))
							{
								System.out.println("PRODUCTO EXPIRADO");
								continue;
							}

							int max = oficina.getProductoCantidad(id - 1);

							if (max == 0)
							{
								System.out.println("NO HAY PRODUCTO");
								continue;
							}

							System.out.printf("Cuantos productos entregara? (1-%d)\n", max);
							int cantidad = userInput.inputCheck(1, max);

							System.out.printf("Se entregaron %d unidades del Producto #%d al cliente\n", cantidad, id);

							//TODO STORE THIS
							oficina.restarCantidad(id, cantidad);
						}
						break;
					case 2:
						if (i == 10)
						{
							//LIST
							oficina.printListaEntragado();
						}
						else
						{
							//ADD
							System.out.println("ID del producto recibido:");
							int id = userInput.inputCheck(1, 10);
							System.out.println("Cuantos productos recibio? (1-250)");
							int cantidad = userInput.inputCheck(1, 250);
							System.out.printf("Cuantos estan dañados? (1-%d)\n", cantidad);
							int cantidadBroken = userInput.inputCheck(0, cantidad);

							oficina.addCantidad(id, cantidad - cantidadBroken, cantidadBroken);
						}
						break;
					case 3:
						oficina.printExistenciaPorProducto();
						break;
					case 4:
						oficina.printProductosVencenUnMes();
						break;
					case 5:
						oficina.printProductorPorProveedores();
						break;
					case 6:
						oficina.printProductoReorden();
						break;
					case 7:
						oficina.printProductoSinExistencia();
						break;
					case 8:
						oficina.printProductoVencido();
						break;
					case 9:
						oficina.printProductoBroken();
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
