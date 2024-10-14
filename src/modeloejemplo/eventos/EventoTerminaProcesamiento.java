package modeloejemplo.eventos;

import java.lang.reflect.Array;
import java.util.ArrayList;

import des.ContadoresEstadisticos;
import des.EstadoDelSistema;
import des.Evento;
import des.ListaDeEventos;
import modeloejemplo.componentespropios.ContadoresEstadisticosEjemplo;
import modeloejemplo.componentespropios.LibreriaDeRutinasEjemplo;
import modeloejemplo.estadodelsistema.ModeloDelEjemplo;
import modeloejemplo.estadodelsistema.Solicitud;

public class EventoTerminaProcesamiento extends Evento {

	public EventoTerminaProcesamiento(double tiempoDeOcurrencia) {
		super(tiempoDeOcurrencia);
	}

	@Override
	public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos eventos,
			LibreriaDeRutinasEjemplo libreria) {
		
		ContadoresEstadisticosEjemplo contadoresEjemplo = (ContadoresEstadisticosEjemplo) contadores;
		contadoresEjemplo.actualizarCantProcesadas();
		//Actualización de contadores estadisticos
		int indiceUltSolicitudProcesada = contadoresEjemplo.getCantProcesadas() - 1;
		ArrayList UltSolicitudProcesada = contadoresEjemplo.getHistorialClases().get(indiceUltSolicitudProcesada);
		int claseUltSolicitudProcesada = (int) UltSolicitudProcesada.get(0);
		int cantidadUltSolicitudProcesada = (int) UltSolicitudProcesada.get(2);
		contadoresEjemplo.actualizarBeneficio(claseUltSolicitudProcesada, cantidadUltSolicitudProcesada);
		
		ModeloDelEjemplo modeloActual = (ModeloDelEjemplo) modelo;

		if(modeloActual.haySolicitudesEnEspera()) {
			Solicitud solicitudAProcesar = modeloActual.obtenerSolicitudPrioritaria();
			modeloActual.atenderSolicitud(solicitudAProcesar);
			float duracionDelProcesamiento = libreria.tiempoDeProcesamiento(solicitudAProcesar.getClase()).indexOf(0);
			EventoTerminaProcesamiento nuevoEvento = new EventoTerminaProcesamiento(duracionDelProcesamiento);	
			eventos.agregar(nuevoEvento);	
		}
		else {

			modeloActual.actualizarServidorDisponible();
		}

	}

}
