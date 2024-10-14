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
		colaKiosco.add(solicitudParaAgregar);
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
