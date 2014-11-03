/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Entidades.ResultadosActividades;
import PersistenciaInterface.PersistenciaResultadosActividadesInterface;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author John Pineda
 */
@Stateless
public class PersistenciaResultadosActividades implements PersistenciaResultadosActividadesInterface {

    @PersistenceContext(unitName = "ProyectoSAGLAM-ejbPU")
    private EntityManager em;

    public void crearResultadosActividades(ResultadosActividades resultadosActividades) {
        try {
            em.persist(resultadosActividades);
        } catch (Exception e) {
            System.err.println("Error crearResultadosActividades PersistenciaResultadosActividades : " + e.toString());

        }
    }

    public void eliminarResultadosActividades(ResultadosActividades resultadosActividades) {
        try {
            em.remove(resultadosActividades);
        } catch (Exception e) {
            System.err.println("Error eliminarResultadosActividades PersistenciaResultadosActividades : " + e.toString());

        }
    }

    public List<ResultadosActividades> consultarResultadosActividades() {
        try {
            em.clear();
            Query query = em.createQuery("SELECT ra FROM ResultadosActividades ra ");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            List<ResultadosActividades> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.err.println("Error consultarGuiasTrabajo PersistenciaResultadosActividades : " + e.toString());
            return null;
        }
    }
}
