/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrar;

import AdministrarInterface.AdministrarAreaTrabajoInterface;
import Entidades.AreaTrabajo;
import Entidades.Usuario;
import PersistenciaInterface.PersistenciaAreaTrabajoInterface;
import PersistenciaInterface.PersistenciaUsuarioInterface;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * AdministrarAreaTrabajo - SessionBean encargado de realizar las operaciones de
 * la capa de negocio de la AreaTrabajo
 *
 * @author Andres Pineda
 */
@Stateless
public class AdministrarAreaTrabajo implements AdministrarAreaTrabajoInterface {

    /**
     * Inyeccion de dependencia del EJB PersistenciaAreaTrabajoInterface, el cua
     * realiza las operaciones relacionadas con las Area de Trabajo
     */
    @EJB
    PersistenciaAreaTrabajoInterface persistenciaAreaTrabajo;
    /**
     * Inyeccion de dependencia del EJB PersistenciaUsuarioInterface, el cua
     * realiza las operaciones relacionadas con el Usuario
     */
    @EJB
    PersistenciaUsuarioInterface persistenciaUsuario;

    public Usuario buscarUsuarioPorSecuencia(BigInteger secuencia) {
        try {
            Usuario usuario = persistenciaUsuario.buscarUsuarioSecuencia(secuencia);
            return usuario;
        } catch (Exception e) {
            System.out.println("Error buscarUsuarioPorSecuencia AdministrarPrestamos : " + e.toString());
            return null;
        }
    }

    @Override
    public void crearAreaDeTrabajo(AreaTrabajo area) {
        try {
            persistenciaAreaTrabajo.crearAreaTrabajo(area);
        } catch (Exception e) {
            System.out.println("Error crearAreaDeTrabajo AdministrarAreaTrabajo : " + e.toString());
        }
    }

    @Override
    public void editarAreaDeTrabajo(AreaTrabajo area) {
        try {
            persistenciaAreaTrabajo.editarAreaTrabajo(area);
        } catch (Exception e) {
            System.out.println("Error editarAreaDeTrabajo AdministrarAreaTrabajo : " + e.toString());
        }
    }

    @Override
    public void borrarAreaDeTrabajo(AreaTrabajo area) {
        try {
            persistenciaAreaTrabajo.borrarAreaTrabajo(area);
        } catch (Exception e) {
            System.out.println("Error borrarAreaDeTrabajo AdministrarAreaTrabajo : " + e.toString());
        }
    }

    @Override
    public List<AreaTrabajo> buscarAreasDeTrabajo() {
        try {
            List<AreaTrabajo> lista = persistenciaAreaTrabajo.buscarAreasDeTrabajo();
            return lista;
        } catch (Exception e) {
            System.out.println("Error buscarAreasDeTrabajo AdministrarAreaTrabajo : " + e.toString());
            return null;
        }
    }
}
