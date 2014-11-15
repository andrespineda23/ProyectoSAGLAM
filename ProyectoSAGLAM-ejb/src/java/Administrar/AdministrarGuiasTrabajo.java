/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrar;

import AdministrarInterface.AdministrarGuiasTrabajoInterface;
import Entidades.GuiaTrabajo;
import PersistenciaInterface.PersistenciaGuiaTrabajoInterface;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 * AdministrarGuiasTrabajo - SessionBean encargado de realizar las operaciones
 * de la capa de negocio de las Guias de Trabajo
 *
 * @author Andres Pineda
 */
@Stateful
public class AdministrarGuiasTrabajo implements AdministrarGuiasTrabajoInterface {

    /**
     * Inyeccion de dependencia del EJB PersistenciaGuiaTrabajoInterface, el cua
     * realiza las operaciones relacionadas con la Guia de trabajo
     */
    @EJB
    PersistenciaGuiaTrabajoInterface persistenciaGuiaTrabajo;

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
}
