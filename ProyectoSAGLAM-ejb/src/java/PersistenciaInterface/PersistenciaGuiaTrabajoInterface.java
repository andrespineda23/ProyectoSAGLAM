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
 *
 * @author John Pineda
 */
@Local
public interface PersistenciaGuiaTrabajoInterface {

    public void crearGuiaTrabajo(GuiaTrabajo guiaTrabajo);

    public void eliminarGuiaTrabajo(GuiaTrabajo guiaTrabajo);

    public List<GuiaTrabajo> consultarGuiasTrabajo();
}