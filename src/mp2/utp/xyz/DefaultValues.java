package mp2.utp.xyz;

import java.util.ArrayList;
import java.util.List;

public class DefaultValues
{
	private String[] nombreProductos;
	private String[] nombreProveedores;

	private int[] puntoReorden;
	private int[] cantidades;
	private int[] cantidadesBroken;

	private String[] fechaFabricacion;
	private String[] fechaVencimiento;

	private List<Producto> productoList = new ArrayList<>();
	private List<Proveedor> proveedorList = new ArrayList<>();

	DefaultValues()
	{
		nombreProductos = new String[]
			{"Tinte Azul", "Tinte Rojo", "Tinte Coral", "Tinte Verde", "Tinte Lila",
				"Tinte Negro", "Tinte Blanco", "Tinte Gris", "Tinte Rosado", "Tinte Malva"};

		nombreProveedores = new String[]
			{"Ahrim", "Karil", "Dharok", "Guthan", "Verac"};

		puntoReorden = new int[]
			{20, 20, 10, 2, 2, 5, 5, 5, 5, 5};

		cantidades = new int[]
			{5, 5, 5, 5, 8, 10, 10, 10, 8, 0};

		cantidadesBroken = new int[]
			{5, 5, 5, 8, 5, 0, 0, 0, 0, 0};

		fechaFabricacion = new String[]
			{"01-01-2020", "01-01-2020", "01-01-2020", "01-01-2020", "01-01-2020",
				"01-01-2020", "01-01-2020", "01-01-2020", "01-01-2020", "01-01-2020"};

		fechaVencimiento = new String[]
			{"03-01-2020", "20-02-2020", null, null, null,
				null, null, null, null, null};

		setProductoList();
		setCantidades();
		setProveedorList();
	}

	void setProductoList()
	{
		for (int i = 0; i < 10; i++)
		{
			productoList.add(new Producto(i+1, 1, puntoReorden[i],  nombreProductos[i], fechaFabricacion[i], fechaVencimiento[i]));
		}
	}

	void setCantidades()
	{
		for (int i = 0; i < 10; i++)
		{
			productoList.get(i).setCantidades(cantidades[i], cantidadesBroken[i]);
		}
	}

	void setProveedorList()
	{
		for (int i = 0; i < 5; i++)
		{
			proveedorList.add(new Proveedor(i +1, nombreProveedores[i]));
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