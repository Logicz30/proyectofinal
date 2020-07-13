package mp2.utp.xyz;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Oficina
{
	private final DateTimeFormatter formatoFecha;
	private List<Producto> productoList;
	private List<Proveedor> proveedorList;

	Oficina(boolean values)
	{
		formatoFecha = DateTimeFormatter.ofPattern("dd-M-yyyy");

		DefaultValues dv = new DefaultValues();
		if (values)
		{
			//TODO CHANGE DEFAULT VALUES
		}
		productoList = dv.getProductoList();
		proveedorList = dv.getProveedorList();
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

			if (fecha != null && fechaHoy.until(fecha).getDays() < 30)
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
		System.out.print("ID  Nombre\n");

		for (Producto producto : productoList)
		{
			producto.setExpired(fechaHoy.format(formatoFecha));
			if (producto.isExpired())
			{
				System.out.printf("#%-2d %s\n", producto.getId(), producto.getNombre());
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

	void printMenu()
	{
		System.out.println("Menu de seleccion:");
		System.out.print(
			"1.  Listar existencia por producto\n" +
				"2.  Listar los productos por vencen dentro de un mes\n" +
				"3.  Listar los productos por proceedor\n" +
				"4.  Listar los productos que estan en el punto de reorden\n" +
				"5.  Listar los productos sin existencia\n" +
				"6.  Listar los productos vendicos\n" +
				"7.  Listar los productos dañados\n" +
				"------\n" +
				"8.  Entregar productos a los clientes\n" +
				"9. Recibir productos de las compras\n" +
				"10. Listar los productos entregados a los clientes durante la semana\n" +
				"11. Listar los productos que se han recibido en las compras durante la semana\n" +
				"0. Siguiente dia/Salir\n"
		);
	}

	public static void main(String[] args)
	{

	}
}
