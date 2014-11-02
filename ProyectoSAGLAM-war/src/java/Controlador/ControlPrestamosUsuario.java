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
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ANDRES PINEDA
 */
@ManagedBean
@SessionScoped
public class ControlPrestamosUsuario implements Serializable {

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

    private List<PrestamoUsuario> listaPrestamosUsuario;
    private List<PrestamoUsuario> filtrarListaPrestamosUsuario;
    private PrestamoUsuario prestamoSeleccionado;

    public ControlPrestamosUsuario() {
        listaPrestamosUsuario = null;
        prestamoSeleccionado = new PrestamoUsuario();
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
        permisoIngresar = true;
        infoUsuarioConectado = usuarioLogin.getNombres() + " " + usuarioLogin.getApellidos();
        if (usuarioLogin.getTipousuario().equalsIgnoreCase("estudiante")) {
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
        if (usuarioLogin.getTipousuario().equalsIgnoreCase("docente")) {
            permisoCerrarSesion = false;
            permisoDocPracticas = false;
            permisoEstadisticas = true;
            permisoGuias = false;
            permisoMateria = true;
            permisoPrestamo = false;
            permisoReservar = false;
            permisoUsuario = false;
            permisoLaboratorio = true;
        }
        if (usuarioLogin.getTipousuario().equalsIgnoreCase("laboratorista")) {
            permisoCerrarSesion = false;
            permisoDocPracticas = false;
            permisoEstadisticas = false;
            permisoGuias = false;
            permisoMateria = false;
            permisoPrestamo = false;
            permisoReservar = true;
            permisoUsuario = false;
            permisoLaboratorio = false;
        }
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

    public List<PrestamoUsuario> getListaPrestamosUsuario() {
        if (usuarioLogin.getSecuencia() != null) {
            listaPrestamosUsuario = administrarPrestamo.obtenerPrestamosDeUnUsuario(usuarioLogin.getSecuencia());
        }
        return listaPrestamosUsuario;
    }

    public void setListaPrestamosUsuario(List<PrestamoUsuario> listaPrestamosUsuario) {
        this.listaPrestamosUsuario = listaPrestamosUsuario;
    }

    public List<PrestamoUsuario> getFiltrarListaPrestamosUsuario() {
        return filtrarListaPrestamosUsuario;
    }

    public void setFiltrarListaPrestamosUsuario(List<PrestamoUsuario> filtrarListaPrestamosUsuario) {
        this.filtrarListaPrestamosUsuario = filtrarListaPrestamosUsuario;
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
