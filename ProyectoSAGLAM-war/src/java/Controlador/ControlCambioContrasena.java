/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AdministrarInterface.AdministrarCambioContrasenaInterface;
import Entidades.Usuario;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author John Pineda
 */
@ManagedBean
@SessionScoped
public class ControlCambioContrasena implements Serializable {

    @EJB
    AdministrarCambioContrasenaInterface administrarCambioContrasena;
    private Usuario usuarioRecibido;
    private String nuevaConstrasena;
    private boolean camposVacios;
    private String mensajeValidacion;

    public ControlCambioContrasena() {
        usuarioRecibido = new Usuario();
        nuevaConstrasena = null;
        usuarioRecibido.setContrasena(null);

    }

    /**
     * *
     * metodo encargado de recivir el usuario de la pantalla anterior
     *
     * @param llegoUsuario Usuario Actual
     */
    public void recibirUsuario(Usuario llegoUsuario) {
        try {
            usuarioRecibido = llegoUsuario;
        } catch (Exception e) {
            System.err.println("ControlCambioContrasena recibirUsuario Error: " + e);
        }
    }

    /**
     * Valida que los campos no esten vacios
     */
    public void validarCampos() {
        if (usuarioRecibido.getContrasena() == null && nuevaConstrasena == null) {
            camposVacios = false;
        } else {
            camposVacios = true;
        }
    }

    /**
     * Metodo de cambiar la contraseña digitada por el usuario por pantalla
     *
     */
    public void cambiarContrasena() {
        RequestContext context = RequestContext.getCurrentInstance();
        validarCampos();
        Usuario validarU;
        if (camposVacios == true) {
            validarU = administrarCambioContrasena.validarContrasenaAntigua(usuarioRecibido);
            if (validarU == null) {
                mensajeValidacion = "Contraseña Incorrecta";
            } else {
                usuarioRecibido.setContrasena(nuevaConstrasena);
                administrarCambioContrasena.editarUsuario(usuarioRecibido);
                mensajeValidacion = "Cambio Realizado Con Exito";
            }
        } else {
            mensajeValidacion = "Llenar Los Campos Requeridos";
        }
        System.out.println("Mensaje Validación : " + mensajeValidacion);
        context.update("form:mensajeMostrar");
        context.execute("mensajeMostrar.show()");

    }

    public Usuario getUsuarioRecibido() {
        return usuarioRecibido;
    }

    public void setUsuarioRecibido(Usuario usuarioRecibido) {
        this.usuarioRecibido = usuarioRecibido;
    }

    public String getNuevaConstrasena() {
        return nuevaConstrasena;
    }

    public void setNuevaConstrasena(String nuevaConstrasena) {
        this.nuevaConstrasena = nuevaConstrasena;
    }

    public String getMensajeValidacion() {
        return mensajeValidacion;
    }

    public void setMensajeValidacion(String mensajeValidacion) {
        this.mensajeValidacion = mensajeValidacion;
    }

}
