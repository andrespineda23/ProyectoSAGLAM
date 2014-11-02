/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministrarInterface;

import Entidades.Prestamo;
import Entidades.PrestamoUsuario;
import Entidades.Usuario;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ANDRES PINEDA
 */
public interface AdministrarPrestamosInterface {

    public Usuario buscarUsuarioPorSecuencia(BigInteger secuencia);

    public List<PrestamoUsuario> obtenerPrestamosEnProcesoDeEspera();

    public void modificarEstadoPrestamo(PrestamoUsuario prestamo);

    public List<PrestamoUsuario> obtenerPrestamosDeUnUsuario(BigInteger secuencia);
}
