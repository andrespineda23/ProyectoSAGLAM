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
import java.util.Map;
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
 * @author JOHN PINEDA
 */
@ManagedBean
@SessionScoped
public class ControlBloquearUsuario implements Serializable {

    @EJB
    AdministrarBloquearUsuarioInterface administrarBloquearUsuario;

    private List<Usuario> listUsuario;
    private List<Usuario> filtrarUsuario;
    private Usuario usuarioSeleccionado;
    private int tipoLista, index;
    private BigInteger l;
    private boolean aceptar, guardado;
    private boolean permisoReservar,
            permisoPrestamo,
            permisoDocPracticas,
            permisoGuias,
            permisoEstadisticas,
            permisoUsuario,
            permisoMateria,
            permisoCerrarSesion,
            permisoLaboratorio;
    private boolean permisoIngresar;
    private String infoUsuarioConectado;

    private int tamano;

    public ControlBloquearUsuario() {
        listUsuario = null;
        guardado = true;
        tamano = 270;
    }

    /**
     * *
     * Metodo encargado de recibir el usuario conectado.
     *
     * @param secuencia
     */
    public void recibiriUsuarioConectado(BigInteger secuencia) {
        usuarioSeleccionado = administrarBloquearUsuario.consultarUsuarioPorSecuencia(secuencia);
        activarFuncionesUsuario();
    }

    /**
     * metodo usado por el remotecommand para saber en que registro a
     * seleccionado
     */
    public void obtenerPosicionBloquar() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String type = map.get("t"); // type attribute of node
        index = Integer.parseInt(type);
    }

    /**
     * *
     * Este metodo se encarga de que el usuario que ha sido seleccionado y que
     * decide bloquear cambia su campo activo a false para que no sea mas
     * visible
     */
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

    /**
     * Metodo encargado de dar los permisos dependiendo del tipo de usuario que
     * este conectado
     */
    public void activarFuncionesUsuario() {
        permisoIngresar = true;
        infoUsuarioConectado = usuarioSeleccionado.getNombres() + " " + usuarioSeleccionado.getApellidos();
        if (usuarioSeleccionado.getTipousuario().equalsIgnoreCase("estudiante")) {
            permisoCerrarSesion = false;
            permisoDocPracticas = false;
            permisoEstadisticas = true;
            permisoGuias = true;
            permisoMateria = true;
            permisoPrestamo = false;
            permisoReservar = false;
            permisoUsuario = false;
            permisoLaboratorio = true;
        }
        if (usuarioSeleccionado.getTipousuario().equalsIgnoreCase("docente")) {
            permisoCerrarSesion = false;
            permisoDocPracticas = false;
            permisoEstadisticas = true;
            permisoGuias = false;
            permisoMateria = false;
            permisoPrestamo = false;
            permisoReservar = false;
            permisoUsuario = false;
            permisoLaboratorio = true;
        }
        if (usuarioSeleccionado.getTipousuario().equalsIgnoreCase("laboratorista")) {
            permisoCerrarSesion = false;
            permisoDocPracticas = false;
            permisoEstadisticas = false;
            permisoGuias = false;
            permisoMateria = true;
            permisoPrestamo = false;
            permisoReservar = false;
            permisoUsuario = false;
            permisoLaboratorio = false;
        }
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

    public boolean isPermisoReservar() {
        return permisoReservar;
    }

    public void setPermisoReservar(boolean permisoReservar) {
        this.permisoReservar = permisoReservar;
    }

    public boolean isPermisoPrestamo() {
        return permisoPrestamo;
    }

    public void setPermisoPrestamo(boolean permisoPrestamo) {
        this.permisoPrestamo = permisoPrestamo;
    }

    public boolean isPermisoDocPracticas() {
        return permisoDocPracticas;
    }

    public void setPermisoDocPracticas(boolean permisoDocPracticas) {
        this.permisoDocPracticas = permisoDocPracticas;
    }

    public boolean isPermisoGuias() {
        return permisoGuias;
    }

    public void setPermisoGuias(boolean permisoGuias) {
        this.permisoGuias = permisoGuias;
    }

    public boolean isPermisoEstadisticas() {
        return permisoEstadisticas;
    }

    public void setPermisoEstadisticas(boolean permisoEstadisticas) {
        this.permisoEstadisticas = permisoEstadisticas;
    }

    public boolean isPermisoUsuario() {
        return permisoUsuario;
    }

    public void setPermisoUsuario(boolean permisoUsuario) {
        this.permisoUsuario = permisoUsuario;
    }

    public boolean isPermisoMateria() {
        return permisoMateria;
    }

    public void setPermisoMateria(boolean permisoMateria) {
        this.permisoMateria = permisoMateria;
    }

    public boolean isPermisoCerrarSesion() {
        return permisoCerrarSesion;
    }

    public void setPermisoCerrarSesion(boolean permisoCerrarSesion) {
        this.permisoCerrarSesion = permisoCerrarSesion;
    }

    public boolean isPermisoLaboratorio() {
        return permisoLaboratorio;
    }

    public void setPermisoLaboratorio(boolean permisoLaboratorio) {
        this.permisoLaboratorio = permisoLaboratorio;
    }

    public boolean isPermisoIngresar() {
        return permisoIngresar;
    }

    public void setPermisoIngresar(boolean permisoIngresar) {
        this.permisoIngresar = permisoIngresar;
    }

    public String getInfoUsuarioConectado() {
        return infoUsuarioConectado;
    }

    public void setInfoUsuarioConectado(String infoUsuarioConectado) {
        this.infoUsuarioConectado = infoUsuarioConectado;
    }

}
