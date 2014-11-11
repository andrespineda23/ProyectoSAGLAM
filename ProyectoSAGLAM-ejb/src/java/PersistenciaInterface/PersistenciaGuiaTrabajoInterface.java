/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenciaInterface;

import Entidades.GuiaTrabajo;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface encargada de determinar las operaciones que se realizan sobre la
 * tabla 'GuiaTrabajo' de la base de datos.
 *
 * @author John Pineda
 */
public interface PersistenciaGuiaTrabajoInterface {

    /**
     * Metodo encargado de crear una nueva GuiaTrabajo en el sistema
     *
     * @param guiaTrabajo GuiaTrabajo que sera ingresada
     */
    public void crearGuiaTrabajo(GuiaTrabajo guiaTrabajo);

    /**
     * Metodo encargado de eliminar una GuiaTrabajo del sistema
     *
     * @param guiaTrabajo GuiaTrabajo que sera eliminada
     */
    public void eliminarGuiaTrabajo(GuiaTrabajo guiaTrabajo);

    /**
     * Metodo encargado de buscar todas las GuiasTrabajo registradas en el
     * sistema
     *
     * @return Lista de GuiasTrabajo
     */
    public List<GuiaTrabajo> consultarGuiasTrabajo();
}
