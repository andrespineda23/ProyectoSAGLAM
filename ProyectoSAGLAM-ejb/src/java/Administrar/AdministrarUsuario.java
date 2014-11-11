/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrar;

import AdministrarInterface.AdministrarUsuarioInterface;
import Entidades.Usuario;
import PersistenciaInterface.PersistenciaUsuarioInterface;
import java.math.BigInteger;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 * AdministrarUsuario - SessionBean encargado de realizar las operaciones de la
 * capa de negocio del Usuario
 *
 * @author Andres Pineda
 */
@Stateful
public class AdministrarUsuario implements AdministrarUsuarioInterface {

    /**
     * Inyeccion de dependencia del EJB PersistenciaUsuarioInterface, el cua
     * realiza las operaciones relacionadas con el Usuario
     */
    @EJB
    PersistenciaUsuarioInterface persistenciaUsuario;

    public void editarUsuario(Usuario usuario) {
        persistenciaUsuario.editarUsuario(usuario);
    }

    public Usuario consultarUsuarioPorSecuencia(BigInteger secEmpleado) {
        Usuario usuarioRetorno;
        usuarioRetorno = persistenciaUsuario.buscarUsuarioSecuencia(secEmpleado);
        return usuarioRetorno;
    }
}
