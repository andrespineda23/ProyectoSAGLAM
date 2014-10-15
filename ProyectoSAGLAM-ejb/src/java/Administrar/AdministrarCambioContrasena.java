/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Administrar;

import AdministrarInterface.AdministrarCambioContrasenaInterface;
import Entidades.Usuario;
import PersistenciaInterface.PersistenciaUsuarioInterface;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author John Pineda
 */
@Stateful
public class AdministrarCambioContrasena implements AdministrarCambioContrasenaInterface {

    @EJB
    PersistenciaUsuarioInterface persistenciaUsuario;

    public void editarUsuario(Usuario usuario) {
        persistenciaUsuario.editarUsuario(usuario);
    }
    
    public Usuario validarContrasenaAntigua(Usuario usuario)
    {
    Usuario usuarioRetorno;
    usuarioRetorno = persistenciaUsuario.validarCambioContrasenaUsuario(usuario.getCorreoelectronico(), usuario.getContrasena());
    return usuarioRetorno;
    }
}

