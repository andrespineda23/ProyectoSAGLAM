/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenciaInterface;

import Entidades.PrestamoUsuario;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ANDRES PINEDA
 */
public interface PersistenciaPrestamoUsuarioInterface {

    public void crearPrestamo(PrestamoUsuario prestamo);

    public void editarPrestamo(PrestamoUsuario prestamo);

    public void borrarPrestamo(PrestamoUsuario prestamo);

    public List<PrestamoUsuario> buscarPrestamosEnEspera();

    public List<PrestamoUsuario> buscarPrestamosDeUnUsuario(BigInteger secuencia);

    public List<PrestamoUsuario> buscarPrestamosAceptados(Date fechaSolicitada);

}
