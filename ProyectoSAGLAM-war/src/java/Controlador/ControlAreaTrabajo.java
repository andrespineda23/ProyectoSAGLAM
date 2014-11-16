/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AdministrarInterface.AdministrarAreaTrabajoInterface;
import Entidades.AreaTrabajo;
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
 * Este controlador es el encargado de la pagina de areatrabajo. Todos los
 * procesos relacionados con la operaciones de las areas de trabajo este
 * controlador se encargara de operarlos
 *
 * @author Andres Pineda
 */
@ManagedBean
@SessionScoped
public class ControlAreaTrabajo implements Serializable {

    //Parametros
    /**
     * Inyeccion del EJB Adminitrador del controlador este administrador
     * controla todo los procesos necesarios que el controlador necesite
     */
    @EJB
    AdministrarAreaTrabajoInterface administrarAreaTrabajo;

    /**
     * Lista de areas de trabajo de la tabla de la pagina
     */
    private List<AreaTrabajo> listaAreasDeTrabajo;
    /**
     * Lista de los procesos filtrados de la lista de areas de trabajo
     */
    private List<AreaTrabajo> filtrarListaAreasDeTrabajo;
    /**
     * Area de Trabajo seleccionada
     */
    private AreaTrabajo seleccionarAreaTrabajo;

    private AreaTrabajo nuevaAreaTrabajo;

    private Usuario usuarioLogin;
    private boolean permisoReservar, permisoPrestamo, permisoDocPracticas, permisoGuias, permisoEstadisticas, permisoUsuario, permisoMateria, permisoCerrarSesion, permisoLaboratorio;
    private boolean permisoIngresar;
    private String infoUsuarioConectado;

    private int indice;
    private int k;

    public ControlAreaTrabajo() {
        k++;
        listaAreasDeTrabajo = null;
        seleccionarAreaTrabajo = null;
        nuevaAreaTrabajo = new AreaTrabajo();
    }

    public void agregarNuevaAreaTrabajo() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            if (nuevaAreaTrabajo.getNombrearea() != null && nuevaAreaTrabajo.getCantmaxpersonas() > 0) {
                k++;
                BigInteger secuencia = new BigInteger(String.valueOf(k));
                nuevaAreaTrabajo.setSecuencia(secuencia);
                administrarAreaTrabajo.crearAreaDeTrabajo(nuevaAreaTrabajo);
                nuevaAreaTrabajo = new AreaTrabajo();
                context.execute("RegistroNuevaAreaTrabajo.hide()");
                FacesMessage msg = new FacesMessage("Información", "Se registro la nueva area de trabajo con éxito");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                context.update("form:growl");
                listaAreasDeTrabajo = null;
                filtrarListaAreasDeTrabajo = null;
                getListaAreasDeTrabajo();
                context.update("form:datosAreaTrabajo");
            } else {
                context.execute("errorDatosNull.show()");
            }
        } catch (Exception e) {
            System.out.println("Error agregarNuevaAreaTrabajo ControlAreaTrabajo : " + e.toString());
            FacesMessage msg = new FacesMessage("Información", "Ocurrio un error en el guardado de la area de trabajo");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.update("form:growl");
        }
    }

    public void cancelarNuevaAreaTrabajo() {
        nuevaAreaTrabajo = new AreaTrabajo();
    }

    public void obtenerPosicionArea() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String type = map.get("t"); // type attribute of node
        indice = Integer.parseInt(type);
    }

    /**
     * Metodo encargado de recibir la secuencia del usuario de una pagina
     * anterior. Realiza la busqueda el usuario por la secuencia ingresada
     *
     * @param secuencia Secuencia del usuario
     */
    public void recibiriUsuarioConectado(BigInteger secuencia) {
        usuarioLogin = administrarAreaTrabajo.buscarUsuarioPorSecuencia(secuencia);
        activarFuncionesUsuario();
    }

    /**
     * Metodo encargado de activar las funciones registradas para el usuario que
     * se encuentra en el sistema
     */
    public void activarFuncionesUsuario() {
        infoUsuarioConectado = usuarioLogin.getNombres() + " " + usuarioLogin.getApellidos();

        if (usuarioLogin.getTipousuario().equalsIgnoreCase("estudiante")) {
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

    public List<AreaTrabajo> getListaAreasDeTrabajo() {
        if (listaAreasDeTrabajo == null) {
            listaAreasDeTrabajo = administrarAreaTrabajo.buscarAreasDeTrabajo();
        }
        return listaAreasDeTrabajo;
    }

    public void setListaAreasDeTrabajo(List<AreaTrabajo> listaAreasDeTrabajo) {
        this.listaAreasDeTrabajo = listaAreasDeTrabajo;
    }

    public List<AreaTrabajo> getFiltrarListaAreasDeTrabajo() {
        return filtrarListaAreasDeTrabajo;
    }

    public void setFiltrarListaAreasDeTrabajo(List<AreaTrabajo> filtrarListaAreasDeTrabajo) {
        this.filtrarListaAreasDeTrabajo = filtrarListaAreasDeTrabajo;
    }

    public AreaTrabajo getSeleccionarAreaTrabajo() {
        return seleccionarAreaTrabajo;
    }

    public void setSeleccionarAreaTrabajo(AreaTrabajo seleccionarAreaTrabajo) {
        this.seleccionarAreaTrabajo = seleccionarAreaTrabajo;
    }

    public AreaTrabajo getNuevaAreaTrabajo() {
        return nuevaAreaTrabajo;
    }

    public void setNuevaAreaTrabajo(AreaTrabajo nuevaAreaTrabajo) {
        this.nuevaAreaTrabajo = nuevaAreaTrabajo;
    }

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
