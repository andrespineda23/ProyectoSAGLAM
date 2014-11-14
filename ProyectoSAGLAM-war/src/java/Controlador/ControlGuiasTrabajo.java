/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AdministrarInterface.AdministrarGuiasTrabajoInterface;
import Entidades.GuiaTrabajo;
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
import org.primefaces.model.UploadedFile;

/**
 *
 * @author John Pineda
 */
@ManagedBean
@SessionScoped
public class ControlGuiasTrabajo implements Serializable {

    @EJB
    AdministrarGuiasTrabajoInterface administrarGuiasTrabajo;

    private List<GuiaTrabajo> listGuiaTrabajo;
    private List<GuiaTrabajo> filtrarGuiaTrabajo;
    private GuiaTrabajo guiaTrabajoSeleccionada;
    private GuiaTrabajo nuevaGuiaTrabajo;
    private Usuario usuarioLogin;
    private int tipoLista, index;
    private BigInteger l;
    private boolean permitirIndex;
    private boolean permisoReservar, permisoPrestamo, permisoDocPracticas, permisoGuias, permisoEstadisticas, permisoUsuario, permisoMateria, permisoCerrarSesion, permisoLaboratorio;
    private boolean permisoIngresar;
    private boolean banderaCamposVacios;
    private String infoUsuarioConectado;
    private int tamano, repetidos;
    private UploadedFile file;

    public ControlGuiasTrabajo() {
        listGuiaTrabajo = null;
        permitirIndex = true;
        tamano = 270;
        nuevaGuiaTrabajo = new GuiaTrabajo();
    }

    public void obtenerPosicionGuiaTrabajo() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String type = map.get("t"); // type attribute of node
        index = Integer.parseInt(type);
    }

    /**
     * Metodo encargado de recibir la secuencia del usuario de una pagina
     * anterior. Realiza la busqueda el usuario por la secuencia ingresada
     *
     * @param secuencia Secuencia del usuario
     */
    public void recibiriUsuarioConectado(BigInteger secuencia) {
        usuarioLogin = administrarGuiasTrabajo.consultarUsuarioPorSecuencia(secuencia);
        activarFuncionesUsuario();
    }

    public void borrarGuiaTrabajo() {
        /**
         * *
         * FALTAS VALIDACIONES
         */
        if (index >= 0) {
            if (tipoLista == 0) {
                System.out.println("Entro a borrandoClasesPensiones");
                administrarGuiasTrabajo.eliminarGuiaTrabajo(listGuiaTrabajo.get(index));
            }
            if (tipoLista == 1) {
                System.out.println("borrandoClasesPensiones ");
                administrarGuiasTrabajo.eliminarGuiaTrabajo(filtrarGuiaTrabajo.get(index));
            }

            listGuiaTrabajo = null;
            getListGuiaTrabajo();
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("form:datosClasesPensiones");
            index = -1;
        }

    }

    /**
     * Metodo encargado de disparar el dialogo agregar una guia
     */
    public void dispararDialogoAgregar() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:RegistroNuevaGuiaTrabajo");
        context.execute("RegistroNuevaGuiaTrabajo.show()");
    }

    public void camposVacios() {
        if (nuevaGuiaTrabajo.getCodigo() != null && nuevaGuiaTrabajo.getNombre() != null && file != null) {
            for (int j = 0; j < listGuiaTrabajo.size(); j++) {
                if (nuevaGuiaTrabajo.getCodigo().equals(listGuiaTrabajo.get(j).getCodigo())) {
                    repetidos++;
                }
            }
            if (repetidos == 0) {
                banderaCamposVacios = false;
            } else {
                banderaCamposVacios = true;
            }
        } else {
            banderaCamposVacios = true;
        }
    }

    public void agregarNuevaGuiaTrabajo() {
        RequestContext context = RequestContext.getCurrentInstance();
        if (banderaCamposVacios == false) {
        } else {
            if (repetidos != 0) {
                context.execute("errorCamposVaciosyRepetidos.show()");
            } else {
                context.execute("errorCamposVacios.show()");
            }
        }
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

    public List<GuiaTrabajo> getListGuiaTrabajo() {
        if (listGuiaTrabajo == null) {
            listGuiaTrabajo = administrarGuiasTrabajo.consultarGuiasTrabajo();
        }
        return listGuiaTrabajo;
    }

    public void setListGuiaTrabajo(List<GuiaTrabajo> listGuiaTrabajo) {
        this.listGuiaTrabajo = listGuiaTrabajo;
    }

    public List<GuiaTrabajo> getFiltrarGuiaTrabajo() {
        return filtrarGuiaTrabajo;
    }

    public void setFiltrarGuiaTrabajo(List<GuiaTrabajo> filtrarGuiaTrabajo) {
        this.filtrarGuiaTrabajo = filtrarGuiaTrabajo;
    }

    public GuiaTrabajo getGuiaTrabajoSeleccionada() {
        return guiaTrabajoSeleccionada;
    }

    public void setGuiaTrabajoSeleccionada(GuiaTrabajo guiaTrabajoSeleccionada) {
        this.guiaTrabajoSeleccionada = guiaTrabajoSeleccionada;
    }

    public GuiaTrabajo getNuevaGuiaTrabajo() {
        return nuevaGuiaTrabajo;
    }

    public void setNuevaGuiaTrabajo(GuiaTrabajo nuevaGuiaTrabajo) {
        this.nuevaGuiaTrabajo = nuevaGuiaTrabajo;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
