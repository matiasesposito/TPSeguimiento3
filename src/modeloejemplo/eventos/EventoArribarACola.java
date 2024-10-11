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
		EventoArribarACola nuevoEvento = new EventoArribarACola(libreria.tiempoEntreArribosSolicitudes());	
		eventos.agregar(nuevoEvento);
		
		//Procesar este arribo, para lo cual es necesario generar la solicitud que acaba de arribar.
		Solicitud solicitudParaAgregar = new Solicitud();
		float duracionDelProcesamiento = (float) libreria.tiempoDeProcesamiento(solicitudParaAgregar.getClase()).get(0);
		int cantidadDeProductos = (int) libreria.tiempoDeProcesamiento(solicitudParaAgregar.getClase()).get(1);

		List list = new ArrayList();
		list.add(solicitudParaAgregar.getClase());
		list.add(duracionDelProcesamiento);
		list.add(cantidadDeProductos);
		contadoresEjemplo.Agregarhistorial(list);




		
		if(modeloActual.estaServidorOcupado()) {
			modeloActual.encolarSolicitud(solicitudParaAgregar);
		}
		else {
			modeloActual.atenderSolicitud(solicitudParaAgregar);
			// int duracionDelProcesamiento = libreria.tiempoDeProcesamiento();
			//float duracionDelProcesamiento = libreria.tiempoDeProcesamiento(solicitudParaAgregar.getClase()).indexOf(0);
			EventoTerminaProcesamiento nuevoEventoAdicional = new EventoTerminaProcesamiento(duracionDelProcesamiento);	
			eventos.agregar(nuevoEventoAdicional);
		}
	}

}
