/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrar;

import AdministrarInterface.AdministrarBloquearUsuarioInterface;
import Entidades.Usuario;
import PersistenciaInterface.PersistenciaUsuarioInterface;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author John Pineda
 */
@Stateful
public class AdministrarBloquearUsuario implements AdministrarBloquearUsuarioInterface {

    @EJB
    PersistenciaUsuarioInterface persistenciaUsario;

    public void modificarUsuario(Usuario usuario) {
        persistenciaUsario.editarUsuario(usuario);
    }

    public List<Usuario> consultarUsuario() {
        List<Usuario> lista;
        lista = persistenciaUsario.buscarUsuariosNOBloquedaos();
        return lista; 
    }
}
