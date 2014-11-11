/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenciaInterface;

import Entidades.AreaTrabajo;
import java.util.List;

/**
 * Interface de la unidad de persistencia AreaTrabajo. Posee los metodos de
 * dicha persistencia
 *
 * @author Andres Pineda
 */
public interface PersistenciaAreaTrabajoInterface {

    /**
     * Metodo encargado de crear una nueva Area de Trabajo en el sistema
     *
     * @param areaTrabajo Area de Trabajo que sera creada
     */
    public void crearAreaTrabajo(AreaTrabajo areaTrabajo);

    /**
     * Metodo encargado de editar una Area de Trabajo del sistema
     *
     * @param areaTrabajo Area de Trabajo que sera editada
     */
    public void editarAreaTrabajo(AreaTrabajo areaTrabajo);

    /**
     * Metodo encargado de borrar una Area de Trabajo del sistema
     *
     * @param areaTrabajo Area de Trabajo que sera creada
     */
    public void borrarAreaTrabajo(AreaTrabajo areaTrabajo);

    /**
     * Metodo encargado de buscar todas las Areas de Trabajo registradas en el
     * sistema
     *
     * @return Lista de Areas de Trabajo
     */
    public List<AreaTrabajo> buscarAreasDeTrabajo();

}
