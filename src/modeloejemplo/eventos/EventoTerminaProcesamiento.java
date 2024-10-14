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
	int servidorInvolucrado;

	public int getServidorInvolucrado() {
		return servidorInvolucrado;
	}

	public void setServidorInvolucrado(int servidorInvolucrado) {
		this.servidorInvolucrado = servidorInvolucrado;
	}

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
		ArrayList UltSolicitudProcesadaDatos = contadoresEjemplo.getHistorialClases().get(indiceUltSolicitudProcesada);
		int claseUltSolicitudProcesada = (int) UltSolicitudProcesadaDatos.get(0);
		int cantidadUltSolicitudProcesada = (int) UltSolicitudProcesadaDatos.get(2);
		contadoresEjemplo.actualizarBeneficio(claseUltSolicitudProcesada, cantidadUltSolicitudProcesada);
		
		ModeloDelEjemplo modeloActual = (ModeloDelEjemplo) modelo;

		if(modeloActual.haySolicitudesEnEspera()) {
			Solicitud solicitudAProcesar = modeloActual.obtenerSolicitudPrioritaria();
			modeloActual.atenderSolicitud(servidorInvolucrado, solicitudAProcesar);
			float duracionDelProcesamiento = (float) libreria.tiempoDeProcesamiento(solicitudAProcesar.getClase()).get(0);
			EventoTerminaProcesamiento nuevoEvento = new EventoTerminaProcesamiento(duracionDelProcesamiento);
			nuevoEvento.setServidorInvolucrado(servidorInvolucrado);		
			eventos.agregar(nuevoEvento);	
		}
		else {
			modeloActual.actualizarServidorDisponible(servidorInvolucrado);
		}

	}

}
