package modeloejemplo.componentespropios;

import des.ContadoresEstadisticos;
import des.GeneradorDeReportes;

/* Subprograma que calcula las estimaciones de las medidas de performance 
 * (a partir de los Contadores Estadísticos). */

public class GeneradorDeReportesEjemplo extends GeneradorDeReportes {

	public void run(ContadoresEstadisticos contadores) {
		
		ContadoresEstadisticosEjemplo contadoresEjemplo = (ContadoresEstadisticosEjemplo) contadores;
		
		System.out.println("------------------------------------------------------");
		System.out.println("***GENERADOR DE REPORTES *** ");
		System.out.println("------------------------------------------------------");

		System.out.println("La cantidad de solicitudes procesadas es de: " + contadoresEjemplo.getCantProcesadas());
		System.out.println("El historial de clases de solicitudes encoladas es: " + contadoresEjemplo.getHistorialClases().toString());		
		System.out.println("El beneficio obtenido es de: $" + contadoresEjemplo.getBeneficioObtenido());
	}

}
