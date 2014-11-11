/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministrarInterface;

import Entidades.AreaTrabajo;
import Entidades.GuiaTrabajo;
import Entidades.Prestamo;
import Entidades.PrestamoUsuario;
import Entidades.Usuario;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Interface del adminitrador ReservaAreaTrabajo. Posee las operaciones
 * relacionadas con la capa de negocio de la pagina ReservaAreaTrabajo
 *
 * @author Andres Pineda
 */
public interface AdministrarReservaAreaTrabajoInterface {

    /**
     * Metodo encargado de registrar la reserva de una area de trabajo
     *
     * @param prestamo Reserva del area de trabajo
     */
    public void registrarReservaAreaTrabajo(PrestamoUsuario prestamo);

    /**
     * Metodo encargado de obtener la lista de PrestamoUsuario que se encuentran
     * activos en uaa fecha especifica
     *
     * @param fechaSolicitada Fecha solicitada
     * @return Lista de PrestamoUsuario activos en la fecha dada
     */
    public List<PrestamoUsuario> obtenerPrestamosYaRegistrados(Date fechaSolicitada);

    /**
     * Metodo encargado de obtener el usuario que se encuentra actualmente en el
     * sistema
     *
     * @param secuencia Secuencia del usuario
     * @return Usuario conectado
     */
    public Usuario buscarUsuarioPorSecuencia(BigInteger secuencia);

    /**
     * Metodo encargado de obtener todas las areas de trabajo
     *
     * @return Lista de areas de trabajo
     */
    public List<AreaTrabajo> buscarAreasDeTrabajo();

    /**
     * Metodo encargado de crear el prestamo del area de trabajo
     *
     * @param prestamo Prestamo a crear
     */
    public void crearPrestamo(Prestamo prestamo);

    /**
     * Metodo encargado de obtener el ultimo prestamo ingresado
     *
     * @return Ultimo prestamo almacenado
     */
    public Prestamo obtenerUltimoPrestamoRegistrado();

    /**
     * Metodo encargado de obtener las guias de trabajo almacenadas
     *
     * @return Lista de guias de trabajo
     */
    public List<GuiaTrabajo> buscarGuiasDeTrabajo();

}
