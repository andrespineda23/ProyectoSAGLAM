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
import javax.ejb.LocalBean;

/**
 *
 * @author ANDRES PINEDA
 */
@Stateless
public class AdministrarMateriaUsuario implements AdministrarMateriaUsuarioInterface {

    @EJB
    PersistenciaUsuarioInterface persistenciaUsuario;
    @EJB
    PersistenciaMateriaInterface persistenciaMateria;
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
