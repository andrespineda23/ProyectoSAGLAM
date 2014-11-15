/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrar;

import AdministrarInterface.AdministrarPrestamosInterface;
import Entidades.MateriaUsuario;
import Entidades.Prestamo;
import Entidades.PrestamoUsuario;
import Entidades.Usuario;
import PersistenciaInterface.PersistenciaMateriaUsuarioInterface;
import PersistenciaInterface.PersistenciaPrestamoUsuarioInterface;
import PersistenciaInterface.PersistenciaUsuarioInterface;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * AdministrarGuiasTrabajo - SessionBean encargado de realizar las operaciones
 * de la capa de negocio de las Guias de Trabajo
 *
 * @author Andres Pineda
 */
@Stateless
public class AdministrarPrestamos implements AdministrarPrestamosInterface {

    /**
     * Inyeccion de dependencia del EJB PersistenciaUsuarioInterface, el cua
     * realiza las operaciones relacionadas con el Usuario
     */
    @EJB
    PersistenciaUsuarioInterface persistenciaUsuario;
    /**
     * Inyeccion de dependencia del EJB PersistenciaPrestamoUsuarioInterface, el cual
     * realiza las operaciones relacionadas con el PrestamoUsuario
     */
    @EJB
    PersistenciaPrestamoUsuarioInterface persistenciaPrestamoUsuario;
    /**
     * Inyeccion de dependencia del EJB PersistenciaMateriaUsuarioInterface, el cual
     * realiza las operaciones relacionadas con la MateriaUsuario
     */
    @EJB
    PersistenciaMateriaUsuarioInterface persistenciaMateriaUsuario;

    //@Override
    public Usuario buscarUsuarioPorSecuencia(BigInteger secuencia) {
        try {
            Usuario usuario = persistenciaUsuario.buscarUsuarioSecuencia(secuencia);
            return usuario;
        } catch (Exception e) {
            System.out.println("Error buscarUsuarioPorSecuencia AdministrarPrestamos : " + e.toString());
            return null;
        }
    }

    @Override
    public List<PrestamoUsuario> obtenerPrestamosEnProcesoDeEspera() {
        try {
            List<PrestamoUsuario> lista = persistenciaPrestamoUsuario.buscarPrestamosEnEspera();
            return lista;
        } catch (Exception e) {
            System.out.println("Error obtenerPrestamosEnProcesoDeEspera AdministrarPrestamos : " + e.toString());
            return null;
        }
    }

    @Override
    public void modificarEstadoPrestamo(PrestamoUsuario prestamo) {
        try {
            persistenciaPrestamoUsuario.editarPrestamo(prestamo);
        } catch (Exception e) {
            System.out.println("Error modificarEstadoPrestamo AdministrarPrestamos : " + e.toString());
        }
    }

    @Override
    public List<PrestamoUsuario> obtenerPrestamosDeUnUsuario(BigInteger secuencia) {
        try {
            List<PrestamoUsuario> lista = persistenciaPrestamoUsuario.buscarPrestamosDeUnUsuario(secuencia);
            return lista;
        } catch (Exception e) {
            System.out.println("Error obtenerPrestamosDeUnUsuario AdministrarPrestamos : " + e.toString());
            return null;
        }
    }

    //@Override
    public List<MateriaUsuario> buscarMateriasUsuarios() {
        try {
            List<MateriaUsuario> lista = persistenciaMateriaUsuario.buscarMateriasUsuarios();
            return lista;
        } catch (Exception e) {
            System.out.println("Error buscarMateriasUsuarios AdministrarPrestamos : " + e.toString());
            return null;
        }
    }

}
