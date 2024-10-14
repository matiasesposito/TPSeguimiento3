package modeloejemplo.componentespropios;

import des.ContadoresEstadisticos;
import java.util.ArrayList;
import java.util.List;

/* Variables que almacenan información estadística referida al comportamiento del sistema. */

public class ContadoresEstadisticosEjemplo extends ContadoresEstadisticos {
	
	private int cantSolicitudesProcesadas;
	private ArrayList<ArrayList> historialClases;
	private float beneficioObtenido;
	private int longPromCola;  //Falta este
	private float tiempoPromClientesKiosco;
	private ArrayList<Float> tasaAtencionEmpleadas;
	private ArrayList<Float> PorcentajeTiempoLibreEmpleadas;

	public ContadoresEstadisticosEjemplo() {
		super();
	}

	public void inicializar() {
		cantSolicitudesProcesadas = 0;
		this.historialClases = new ArrayList<>();
		beneficioObtenido = 0;
		longPromCola = 0;
		tiempoPromClientesKiosco = 0;
		tasaAtencionEmpleadas = new ArrayList<Float>();
		PorcentajeTiempoLibreEmpleadas = new ArrayList<Float>();
	}

	public int getCantProcesadas() {
		return cantSolicitudesProcesadas;
	}

	public void actualizarCantProcesadas() {
		cantSolicitudesProcesadas++;		
	}

	public ArrayList<ArrayList> getHistorialClases(){
		return historialClases;
	}

	public void Agregarhistorial(ArrayList list) {
		// Posicion 0: Clase de la solicitud
		// Posicion 1: Duracion del procesamiento
		// Posicion 2: Cantidad de productos
		
		historialClases.add(list);
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
}
