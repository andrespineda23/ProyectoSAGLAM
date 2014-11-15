package Controlador;

import AdministrarInterface.AdministrarPrestamosInterface;
import Entidades.Materia;
import Entidades.MateriaUsuario;
import Entidades.PrestamoUsuario;
import Entidades.Usuario;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ANDRES PINEDA
 */
@ManagedBean
@SessionScoped
public class ControlPrestamosAsignatura implements Serializable {

    //Parametros
    //Inyeccion del EJB Adminitrador del controlador
    //este administrador controla todo los procesos necesarios
    //que el controlador necesite
    @EJB
    AdministrarPrestamosInterface administrarPrestamo;

    //Usuario que se encuentra actualmente en el sistema
    private Usuario usuarioLogin;
    //Permisos del usuario conectado
    private boolean permisoReservar, permisoPrestamo, permisoDocPracticas, permisoGuias, permisoEstadisticas, permisoUsuario, permisoMateria, permisoCerrarSesion, permisoLaboratorio;
    private boolean permisoIngresar;
    //Informacion del usuario conectado
    private String infoUsuarioConectado;

    //Lista de los prestamos de un usuario 
    private List<PrestamoUsuario> listaPrestamos;
    //Lista filtrada de los prestamos de un usuario
    private List<PrestamoUsuario> filtrarListaPrestamos;
    //Prestamo seleccionado de la tabla
    private PrestamoUsuario prestamoSeleccionado;
    //
    private int indice;
    //Lista de las asociaciones de materias y usuarios (docentes) registradas
    private List<MateriaUsuario> listaMateriaUsuario;
    //Lista filtrada de materiasusuarios
    private List<MateriaUsuario> filtrarListaMateriaUsuario;
    //MatereiaUsuario seleccionada
    private MateriaUsuario materiaUsuarioSeleccionado;
    //MateriaUsuario que se encuentra actualmente seleccionada por el usuario
    private MateriaUsuario actualMateriaUsuario;
    //Variable aceptar que controla el 'Aceptar' de los dialogos
    private boolean aceptar;

    public ControlPrestamosAsignatura() {
        aceptar = true;
        listaPrestamos = null;
        prestamoSeleccionado = null;
        listaMateriaUsuario = null;
        materiaUsuarioSeleccionado = new MateriaUsuario();
        actualMateriaUsuario = new MateriaUsuario();
        actualMateriaUsuario.setGrupo("");
        actualMateriaUsuario.setMateria(new Materia());
        actualMateriaUsuario.setUsuario(new Usuario());
    }

