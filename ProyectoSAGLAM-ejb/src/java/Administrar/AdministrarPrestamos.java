/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrar;

import AdministrarInterface.AdministrarPrestamosInterface;
import Entidades.Prestamo;
import Entidades.PrestamoUsuario;
import Entidades.Usuario;
import PersistenciaInterface.PersistenciaPrestamoUsuarioInterface;
import PersistenciaInterface.PersistenciaUsuarioInterface;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ANDRES PINEDA
 */
@Stateless
public class AdministrarPrestamos implements AdministrarPrestamosInterface {

    @EJB
    PersistenciaUsuarioInterface persistenciaUsuario;
    @EJB
    PersistenciaPrestamoUsuarioInterface persistenciaPrestamoUsuario;
 
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

}
