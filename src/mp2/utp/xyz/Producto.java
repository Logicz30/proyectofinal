package mp2.utp.xyz;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Producto
{
	private final int id;
	private final int provedorId;
	private final int puntoReorden;
	private final String nombre;

	private final LocalDate fechaFabricacion;
	private final LocalDate fechaVencimiento;

	private int cantidad;
	private int cantidadBroken;
	private boolean expired;

	private static final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("d-M-yyyy");

	Producto(int id, int provedorId, int puntoReorden, String nombre, String fechaFabricacion, String fechaVencimiento)
	{
		this.id = id;
		this.provedorId = provedorId;
		this.puntoReorden = puntoReorden;
		this.nombre = nombre;
		this.expired = false;

		this.fechaFabricacion = LocalDate.parse(fechaFabricacion, formatoFecha);
		this.fechaVencimiento = fechaVencimiento == null ? null : LocalDate.parse(fechaVencimiento, formatoFecha);
	}

	public void setExpired(String fecha)
	{
		if (fechaVencimiento == null)
		{
			return;
		}

		LocalDate fechaHoy = LocalDate.parse(fecha, formatoFecha);

		if (fechaHoy != null && fechaHoy.isAfter(fechaVencimiento))
		{
			expired = true;
		}
	}

	public void setCantidades(int cantidad, int cantidadBroken)
	{
		this.cantidad = cantidad;
		this.cantidadBroken = cantidadBroken;
	}

	public void agregarCantidades(int cantidad, int cantidadBroken)
	{
		this.cantidad += cantidad;
		this.cantidadBroken += cantidadBroken;
	}

	public void restarCantidades(int cantidad, int cantidadBroken)
	{
		this.cantidad -= cantidad;
		this.cantidadBroken -= cantidadBroken;
	}

	public boolean needReorden()
	{
		return puntoReorden > getCantidadTotal();
	}

	public int getId()
	{
		return id;
	}

	public int getProvedorId()
	{
		return provedorId;
	}

	public int getCantidad()
	{
		return cantidad;
	}

	public int getCantidadBroken()
	{
		return cantidadBroken;
	}

	public int getCantidadTotal()
	{
		return cantidad + cantidadBroken;
	}

	public int getPuntoReorden()
	{
		return puntoReorden;
	}

	public boolean isExpired()
	{
		return expired;
	}

	public String getNombre()
	{
		return nombre;
	}

	public LocalDate getFechaFabricacion()
	{
		return fechaFabricacion;
	}

	public LocalDate getFechaVencimiento()
	{
		return fechaVencimiento;
	}
}