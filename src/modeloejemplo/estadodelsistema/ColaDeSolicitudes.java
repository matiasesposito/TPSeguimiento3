package modeloejemplo.estadodelsistema;

import java.util.LinkedList;
import java.util.Queue;

public class ColaDeSolicitudes {

	private Queue<Solicitud> colaKiosco;
	
	public ColaDeSolicitudes() {
		super();
		colaKiosco = new LinkedList<Solicitud>();
	}

	public void encolarSolicitud(Solicitud solicitudParaAgregar) {
		switch(solicitudParaAgregar.getClase()) {
		case 1: colaKiosco.add(solicitudParaAgregar); break;
		}
	}

	public int getCantSolicitudesEsperando() {
		return colaKiosco.size();
	}

	public Solicitud solicitudPrioritaria() {
		Solicitud ret;
		ret = colaKiosco.remove();
		return ret;
	}
	
}
