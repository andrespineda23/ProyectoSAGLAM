/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministrarInterface;

import Entidades.MateriaUsuario;
import Entidades.PrestamoUsuario;
import Entidades.Usuario;
import java.math.BigInteger;
import java.util.List;

/**
 * Interface del adminitrador Prestamos. Posee las operaciones relacionadas con
 * la capa de negocio de la pagina Prestamos
 *
 * @author Andres Pineda
 */
public interface AdministrarPrestamosInterface {

    /**
     * Metodo encargado de obtener el usuario que actualmente esa conectado en
     * el sistema
     *
     * @param secuencia Secuencia del usuario
     * @return Usuario conectado
     */
    public Usuario buscarUsuarioPorSecuencia(BigInteger secuencia);

    /**
     * Metodo encargado de obtener la lista de prestamos que se encuentran en
     * proceso de espera
     *
     * @return Lista de PrestamoUsuario en proceso de espera
     */
    public List<PrestamoUsuario> obtenerPrestamosEnProcesoDeEspera();

    /**
     * Metodo encargado de modificar el estado de prestamo de un PrestamoUsuario
     *
     * @param prestamo PrestamoUsuario que va a ser modificado
     */
    public void modificarEstadoPrestamo(PrestamoUsuario prestamo);

    /**
     * Metodo encargado de obtener la lista de PrestamoUsuario de un usuario por
     * medio de su sencuencia
     *
     * @param secuencia Secuencia del usuario
     * @return Lista de PrestamoUsuario del usuaro
     */
    public List<PrestamoUsuario> obtenerPrestamosDeUnUsuario(BigInteger secuencia);

    /**
     * Metodo encargado de buscar todos los registros de MateriaUsuario
     *
     * @return Lista de MateriaUsuario
     */
    public List<MateriaUsuario> buscarMateriasUsuarios();
}
