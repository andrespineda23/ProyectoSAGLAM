/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrar;

import AdministrarInterface.AdministrarMateriaUsuarioInterface;
import Entidades.Materia;
import Entidades.MateriaUsuario;
import Entidades.Usuario;
import PersistenciaInterface.PersistenciaMateriaInterface;
import PersistenciaInterface.PersistenciaMateriaUsuarioInterface;
import PersistenciaInterface.PersistenciaUsuarioInterface;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * AdministrarMateriaUsuario - SessionBean encargado de realizar las operaciones
 * de la capa de negocio de los Usuarios
 *
 * @author Andres Pineda
 */
@Stateless
public class AdministrarMateriaUsuario implements AdministrarMateriaUsuarioInterface {

    /**
     * Inyeccion de dependencia del EJB PersistenciaUsuarioInterface, el cua
     * realiza las operaciones relacionadas con el Usuario
     */
    @EJB
    PersistenciaUsuarioInterface persistenciaUsuario;
    /**
     * Inyeccion de dependencia del EJB PersistenciaMateriaInterface, el cua
     * realiza las operaciones relacionadas con la Materia
     */
    @EJB
    PersistenciaMateriaInterface persistenciaMateria;
    /**
     * Inyeccion de dependencia del EJB PersistenciaMateriaUsuarioInterface, el cua
     * realiza las operaciones relacionadas con la MateriaUsuario
     */
    @EJB
    PersistenciaMateriaUsuarioInterface persistenciaMateriaUsuario;

    public Usuario buscarUsuarioPorSecuencia(BigInteger secuencia) {
        try {
            Usuario usuario = persistenciaUsuario.buscarUsuarioSecuencia(secuencia);
            return usuario;
        } catch (Exception e) {
            System.out.println("Error buscarUsuarioPorSecuencia AdministrarMateriaUsuario : " + e.toString());
            return null;
        }
    }

    @Override
    public List<Materia> obtenerMaterias() {
        try {
            List<Materia> lista = persistenciaMateria.consultarMaterias();
            return lista;
        } catch (Exception e) {
            System.out.println("Error obtenerMaterias AdministrarMateriaUsuario : " + e.toString());
            return null;
        }
    }

    @Override
    public void registrarAsociacionMateriaUsuario(MateriaUsuario materiaU) {
        try {
            persistenciaMateriaUsuario.crearMateriaUsuario(materiaU);
        } catch (Exception e) {
            System.out.println("Error registrarAsociacionMateriaUsuario AdministrarMateriaUsuario : " + e.toString());
        }
    }

}
