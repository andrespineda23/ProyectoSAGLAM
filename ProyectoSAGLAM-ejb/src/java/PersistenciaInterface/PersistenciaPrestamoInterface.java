/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenciaInterface;

import Entidades.Prestamo;

/**
 * Interface de la unidad de persistencia Prestamo. Posee los metodos de dicha
 * persistencia
 *
 * @author Andres Pineda
 */
public interface PersistenciaPrestamoInterface {

    /**
     * Metodo encargado de crear un Prestamo en el sistema
     *
     * @param prestamo Prestamo que sera creado
     */
    public void crearPrestamo(Prestamo prestamo);

    /**
     * Metodo encargado de editar un Prestamo en el sistema
     *
     * @param prestamo Prestamo que sera editado
     */
    public void editarPrestamo(Prestamo prestamo);

    /**
     * Metodo encargado de borrar un Prestamo en el sistema
     *
     * @param prestamo Prestamo que sera borrado
     */
    public void borrarPrestamo(Prestamo prestamo);

    /**
     * Metodo que permite obtener el ultimo prestamo registrado en el sistema.
     *
     * @return Ultimo prestamo registrado en el sistema
     */
    public Prestamo obtenerUltimoPrestamoRegistrado();
}
