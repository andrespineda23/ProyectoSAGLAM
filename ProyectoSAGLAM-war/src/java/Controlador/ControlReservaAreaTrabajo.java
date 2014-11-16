package Controlador;

import AdministrarInterface.AdministrarReservaAreaTrabajoInterface;
import Entidades.AreaTrabajo;
import Entidades.GuiaTrabajo;
import Entidades.Prestamo;
import Entidades.PrestamoUsuario;
import Entidades.Usuario;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
public class ControlReservaAreaTrabajo implements Serializable {

    //Parametros
    //Inyeccion del EJB Adminitrador del controlador
    //este administrador controla todo los procesos necesarios
    //que el controlador necesite
    @EJB
    AdministrarReservaAreaTrabajoInterface administrarReservaAreaTrabajo;
    //Usuario que se encuentra actualmente registrado en el sistema
    private Usuario usuarioLogin;
    //Permisos del usuario conectado
    private boolean permisoReservar, permisoPrestamo, permisoDocPracticas, permisoGuias, permisoEstadisticas, permisoUsuario, permisoMateria, permisoCerrarSesion, permisoLaboratorio;
    private boolean permisoIngresar;
    //Informacion del usuario conectado
    private String infoUsuarioConectado;
    //Fecha en la cual sera solicitada el prestamo
    private Date fechaSeleccionada;
    //Nuevo prestamo asociado a un usuario que sera registrado
    private PrestamoUsuario nuevoPrestamo;
    //Nuevo objeto de prestamo que sera almacenado en el sistema
    private Prestamo prestamoReserva;
    //Lista de prestamos usuario que se encuentra aceptados y que en la fecha solicitada ya tienen un horario establecido
    private List<PrestamoUsuario> listaPrestamos;
    //Lista filtrada de los prestamos aceptados
    private List<PrestamoUsuario> filtrarListaPrestamos;
    //Lista de areas de trabajo registradas en el sistema
    private List<AreaTrabajo> listaAreasTrabajo;
    //Lista filtrada de areas de trabajo
    private List<AreaTrabajo> filtrarListaAreasTrabajo;
    //Area de trabajo seleccionada para el prestamo
    private AreaTrabajo areaTrabajoSeleccionado;

    //Lista de guias de trabajo almacenadas en el sistema
    private List<GuiaTrabajo> listaGuiasTrabajo;
    //Lista filtrada de guias de trabajo
    private List<GuiaTrabajo> filtrarListaGuiasTrabajo;
    //Guia de trabajo que sera almacenada en el prestamo
    private GuiaTrabajo guiaTrabajoSeleccionada;
    //
    private int indice, k;
    //Variable encargada de activar boton 'Aceptar' de los dialogos
    private boolean aceptar;

    public ControlReservaAreaTrabajo() {
        aceptar = true;
        fechaSeleccionada = new Date();
        listaAreasTrabajo = null;
        k = 1;
        prestamoReserva = new Prestamo();
        prestamoReserva.setAreatrabajo(new AreaTrabajo());
        prestamoReserva.setGuiatrabajo(new GuiaTrabajo());
        listaAreasTrabajo = null;
        listaGuiasTrabajo = null;
    }

