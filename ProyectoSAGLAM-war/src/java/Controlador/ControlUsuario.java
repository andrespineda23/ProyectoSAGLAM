/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AdministrarInterface.AdministrarUsuarioInterface;
import Entidades.Usuario;
import java.io.Serializable;
import java.math.BigInteger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author John Pineda
 */
@ManagedBean
@SessionScoped
public class ControlUsuario implements Serializable {

    /**
     * Creates a new instance of ControlUsuario
     */
    @EJB
    AdministrarUsuarioInterface administrarUsuario;
    private String contrasenaNueva;
    private String contrasenaAntigua;
    private Usuario usuarioRecibido;
    private boolean camposVacios;
    private String paginaAnterior;
    private boolean permisoReservar, permisoPrestamo, permisoDocPracticas, permisoGuias, permisoEstadisticas, permisoUsuario, permisoMateria, permisoCerrarSesion, permisoLaboratorio;
    private boolean permisoIngresar;
    private boolean permisoBloquearUsuario;
    private String infoUsuarioConectado;
    private UploadedFile file;

    public ControlUsuario() {
        infoUsuarioConectado = "Información Usuario Conectado";
        permisoIngresar = false;
        permisoReservar = true;
        permisoPrestamo = true;
        permisoDocPracticas = true;
        permisoLaboratorio = true;
        permisoEstadisticas = true;
        permisoGuias = true;
        permisoUsuario = true;
        permisoMateria = true;
        permisoCerrarSesion = true;

    }

    public void usuarioRecibidoConectado(BigInteger secEmpleado) {
        if (secEmpleado == null) {
            System.err.println("ControlUsuario usuarioRecibidoERROR EL USUARIO ES NULO");
        } else {
            usuarioRecibido = administrarUsuario.consultarUsuarioPorSecuencia(secEmpleado);
            System.err.println("ControlUsuario usuarioRecibidoERROR usuario : " + usuarioRecibido.getCorreoelectronico());
            System.err.println("ControlUsuario usuarioRecibidoERROR contrasena : " + usuarioRecibido.getContrasena());
            activarFuncionesUsuario();
        }
    }

    /**
     * metodo encargado de guardar el nombre de la pagina donde fue llamada la
     * pagina usuario
     *
     * @param pagina Pagina que redirecciono
     */
    public void paginaRecibida(String pagina) {
        paginaAnterior = pagina;
    }

    public void validarCampos() {
        if (contrasenaAntigua != null && contrasenaNueva != null) {
            camposVacios = false;
        } else {
            camposVacios = true;
        }
    }

    /**
     * *
     * Metodo encargado de cambiar la contraseña del usuario actual compara la
     * contrasena antigua que es digitada por pantalla y si es la misma que la
     * que tiene el usuario la cambia por la contraseña que ha digitado.
     *
     */
    public void cambiarContrasena() {
        RequestContext context = RequestContext.getCurrentInstance();
        if (camposVacios == false) {
            if (usuarioRecibido.getContrasena().equals(contrasenaAntigua)) {
                System.out.println("Antes de cambiar Contraseña:" + usuarioRecibido.getContrasena());
                usuarioRecibido.setContrasena(contrasenaNueva);
                administrarUsuario.editarUsuario(usuarioRecibido);
                context.reset("form:DialogoCambiarContrasena");
                context.execute("DialogoCambiarContrasena.hide()");
            } else {
                context.update("form:contrasenaErronea");
                context.execute("contrasenaErronea.show()");
            }
        } else {
            context.update("form:errorDatosObligatorios");
            context.execute("errorDatosObligatorios.show()");
        }
    }

    public void cancelarCambioContrasena() {
        RequestContext context = RequestContext.getCurrentInstance();
        contrasenaAntigua = null;
        contrasenaAntigua = null;
        context.update("form:DialogoCambiarContrasena");
    }

    /**
     * Metodo encargado de disparar el dialogo de cambiar contraseña
     */
    public void dispararDialogoCambiarContrasena() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:DialogoCambiarContrasena");
        context.execute("DialogoCambiarContrasena.show()");
    }

    /**
     * Metodo encargado de activar las funciones registradas para el usuario que
     * se encuentra en el sistema
     */
    public void activarFuncionesUsuario() {

        if (usuarioRecibido.getTipousuario().equalsIgnoreCase("estudiante")) {
            permisoCerrarSesion = false;
            permisoDocPracticas = false;
            permisoEstadisticas = true;
            permisoGuias = true;
            permisoMateria = true;
            permisoPrestamo = false;
            permisoReservar = false;
            permisoUsuario = true;
            permisoLaboratorio = true;
            permisoBloquearUsuario = true;
        }
        if (usuarioRecibido.getTipousuario().equalsIgnoreCase("docente")) {
            permisoCerrarSesion = false;
            permisoDocPracticas = false;
            permisoEstadisticas = true;
            permisoGuias = false;
            permisoMateria = false;
            permisoPrestamo = false;
            permisoReservar = false;
            permisoUsuario = true;
            permisoLaboratorio = true;
            permisoBloquearUsuario = true;

        }
        if (usuarioRecibido.getTipousuario().equalsIgnoreCase("laboratorista")) {
            permisoCerrarSesion = false;
            permisoDocPracticas = false;
            permisoEstadisticas = false;
            permisoGuias = false;
            permisoMateria = true;
            permisoPrestamo = false;
            permisoReservar = true;
            permisoUsuario = true;
            permisoLaboratorio = false;
            permisoBloquearUsuario = false;
        }
        permisoIngresar = true;
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:PanelOpciones");
    }

    public String getContrasenaNueva() {
        return contrasenaNueva;
    }

    public void setContrasenaNueva(String contrasenaNueva) {
        this.contrasenaNueva = contrasenaNueva;
    }

    public String getContrasenaAntigua() {
        return contrasenaAntigua;
    }

    public void setContrasenaAntigua(String contrasenaAntigua) {
        this.contrasenaAntigua = contrasenaAntigua;
    }

    public Usuario getUsuarioRecibido() {
        return usuarioRecibido;
    }

    public void setUsuarioRecibido(Usuario usuarioRecibido) {
        this.usuarioRecibido = usuarioRecibido;
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

    public boolean isPermisoBloquearUsuario() {
        return permisoBloquearUsuario;
    }

    public void setPermisoBloquearUsuario(boolean permisoBloquearUsuario) {
        this.permisoBloquearUsuario = permisoBloquearUsuario;
    }

}
