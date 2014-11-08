/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenciaInterface;

import Entidades.AreaTrabajo;
import java.util.List;

/**
 *
 * @author ANDRES PINEDA
 */
public interface PersistenciaAreaTrabajoInterface {

    public void crearAreaTrabajo(AreaTrabajo areaTrabajo);

    public void editarAreaTrabajo(AreaTrabajo areaTrabajo);

    public void borrarAreaTrabajo(AreaTrabajo areaTrabajo);

    public List<AreaTrabajo> buscarAreasDeTrabajo();

}
