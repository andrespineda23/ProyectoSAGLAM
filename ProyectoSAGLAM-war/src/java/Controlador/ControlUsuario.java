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

    public ControlUsuario() {

    }

    public void usuarioRecibidoConectado(BigInteger secEmpleado) {
        if (secEmpleado == null) {
            System.err.println("ControlUsuario usuarioRecibidoERROR EL USUARIO ES NULO");
        } else {
            usuarioRecibido = administrarUsuario.consultarUsuarioPorSecuencia(secEmpleado);
            System.err.println("ControlUsuario usuarioRecibidoERROR usuario : " + usuarioRecibido.getCorreoelectronico());
            System.err.println("ControlUsuario usuarioRecibidoERROR contrasena : " + usuarioRecibido.getContrasena());
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
            } else {
                context.update("form:contrasenaErronea");
                context.execute("contrasenaErronea.show()");
            }
        } else {
            context.update("form:errorDatosObligatorios");
            context.execute("errorDatosObligatorios.show()");
        }
    }

    public void cancelarNuevoRegistroUsuario() {
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

}
