/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Entidades.Usuario;
import PersistenciaInterface.PersistenciaUsuarioInterface;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author John Pineda
 */
@Stateless
public class PersistenciaUsuario implements PersistenciaUsuarioInterface {

    @PersistenceContext(unitName = "ProyectoSAGLAM-ejbPU")
    private EntityManager em;

    @Override
    public void crearUsuario(Usuario usuario) {
        em.clear();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(usuario);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error crearUsuario PersistenciaUsuario : " + e.toString());
            if (tx.isActive()) {
                tx.rollback();
            }
        }
    }

    @Override
    public void editarUsuario(Usuario usuario) {
        em.clear();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(usuario);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error editarUsuario PersistenciaUsuario : " + e.toString());
            if (tx.isActive()) {
                tx.rollback();
            }
        }
    }

    @Override
    public void borrarUsuario(Usuario usuario) {
        em.clear();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.merge(usuario));
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error borrarUsuario PersistenciaUsuario : " + e.toString());
            if (tx.isActive()) {
                tx.rollback();
            }
        }
    }

    @Override
    public List<Usuario> buscarUsuarios() {
        try {
            em.clear();
            Query query = em.createQuery("SELECT u FROM Usuario u");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            List<Usuario> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error v PersistenciaUsuario : " + e.toString());
            return null;
        }
    }

    @Override
    public Usuario buscarUsuarioSecuencia(BigInteger secuencia) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.secuencia = :secuencia");
            query.setParameter("secuencia", secuencia);
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            Usuario usuario = (Usuario) query.getSingleResult();
            return usuario;
        } catch (Exception e) {
            System.out.println("Error buscarUsuarioSecuencia PersistenciaUsuario : " + e.toString());
            return null;
        }
    }

    @Override
    public Usuario buscarUsuarioPorCorreo_Contrasena(String correo, String contrasena) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.correoelectronico = :correo AND u.contrasena = :contrasena");
            query.setParameter("correo", correo);
            query.setParameter("contrasena", contrasena);
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            Usuario usuario = (Usuario) query.getSingleResult();
            return usuario;
        } catch (Exception e) {
            System.out.println("Error buscarUsuarioPorCorreo_Contrasena PersistenciaUsuario : " + e.toString());
            return null;
        }
    }

    @Override
    public Usuario buscarUsuarioPorCorreo(String correo) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.correoelectronico = :correo");
            query.setParameter("correo", correo);
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            Usuario usuario = (Usuario) query.getSingleResult();
            return usuario;
        } catch (Exception e) {
            System.out.println("Error buscarUsuarioPorCorreo PersistenciaUsuario : " + e.toString());
            return null;
        }
    }

    @Override
    public String buscarContrasenaUsuarioPorCorreo_NumeroDocumento(String correo, String numDocumento) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.correoelectronico = :correo AND u.numerodocumento =: numDocumento");
            query.setParameter("correo", correo);
            query.setParameter("numDocumento", numDocumento);
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            Usuario usuario = (Usuario) query.getSingleResult();
            if (usuario != null) {
                return usuario.getContrasena();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error buscarContrasenaUsuarioPorCorreo_NumeroDocumento PersistenciaUsuario : " + e.toString());
            return null;
        }
    }

    @Override
    public Usuario buscarUsuarioRegistradoEnSistema(String correo, String numDocumento) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.correoelectronico = :correo AND u.numerodocumento =: numDocumento");
            query.setParameter("correo", correo);
            query.setParameter("numDocumento", numDocumento);
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            Usuario usuario = (Usuario) query.getSingleResult();
            return usuario;
        } catch (Exception e) {
            System.out.println("Error buscarUsuarioRegistradoEnSistema PersistenciaUsuario : " + e.toString());
            return null;
        }
    }

}
