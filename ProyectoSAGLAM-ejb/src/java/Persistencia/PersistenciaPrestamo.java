/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Entidades.Prestamo;
import PersistenciaInterface.PersistenciaPrestamoInterface;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ANDRES PINEDA
 */
@Stateless
public class PersistenciaPrestamo implements PersistenciaPrestamoInterface {

    @PersistenceContext(unitName = "ProyectoSAGLAM-ejbPU")
    private EntityManager em;

    @Override
    public void crearPrestamo(Prestamo prestamo) {
        try {
            em.persist(prestamo);
        } catch (Exception e) {
            System.err.println("Error crearPrestamo PersistenciaPrestamo : " + e.toString());

        }
    }

    @Override
    public void editarPrestamo(Prestamo prestamo) {
        try {
            em.merge(prestamo);
        } catch (Exception e) {
            System.out.println("Error editarPrestamo PersistenciaPrestamo : " + e.toString());
        }
    }

    @Override
    public void borrarPrestamo(Prestamo prestamo) {
        try {
            em.remove(em.merge(prestamo));
        } catch (Exception e) {
            System.out.println("Error borrarPrestamo PersistenciaPrestamo : " + e.toString());
        }
    }
}
