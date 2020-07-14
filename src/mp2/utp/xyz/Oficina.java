package mp2.utp.xyz;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Oficina
{
	private final DateTimeFormatter formatoFecha;
	final private List<Producto> productoList;
	final private List<Proveedor> proveedorList;

	DefaultValues dv;

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

	void setCantidades(boolean change)
	{
		if (change)
		{
			dv.changeCantidades();
		}
		dv.setCantidades(productoList);
	}

	void printExistenciaPorProducto()
	{
		System.out.println("Existencia por producto:");
		System.out.printf("ID  %-13s Cnt\n", "Nombre");
		for (Producto producto : productoList)
		{
			System.out.printf("#%-2d %-13s %d\n", producto.getId(), producto.getNombre(), producto.getCantidadTotal());
		}
	}

	void printProductosVencenUnMes(LocalDate fechaHoy)
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
		System.out.println("Productos que estan en el punto de reorden:");
		System.out.printf("ID  %-13s CANT PRO\n", "Nombre");
		for (Producto producto : productoList)
		{
			if (producto.needReorden())
			{
				System.out.printf("#%-2d %-13s %-4d %d\n", producto.getId(), producto.getNombre(), producto.getCantidadTotal(), producto.getPuntoReorden());
			}
		}
	}

	void printProductoSinExistencia()
	{
		System.out.println("Productos sin existencia:");
		System.out.print("ID  Nombre\n");

		for (Producto producto : productoList)
		{
			if (producto.getCantidadTotal() == 0)
			{
				System.out.printf("#%-2d %s\n", producto.getId(), producto.getNombre());
			}
		}
	}

	void printProductoVencido(LocalDate fechaHoy)
	{
		System.out.println("Productos vencidos:");
		System.out.printf("ID  %-13s FDV\n", "Nombre");

		for (Producto producto : productoList)
		{
			//TODO MEJORAR
			producto.setExpired(fechaHoy);
			String builder = null;
			if (producto.getFechaVencimiento() != null)
			{
				builder = producto.getFechaVencimiento().format(formatoFecha);
			}

			if (producto.isExpired())
			{
				System.out.printf("#%-2d %-13s %s\n", producto.getId(), producto.getNombre(), builder);
			}
		}
	}

	void printProductoBroken()
	{
		System.out.println("Productos dañados:");
		System.out.printf("ID  %-13s CANT\n", "Nombre");

		for (Producto producto : productoList)
		{
			if (producto.getCantidadBroken() != 0)
			{
				System.out.printf("#%-2d %-13s %d\n", producto.getId(), producto.getNombre(), producto.getCantidadBroken());
			}
		}
	}

	void printMenu(boolean stage)
	{
		System.out.println("Menu de seleccion:");
		System.out.print(
			"1.  Listar existencia por producto\n" +
				"2.  Listar los productos por vencen dentro de un mes\n" +
				"3.  Listar los productos por proceedor\n" +
				"4.  Listar los productos que estan en el punto de reorden\n" +
				"5.  Listar los productos sin existencia\n" +
				"6.  Listar los productos vendicos\n" +
				"7.  Listar los productos dañados\n"
		);

		String builder;

		if (stage)
		{
			builder =
				"8.  Entregar productos a los clientes\n" +
					"9.  Recibir productos de las compras\n" +
					"10. Repetir menu\n" +
					"0.  Siguiente dia\n";
		}
		else
		{
			builder =
				"8.  Listar los productos entregados a los clientes durante la semana\n" +
					"9.  Listar los productos que se han recibido en las compras durante la semana\n" +
					"10. Repetir menu\n" +
					"0.  Salir\n";
		}

		System.out.println(builder);
	}

	public static void main(String[] args)
	{

	}
}
