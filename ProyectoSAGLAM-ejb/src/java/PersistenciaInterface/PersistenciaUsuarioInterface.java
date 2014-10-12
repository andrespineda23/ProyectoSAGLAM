/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenciaInterface;

import Entidades.Usuario;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ANDRES PINEDA
 */
public interface PersistenciaUsuarioInterface {

    public void crearUsuario(Usuario usuario);

    public void editarUsuario(Usuario usuario);

    public void borrarUsuario(Usuario usuario);

    public List<Usuario> buscarUsuarios();

    public Usuario buscarUsuarioSecuencia(BigInteger secuencia);

    public Usuario buscarUsuarioPorCorreo_Contrasena(String correo, String contrasena);

    public Usuario buscarUsuarioPorCorreo(String correo);

    public String buscarContrasenaUsuarioPorCorreo_NumeroDocumento(String correo, String numDocumento);

    public Usuario buscarUsuarioRegistradoEnSistema(String correo, String numDocumento);

}
