package mp2.utp.xyz;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultValues
{
	private String[] defaultNombreProductos;
	private String[] defaultNombreProveedores;

	private int[] defaultProveedores;
	private int[] defaultPuntoReorden;
	private int[] cantidades;
	private int[] cantidadesBroken;

	private String[] defaultFechaFabricacion;
	private String[] defaultFechaVencimiento;

	private List<Producto> productoList;
	private List<Proveedor> proveedorList;

	DefaultValues(boolean e)
	{
		productoList  = new ArrayList<>();
		proveedorList = new ArrayList<>();

		//DefaultValues
		defaultNombreProductos = new String[]
			{"Tinta Azul", "Tinta Roja", "Tinta Coral", "Tinta Verde", "Tinta Lila",
				"Tinta Negra", "Tinta Blanca", "Tinta Gris", "Tinta Rosada", "Tinta Malva"};

		defaultNombreProveedores = new String[]
			{"Ahrim", "Karil", "Dharok", "Guthan", "Verac"};

		defaultProveedores = new int[]
			{1, 1, 1, 2, 2, 3, 3, 4, 4, 5};

		defaultPuntoReorden = new int[]
			{20, 20, 10, 2, 2, 5, 5, 5, 5, 5};

		defaultFechaFabricacion = new String[10];
		Arrays.fill(defaultFechaFabricacion, "1-1-2020");

		defaultFechaVencimiento = new String[]
			{"2-1-2020", "4-1-2020", "10-1-2020", "10-1-2020", "10-2-2020",
				"10-2-2020", null, null, null, null};

		//DefaultCantidades
		cantidades = new int[]
			{5, 5, 8, 5, 10, 20, 20, 20, 0, 0};

		cantidadesBroken = new int[]
			{5, 5, 5, 2, 5, 0, 0, 0, 0, 0};

		if (e)
		{
			changeValues();
		}

		for (int i = 0; i < 10; i++)
		{
			productoList.add(new Producto(i + 1, defaultProveedores[i], defaultPuntoReorden[i],  defaultNombreProductos[i], defaultFechaFabricacion[i], defaultFechaVencimiento[i]));
		}
		for (int i = 0; i < 5; i++)
		{
			proveedorList.add(new Proveedor(i + 1, defaultNombreProveedores[i]));
		}
	}

	void changeValues()
	{
		UserInput userInput = new UserInput();
		int mainSelec;

		System.out.println("|********************|");
		do
		{
			int subSelec, e;
			//Main selecc
			System.out.print(
				"Seleccione una opcion:\n" +
					"1. Editar Informacion de Productos\n" +
					"2. Editar Informacion de Proveedores\n" +
					"3. Mostrar Informacion\n" +
					"0. Salir (Empezar Programa)\n"
			);
			mainSelec = userInput.inputCheck(0, 3);

			switch (mainSelec)
			{
				case 1:
					//Edit Producto
					System.out.println("Que producto desea editar? (1-10)");
					subSelec = userInput.inputCheck(1, 10);

					System.out.print(
						"Que valor desea cambiar?\n" +
							"1. Nombre\n" +
							"2. Proveedor\n" +
							"3. Fecha Fabricacion\n" +
							"4. Fecha Vencimiento\n" +
							"5. Punto de Reorden\n" +
							"0. Atras"
					);
					e = userInput.inputCheck(0, 5);

					//Producto switch
					if (e > 0 && e < 5)
					{
						System.out.print("Ingrese nuevo valor");
						switch (e)
						{
							case 1:
								System.out.print("\n");
								defaultNombreProductos[subSelec - 1] = userInput.inputString();
								break;
							case 2:
								System.out.print(" (1-5)\n");
								defaultProveedores[subSelec -1] = userInput.inputCheck(1, 5);
							case 3:
								System.out.print(" (d-M-yyy)\n");
								defaultFechaFabricacion[subSelec - 1] = userInput.inputString();
								break;
							case 4:
								System.out.print(" (d-M-yyy)\n");
								String fechaV = userInput.inputString();
								defaultFechaVencimiento[subSelec - 1] = fechaV.equals("null") ? null : fechaV;
								break;
							case 5:
								System.out.println(" (1-100)\n");
								defaultPuntoReorden[subSelec - 1] = userInput.inputCheck(1, 100);
								break;
						}
					}
					break;
				case 2:
					//Edit Proveedor
					System.out.println("Que proveedor desea editar? (1-5)");
					subSelec = userInput.inputCheck(1, 5);

					System.out.print(
						"Que valor desea cambiar?\n" +
							"1. Nombre\n" +
							"0. Atras\n"
					);
					e = userInput.inputCheck(0, 1);

					if (e == 1)
					{
						System.out.print("Ingrese nuevo valor\n");
						defaultNombreProveedores[subSelec - 1] = userInput.inputString();
					}
					break;
				case 3:
					System.out.print(
						"Valores a cargar:\n" +
							"\tProveedores: 5\n" +
							"\tID NOMBRE\n"
					) ;

					for (int i = 0; i < 5; i++)
					{
						System.out.printf("\t#%d %s\n", i + 1, defaultNombreProveedores[i]);
					}
					System.out.println();

					System.out.printf(
						"\tProductos: 10\n" +
							"\tID  %-13s %-13s %-13s POR\n", "Nombre", "FDF", "FDV"
					);

					for (int i = 0; i < 10; i++)
					{
						System.out.printf(
							"\t#%-2d %-13s %-13s %-13s %d\n",
							i + 1, defaultNombreProductos[i], defaultFechaFabricacion[i],
							defaultFechaVencimiento[i], defaultPuntoReorden[i]
						);
					}
					System.out.println();
					break;
			}
		} while (mainSelec != 0);
	}

	void changeCantidades()
	{
		UserInput userInput = new UserInput();
		int mainSelec;

		System.out.println("|********************|");
		do
		{
			//Main selecc
			System.out.print(
				"Seleccione una producto: (1-10)\n" +
					"11. Mostrar Cantidades\n" +
					"0. Para salir\n"
			);
			mainSelec = userInput.inputCheck(0, 10);

			if (mainSelec != 0 && mainSelec != 11)
			{
				System.out.println("Ingrese Cantidad Total de productos: (0-250)");
				int cTotal = userInput.inputCheck(0, 250);
				System.out.printf("Cuantos de estos productos estan dañados?: (0-%d)\n", cTotal);
				int cBroken = userInput.inputCheck(0, cTotal);

				cantidades[mainSelec - 1] = cTotal - cBroken;
				cantidadesBroken[mainSelec - 1] = cBroken;
			}

			if (mainSelec == 11)
			{
				System.out.printf(
					"\tProductos: %d\n" +
						"\tID %-13s %-4s %-4s DAÑADOS", 10, "Nombre", "TOTAL", "CANT");

				for (int i = 0; i < 10; i++)
				{
					int total = cantidades[i] + cantidadesBroken[i];
					System.out.printf(
						"\t#%-2d %-13s %-3d %-3d %-3d\n",
						i + 1, defaultNombreProductos[i], total, cantidades[i], cantidadesBroken[i]
					);
				}
				System.out.println();
			}
		} while (mainSelec != 0);
	}

	void setCantidades(List<Producto> productoList)
	{
		for (int i = 0; i < 10; i++)
		{
			productoList.get(i).setCantidades(cantidades[i], cantidadesBroken[i]);
		}
	}

	public List<Producto> getProductoList()
	{
		return productoList;
	}

	public List<Proveedor> getProveedorList()
	{
		return proveedorList;
	}
}