/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrar;

import AdministrarInterface.AdministrarResultadosActividadesInterface;
import Entidades.ResultadosActividades;
import PersistenciaInterface.PersistenciaResultadosActividadesInterface;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author John Pineda
 */
@Stateful
public class AdministrarResultadosActividades implements AdministrarResultadosActividadesInterface {

    @EJB
    PersistenciaResultadosActividadesInterface persistenciaResultadosActividades;

    public void crearResultadosActividades(ResultadosActividades resultadosActividades) {
        persistenciaResultadosActividades.crearResultadosActividades(resultadosActividades);
    }

    public void eliminarResultadosActividades(ResultadosActividades resultadosActividades) {
        persistenciaResultadosActividades.eliminarResultadosActividades(resultadosActividades);
    }

    @Override
    public List<ResultadosActividades> consultarResultadosActividades() {
        List<ResultadosActividades> lista;
        lista = persistenciaResultadosActividades.consultarResultadosActividades();
        return lista;
    }
}
