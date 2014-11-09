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

    @EJB
    AdministrarReservaAreaTrabajoInterface administrarReservaAreaTrabajo;

    private Usuario usuarioLogin;
    private boolean permisoReservar, permisoPrestamo, permisoDocPracticas, permisoGuias, permisoEstadisticas, permisoUsuario, permisoMateria, permisoCerrarSesion, permisoLaboratorio;
    private boolean permisoIngresar;
    private String infoUsuarioConectado;
    //
    private Date fechaSeleccionada;
    //
    private PrestamoUsuario nuevoPrestamo;
    private Prestamo prestamoReserva;
    //
    private List<PrestamoUsuario> listaPrestamos;
    private List<PrestamoUsuario> filtrarListaPrestamos;

    private List<AreaTrabajo> listaAreasTrabajo;
    private List<AreaTrabajo> filtrarListaAreasTrabajo;
    private AreaTrabajo areaTrabajoSeleccionado;

    private List<GuiaTrabajo> listaGuiasTrabajo;
    private List<GuiaTrabajo> filtrarListaGuiasTrabajo;
    private GuiaTrabajo guiaTrabajoSeleccionada;
    //
    private int indice, k;
    //
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

    public void obtenerPosicionTablaPrestamo() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String type = map.get("t"); // type attribute of node
        indice = Integer.parseInt(type);
    }

    public void dispararDialogoRegistro() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:NuevaSolicitudPrestamo");
        context.execute("NuevaSolicitudPrestamo.show()");
    }

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

    public void cancelarPrestamoDeAreaTrabajo() {
        nuevoPrestamo = new PrestamoUsuario();
        prestamoReserva = new Prestamo();
        prestamoReserva.setAreatrabajo(new AreaTrabajo());
        prestamoReserva.setGuiatrabajo(new GuiaTrabajo());
        guiaTrabajoSeleccionada = new GuiaTrabajo();
        areaTrabajoSeleccionado = new AreaTrabajo();
    }

    public void seleccionarGuiaTrabajo() {
        prestamoReserva.setGuiatrabajo(guiaTrabajoSeleccionada);
        guiaTrabajoSeleccionada = new GuiaTrabajo();
        filtrarListaGuiasTrabajo = null;
        aceptar = true;
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:NuevaSolicitudPrestamo:nuevaGuiaTrabajo");
    }

    public void cancelarGuiaTrabajo() {
        guiaTrabajoSeleccionada = new GuiaTrabajo();
        filtrarListaGuiasTrabajo = null;
        aceptar = true;
    }

    public void seleccionarAreaTrabajo() {
        prestamoReserva.setAreatrabajo(areaTrabajoSeleccionado);
        areaTrabajoSeleccionado = new AreaTrabajo();
        filtrarListaAreasTrabajo = null;
        aceptar = true;
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:NuevaSolicitudPrestamo:nuevaAreaTrabajo");
    }

    public void cancelarAreaTrabajo() {
        areaTrabajoSeleccionado = new AreaTrabajo();
        filtrarListaAreasTrabajo = null;
        aceptar = true;
    }

    public void dispararDialogoAreaTrabajo() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:AreaDialogo");
        context.execute("AreaDialogo.show()");
    }

    public void dispararDialogoGuiaTrabajo() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:GuiasDialogo");
        context.execute("GuiasDialogo.show()");
    }

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
