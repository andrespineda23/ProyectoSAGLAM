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
 * Este controlador es el encargado de la pagina inicial del sistema. Cuando el
 * usuario ingresa, la pagina de login sera la inicial para todos. Se encarga de
 * los procesos de ingreso al sistema, registro de usuario y recordar contraseña
 * de un usuario
 *
 * @author Andres Pineda
 */
@ManagedBean
@SessionScoped
public class ControlLogin implements Serializable {

    //Parametros
    //Inyeccion del EJB Adminitrador del controlador
    //este administrador controla todo los procesos necesarios
    //que el controlador necesite
    @EJB
    AdministrarLoginInterface administrarLogin;

    //Variable del usuario nuevo que se puede registrar
    private Usuario nuevoUsuarioRegistrar;
    //variable del usuario que ingresa al sistema
    private Usuario usuarioLogin;
    //Variables que almacenan la informacion de correo, contraseña y numero de documento de un usuario
    private String correo, contrasena, numDocumento;
    //Si se presenta un proceso de recuperar contraseña, esta variable se encargara de almacenarla
    private String contrasenaRecuperada;

    /**
     * Constructor del controlador login
     */
    public ControlLogin() {

        contrasenaRecuperada = null;
        correo = null;
        contrasena = null;
        numDocumento = null;

        nuevoUsuarioRegistrar = new Usuario();
        usuarioLogin = null;

    }

    /**
     * Metodo encargado de disparar el dialogo del registro de un nuevo usuario
     */
    public void dispararDialogoRegistro() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:RegistroNuevoUsuario");
        context.execute("RegistroNuevoUsuario.show()");
    }

    /**
     * Metodo encargado de disparar el dialogo del ingreso al sistema
     */
    public void dispararDialogoLogin() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:loginUsuario");
        context.execute("loginUsuario.show()");
    }

    /**
     * Metodo encargado de disparar el dialogo de recuperar la contraseña de un
     * usuario
     */
    public void dispararDialogoRecuperar() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:recuperarContrasena");
        context.execute("recuperarContrasena.show()");
    }

    /**
     * Metodo encargado de validar que el usuario registrado para el nuevo
     * ingreso no se encuentre ya registrado en el sistema
     *
     * @return Retorna true si el usuario se encuentra registrado en el sistema,
     * false si no esta almacenado
     */
    public boolean validarUsuarioYaRegistrado() {
        boolean retorno = true;
        Usuario usuario = administrarLogin.validarUsuarioRegistradoEnSistema(nuevoUsuarioRegistrar.getCorreoelectronico(), nuevoUsuarioRegistrar.getNumerodocumento());
        if (usuario != null) {
            retorno = false;
        }
        return retorno;
    }

    /**
     * Metodo encargado de validar los datos del ingreso de un nuevo registro de
     * usuario
     *
     * @return Retorna true si todos los campos estan completos, false en caso
     * de que un campo se encuentre vacio
     */
    public boolean validarDatosObligatorios() {
        boolean retorno = true;
        System.out.println(" Nombre: " + nuevoUsuarioRegistrar.getNombres());
        System.out.println(" Apellido: " + nuevoUsuarioRegistrar.getApellidos());
        System.out.println(" Contraseña: " + nuevoUsuarioRegistrar.getContrasena());
        System.out.println(" Correo Electronico: " + nuevoUsuarioRegistrar.getCorreoelectronico());
        System.out.println(" Numero Documento: " + nuevoUsuarioRegistrar.getNumerodocumento());
        System.out.println(" Tipo Usuario: " + nuevoUsuarioRegistrar.getTipousuario());

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

    /**
     * Metodo que valida que el tipo de usuario seleccionado por el usuario
     * corresponda al registrado en la universidad
     *
     * @return Retorna true si el nuevo usuario corresponde con el tipo de
     * usuario seleccionado, false en caso contrario
     */
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

    /**
     * Metodo encargado de registrar un nuevo usuario en el sistema
     */
    public void registarNuevoUsuario() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            boolean datosOk = validarDatosObligatorios();
            System.out.println("1 datosOk : " + datosOk);
            if (datosOk == true) {
                System.out.println("2 datosOk : " + datosOk);
                boolean usuarioOk = validarUsuarioYaRegistrado();
                if (usuarioOk == true) {
                    System.out.println("3 datosOk : " + datosOk);
                    boolean tipoUsuarioOk = validarTipoUsuarioNuevoRegistro();
                    if (tipoUsuarioOk == true) {
                        System.out.println("4 datosOk : " + datosOk);
                        int k = 1;
                        BigInteger secuencia = new BigInteger(String.valueOf(k));
                        nuevoUsuarioRegistrar.setSecuencia(secuencia);
                        nuevoUsuarioRegistrar.setActivo(true);
                        System.out.println(" Nombre: " + nuevoUsuarioRegistrar.getNombres());
                        System.out.println(" Apellido: " + nuevoUsuarioRegistrar.getApellidos());
                        System.out.println(" Contraseña: " + nuevoUsuarioRegistrar.getContrasena());
                        System.out.println(" Correo Electronico: " + nuevoUsuarioRegistrar.getCorreoelectronico());
                        System.out.println(" Numero Documento: " + nuevoUsuarioRegistrar.getNumerodocumento());
                        System.out.println(" Tipo Usuario: " + nuevoUsuarioRegistrar.getTipousuario());
                        System.out.println(" Secuencia: " + nuevoUsuarioRegistrar.getSecuencia());
                        System.out.println(" Activo: " + nuevoUsuarioRegistrar.getActivo());
                        administrarLogin.crearNuevoUsuario(nuevoUsuarioRegistrar);

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

    /**
     * Metodo encargado de limpiar la informacion del registro del nuevo usuario
     */
    public void cancelarNuevoRegistroUsuario() {
        nuevoUsuarioRegistrar = new Usuario();
    }

    /**
     * Metodo encargado de realizar el proceso de ingreso al sistema, validando
     * que los campos de correo y contraseña se encuentren correctos y
     * realizando la consulta del usuario por los parametros
     */
    public void loginUsuario() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            System.out.println("ControlLogin Correo: " + correo);
            System.out.println("ControlLogin Contraseña:" + contrasena);
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

    /**
     * Metodo encargado de limpiar las variables de ingreso al sistema despues
     * de haber cancelado el proceso
     */
    public void cancelarLoginUsuario() {
        contrasena = null;
        correo = null;
    }

    /**
     * Metodo encargado de recuperar la contraseña de un usuario por medio de su
     * correo y numero de documento
     */
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

    /**
     * Metodo encargado de limpiar la informacion del proceso de recuperar
     * contraseña despues que se cancelo el proceso
     */
    public void cancelarRecuperarContrasena() {
        correo = null;
        contrasena = null;
    }

    //GET - SET Variables
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
