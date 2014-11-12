/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrar;

import AdministrarInterface.AdministrarGuiasTrabajoInterface;
import Entidades.GuiaTrabajo;
import Entidades.Usuario;
import PersistenciaInterface.PersistenciaGuiaTrabajoInterface;
import PersistenciaInterface.PersistenciaUsuarioInterface;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author John Pineda
 */
@Stateful
public class AdministrarGuiasTrabajo implements AdministrarGuiasTrabajoInterface {

    @EJB
    PersistenciaGuiaTrabajoInterface persistenciaGuiaTrabajo;
    @EJB
    PersistenciaUsuarioInterface persistenciaUsuario;

    public void crearGuiaTrabajo(GuiaTrabajo guiaTrabajo) {
        persistenciaGuiaTrabajo.crearGuiaTrabajo(guiaTrabajo);
    }

    public void eliminarGuiaTrabajo(GuiaTrabajo guiaTrabajo) {
        persistenciaGuiaTrabajo.eliminarGuiaTrabajo(guiaTrabajo);
    }

    public List<GuiaTrabajo> consultarGuiasTrabajo() {
        List<GuiaTrabajo> lista;
        lista = persistenciaGuiaTrabajo.consultarGuiasTrabajo();
        return lista;
    }

    public Usuario consultarUsuarioPorSecuencia(BigInteger secUsuario) {
        Usuario usuario;
        usuario = persistenciaUsuario.buscarUsuarioSecuencia(secUsuario);
        return usuario;
    }

}
