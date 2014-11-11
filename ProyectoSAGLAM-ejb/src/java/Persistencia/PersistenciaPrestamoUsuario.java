/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Entidades.PrestamoUsuario;
import PersistenciaInterface.PersistenciaPrestamoUsuarioInterface;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * PersistenciaPrestamoUsuario - Encargada de las operaciones de los datos de la
 * tabla PrestamoUsuario
 *
 * @author Andres Pineda.
 */
@Stateless
public class PersistenciaPrestamoUsuario implements PersistenciaPrestamoUsuarioInterface {

    /**
     * Atributo EntityManager. Representa la comunicación con la base de datos
     */
    @PersistenceContext(unitName = "ProyectoSAGLAM-ejbPU")
    private EntityManager em;

    @Override
    public void crearPrestamo(PrestamoUsuario prestamo) {
        try {
            em.persist(prestamo);
        } catch (Exception e) {
            System.err.println("Error crearPrestamo PersistenciaPrestamoUsuario : " + e.toString());

        }
    }

    @Override
    public void editarPrestamo(PrestamoUsuario prestamo) {
        try {
            em.merge(prestamo);
        } catch (Exception e) {
            System.out.println("Error editarPrestamo PersistenciaPrestamoUsuario : " + e.toString());
        }
    }

    @Override
    public void borrarPrestamo(PrestamoUsuario prestamo) {
        try {
            em.remove(em.merge(prestamo));
        } catch (Exception e) {
            System.out.println("Error borrarPrestamo PersistenciaPrestamoUsuario : " + e.toString());
        }
    }

    @Override
    public List<PrestamoUsuario> buscarPrestamosEnEspera() {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM PrestamoUsuario p WHERE p.prestamo.estadosolicitud='EN ESPERA'");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            List<PrestamoUsuario> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error buscarPrestamosEnEspera PersistenciaPrestamoUsuario : " + e.toString());
            return null;
        }
    }

    @Override
    public List<PrestamoUsuario> buscarPrestamosAceptados(Date fechaSolicitada) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM PrestamoUsuario p WHERE p.prestamo.estadosolicitud='ACEPTADO' AND p.prestamo.fecha=:fechaSolicitada ORDER BY p.prestamo.horainicial DESC");
            query.setParameter("fechaSolicitada", fechaSolicitada);
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            List<PrestamoUsuario> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error buscarPrestamosAceptados PersistenciaPrestamoUsuario : " + e.toString());
            return null;
        }
    }

    @Override
    public List<PrestamoUsuario> buscarPrestamosDeUnUsuario(BigInteger secuencia) {
        try {
            em.clear();
            Date fechaDia = new Date();
            Query query = em.createQuery("SELECT p FROM PrestamoUsuario p WHERE p.usuario.secuencia=:secuencia AND p.prestamo.fecha>=:fechaDia");
            query.setParameter("secuencia", secuencia);
            query.setParameter("fechaDia", fechaDia);
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            List<PrestamoUsuario> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error buscarPrestamosDeUnUsuario PersistenciaPrestamoUsuario : " + e.toString());
            return null;
        }
    }
}
