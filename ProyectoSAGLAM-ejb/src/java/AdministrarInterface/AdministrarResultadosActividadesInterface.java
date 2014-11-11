/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministrarInterface;

import Entidades.ResultadosActividades;
import Entidades.Usuario;
import java.math.BigInteger;
import java.util.List;

/**
 * Interface del adminitrador ResultadosActividades. Posee las operaciones
 * relacionadas con la capa de negocio de la pagina ResultadosActividades
 *
 * @author John Pineda
 */
public interface AdministrarResultadosActividadesInterface {

    /**
     * Metodo encargado de crear un ResultadoActividad
     *
     * @param resultadosActividades ResultadosActividades a crear
     */
    public void crearResultadosActividades(ResultadosActividades resultadosActividades);

    /**
     * Metodo encargado de ekiminar un ResultadoActividad
     *
     * @param resultadosActividades ResultadosActividades a eliminar
     */
    public void eliminarResultadosActividades(ResultadosActividades resultadosActividades);

    /**
     * Metodo encargado de consultar los ResultadosActividades del sistema
     *
     * @return Lista de ResultadosActividades
     */
    public List<ResultadosActividades> consultarResultadosActividades();

    /**
     * Metodo encargado de buscar el usuario actualmente conectado en el sistema
     *
     * @param secuencia Secuencia del usuario
     * @return Usuario conectado al sistema
     */
    public Usuario buscarUsuarioPorSecuencia(BigInteger secuencia);
}
