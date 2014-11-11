/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministrarInterface;

import Entidades.AreaTrabajo;
import Entidades.Usuario;
import java.math.BigInteger;
import java.util.List;

/**
 * Interface del adminitrador AreaTrabajo. Posee las operaciones relacionadas
 * con la capa de negocio de la pagina AreaTrabajo
 *
 * @author Andres Pineda
 */
public interface AdministrarAreaTrabajoInterface {

    /**
     * Metodo encargado de obtener el usuario conectado en el sistema por medio
     * de su secuencia
     *
     * @param secuencia Secuencia del usuario
     * @return Usuario conectado
     */
    public Usuario buscarUsuarioPorSecuencia(BigInteger secuencia);

    /**
     * Metodo encargado de crear un Area de Trabajo en el sistema
     *
     * @param area Area de Trabajo a crear
     */
    public void crearAreaDeTrabajo(AreaTrabajo area);

    /**
     * Metodo encargado de editar un Area de Trabajo del sistema
     *
     * @param area Area de Trabajo a crear
     */
    public void editarAreaDeTrabajo(AreaTrabajo area);

    /**
     * Metodo encargado de eliminar un Area de Trabajo del sistema
     *
     * @param area Area de Trabajo a eliminar
     */
    public void borrarAreaDeTrabajo(AreaTrabajo area);

    /**
     * Metodo encargado de consultar todas las Areas de Trabajo registradas en
     * el sistema
     *
     * @return Lista de Areas de Trabajo
     */
    public List<AreaTrabajo> buscarAreasDeTrabajo();

}
