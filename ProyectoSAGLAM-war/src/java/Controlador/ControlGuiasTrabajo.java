/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AdministrarInterface.AdministrarGuiasTrabajoInterface;
import Entidades.GuiaTrabajo;
import java.io.Serializable;
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
public class ControlGuiasTrabajo implements Serializable {

    @EJB
    AdministrarGuiasTrabajoInterface administrarGuiasTrabajo;

    private List<GuiaTrabajo> listGuiaTrabajo;
    private List<GuiaTrabajo> filtrarGuiaTrabajo;
    private GuiaTrabajo guiaTrabajoSeleccionada;
    private GuiaTrabajo nuevaGuiaTrabajo;
    private int cualCelda, tipoLista, index, tipoActualizacion, k, bandera;
    private BigInteger l;
    private boolean aceptar, guardado;
    private boolean permitirIndex;
    private Column nombre, apellido, documento, tipousuario;

    private int tamano;

    public ControlGuiasTrabajo() {
        listGuiaTrabajo = null;
        permitirIndex = true;
        guardado = true;
        tamano = 270;
        nuevaGuiaTrabajo = new GuiaTrabajo();
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

    public void borrarGuiaTrabajo() {
        /**
         * *
         * FALTAS VALIDACIONES
         */
        if (index >= 0) {
            if (tipoLista == 0) {
                System.out.println("Entro a borrandoClasesPensiones");
                administrarGuiasTrabajo.eliminarGuiaTrabajo(listGuiaTrabajo.get(index));
            }
            if (tipoLista == 1) {
                System.out.println("borrandoClasesPensiones ");
                administrarGuiasTrabajo.eliminarGuiaTrabajo(filtrarGuiaTrabajo.get(index));
            }

            listGuiaTrabajo = null;
            getListGuiaTrabajo();
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
        context.update("form:RegistroNuevaGuiaTrabajo");
        context.execute("RegistroNuevaGuiaTrabajo.show()");
    }

    public void agregarNuevaGuiaTrabajo() {
    }

    public List<GuiaTrabajo> getListGuiaTrabajo() {
        if (listGuiaTrabajo == null) {
            listGuiaTrabajo = administrarGuiasTrabajo.consultarGuiasTrabajo();
        }
        return listGuiaTrabajo;
    }

    public void setListGuiaTrabajo(List<GuiaTrabajo> listGuiaTrabajo) {
        this.listGuiaTrabajo = listGuiaTrabajo;
    }

    public List<GuiaTrabajo> getFiltrarGuiaTrabajo() {
        return filtrarGuiaTrabajo;
    }

    public void setFiltrarGuiaTrabajo(List<GuiaTrabajo> filtrarGuiaTrabajo) {
        this.filtrarGuiaTrabajo = filtrarGuiaTrabajo;
    }

    public GuiaTrabajo getGuiaTrabajoSeleccionada() {
        return guiaTrabajoSeleccionada;
    }

    public void setGuiaTrabajoSeleccionada(GuiaTrabajo guiaTrabajoSeleccionada) {
        this.guiaTrabajoSeleccionada = guiaTrabajoSeleccionada;
    }

    public GuiaTrabajo getNuevaGuiaTrabajo() {
        return nuevaGuiaTrabajo;
    }

    public void setNuevaGuiaTrabajo(GuiaTrabajo nuevaGuiaTrabajo) {
        this.nuevaGuiaTrabajo = nuevaGuiaTrabajo;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

}
