/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Entidades.GuiaTrabajo;
import PersistenciaInterface.PersistenciaGuiaTrabajoInterface;
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
public class PersistenciaGuiaTrabajo implements PersistenciaGuiaTrabajoInterface {

    @PersistenceContext(unitName = "ProyectoSAGLAM-ejbPU")
    private EntityManager em;

    public void crearGuiaTrabajo(GuiaTrabajo guiaTrabajo) {
        try {
            em.persist(guiaTrabajo);
        } catch (Exception e) {
            System.err.println("Error crearGuiaTrabajo PersistenciaGuiaTrabajo : " + e.toString());

        }
    }

    public void eliminarGuiaTrabajo(GuiaTrabajo guiaTrabajo) {
        try {
            em.remove(guiaTrabajo);
        } catch (Exception e) {
            System.err.println("Error eliminarGuiaTrabajo PersistenciaGuiaTrabajo : " + e.toString());

        }
    }

    public List<GuiaTrabajo> consultarGuiasTrabajo() {
        try {
            em.clear();
            Query query = em.createQuery("SELECT gt FROM GuiaTrabajo gt ");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            List<GuiaTrabajo> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.err.println("Error consultarGuiasTrabajo PersistenciaGuiaTrabajo : " + e.toString());
            return null;
        }
    }
}
