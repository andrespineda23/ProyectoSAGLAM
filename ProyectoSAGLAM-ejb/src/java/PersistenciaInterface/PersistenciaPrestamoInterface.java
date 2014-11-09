/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenciaInterface;

import Entidades.Prestamo;
import java.util.List;

/**
 *
 * @author ANDRES PINEDA
 */
public interface PersistenciaPrestamoInterface {

    public void crearPrestamo(Prestamo prestamo);

    public void editarPrestamo(Prestamo prestamo);

    public void borrarPrestamo(Prestamo prestamo);

    public Prestamo obtenerUltimoPrestamoRegistrado();
}
