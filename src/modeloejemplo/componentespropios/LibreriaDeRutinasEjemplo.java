package modeloejemplo.componentespropios;

import des.LibreriaDeRutinas;

/* Subprogramas usados para generar observaciones aleatorias desde las distribuciones de 
 * probabilidad asociadas al modelo. */

public class LibreriaDeRutinasEjemplo extends LibreriaDeRutinas {

	public float tiempoEntreArribosSolicitudes() {
		// Aca se debería programar el método de generación de variables que corresponde.
		// Los tiempos entre arribos de los clientes siguen la distribución exponencial ya utilizada (media de 4 minutos)
		float tiempo =  -4 * (float) Math.log(1 - Math.random());
		return tiempo;
	}

	public int tiempoDeProcesamiento() {
		// Aca se debería programar el método de generación de variables que corresponde.
		return 1;
	}

}
