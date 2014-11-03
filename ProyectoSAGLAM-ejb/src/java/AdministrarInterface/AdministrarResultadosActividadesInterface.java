/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministrarInterface;

import Entidades.ResultadosActividades;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author John Pineda
 */
@Local
public interface AdministrarResultadosActividadesInterface {

    public void crearResultadosActividades(ResultadosActividades resultadosActividades);

    public void eliminarResultadosActividades(ResultadosActividades resultadosActividades);

    public List<ResultadosActividades> consultarResultadosActividades();
}
