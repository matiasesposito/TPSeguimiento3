package modeloejemplo.componentespropios;

import des.ContadoresEstadisticos;
import java.util.ArrayList;
import java.util.List;

/* Variables que almacenan información estadística referida al comportamiento del sistema. */

public class ContadoresEstadisticosEjemplo extends ContadoresEstadisticos {
	
	private int cantSolicitudesProcesadas;
	private List<String> historialClases;

	public ContadoresEstadisticosEjemplo() {
		super();
	}

	public void inicializar() {
		cantSolicitudesProcesadas = 0;
		this.historialClases = new ArrayList<String>();
	}

	public int getCantProcesadas() {
		return cantSolicitudesProcesadas;
	}

	public void actualizarCantProcesadas() {
		cantSolicitudesProcesadas++;		
	}

	public List<String> historialClases(){
		return historialClases;
	}

	public void Agregarhistorial(List list) {
		historialClases.add(list.toString());
	}

}
