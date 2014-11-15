/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministrarInterface;

import Entidades.GuiaTrabajo;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface del adminitrador GuiaTrabajo. Posee las operaciones relacionadas
 * con la capa de negocio de la pagina GuiaTrabajo
 *
 * @author Andres Pineda
 */
public interface AdministrarGuiasTrabajoInterface {

    /**
     * Metodo encargado de crear una guia de trabajo
     *
     * @param guiaTrabajo Guia a crear
     */
    public void crearGuiaTrabajo(GuiaTrabajo guiaTrabajo);

    /**
     * Metodo encargado de eliminar una guia de trabajo
     *
     * @param guiaTrabajo Guia a eliminar
     */
    public void eliminarGuiaTrabajo(GuiaTrabajo guiaTrabajo);

    /**
     * Metodo encargado de consultar las guias de trabajo registradas
     *
     * @return Lista de guias de trabajo
     */
    public List<GuiaTrabajo> consultarGuiasTrabajo();
}
