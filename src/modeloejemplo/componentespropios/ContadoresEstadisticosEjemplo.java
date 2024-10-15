package modeloejemplo.componentespropios;

import des.ContadoresEstadisticos;
import java.util.ArrayList;
import java.util.List;

/* Variables que almacenan información estadística referida al comportamiento del sistema. */

public class ContadoresEstadisticosEjemplo extends ContadoresEstadisticos {
	int cantidadServidores = 2;
	//Estadisticos auxiliares
	private ArrayList<Integer> cantSolicitudesProcesadas;
	private ArrayList<ArrayList> historialClases;
	private double tiempoTotalEnSistema;
	private ArrayList<Double> tiempoOcupacionServidores;
	//Estadisticos para salida
	private float beneficioObtenido;
	private int longPromCola;  //Falta este
	private double tiempoPromClientesKiosco;
	private ArrayList<Double> tasaAtencionEmpleadas;
	private ArrayList<Double> PorcentajeTiempoLibreEmpleadas;

	public ContadoresEstadisticosEjemplo() {
		super();
	}

	public void inicializar() {
		cantSolicitudesProcesadas = new ArrayList<Integer>();
		for (int i = 0; i < cantidadServidores; i++) {
			cantSolicitudesProcesadas.add(0);
		}
		this.historialClases = new ArrayList<>();
		tiempoTotalEnSistema = 0;
		tiempoOcupacionServidores = new ArrayList<Double>();
		for (int i = 0; i < cantidadServidores; i++) {
			tiempoOcupacionServidores.add(0d);
		}

		beneficioObtenido = 0;
		longPromCola = 0;
		tiempoPromClientesKiosco = 0;
		tasaAtencionEmpleadas = new ArrayList<Double>();
		for (int i = 0; i < cantidadServidores; i++) {
			tasaAtencionEmpleadas.add(0d);
		}
		PorcentajeTiempoLibreEmpleadas = new ArrayList<Double>();
		for (int i = 0; i < cantidadServidores; i++) {
			PorcentajeTiempoLibreEmpleadas.add(0d);
		}
	}

	public ArrayList<Integer> getCantSolicitudesProcesadas() {
		return cantSolicitudesProcesadas;
	}

	public int getCantTotalProcesadas() {
		int cantProcesadas = 0;
		for (int i = 0; i < cantSolicitudesProcesadas.size(); i++) {
			cantProcesadas += cantSolicitudesProcesadas.get(i);
		}
		return cantProcesadas;
	}

	public void actualizarCantProcesadas(int i) {
		Integer cant = cantSolicitudesProcesadas.get(i);
		cantSolicitudesProcesadas.set(i, cant + 1);	
	}

	public ArrayList<ArrayList> getHistorialClases(){
		return historialClases;
	}

	public void Agregarhistorial(ArrayList list) {
		// Posicion 0: Clase de la solicitud
		// Posicion 1: Duracion del procesamiento
		// Posicion 2: Cantidad de productos
		// Posicion 3: Tiempo entre arribos
		
		historialClases.add(list);
	}

	public void actualizarTiempoEnSistema(double t) {
		tiempoTotalEnSistema += t;		
	}

	public void actualizarTiempoOcupacion(int i, double t) {
		Double tiempo = tiempoOcupacionServidores.get(i);
		tiempoOcupacionServidores.set(i, tiempo + t);	
	}

	public void calcularTiempoPromClientesKiosco() {
		tiempoPromClientesKiosco = tiempoTotalEnSistema / getCantTotalProcesadas();
	}

	public double getTiempoPromClientesKiosco() {
		return tiempoPromClientesKiosco;
	}

	public float getBeneficioObtenido() {
		return beneficioObtenido;
	}

	public void actualizarBeneficio(int clase, int cantidad) {
		//Bebida cuesta 600 y se vende a 1200 -> 600 de ganancia
		float gananciaBebidas = 600;
		//Panaderia cuesta 400 y se vende a 850 -> 450 de ganancia
		float gananciaPanaderia = 450;

		if (clase == 1) {	//Bebidas
			beneficioObtenido += gananciaBebidas*cantidad;
		} else {	//Panaderia
			beneficioObtenido += gananciaPanaderia*cantidad;
		}
	}

	public ArrayList<Double> getTasaAtencionEmpleadas() {
		return tasaAtencionEmpleadas;
	}

	public void calcularTasaAtencionEmpleadas(double tiempoFinSimulacion) {
		for (int i = 0; i < cantSolicitudesProcesadas.size(); i++) {
			double tasa = cantSolicitudesProcesadas.get(i) / (tiempoFinSimulacion / 60);
			tasaAtencionEmpleadas.set(i, tasa);
		}
	}

	public void calcularPorcentajeTiempoLibre(double tiempoFinSimulacion) {
		for (int i = 0; i < PorcentajeTiempoLibreEmpleadas.size(); i++) {
			double valor = tiempoOcupacionServidores.get(i) / tiempoFinSimulacion;
			PorcentajeTiempoLibreEmpleadas.set(i, 1 - valor);
		}
	}

	public ArrayList<Double> getPorcentajeTiempoLibreEmpleadas() {
		return PorcentajeTiempoLibreEmpleadas;
	}
}
