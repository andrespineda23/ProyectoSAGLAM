/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidades.Usuario;
import AdministrarInterface.AdministrarBloquearUsuarioInterface;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.column.Column;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.Exporter;
import org.primefaces.context.RequestContext;

/**
 *
 * @author user
 */
@ManagedBean
@SessionScoped
public class ControlBloquearUsuario implements Serializable {

    @EJB
    AdministrarBloquearUsuarioInterface administrarBloquearUsuario;

    private List<Usuario> listUsuario;
    private List<Usuario> filtrarUsuario;
    private Usuario usuarioSeleccionado;
    private int cualCelda, tipoLista, index, tipoActualizacion, k, bandera;
    private BigInteger l;
    private boolean aceptar, guardado;
    private boolean permitirIndex;
    private Column codigo, descripcion;

    private int tamano;

    public ControlBloquearUsuario() {
        listUsuario = null;
        permitirIndex = true;
        guardado = true;
        tamano = 270;
        System.out.println("controlBloquearUsuario Constructor");
    }

    public void eventoFiltrar() {
        try {
            System.out.println("\n ENTRE A ControlClasesPensiones.eventoFiltrar \n");
            if (tipoLista == 0) {
                tipoLista = 1;
            }
            RequestContext context = RequestContext.getCurrentInstance();
        } catch (Exception e) {
            System.out.println("ERROR ControlClasesPensiones eventoFiltrar ERROR : " + e.getMessage());
        }
    }

    public void cambiarIndice(int indice, int celda) {
        System.err.println("TIPO LISTA = " + tipoLista);

        if (permitirIndex == true) {
            index = indice;
            cualCelda = celda;
        }
        System.out.println("Indice: " + index + " Celda: " + cualCelda);
    }

    public void asignarIndex(Integer indice, int LND, int dig) {
        try {
            System.out.println("\n ENTRE A ControlClasesPensiones.asignarIndex \n");
            index = indice;
            if (LND == 0) {
                tipoActualizacion = 0;
            } else if (LND == 1) {
                tipoActualizacion = 1;
                System.out.println("Tipo Actualizacion: " + tipoActualizacion);
            } else if (LND == 2) {
                tipoActualizacion = 2;
            }

        } catch (Exception e) {
            System.out.println("ERROR ControlClasesPensiones.asignarIndex ERROR : " + e.getMessage());
        }
    }

    public void activarAceptar() {
        aceptar = false;
    }

    public void activarCtrlF11() {
        FacesContext c = FacesContext.getCurrentInstance();
        if (bandera == 0) {
            tamano = 246;
            codigo = (Column) c.getViewRoot().findComponent("form:datosBloquearUsuario:codigo");
            codigo.setFilterStyle("width: 170px");
            descripcion = (Column) c.getViewRoot().findComponent("form:datosBloquearUsuario:descripcion");
            descripcion.setFilterStyle("width: 400px");
            RequestContext.getCurrentInstance().update("form:datosBloquearUsuario");
            System.out.println("Activar");
            bandera = 1;
        } else if (bandera == 1) {
            System.out.println("Desactivar");
            tamano = 270;
            codigo = (Column) c.getViewRoot().findComponent("form:datosBloquearUsuario:codigo");
            codigo.setFilterStyle("display: none; visibility: hidden;");
            descripcion = (Column) c.getViewRoot().findComponent("form:datosBloquearUsuario:descripcion");
            descripcion.setFilterStyle("display: none; visibility: hidden;");
            RequestContext.getCurrentInstance().update("form:datosBloquearUsuario");
            bandera = 0;
            filtrarUsuario = null;
            tipoLista = 0;
        }
    }

    public void bloquearUsuario() {
        RequestContext context = RequestContext.getCurrentInstance();
        if (tipoLista == 0) {
            listUsuario.get(index).setActivo(false);
            administrarBloquearUsuario.modificarUsuario(listUsuario.get(index));
        } else {
            filtrarUsuario.get(index).setActivo(false);
            administrarBloquearUsuario.modificarUsuario(filtrarUsuario.get(index));
        }

        System.out.println("Se guardaron los datos con exito");
        listUsuario = null;
        getListUsuario();
        FacesMessage msg = new FacesMessage("Información", "Se gurdarón los datos con éxito");
        context.update("form:datosBloquearUsuario");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.update("form:growl");

        index = -1;
        RequestContext.getCurrentInstance().update("form:ACEPTAR");

    }

    //*/*/*/*/*/*/*/*/*/*-/-*//-*/-*/*/*-*/-*/-*/*/*/*/*/---/*/*/*/*/-*/-*/-*/-*/-*/
    public List<Usuario> getListUsuario() {
        if (listUsuario == null) {
            listUsuario = administrarBloquearUsuario.consultarUsuario();
        }
        return listUsuario;
    }

    public void setListUsuario(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }

    public List<Usuario> getFiltrarUsuario() {
        return filtrarUsuario;
    }

    public void setFiltrarUsuario(List<Usuario> filtrarUsuario) {
        this.filtrarUsuario = filtrarUsuario;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

}