    /**
     * Metodo encargado de dispara el dialogo que visualiza las asociacioes
     * entre materias y usuarios
     */
    public void dispararDialogoMateriaUsuario() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:MateriaUsuarioDialogo");
        context.execute("MateriaUsuarioDialogo.show()");
    }

    /**
     * Metodo encargado de seleccionar la opcion escogida por el usuario
     */
    public void seleccionarMateriaUsuario() {
        actualMateriaUsuario = materiaUsuarioSeleccionado;
        materiaUsuarioSeleccionado = new MateriaUsuario();
        filtrarListaMateriaUsuario = null;
        aceptar = true;
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:materiaAsignatura");
        context.update("form:grupoAsignatura");
        context.update("form:docenteAsignatura");
    }

    public void cancelarMateriaUsuario() {
        materiaUsuarioSeleccionado = new MateriaUsuario();
        filtrarListaMateriaUsuario = null;
        aceptar = true;
    }

    public void buscarPrestamosDeUnaAsignatura() {
        try {
            RequestContext context = RequestContext.getCurrentInstance();
            if (actualMateriaUsuario.getSecuencia() != null) {
                listaPrestamos = administrarPrestamo.obtenerPrestamosDeUnUsuario(actualMateriaUsuario.getUsuario().getSecuencia());
                context.update("form:datosPrestamo");
            } else {
                context.execute("errorSeleccioneMateria.show()");
            }
        } catch (Exception e) {
            System.out.println("Error buscarPrestamosDeUnaAsignatura ControlPrestamosAsignatura : " + e.toString());
        }
    }

    public void limpiarResultados() {
        RequestContext context = RequestContext.getCurrentInstance();
        listaPrestamos = null;
        actualMateriaUsuario = new MateriaUsuario();
        actualMateriaUsuario.setGrupo("");
        actualMateriaUsuario.setMateria(new Materia());
        actualMateriaUsuario.setUsuario(new Usuario());
        context.update("form:PanelTotal");
    }

    public void obtenerPosicionTablaPrestamo() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String type = map.get("t"); // type attribute of node
        indice = Integer.parseInt(type);
    }

    /**
     * Metodo encargado de recibir la secuencia del usuario de una pagina
     * anterior. Realiza la busqueda el usuario por la secuencia ingresada
     *
     * @param secuencia Secuencia del usuario
     */
    public void recibiriUsuarioConectado(BigInteger secuencia) {
        usuarioLogin = administrarPrestamo.buscarUsuarioPorSecuencia(secuencia);
        activarFuncionesUsuario();
    }

    /**
     * Metodo encargado de activar las funciones registradas para el usuario que
     * se encuentra en el sistema
     */
    public void activarFuncionesUsuario() {
        permisoIngresar = true;
        infoUsuarioConectado = usuarioLogin.getNombres() + " " + usuarioLogin.getApellidos();
        if (usuarioLogin.getTipousuario().equalsIgnoreCase("estudiante")) {
            permisoCerrarSesion = false;
            permisoDocPracticas = false;
            permisoEstadisticas = true;
            permisoGuias = true;
            permisoMateria = true;
            permisoPrestamo = false;
            permisoReservar = false;
            permisoUsuario = false;
            permisoLaboratorio = true;
        }
        if (usuarioLogin.getTipousuario().equalsIgnoreCase("docente")) {
            permisoCerrarSesion = false;
            permisoDocPracticas = false;
            permisoEstadisticas = true;
            permisoGuias = false;
            permisoMateria = false;
            permisoPrestamo = false;
            permisoReservar = false;
            permisoUsuario = false;
            permisoLaboratorio = true;
        }
        if (usuarioLogin.getTipousuario().equalsIgnoreCase("laboratorista")) {
            permisoCerrarSesion = false;
            permisoDocPracticas = false;
            permisoEstadisticas = false;
            permisoGuias = false;
            permisoMateria = true;
            permisoPrestamo = false;
            permisoReservar = true;
            permisoUsuario = false;
            permisoLaboratorio = false;
        }
    }

    public void activarAceptar() {
        if (aceptar == true) {
            aceptar = false;
        }
    }

    //GET-SET
    public Usuario getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuario usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public boolean isPermisoReservar() {
        return permisoReservar;
    }

    public void setPermisoReservar(boolean permisoReservar) {
        this.permisoReservar = permisoReservar;
    }

    public boolean isPermisoPrestamo() {
        return permisoPrestamo;
    }

    public void setPermisoPrestamo(boolean permisoPrestamo) {
        this.permisoPrestamo = permisoPrestamo;
    }

    public boolean isPermisoDocPracticas() {
        return permisoDocPracticas;
    }

    public void setPermisoDocPracticas(boolean permisoDocPracticas) {
        this.permisoDocPracticas = permisoDocPracticas;
    }

    public boolean isPermisoGuias() {
        return permisoGuias;
    }

    public void setPermisoGuias(boolean permisoGuias) {
        this.permisoGuias = permisoGuias;
    }

    public boolean isPermisoEstadisticas() {
        return permisoEstadisticas;
    }

    public void setPermisoEstadisticas(boolean permisoEstadisticas) {
        this.permisoEstadisticas = permisoEstadisticas;
    }

    public boolean isPermisoUsuario() {
        return permisoUsuario;
    }

    public void setPermisoUsuario(boolean permisoUsuario) {
        this.permisoUsuario = permisoUsuario;
    }

    public boolean isPermisoMateria() {
        return permisoMateria;
    }

    public void setPermisoMateria(boolean permisoMateria) {
        this.permisoMateria = permisoMateria;
    }

    public boolean isPermisoCerrarSesion() {
        return permisoCerrarSesion;
    }

    public void setPermisoCerrarSesion(boolean permisoCerrarSesion) {
        this.permisoCerrarSesion = permisoCerrarSesion;
    }

    public boolean isPermisoLaboratorio() {
        return permisoLaboratorio;
    }

    public void setPermisoLaboratorio(boolean permisoLaboratorio) {
        this.permisoLaboratorio = permisoLaboratorio;
    }

    public boolean isPermisoIngresar() {
        return permisoIngresar;
    }

    public void setPermisoIngresar(boolean permisoIngresar) {
        this.permisoIngresar = permisoIngresar;
    }

    public String getInfoUsuarioConectado() {
        return infoUsuarioConectado;
    }

    public void setInfoUsuarioConectado(String infoUsuarioConectado) {
        this.infoUsuarioConectado = infoUsuarioConectado;
    }

    public List<PrestamoUsuario> getListaPrestamos() {
        return listaPrestamos;
    }

    public void setListaPrestamos(List<PrestamoUsuario> listaPrestamos) {
        this.listaPrestamos = listaPrestamos;
    }

    public List<PrestamoUsuario> getFiltrarListaPrestamos() {
        return filtrarListaPrestamos;
    }

    public void setFiltrarListaPrestamos(List<PrestamoUsuario> filtrarListaPrestamos) {
        this.filtrarListaPrestamos = filtrarListaPrestamos;
    }

    public PrestamoUsuario getPrestamoSeleccionado() {
        return prestamoSeleccionado;
    }

    public void setPrestamoSeleccionado(PrestamoUsuario prestamoSeleccionado) {
        this.prestamoSeleccionado = prestamoSeleccionado;
    }

    public List<MateriaUsuario> getListaMateriaUsuario() {
        listaMateriaUsuario = administrarPrestamo.buscarMateriasUsuarios();
        return listaMateriaUsuario;
    }

    public void setListaMateriaUsuario(List<MateriaUsuario> listaMateriaUsuario) {
        this.listaMateriaUsuario = listaMateriaUsuario;
    }

    public List<MateriaUsuario> getFiltrarListaMateriaUsuario() {
        return filtrarListaMateriaUsuario;
    }

    public void setFiltrarListaMateriaUsuario(List<MateriaUsuario> filtrarListaMateriaUsuario) {
        this.filtrarListaMateriaUsuario = filtrarListaMateriaUsuario;
    }

    public MateriaUsuario getMateriaUsuarioSeleccionado() {
        return materiaUsuarioSeleccionado;
    }

    public void setMateriaUsuarioSeleccionado(MateriaUsuario materiaUsuarioSeleccionado) {
        this.materiaUsuarioSeleccionado = materiaUsuarioSeleccionado;
    }

    public MateriaUsuario getActualMateriaUsuario() {
        return actualMateriaUsuario;
    }

    public void setActualMateriaUsuario(MateriaUsuario actualMateriaUsuario) {
        this.actualMateriaUsuario = actualMateriaUsuario;
    }

    public boolean isAceptar() {
        return aceptar;
    }

    public void setAceptar(boolean aceptar) {
        this.aceptar = aceptar;
    }

}
