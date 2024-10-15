package modeloejemplo.estadodelsistema;

import java.util.ArrayList;

import des.EstadoDelSistema;

/* Colección de variables de estado necesarias para describir  el sistema en un punto en el tiempo. */

public class ModeloDelEjemplo extends EstadoDelSistema {
	
	private ColaDeSolicitudes cola;
	private ArrayList<Servidor> servidor;
	private int cantidadServidores = 1;		//Factor experimental (modificable)(modificar tambien ContadoresEstadisticosEjemplo)
	

	public void inicializar() {
		cola = new ColaDeSolicitudes();
		servidor = new ArrayList<Servidor>();
		for (int i = 0; i < cantidadServidores; i++) {
			Servidor s = new Servidor(false);
			servidor.add(s);
		}
	}

	public ColaDeSolicitudes getCola() {
		return cola;
	}

	public void encolarSolicitud(Solicitud solicitudParaAgregar) {		
		System.out.println("\t\t-- El MODELO encola una solicitud de la clase " + solicitudParaAgregar.getClase() + " ya que los servidores están ocupados.");
		cola.encolarSolicitud(solicitudParaAgregar);
	}

	public boolean estaServidorOcupado(int indice) {
		return servidor.get(indice).getEstaOcupado();
	}

	public void atenderSolicitud(int serv, Solicitud solicitudParaAgregar) {
		System.out.println("\t\t-- El MODELO atiende una solicitud de la clase " + solicitudParaAgregar.getClase() + ".");
		servidor.get(serv).pasarAOcupado(solicitudParaAgregar);
	}

	public boolean haySolicitudesEnEspera() {
		return (cola.getCantSolicitudesEsperando()>0);
	}

	public Solicitud obtenerSolicitudPrioritaria() {
		return cola.solicitudPrioritaria();
	}

	public void actualizarServidorDisponible(int serv) {
		System.out.println("\t\t-- El MODELO deja al servidor " + serv + " disponible ya que no hay solicitudes en espera.");
		servidor.get(serv).setEstaOcupado(false);
	}
	
	public int getCantidadServidores() {
		return cantidadServidores;
	}

}
