/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Entidades.MateriaUsuario;
import PersistenciaInterface.PersistenciaMateriaUsuarioInterface;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * PersistenciaMateriaUsuario - Encargada de las operaciones de los datos de la
 * tabla MateriaUsuario
 *
 * @author Andres Pineda.
 */
@Stateless
public class PersistenciaMateriaUsuario implements PersistenciaMateriaUsuarioInterface {

    /**
     * Atributo EntityManager. Representa la comunicación con la base de datos
     */
    @PersistenceContext(unitName = "ProyectoSAGLAM-ejbPU")
    private EntityManager em;

    @Override
    public void crearMateriaUsuario(MateriaUsuario materiaUsuario) {
        try {
            em.persist(materiaUsuario);
        } catch (Exception e) {
            System.err.println("Error crearMateriaUsuario PersistenciaMateriaUsuario : " + e.toString());

        }
    }

    @Override
    public List<MateriaUsuario> buscarMateriasUsuarios() {
        try {
            em.clear();
            Query query = em.createQuery("SELECT a FROM MateriaUsuario a ORDER BY a.secuencia DESC");
            List<MateriaUsuario> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error buscarMateriasUsuarios null;PersistenciaMateriaUsuario : " + e.toString());
            return null;
        }
    }
}
