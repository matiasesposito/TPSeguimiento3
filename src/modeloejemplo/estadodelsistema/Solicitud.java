package modeloejemplo.estadodelsistema;

/* Solicitud que puede ser procesada por el servidor. */

public class Solicitud {
	
	private int clase;

	public Solicitud() {
		super();
		// Se genera un numero aleatorio entre 0 y 1, si es menor a 0.7 es de clase 1(Bebidas Saludables), de lo contrario es de clase 2(Panadería)
		this.clase = (Math.random() < 0.7) ? 1 : 2;
	}

	public int getClase() {
		return clase;
	}
	
}
