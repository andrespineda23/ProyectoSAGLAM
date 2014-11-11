/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenciaInterface;

import Entidades.PrestamoUsuario;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Interface de la unidad de persistencia PrestamoUsuario. Posee los metodos de
 * dicha persistencia
 *
 * @author Andres Pineda
 */
public interface PersistenciaPrestamoUsuarioInterface {

    /**
     * Metodo encargado de crear un PrestamoUsuario en el sistema
     *
     * @param prestamo Prestamo que sera creado
     */
    public void crearPrestamo(PrestamoUsuario prestamo);

    /**
     * Metodo encargado de editar un PrestamoUsuario del sistema
     *
     * @param prestamo Prestamo que sera editado
     */
    public void editarPrestamo(PrestamoUsuario prestamo);

    /**
     * Metodo encargado de borrar un PrestamoUsuario del sistema
     *
     * @param prestamo Prestamo que sera eliminado
     */
    public void borrarPrestamo(PrestamoUsuario prestamo);

    /**
     * Metodo encargado de buscar los PrestamosUsuario que se encuentren en
     * proceso de espera para ser aceptados o rechazados
     *
     * @return Lista de PrestamosUsuarios en proceso de espera
     */
    public List<PrestamoUsuario> buscarPrestamosEnEspera();

    /**
     * Metodo encargado de buscar los PrestamosUsuarios asociados a un Usuario
     * por medio de su secuencia
     *
     * @param secuencia Secuencia del Usuario
     * @return Lista de PrestamoUsuario registrados para el Usuario
     */
    public List<PrestamoUsuario> buscarPrestamosDeUnUsuario(BigInteger secuencia);

    /**
     * Metodo encargado de obtener los PrestamosUsuario aceptados despues de una
     * fecha ingresada
     *
     * @param fechaSolicitada Fecha a consultar
     * @return Lista de PrestamoUsuario aceptados
     */
    public List<PrestamoUsuario> buscarPrestamosAceptados(Date fechaSolicitada);

}