    /**
     * Metodo encargado de obtener la posicion de la tabla de prestamos
     */
    public void obtenerPosicionTablaPrestamo() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String type = map.get("t"); // type attribute of node
        indice = Integer.parseInt(type);
    }

    /**
     * Metodo encargado de mostrar el dialogo del registro de una nueva
     * solicitud de prestamo
     */
    public void dispararDialogoRegistro() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:NuevaSolicitudPrestamo");
        context.execute("NuevaSolicitudPrestamo.show()");
    }

    /**
     * Metodo encargado de validar los datos del nuevo registro del prestamo
     *
     * @return true- Los datos estan completos / false- Algun dato se encuentra
     * vacio
     */
    public boolean validarDatosReserva() {
        boolean retorno = true;
        if (prestamoReserva.getAreatrabajo().getSecuencia() == null) {
            retorno = false;
        }
        if (prestamoReserva.getFecha() == null) {
            retorno = false;
        }
        if (prestamoReserva.getGuiatrabajo().getSecuencia() == null) {
            retorno = false;
        }
        if (prestamoReserva.getHorafinal() > 0 && prestamoReserva.getHorainicial() > 0) {
            retorno = false;
        }
        if (prestamoReserva.getTipoactividad() == null || prestamoReserva.getTipoactividad().isEmpty()) {
            retorno = false;
        }
        return retorno;
    }

    /**
     * Metodo encargado de almacenar la nueva solicitud de prestamo de un
     * usuario
     */
    public void registrarPrestamoDeAreaTrabajo() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            if (validarDatosReserva() == true) {
                k++;
                BigInteger secuencia = new BigInteger(String.valueOf(k));
                prestamoReserva.setEstadosolicitud("EN ESPERA");
                prestamoReserva.setSecuencia(secuencia);
                administrarReservaAreaTrabajo.crearPrestamo(prestamoReserva);
                Prestamo prestamoRegistrado = administrarReservaAreaTrabajo.obtenerUltimoPrestamoRegistrado();
                nuevoPrestamo.setUsuario(usuarioLogin);
                nuevoPrestamo.setPrestamo(prestamoRegistrado);
                k++;
                secuencia = new BigInteger(String.valueOf(k));
                nuevoPrestamo.setSecuencia(secuencia);
                administrarReservaAreaTrabajo.registrarReservaAreaTrabajo(nuevoPrestamo);
                nuevoPrestamo = new PrestamoUsuario();
                prestamoReserva = new Prestamo();
                prestamoReserva.setAreatrabajo(new AreaTrabajo());
                prestamoReserva.setGuiatrabajo(new GuiaTrabajo());
                guiaTrabajoSeleccionada = new GuiaTrabajo();
                areaTrabajoSeleccionado = new AreaTrabajo();
                FacesMessage msg = new FacesMessage("Información", "Ocurrio un error en el guardado de la reserva, intente nuevamente");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                context.update("form:growl");
                context.execute("NuevaSolicitudPrestamo.hide()");
            } else {
                context.execute("errorDatosObligatorios.show()");
            }
        } catch (Exception e) {
            System.out.println("Error registrarPrestamoDeAreaTrabajo ControlReservaAreaTrabajo : " + e.toString());
            FacesMessage msg = new FacesMessage("Información", "Ocurrio un error en el guardado de la reserva, intente nuevamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.update("form:growl");
        }
    }

    /**
     * Metodo encargado de cancelar el registro de una nueva solicitud de
     * prestamo
     */
    public void cancelarPrestamoDeAreaTrabajo() {
        nuevoPrestamo = new PrestamoUsuario();
        prestamoReserva = new Prestamo();
        prestamoReserva.setAreatrabajo(new AreaTrabajo());
        prestamoReserva.setGuiatrabajo(new GuiaTrabajo());
        guiaTrabajoSeleccionada = new GuiaTrabajo();
        areaTrabajoSeleccionado = new AreaTrabajo();
    }

    /**
     * Metodo encargado de seleccionar una guia de trabajo y enlazarla a la
     * solicitud de prestamo
     */
    public void seleccionarGuiaTrabajo() {
        prestamoReserva.setGuiatrabajo(guiaTrabajoSeleccionada);
        guiaTrabajoSeleccionada = new GuiaTrabajo();
        filtrarListaGuiasTrabajo = null;
        aceptar = true;
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:NuevaSolicitudPrestamo:nuevaGuiaTrabajo");
    }

    /**
     * Metodo encargado de cancelar la seleccion de la guia de trabajo
     */
    public void cancelarGuiaTrabajo() {
        guiaTrabajoSeleccionada = new GuiaTrabajo();
        filtrarListaGuiasTrabajo = null;
        aceptar = true;
    }

    /**
     * Metodo encargado de seleccionar una nueva area de trabajo para la
     * solicitud de prestamo
     */
    public void seleccionarAreaTrabajo() {
        prestamoReserva.setAreatrabajo(areaTrabajoSeleccionado);
        areaTrabajoSeleccionado = new AreaTrabajo();
        filtrarListaAreasTrabajo = null;
        aceptar = true;
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:NuevaSolicitudPrestamo:nuevaAreaTrabajo");
    }

    /**
     * Metodo encargado de cancelar la seleccion del area de trabajo
     */
    public void cancelarAreaTrabajo() {
        areaTrabajoSeleccionado = new AreaTrabajo();
        filtrarListaAreasTrabajo = null;
        aceptar = true;
    }

    /**
     * Metodo encargado de visualizar el dialogo de areas de trabajo para la
     * solicitud de prestamo
     */
    public void dispararDialogoAreaTrabajo() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:AreaDialogo");
        context.execute("AreaDialogo.show()");
    }

    /**
     * Metodo encargado de visualizar el dialogo de guias de trabajo para la
     * solicitud de prestamo
     */
    public void dispararDialogoGuiaTrabajo() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:GuiasDialogo");
        context.execute("GuiasDialogo.show()");
    }

    /**
     * Metodo encargado de obtener la lista de prestamos aceptados en una fecha
     * ingresada
     */
    public void buscarPrestamosSolicitados() {
        try {
            RequestContext context = RequestContext.getCurrentInstance();
            if (fechaSeleccionada != null) {
                listaPrestamos = administrarReservaAreaTrabajo.obtenerPrestamosYaRegistrados(fechaSeleccionada);
                if (listaPrestamos != null) {
                    System.out.println("listaPrestamos : " + listaPrestamos.size());
                } else {
                    System.out.println("listaPrestamos : Super Cero !");
                }
                context.update("form:datosPrestamos");
            } else {
                context.execute("errorFechaSeleccionada.show()");
            }
        } catch (Exception e) {
            System.out.println("Error buscarPrestamosSolicitados ControlReservaAreaTrabajo : " + e.toString());
        }
    }

    /**
     * Metodo encargado de recibir la secuencia del usuario de una pagina
     * anterior. Realiza la busqueda el usuario por la secuencia ingresada
     *
     * @param secuencia Secuencia del usuario
     */
    public void recibiriUsuarioConectado(BigInteger secuencia) {
        usuarioLogin = administrarReservaAreaTrabajo.buscarUsuarioPorSecuencia(secuencia);
        activarFuncionesUsuario();
    }

    /**
     * Metodo encargado de activar las funciones registradas para el usuario que
     * se encuentra en el sistema
     */
    public void activarFuncionesUsuario() {

        if (usuarioLogin.getTipousuario().equalsIgnoreCase("estudiante")) {
            permisoCerrarSesion = false;
            permisoDocPracticas = false;
            permisoEstadisticas = false;
            permisoGuias = false;
            permisoMateria = false;
            permisoPrestamo = false;
            permisoReservar = false;
            permisoUsuario = false;
            permisoLaboratorio = false;
        }
        if (usuarioLogin.getTipousuario().equalsIgnoreCase("docente")) {
            permisoCerrarSesion = false;
            permisoDocPracticas = false;
            permisoEstadisticas = false;
            permisoGuias = false;
            permisoMateria = false;
            permisoPrestamo = false;
            permisoReservar = false;
            permisoUsuario = false;
            permisoLaboratorio = false;
        }
        if (usuarioLogin.getTipousuario().equalsIgnoreCase("laboratorista")) {
            permisoCerrarSesion = false;
            permisoDocPracticas = false;
            permisoEstadisticas = false;
            permisoGuias = false;
            permisoMateria = false;
            permisoPrestamo = false;
            permisoReservar = false;
            permisoUsuario = false;
            permisoLaboratorio = false;
        }
        permisoIngresar = true;
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:PanelOpciones");
    }

    /**
     * Metodo encargado de cambiar el estado del boton 'Aceptar' de los dialogos
     */
    public void activarAceptar() {
        if (aceptar == true) {
            aceptar = false;
        }
    }

    //GET _ SET
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

    public Date getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    public void setFechaSeleccionada(Date fechaSeleccionada) {
        this.fechaSeleccionada = fechaSeleccionada;
    }

    public PrestamoUsuario getNuevoPrestamo() {
        return nuevoPrestamo;
    }

    public void setNuevoPrestamo(PrestamoUsuario nuevoPrestamo) {
        this.nuevoPrestamo = nuevoPrestamo;
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

    public List<AreaTrabajo> getListaAreasTrabajo() {
        if (listaAreasTrabajo == null) {
            listaAreasTrabajo = administrarReservaAreaTrabajo.buscarAreasDeTrabajo();
        }
        return listaAreasTrabajo;
    }

    public void setListaAreasTrabajo(List<AreaTrabajo> listaAreasTrabajo) {
        this.listaAreasTrabajo = listaAreasTrabajo;
    }

    public Prestamo getPrestamoReserva() {
        return prestamoReserva;
    }

    public void setPrestamoReserva(Prestamo prestamoReserva) {
        this.prestamoReserva = prestamoReserva;
    }

    public List<GuiaTrabajo> getListaGuiasTrabajo() {
        if (listaGuiasTrabajo == null) {
            listaGuiasTrabajo = administrarReservaAreaTrabajo.buscarGuiasDeTrabajo();
        }
        return listaGuiasTrabajo;
    }

    public void setListaGuiasTrabajo(List<GuiaTrabajo> listaGuiasTrabajo) {
        this.listaGuiasTrabajo = listaGuiasTrabajo;
    }

    public AreaTrabajo getAreaTrabajoSeleccionado() {
        return areaTrabajoSeleccionado;
    }

    public void setAreaTrabajoSeleccionado(AreaTrabajo areaTrabajoSeleccionado) {
        this.areaTrabajoSeleccionado = areaTrabajoSeleccionado;
    }

    public GuiaTrabajo getGuiaTrabajoSeleccionada() {
        return guiaTrabajoSeleccionada;
    }

    public void setGuiaTrabajoSeleccionada(GuiaTrabajo guiaTrabajoSeleccionada) {
        this.guiaTrabajoSeleccionada = guiaTrabajoSeleccionada;
    }

    public List<AreaTrabajo> getFiltrarListaAreasTrabajo() {
        return filtrarListaAreasTrabajo;
    }

    public void setFiltrarListaAreasTrabajo(List<AreaTrabajo> filtrarListaAreasTrabajo) {
        this.filtrarListaAreasTrabajo = filtrarListaAreasTrabajo;
    }

    public List<GuiaTrabajo> getFiltrarListaGuiasTrabajo() {
        return filtrarListaGuiasTrabajo;
    }

    public void setFiltrarListaGuiasTrabajo(List<GuiaTrabajo> filtrarListaGuiasTrabajo) {
        this.filtrarListaGuiasTrabajo = filtrarListaGuiasTrabajo;
    }

    public boolean isAceptar() {
        return aceptar;
    }

    public void setAceptar(boolean aceptar) {
        this.aceptar = aceptar;
    }

}
