/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AdministrarInterface.AdministrarLoginInterface;
import Entidades.Usuario;
import java.io.Serializable;
import java.math.BigInteger;
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
public class ControlLogin implements Serializable {

    @EJB
    AdministrarLoginInterface administrarLogin;

    private Usuario nuevoUsuarioRegistrar;
    private Usuario usuarioLogin;
    private String correo, contrasena, numDocumento;
    private String contrasenaRecuperada;

    public ControlLogin() {

        contrasenaRecuperada = null;
        correo = null;
        contrasena = null;
        numDocumento = null;

        nuevoUsuarioRegistrar = new Usuario();
        usuarioLogin = null;

    }

    public void dispararDialogoRegistro() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:RegistroNuevoUsuario");
        context.execute("RegistroNuevoUsuario.show()");
    }

    public void dispararDialogoLogin() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:loginUsuario");
        context.execute("loginUsuario.show()");
    }

    public void dispararDialogoRecuperar() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:recuperarContrasena");
        context.execute("recuperarContrasena.show()");
    }

    public boolean validarUsuarioYaRegistrado() {
        boolean retorno = true;
        Usuario usuario = administrarLogin.validarUsuarioRegistradoEnSistema(nuevoUsuarioRegistrar.getCorreoelectronico(), nuevoUsuarioRegistrar.getNumerodocumento());
        if (usuario != null) {
            retorno = false;
        }
        return retorno;
    }

    public boolean validarDatosObligatorios() {
        boolean retorno = true;
        if (nuevoUsuarioRegistrar.getNombres() != null
                && nuevoUsuarioRegistrar.getApellidos() != null
                && nuevoUsuarioRegistrar.getContrasena() != null
                && nuevoUsuarioRegistrar.getCorreoelectronico() != null
                && nuevoUsuarioRegistrar.getNumerodocumento() != null
                && nuevoUsuarioRegistrar.getTipousuario() != null) {
            retorno = true;
        } else {
            retorno = false;
        }
        return retorno;
    }

    public boolean validarTipoUsuarioNuevoRegistro() {
        boolean retorno = true;
        String respuesta = administrarLogin.validarTipoUsuarioNuevoRegistro(nuevoUsuarioRegistrar.getCorreoelectronico(), nuevoUsuarioRegistrar.getNumerodocumento(), nuevoUsuarioRegistrar.getTipousuario());
        if (respuesta != null) {
            if (respuesta.equalsIgnoreCase("S")) {
                retorno = true;
            } else {
                retorno = false;
            }
        } else {
            retorno = false;
        }
        return retorno;
    }

    public void registarNuevoUsuario() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            boolean datosOk = validarDatosObligatorios();
            if (datosOk == true) {
                boolean usuarioOk = validarUsuarioYaRegistrado();
                if (usuarioOk == true) {
                    boolean tipoUsuarioOk = validarTipoUsuarioNuevoRegistro();
                    if (tipoUsuarioOk == true) {
                        int k = 1;
                        BigInteger secuencia = new BigInteger(String.valueOf(k));
                        nuevoUsuarioRegistrar.setSecuencia(secuencia);
                        nuevoUsuarioRegistrar.setActivo(true);

                        administrarLogin.crearNuevoUsuario(usuarioLogin);

                        nuevoUsuarioRegistrar = new Usuario();

                        context.execute("RegistroNuevoUsuario.hide()");
                        FacesMessage msg = new FacesMessage("Información", "Se registro el nuevo usuario con éxito");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        context.update("form:growl");
                    } else {
                        context.execute("errorTipoUsuario.show()");
                    }
                } else {
                    context.execute("errorUsuarioRegistrado.show()");
                }
            } else {
                context.execute("errorDatosObligatorios.show()");
            }
        } catch (Exception e) {
            System.out.println("Error registarNuevoUsuario ControlLogin : " + e.toString());
            FacesMessage msg = new FacesMessage("Información", "Ocurrio un error en el guardado del usuario, intente nuevamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.update("form:growl");
        }
    }

    public void cancelarNuevoRegistroUsuario() {
        nuevoUsuarioRegistrar = new Usuario();
    }

    public void loginUsuario() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            if (correo != null && contrasena != null) {
                context.execute("loginUsuario.hide()");
                Usuario user = administrarLogin.obtenerUsuarioLogin(correo, contrasena);
                if (user == null) {
                    context.execute("errorUsuarioNoExiste.show()");
                } else {
                    usuarioLogin = user;
                    contrasena = null;
                    correo = null;
                    FacesMessage msg = new FacesMessage("Información", "Ingreso exitoso");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    context.update("form:growl");
                }
            } else {
                context.execute("errorDatosObligatorios.show()");
            }
        } catch (Exception e) {
            System.out.println("Error loginUsuario ControlLogin : " + e.toString());
            FacesMessage msg = new FacesMessage("Información", "Ocurrio un error en el proceso, intente nuevamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.update("form:growl");
        }
    }

    public void cancelarLoginUsuario() {
        contrasena = null;
        correo = null;
    }

    public void recuperarContrasenaUsuario() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            if (correo != null && numDocumento != null) {
                context.execute("recuperarContrasena.hide()");
                String pass = administrarLogin.recordarContrasenaUsuario(correo, numDocumento);
                if (pass != null) {
                    contrasenaRecuperada = pass;
                    context.execute("errorContrasenaRecuperada.show()");
                } else {
                    context.execute("errorContrasenaRecuperada.show()");
                }
            } else {
                context.execute("errorDatosObligatorios.show()");
            }
        } catch (Exception e) {
            System.out.println("Error recuperarContrasenaUsuario ControlLogin : " + e.toString());
            FacesMessage msg = new FacesMessage("Información", "Ocurrio un error en el proceso, intente nuevamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.update("form:growl");
        }
    }

    public void cancelarRecuperarContrasena() {
        correo = null;
        contrasena = null;
    }

    public Usuario getNuevoUsuarioRegistrar() {
        return nuevoUsuarioRegistrar;
    }

    public void setNuevoUsuarioRegistrar(Usuario nuevoUsuarioRegistrar) {
        this.nuevoUsuarioRegistrar = nuevoUsuarioRegistrar;
    }

    public Usuario getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuario usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getContrasenaRecuperada() {
        return contrasenaRecuperada;
    }

    public void setContrasenaRecuperada(String contrasenaRecuperada) {
        this.contrasenaRecuperada = contrasenaRecuperada;
    }

}
