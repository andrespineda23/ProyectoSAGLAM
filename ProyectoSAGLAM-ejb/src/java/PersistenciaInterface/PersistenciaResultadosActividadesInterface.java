/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenciaInterface;

import Entidades.ResultadosActividades;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface de la unidad de persistencia ResultadosActividades. Posee los
 * metodos de dicha persistencia
 *
 * @author John Pineda
 */
public interface PersistenciaResultadosActividadesInterface {

    /**
     * Metodo encargado de crear un nuevo registro de ResultadosActividades en
     * el sistema
     *
     * @param resultadosActividades ResultadoActividad que sera creado
     */
    public void crearResultadosActividades(ResultadosActividades resultadosActividades);

    /**
     * Metodo encargado de eliminar un ResultadoActividad del sistema
     *
     * @param resultadosActividades ResultadoActividad que sera eliminado
     */
    public void eliminarResultadosActividades(ResultadosActividades resultadosActividades);

    /**
     * Metodo encargado de buscar los ResultadosActividades registrados en el
     * sistema
     *
     * @return Lista de ResultadosActividades
     */
    public List<ResultadosActividades> consultarResultadosActividades();
}
