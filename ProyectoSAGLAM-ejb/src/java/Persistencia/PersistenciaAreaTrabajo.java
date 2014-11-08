/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Entidades.AreaTrabajo;
import PersistenciaInterface.PersistenciaAreaTrabajoInterface;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ANDRES PINEDA
 */
@Stateless
public class PersistenciaAreaTrabajo implements PersistenciaAreaTrabajoInterface {

    @PersistenceContext(unitName = "ProyectoSAGLAM-ejbPU")
    private EntityManager em;

    @Override
    public void crearAreaTrabajo(AreaTrabajo areaTrabajo) {
        try {
            em.persist(areaTrabajo);
        } catch (Exception e) {
            System.err.println("Error crearAreaTrabajo PersistenciaAreaTrabajo : " + e.toString());

        }
    }

    @Override
    public void editarAreaTrabajo(AreaTrabajo areaTrabajo) {
        try {
            em.merge(areaTrabajo);
        } catch (Exception e) {
            System.out.println("Error editarAreaTrabajo PersistenciaAreaTrabajo : " + e.toString());
        }
    }

    @Override
    public void borrarAreaTrabajo(AreaTrabajo areaTrabajo) {
        try {
            em.remove(em.merge(areaTrabajo));
        } catch (Exception e) {
            System.out.println("Error borrarAreaTrabajo PersistenciaAreaTrabajo : " + e.toString());
        }
    }

    @Override
    public List<AreaTrabajo> buscarAreasDeTrabajo() {
        try {
            em.clear();
            Query query = em.createQuery("SELECT a FROM AreaTrabajo a");
            List<AreaTrabajo> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error buscarAreasDeTrabajo PersistenciaAreaTrabajo : " + e.toString());
            return null;
        }
    }
}
