/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AdministrarInterface.AdministrarPrestamosInterface;
import Entidades.PrestamoUsuario;
import Entidades.Usuario;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ANDRES PINEDA
 */
@ManagedBean
@SessionScoped
public class ControlPrestamosEspera implements Serializable {

    //Parametros
    //Inyeccion del EJB Adminitrador del controlador
    //este administrador controla todo los procesos necesarios
    //que el controlador necesite
    @EJB
    AdministrarPrestamosInterface administrarPrestamo;

    private Usuario usuarioLogin;
    private boolean permisoReservar, permisoPrestamo, permisoDocPracticas, permisoGuias, permisoEstadisticas, permisoUsuario, permisoMateria, permisoCerrarSesion, permisoLaboratorio;
    private boolean permisoIngresar;
    private String infoUsuarioConectado;

    //
    private List<PrestamoUsuario> listaPrestamos;
    private List<PrestamoUsuario> filtrarListaPrestamos;
    private PrestamoUsuario prestamoSeleccionado;
    //
    private int indice;

    public ControlPrestamosEspera() {
        listaPrestamos = null;
        prestamoSeleccionado = null;
    }

    public void obtenerPosicionTablaPrestamo() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String type = map.get("t"); // type attribute of node
        indice = Integer.parseInt(type);
    }

    public void validarPrestamoEnEspera() {
        if (prestamoSeleccionado != null) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("form:validarPrestamo");
            context.execute("validarPrestamo.show()");
        }
    }

    public void aceptarValidacionPrestamo() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            prestamoSeleccionado.getPrestamo().setEstadosolicitud("ACEPTADO");
            administrarPrestamo.modificarEstadoPrestamo(prestamoSeleccionado);
            prestamoSeleccionado = null;
            indice = -1;
            listaPrestamos = null;
            getListaPrestamos();
            context.update("form:datosPrestamosEspera");

            FacesMessage msg = new FacesMessage("Información", "El prestamo validado exitosamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.update("form:growl");
        } catch (Exception e) {
            System.out.println("Error aceptarValidacionPrestamo ControlPrestamosEspera : " + e.toString());
            FacesMessage msg = new FacesMessage("Información", "Ocurrio un error en el proceso, intente nuevamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.update("form:growl");
        }

    }

    public void cancelarValidacionPrestamo() {
        prestamoSeleccionado = null;
        indice = -1;
        RequestContext.getCurrentInstance().update("form:datosPrestamosEspera");
    }

    /**
     * Metodo encargado de recibir la secuencia del usuario de una pagina
     * anterior. Realiza la busqueda el usuario por la secuencia ingresada
     *
     * @param secuencia Secuencia del usuario
     */
    public void recibiriUsuarioConectado(BigInteger secuencia) {
        usuarioLogin = administrarPrestamo.buscarUsuarioPorSecuencia(secuencia);
        activarFuncionesUsuario();
    }

    /**
     * Metodo encargado de activar las funciones registradas para el usuario que
     * se encuentra en el sistema
     */
    public void activarFuncionesUsuario() {

        if (usuarioLogin.getTipousuario().equalsIgnoreCase("estudiante")) {
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
        if (usuarioLogin.getTipousuario().equalsIgnoreCase("docente")) {
            permisoCerrarSesion = false;
            permisoDocPracticas = false;
            permisoEstadisticas = false;
            permisoGuias = false;
            permisoMateria = false;
            permisoPrestamo = false;
            permisoReservar = false;
            permisoUsuario = false;
            permisoLaboratorio = false;
        }
        if (usuarioLogin.getTipousuario().equalsIgnoreCase("laboratorista")) {
            permisoCerrarSesion = false;
            permisoDocPracticas = false;
            permisoEstadisticas = false;
            permisoGuias = false;
            permisoMateria = false;
            permisoPrestamo = false;
            permisoReservar = false;
            permisoUsuario = false;
            permisoLaboratorio = false;
        }
        permisoIngresar = true;
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:PanelOpciones");
    }

    //GET - SET Variables
    public Usuario getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuario usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
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

    public List<PrestamoUsuario> getListaPrestamos() {
        if (listaPrestamos == null) {
            listaPrestamos = administrarPrestamo.obtenerPrestamosEnProcesoDeEspera();
        }
        return listaPrestamos;
    }

    public void setListaPrestamos(List<PrestamoUsuario> listaPrestamos) {
        this.listaPrestamos = listaPrestamos;
    }

    public List<PrestamoUsuario> getFiltrarListaPrestamos() {
        return filtrarListaPrestamos;
    }

    public void setFiltrarListaPrestamos(List<PrestamoUsuario> filtrarListaPrestamos) {
        this.filtrarListaPrestamos = filtrarListaPrestamos;
    }

    public PrestamoUsuario getPrestamoSeleccionado() {
        return prestamoSeleccionado;
    }

    public void setPrestamoSeleccionado(PrestamoUsuario prestamoSeleccionado) {
        this.prestamoSeleccionado = prestamoSeleccionado;
    }

    public boolean isPermisoLaboratorio() {
        return permisoLaboratorio;
    }

    public void setPermisoLaboratorio(boolean permisoLaboratorio) {
        this.permisoLaboratorio = permisoLaboratorio;
    }

}
