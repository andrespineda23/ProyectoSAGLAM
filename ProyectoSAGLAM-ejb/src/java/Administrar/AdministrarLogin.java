/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrar;

import AdministrarInterface.AdministrarLoginInterface;
import Entidades.Usuario;
import PersistenciaInterface.PersistenciaUniversidadInterface;
import PersistenciaInterface.PersistenciaUsuarioInterface;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Administrador del controlador de login, el cual se encarga de administrar los procesos de
 * registro de usuarios, ingreso al sistema, validaciones y proceso de recordar contraseña.
 * @author Andres Pineda
 */
@Stateless
public class AdministrarLogin implements AdministrarLoginInterface {

    /**
     * Inyeccion del EJB de la PersistenciaUniversidad
     */
    @EJB
    PersistenciaUniversidadInterface persistenciaUniversidad;
    /**
     * Inyeccion del EJB de la PersistenciaUniversidad
     */
    @EJB
    PersistenciaUsuarioInterface persistenciaUsuario;

    @Override
    public void crearNuevoUsuario(Usuario usuario) {
        try {
            persistenciaUsuario.crearUsuario(usuario);
        } catch (Exception e) {
            System.out.println("Error crearNuevoUsuario AdministrarLogin : " + e.toString());
        }
    }

    @Override
    public String recordarContrasenaUsuario(String correo, String numDocumento) {
        try {
            String contra = persistenciaUsuario.buscarContrasenaUsuarioPorCorreo_NumeroDocumento(correo, numDocumento);
            return contra;
        } catch (Exception e) {
            System.out.println("Error recordarContrasenaUsuario AdministrarLogin : " + e.toString());
            return null;
        }
    }

    @Override
    public Usuario obtenerUsuarioLogin(String correo, String contrasena) {
        try {
            Usuario usuario = persistenciaUsuario.buscarUsuarioPorCorreo_Contrasena(correo, contrasena);
            return usuario;
        } catch (Exception e) {
            System.out.println("Error obtenerUsuarioLogin AdministrarLogin : " + e.toString());
            return null;
        }
    }

    @Override
    public String validarTipoUsuarioNuevoRegistro(String correo, String documento, String tipoUsuario) {
        try {
            String validar = persistenciaUniversidad.validarUsuarioPorDatosUniversidad(correo, documento, tipoUsuario);
            return validar;
        } catch (Exception e) {
            System.out.println("Error validarTipoUsuarioNuevoRegistro AdministrarLogin : " + e.toString());
            return null;
        }
    }

    @Override
    public Usuario validarUsuarioRegistradoEnSistema(String correo, String documento) {
        try {
            Usuario user = persistenciaUsuario.buscarUsuarioRegistradoEnSistema(correo, documento);
            return user;
        } catch (Exception e) {
            System.out.println("Error validarUsuarioRegistradoEnSistema AdministrarLogin : " + e.toString());
            return null;
        }
    }
}
