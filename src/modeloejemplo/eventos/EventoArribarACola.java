package modeloejemplo.eventos;

import des.ContadoresEstadisticos;
import des.EstadoDelSistema;
import des.Evento;
import des.ListaDeEventos;
import java.util.ArrayList;
import java.util.List;
import modeloejemplo.componentespropios.ContadoresEstadisticosEjemplo;
import modeloejemplo.componentespropios.LibreriaDeRutinasEjemplo;
import modeloejemplo.estadodelsistema.ModeloDelEjemplo;
import modeloejemplo.estadodelsistema.Solicitud;

public class EventoArribarACola extends Evento {

	public EventoArribarACola(double tiempoDeOcurrencia) {
		super(tiempoDeOcurrencia);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos eventos,
			LibreriaDeRutinasEjemplo libreria) {
				
		ModeloDelEjemplo modeloActual = (ModeloDelEjemplo) modelo;
		ContadoresEstadisticosEjemplo contadoresEjemplo = (ContadoresEstadisticosEjemplo) contadores;
		
		//Agendar el próximo arribo de solicitud.
		float tiempoEntreArribos = libreria.tiempoEntreArribosSolicitudes();
		EventoArribarACola nuevoEvento = new EventoArribarACola(tiempoEntreArribos);	
		eventos.agregar(nuevoEvento);
		
		//Procesar este arribo, para lo cual es necesario generar la solicitud que acaba de arribar.
		Solicitud solicitudParaAgregar = new Solicitud();
		solicitudParaAgregar.setTiempoArribo(getTiempoDeOcurrencia());
		float duracionDelProcesamiento = (float) libreria.tiempoDeProcesamiento(solicitudParaAgregar.getClase()).get(0);
		int cantidadDeProductos = (int) libreria.tiempoDeProcesamiento(solicitudParaAgregar.getClase()).get(1);

		ArrayList list = new ArrayList();
		list.add(solicitudParaAgregar.getClase());
		list.add(duracionDelProcesamiento);
		list.add(cantidadDeProductos);
		list.add(tiempoEntreArribos);
		contadoresEjemplo.Agregarhistorial(list);

		//Chequear si hay algún servidor(empleada) libre que pueda atender la solicitud, de lo contrario añadir a la cola
		int cantidadServidores = modeloActual.getCantidadServidores();
		for (int i = 0; i < cantidadServidores; i++) {
			if(modeloActual.estaServidorOcupado(i)) {
				if ((i == (cantidadServidores - 1))) {
					modeloActual.encolarSolicitud(solicitudParaAgregar);	
				}
			}
			else {
				modeloActual.atenderSolicitud(i, solicitudParaAgregar);
				// int duracionDelProcesamiento = libreria.tiempoDeProcesamiento();
				//float duracionDelProcesamiento = libreria.tiempoDeProcesamiento(solicitudParaAgregar.getClase()).indexOf(0);
				EventoTerminaProcesamiento nuevoEventoAdicional = new EventoTerminaProcesamiento(duracionDelProcesamiento);
				nuevoEventoAdicional.setServidorInvolucrado(i);	
				eventos.agregar(nuevoEventoAdicional);

				contadoresEjemplo.actualizarTiempoEnSistema(duracionDelProcesamiento);
				break;
			}
		}
		
		
	}

}
