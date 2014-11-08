/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Entidades.MateriaUsuario;
import PersistenciaInterface.PersistenciaMateriaUsuarioInterface;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ANDRES PINEDA
 */
@Stateless
public class PersistenciaMateriaUsuario implements PersistenciaMateriaUsuarioInterface {

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
}
