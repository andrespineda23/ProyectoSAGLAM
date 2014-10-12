/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministrarInterface;

import Entidades.Usuario;

/**
 *
 * @author ANDRES PINEDA
 */
public interface AdministrarLoginInterface {

    public void crearNuevoUsuario(Usuario usuario);

    public String recordarContrasenaUsuario(String correo, String numDocumento);

    public Usuario obtenerUsuarioLogin(String correo, String contrasena);

    public String validarTipoUsuarioNuevoRegistro(String correo, String documento, String tipoUsuario);

    public Usuario validarUsuarioRegistradoEnSistema(String correo, String documento);

}
