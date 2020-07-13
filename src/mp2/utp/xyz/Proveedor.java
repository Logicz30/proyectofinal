package mp2.utp.xyz;

public class Proveedor
{
	private final int id;
	private final String nombre;

	Proveedor(int id, String nombre)
	{
		this.id = id;
		this.nombre = nombre;
	}

	public String getNombre()
	{
		return nombre;
	}

	public int getId()
	{
		return id;
	}
}
