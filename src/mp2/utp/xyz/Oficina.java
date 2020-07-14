package mp2.utp.xyz;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Oficina
{
	private final DateTimeFormatter formatoFecha;
	final private List<Producto> productoList;
	final private List<Proveedor> proveedorList;

	LocalDate fechaHoy;

	DefaultValues dv;

	//init
	Oficina(boolean change)
	{
		formatoFecha = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

		dv = new DefaultValues(change);
		if (change)
		{
			dv.changeValues();
		}
		productoList = dv.getProductoList();
		proveedorList = dv.getProveedorList();
	}

	void setExpired(LocalDate fechaHoy)
	{
		this.fechaHoy = fechaHoy;
		for (Producto producto: productoList)
		{
			producto.setExpired(fechaHoy);
		}
	}

	void setCantidades(boolean change)
	{
		if (change)
		{
			dv.changeCantidades();
		}
		dv.setCantidades(productoList);
	}

	void addCantidad(int id, int cantidad, int cantidadBroken)
	{
		productoList.get(id - 1).agregarCantidades(cantidad, cantidadBroken);
	}

	void restarCantidad(int id, int cantidad)
	{
		productoList.get(id - 1).restarCantidades(cantidad);
	}

	//prints

	void printExistenciaPorProducto()
	{
		System.out.println("Existencia por producto:");
		System.out.printf("ID  %-13s Cnt\n", "Nombre");
		for (Producto producto : productoList)
		{
			System.out.printf("#%-2d %-13s %d\n", producto.getId(), producto.getNombre(), producto.getCantidadTotal());
		}
	}

	void printProductosVencenUnMes()
	{
		System.out.println("Productos que vencen en menos de 1 mes:");
		System.out.printf("ID  %-13s Fecha Vencimiento\n", "Nombre");
		boolean empty = true;
		for (Producto producto : productoList)
		{
			LocalDate fecha = producto.getFechaVencimiento();
			if (fecha == null)
			{
				continue;
			}
			int dias = fechaHoy.until(fecha).getDays();
			if (dias < 30 && dias >= 0)
			{
				empty = false;
				String builder = fecha.format(formatoFecha);
				System.out.printf("#%-2d %-13s %s\n", producto.getId(), producto.getNombre(), builder);
			}
		}
		if (empty)
		{
			System.out.println("\tVACIO");
		}
	}

	void printProductorPorProveedores()
	{
		System.out.println("Productos por proveedores:");
		for (Proveedor proveedor : proveedorList)
		{
			boolean empty = true;
			System.out.printf("#%d %s\n", proveedor.getId(), proveedor.getNombre());
			for (Producto producto : productoList)
			{
				if (proveedor.getId() == producto.getProvedorId())
				{
					empty = false;
					System.out.printf("\t#%-2d %s\n", producto.getId(), producto.getNombre());
				}
			}
			if (empty)
			{
				System.out.println("\tVACIO");
			}
		}
	}

	void printProductoReorden()
	{
		boolean empty = true;
		System.out.println("Productos que estan en el punto de reorden:");
		System.out.printf("ID  %-13s CANT PRO\n", "Nombre");
		for (Producto producto : productoList)
		{
			if (producto.needReorden())
			{
				empty = false;
				System.out.printf("#%-2d %-13s %-4d %d\n", producto.getId(), producto.getNombre(), producto.getCantidadTotal(), producto.getPuntoReorden());
			}
		}

		if (empty)
		{
			System.out.println("\tVACIO");
		}
	}

	void printProductoSinExistencia()
	{
		boolean empty = true;
		System.out.println("Productos sin existencia:");
		System.out.print("ID  Nombre\n");

		for (Producto producto : productoList)
		{
			if (producto.getCantidadTotal() == 0)
			{
				empty = false;
				System.out.printf("#%-2d %s\n", producto.getId(), producto.getNombre());
			}
		}

		if (empty)
		{
			System.out.println("\tVACIO");
		}
	}

	void printProductoVencido()
	{
		boolean empty = true;
		System.out.println("Productos vencidos:");
		System.out.printf("ID  %-13s FDV\n", "Nombre");

		for (Producto producto : productoList)
		{
			//TODO MEJORAR
			String builder = null;
			if (producto.getFechaVencimiento() != null)
			{
				builder = producto.getFechaVencimiento().format(formatoFecha);
			}

			if (producto.isExpired())
			{
				empty = false;
				System.out.printf("#%-2d %-13s %s\n", producto.getId(), producto.getNombre(), builder);
			}
		}

		if (empty)
		{
			System.out.println("\tVACIO");
		}
	}

	void printProductoBroken()
	{
		boolean empty = true;
		System.out.println("Productos dañados:");
		System.out.printf("ID  %-13s CANT\n", "Nombre");

		for (Producto producto : productoList)
		{
			if (producto.getCantidadBroken() != 0)
			{
				empty =	false;
				System.out.printf("#%-2d %-13s %d\n", producto.getId(), producto.getNombre(), producto.getCantidadBroken());
			}
		}

		if (empty)
		{
			System.out.println("\tVACIO");
		}
	}

	//semana

	void printListaEntragado()
	{
		System.out.println("Productos entregados en la semana: ");
		System.out.printf("ID  %-13s CANT\n", "Nombre");

		for (Producto producto : productoList)
		{
			System.out.printf("#%-2d %-13s %d\n", producto.getId(), producto.getNombre(), producto.getEntregado());
		}
	}

	void printListaRecibido()
	{
		System.out.println("Productos recibidos en la semana: ");
		System.out.printf("ID  %-13s %-4s BROKEN \n", "Nombre", "CANT");

		for (Producto producto : productoList)
		{
			System.out.printf("#%-2d %-13s %d %d\n", producto.getId(), producto.getNombre(), producto.getRecibido(), producto.getRecibidoBroken());
		}
	}

	//menu

	void printMenu(boolean stage)
	{
		System.out.println("Menu de seleccion:");

		String builder;

		if (stage)
		{
			builder =
				"Acciones Semanales:\n" +
					"\t1.  Listar los productos entregados a los clientes durante la semana\n" +
					"\t2.  Listar los productos que se han recibido en las compras durante la semana\n" +
					"\t0.  Salir\n";
		}
		else
		{
			builder =
				"Acciones Diarias:\n" +
					"\t1.  Entregar productos a los clientes\n" +
					"\t2.  Recibir productos de las compras\n" +
					"\t0.  Siguiente dia\n";
		}

		System.out.print(builder);

		System.out.print(
			"Impresiones:\n" +
				"\t3.  Listar existencia por producto\n" +
				"\t4.  Listar los productos por vencen dentro de un mes\n" +
				"\t5.  Listar los productos por proceedor\n" +
				"\t6.  Listar los productos que estan en el punto de reorden\n" +
				"\t7.  Listar los productos sin existencia\n" +
				"\t8.  Listar los productos vendicos\n" +
				"\t9.  Listar los productos dañados\n" +
				"\t10. Repetir menu\n"
		);
	}

	public int getProductoCantidad(int id)
	{
		return productoList.get(id).getCantidad();
	}

	public boolean getProductoExpired(int id)
	{
		return productoList.get(id).isExpired();
	}
}
