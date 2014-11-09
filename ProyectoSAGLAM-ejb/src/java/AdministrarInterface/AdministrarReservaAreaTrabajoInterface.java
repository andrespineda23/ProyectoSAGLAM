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
 *
 * @author ANDRES PINEDA
 */
public interface AdministrarReservaAreaTrabajoInterface {

    public void registrarReservaAreaTrabajo(PrestamoUsuario prestamo);

    public List<PrestamoUsuario> obtenerPrestamosYaRegistrados(Date fechaSolicitada);

    public Usuario buscarUsuarioPorSecuencia(BigInteger secuencia);

    public List<AreaTrabajo> buscarAreasDeTrabajo();

    public void crearPrestamo(Prestamo prestamo);

    public Prestamo obtenerUltimoPrestamoRegistrado();

    public List<GuiaTrabajo> buscarGuiasDeTrabajo();

}
