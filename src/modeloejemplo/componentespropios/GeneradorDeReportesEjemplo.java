package modeloejemplo.componentespropios;

import java.util.ArrayList;

import des.ContadoresEstadisticos;
import des.GeneradorDeReportes;

/* Subprograma que calcula las estimaciones de las medidas de performance 
 * (a partir de los Contadores Estadísticos). */

public class GeneradorDeReportesEjemplo extends GeneradorDeReportes {
	private double tiempoFinSimulacion;

	public void setTiempoFinSimulacion(double tiempoFinSimulacion) {
		this.tiempoFinSimulacion = tiempoFinSimulacion;
	}

	public void run(ContadoresEstadisticos contadores) {
		
		ContadoresEstadisticosEjemplo contadoresEjemplo = (ContadoresEstadisticosEjemplo) contadores;
		contadoresEjemplo.calcularTiempoPromClientesKiosco();
		contadoresEjemplo.calcularTasaAtencionEmpleadas(tiempoFinSimulacion);
		ArrayList<Double> tasaAtencionEmpleadas = contadoresEjemplo.getTasaAtencionEmpleadas();
		
		System.out.println("------------------------------------------------------");
		System.out.println("***GENERADOR DE REPORTES *** ");
		System.out.println("------------------------------------------------------");

		System.out.println("La cantidad de solicitudes procesadas es de: " + contadoresEjemplo.getCantTotalProcesadas());
		System.out.println("El historial de clases de solicitudes encoladas es: " + contadoresEjemplo.getHistorialClases().toString());		
		System.out.println("El beneficio obtenido es de: $" + contadoresEjemplo.getBeneficioObtenido());
		System.out.println("El tiempo promedio de los clientes en el kiosco es: " + contadoresEjemplo.getTiempoPromClientesKiosco());
		for (int i = 0; i < tasaAtencionEmpleadas.size(); i++) {
			System.out.println("La tasa de atención (clientes por hora) para la empleada " + (i+1) + " es: " + tasaAtencionEmpleadas.get(i));
		}
	}

}
