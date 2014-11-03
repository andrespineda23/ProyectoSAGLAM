/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AdministrarInterface.AdministrarResultadosActividadesInterface;
import Entidades.ResultadosActividades;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.component.column.Column;
import org.primefaces.context.RequestContext;

/**
 *
 * @author John Pineda
 */
@ManagedBean
@SessionScoped
public class ControlResultadosActividades {

    @EJB
    AdministrarResultadosActividadesInterface administrarResultadosActividades;

    private List<ResultadosActividades> listResultadosActividades;
    private List<ResultadosActividades> filtrarResultadosActividades;
    private ResultadosActividades guiaTrabajoSeleccionada;
    private ResultadosActividades nuevaResultadosActividades;
    private int cualCelda, tipoLista, index, tipoActualizacion, k, bandera;
    private BigInteger l;
    private boolean aceptar, guardado;
    private boolean permitirIndex;
    private Column nombre, apellido, documento, tipousuario;

    private int tamano;

    public ControlResultadosActividades() {
        listResultadosActividades = null;
        permitirIndex = true;
        guardado = true;
        tamano = 270;
        nuevaResultadosActividades = new ResultadosActividades();
    }

    /**
     * *
     * Metodo encargado de capturar la posicion en la que el usuario a
     * seleccionado
     *
     * @param indice fila
     * @param celda columna
     */
    public void cambiarIndice(int indice, int celda) {
        System.err.println("TIPO LISTA = " + tipoLista);

        if (permitirIndex == true) {
            index = indice;
            cualCelda = celda;
        }
        System.out.println("Indice: " + index + " Celda: " + cualCelda);
    }

    /**
     * *
     * Metodo encargado de cambiar la bandera tipoLista para el manejo de la
     * lista que se esta usando
     */
    public void eventoFiltrar() {
        if (tipoLista == 0) {
            tipoLista = 1;
        }
    }

    public void borrarResultadosActividades() {
        /**
         * *
         * FALTAS VALIDACIONES
         */
        if (index >= 0) {
            if (tipoLista == 0) {
                System.out.println("Entro a borrandoClasesPensiones");
                administrarResultadosActividades.eliminarResultadosActividades(listResultadosActividades.get(index));
            }
            if (tipoLista == 1) {
                System.out.println("borrandoClasesPensiones ");
                administrarResultadosActividades.eliminarResultadosActividades(filtrarResultadosActividades.get(index));
            }

            listResultadosActividades = null;
            getListResultadosActividades();
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("form:datosClasesPensiones");
            index = -1;

            if (guardado == true) {
                guardado = false;
            }
            context.update("form:ACEPTAR");
        }

    }

    /**
     * Metodo encargado de disparar el dialogo agregar una guia
     */
    public void dispararDialogoAgregar() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:RegistroNuevaResultadosActividades");
        context.execute("RegistroNuevaResultadosActividades.show()");
    }

    public void agregarNuevaResultadosActividades() {
    }

    public List<ResultadosActividades> getListResultadosActividades() {
        if (listResultadosActividades == null) {
            listResultadosActividades = administrarResultadosActividades.consultarResultadosActividades();
        }
        return listResultadosActividades;
    }

    public void setListResultadosActividades(List<ResultadosActividades> listResultadosActividades) {
        this.listResultadosActividades = listResultadosActividades;
    }

    public List<ResultadosActividades> getFiltrarResultadosActividades() {
        return filtrarResultadosActividades;
    }

    public void setFiltrarResultadosActividades(List<ResultadosActividades> filtrarResultadosActividades) {
        this.filtrarResultadosActividades = filtrarResultadosActividades;
    }

    public ResultadosActividades getResultadosActividadesSeleccionada() {
        return guiaTrabajoSeleccionada;
    }

    public void setResultadosActividadesSeleccionada(ResultadosActividades guiaTrabajoSeleccionada) {
        this.guiaTrabajoSeleccionada = guiaTrabajoSeleccionada;
    }

    public ResultadosActividades getNuevaResultadosActividades() {
        return nuevaResultadosActividades;
    }

    public void setNuevaResultadosActividades(ResultadosActividades nuevaResultadosActividades) {
        this.nuevaResultadosActividades = nuevaResultadosActividades;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

}
