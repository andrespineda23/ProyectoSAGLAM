/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Entidades.Materia;
import PersistenciaInterface.PersistenciaMateriaInterface;
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
public class PersistenciaMateria implements PersistenciaMateriaInterface {

    @PersistenceContext(unitName = "ProyectoSAGLAM-ejbPU")
    private EntityManager em;

    @Override
    public List<Materia> consultarMaterias() {
        try {
            em.clear();
            Query query = em.createQuery("SELECT m FROM Materia m");
            List<Materia> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error consultarMaterias PersistenciaMateria : " + e.toString());
            return null;
        }
    }
}
