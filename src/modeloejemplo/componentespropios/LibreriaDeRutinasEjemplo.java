package modeloejemplo.componentespropios;

import des.LibreriaDeRutinas;
import java.util.ArrayList;

/* Subprogramas usados para generar observaciones aleatorias desde las distribuciones de 
 * probabilidad asociadas al modelo. */

public class LibreriaDeRutinasEjemplo extends LibreriaDeRutinas {

	public float tiempoEntreArribosSolicitudes() {
		// Aca se debería programar el método de generación de variables que corresponde.
		// Los tiempos entre arribos de los clientes siguen la distribución exponencial ya utilizada (media de 4 minutos)
		float tiempo =  -4 * (float) Math.log(1 - Math.random());
		return tiempo;
	}

	// public int tiempoDeProcesamiento() {
	// 	// Aca se debería programar el método de generación de variables que corresponde.
	// 	return 1;
	// }

	public ArrayList tiempoDeProcesamiento(int clase) {
		// Si es de clase 1, las cantidades son: 1 : 57%, 2:33 %, 3:10%
		// Si es de clase 2, las cantidades son: 1 : 27%, 2:25 %, 3:35%, 4:13%

		// Tiempo de procesamiento para bebidas saludables y panaderia
		// Bebidas saludables: Exponencial con media de 2,4 minutos.
		// Panaderia: Exponencial con media de 3,5 minutos.
		
		float tiempoProcBebidas = -2.4f * (float) Math.log(1 - Math.random());
		float tiempoProcPanaderia = -3.5f * (float) Math.log(1 - Math.random());

		// Generar un ArrayList para retornar los valores tiempoDeProcesamiento, cantidad de productos
		ArrayList list = new ArrayList();

		if (clase == 1) {
			double random = Math.random();
			if (random < 0.57) {list.add(tiempoProcBebidas); list.add(1);}
			if (random < 0.90) {list.add(tiempoProcBebidas * 0.10f + tiempoProcBebidas); list.add(2);}
			list.add(tiempoProcBebidas * 0.13f + tiempoProcBebidas); list.add(3);
		}else if(clase == 2) {
			double random = Math.random();
			if (random < 0.27) {list.add(tiempoProcPanaderia); list.add(1);}
			if (random < 0.52) {list.add(tiempoProcPanaderia * 0.12f + tiempoProcPanaderia); list.add(2);}
			if (random < 0.87) {list.add(tiempoProcPanaderia * 0.15f + tiempoProcPanaderia); list.add(3);}
			list.add(tiempoProcPanaderia * 0.20f + tiempoProcPanaderia); list.add(4);
		}
		return list;
	}

}
