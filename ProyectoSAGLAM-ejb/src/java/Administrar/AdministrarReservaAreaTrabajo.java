package Administrar;

import AdministrarInterface.AdministrarReservaAreaTrabajoInterface;
import Entidades.AreaTrabajo;
import Entidades.GuiaTrabajo;
import Entidades.Prestamo;
import Entidades.PrestamoUsuario;
import Entidades.Usuario;
import PersistenciaInterface.PersistenciaAreaTrabajoInterface;
import PersistenciaInterface.PersistenciaGuiaTrabajoInterface;
import PersistenciaInterface.PersistenciaPrestamoInterface;
import PersistenciaInterface.PersistenciaPrestamoUsuarioInterface;
import PersistenciaInterface.PersistenciaUsuarioInterface;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ANDRES PINEDA
 */
@Stateless
public class AdministrarReservaAreaTrabajo implements AdministrarReservaAreaTrabajoInterface {

    @EJB
    PersistenciaUsuarioInterface persistenciaUsuario;
    @EJB
    PersistenciaPrestamoUsuarioInterface persistenciaPrestamoUsuario;
    @EJB
    PersistenciaAreaTrabajoInterface persistenciaAreaTrabajo;
    @EJB
    PersistenciaPrestamoInterface persistenciaPrestamo;
    @EJB
    PersistenciaGuiaTrabajoInterface persistenciaGuiaTrabajo;

    @Override
    public void registrarReservaAreaTrabajo(PrestamoUsuario prestamo) {
        try {
            persistenciaPrestamoUsuario.crearPrestamo(prestamo);
        } catch (Exception e) {
            System.out.println("Error registrarReservaAreaTrabajo AdministrarReservaAreaTrabajo : " + e.toString());
        }
    }

    @Override
    public List<PrestamoUsuario> obtenerPrestamosYaRegistrados(Date fechaSolicitada) {
        try {
            List<PrestamoUsuario> lista = persistenciaPrestamoUsuario.buscarPrestamosAceptados(fechaSolicitada);
            return lista;
        } catch (Exception e) {
            System.out.println("Error obtenerPrestamosYaRegistrados AdministrarReservaAreaTrabajo : " + e.toString());
            return null;
        }
    }

    @Override
    public Usuario buscarUsuarioPorSecuencia(BigInteger secuencia) {
        try {
            Usuario usuario = persistenciaUsuario.buscarUsuarioSecuencia(secuencia);
            return usuario;
        } catch (Exception e) {
            System.out.println("Error buscarUsuarioPorSecuencia AdministrarPrestamos : " + e.toString());
            return null;
        }
    }

    @Override
    public List<AreaTrabajo> buscarAreasDeTrabajo() {
        try {
            List<AreaTrabajo> lista = persistenciaAreaTrabajo.buscarAreasDeTrabajo();
            return lista;
        } catch (Exception e) {
            System.out.println("Error buscarAreasDeTrabajo AdministrarReservaAreaTrabajo : " + e.toString());
            return null;
        }
    }

    @Override
    public void crearPrestamo(Prestamo prestamo) {
        try {
            persistenciaPrestamo.crearPrestamo(prestamo);
        } catch (Exception e) {
            System.out.println("Error crearPrestamo AdministrarReservaAreaTrabajo : " + e.toString());
        }
    }

    @Override
    public Prestamo obtenerUltimoPrestamoRegistrado() {
        try {
            Prestamo prestamo = persistenciaPrestamo.obtenerUltimoPrestamoRegistrado();
            return prestamo;
        } catch (Exception e) {
            System.out.println("Error obtenerUltimoPrestamoRegistrado AdministrarReservaAreaTrabajo : " + e.toString());
            return null;
        }
    }

    @Override
    public List<GuiaTrabajo> buscarGuiasDeTrabajo() {
        try {
            List<GuiaTrabajo> lista = persistenciaGuiaTrabajo.consultarGuiasTrabajo();
            return lista;
        } catch (Exception e) {
            System.out.println("Error buscarGuiasDeTrabajo AdministrarReservaAreaTrabajo : " + e.toString());
            return null;
        }
    }
}
