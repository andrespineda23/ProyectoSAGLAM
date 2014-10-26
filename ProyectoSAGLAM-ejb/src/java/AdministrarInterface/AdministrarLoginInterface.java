/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministrarInterface;

import Entidades.Usuario;
import java.math.BigInteger;

/**
 * Interface del AdministrarLogin. Esta interface contiene la informacion
 * pertinente al login del sistema.
 *
 * @author Andres Pineda
 */
public interface AdministrarLoginInterface {

    /**
     * Metodo encargado de crear un nuevo usuario en el sistema
     * @param usuario Usuario que va a ser registrado en el sistema
     */
    public void crearNuevoUsuario(Usuario usuario);

    /**
     * Metodo encargado de recordar la contraseña de un usuario por medio de su correo y numero de documento
     * @param correo Correo del usuario que solicita la contraseña
     * @param numDocumento Numero de documento del usuario que solicita la contraseña
     * @return La contraseña del usuario al cual le corresponden el correo y numero de documento ingresado
     */
    public String recordarContrasenaUsuario(String correo, String numDocumento);

    /**
     * Metodo encargado de obtener un usuario para el ingreso al sistema por su correo y contraseña
     * @param correo Correo del usuario
     * @param contrasena Contraseña del usuario
     * @return Usuario que esta registrado con el correo y contraseña dadas
     */
    public Usuario obtenerUsuarioLogin(String correo, String contrasena);

    /**
     * Metodo encargado de validar si el usuario registrado corresponde con el tipo de usuario seleccionado
     * por medio de una comparacion en la base de datos de la universidad
     * @param correo Correo electronico del usuario
     * @param documento Numero de documento del usuario
     * @param tipoUsuario Tipo de usuario seleccionado
     * @return Respuesta que indica si el tipo de usuario corresponde con el usuario
     */
    public String validarTipoUsuarioNuevoRegistro(String correo, String documento, String tipoUsuario);

    /**
     * Metodo encargado de validar si un usuario por medio de su correo y numero de documento
     * ya se encuentra registrado en el sistema
     * @param correo Correo electronico
     * @param documento Numero de documento
     * @return Usuario que posee las credenciales ingreadas
     */
    public Usuario validarUsuarioRegistradoEnSistema(String correo, String documento);
    
    /**
     * Metodo que permite buscar un usuario por medio de su secuencia de registro
     * @param secuencia Secuencia del usuario
     * @return  Usuario que corresponde a la secuencia dad
     */
     public Usuario buscarUsuarioPorSecuencia(BigInteger secuencia);

}
