/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AdministrarInterface.AdministrarMateriaUsuarioInterface;
import Entidades.Materia;
import Entidades.MateriaUsuario;
import Entidades.Usuario;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
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
public class ControlAsociarMateriaUsuario implements Serializable {

    @EJB
    AdministrarMateriaUsuarioInterface administrarMateriaUsuario;

    private Usuario usuarioLogin;
    private boolean permisoReservar, permisoPrestamo, permisoDocPracticas, permisoGuias, permisoEstadisticas, permisoUsuario, permisoMateria, permisoCerrarSesion, permisoLaboratorio;
    private boolean permisoIngresar;
    private String infoUsuarioConectado;

    private List<Materia> listaMaterias;
    private List<Materia> filtrarListaMaterias;
    private Materia materiaSeleccionada;

    private MateriaUsuario nuevaAsociacionMateria;

    private boolean aceptar;

    public ControlAsociarMateriaUsuario() {
        nuevaAsociacionMateria = new MateriaUsuario();
        nuevaAsociacionMateria.setMateria(new Materia());
        listaMaterias = null;
        materiaSeleccionada = null;
        aceptar = true;
    }

    public void seleccionarMateria() {
        nuevaAsociacionMateria.setMateria(materiaSeleccionada);
        materiaSeleccionada = new Materia();
        filtrarListaMaterias = null;
        aceptar = true;
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:materiaUsuario");
    }

    public void cancelarMateria() {
        materiaSeleccionada = new Materia();
        filtrarListaMaterias = null;
        aceptar = true;
    }

    public void dispararDialogoMaterias() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:MateriaDialogo");
        context.execute("MateriaDialogo.show()");
    }

    public void asociarNuevaMateriaAUsuario() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            if (nuevaAsociacionMateria.getMateria().getSecuencia() != null
                    && nuevaAsociacionMateria.getGrupo() != null) {
                int k = 1;
                BigInteger secuencia = new BigInteger(String.valueOf(k));
                nuevaAsociacionMateria.setSecuencia(secuencia);
                nuevaAsociacionMateria.setUsuario(usuarioLogin);
                administrarMateriaUsuario.registrarAsociacionMateriaUsuario(nuevaAsociacionMateria);
                nuevaAsociacionMateria = new MateriaUsuario();
                nuevaAsociacionMateria.setMateria(new Materia());
                FacesMessage msg = new FacesMessage("Información", "Se almaceno la relacion materia-usuario con exito");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                context.update("form:growl");
                context.update("form:PanelTotal");
            } else {
                context.execute("errorDatosNull.show()");
            }
        } catch (Exception e) {
            System.out.println("Error asociarNuevaMateriaAUsuario ControlAsociarMateriaUsuario : " + e.toString());
            FacesMessage msg = new FacesMessage("Información", "Ocurrio un error en el guardado de la relacion materia-usuario");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.update("form:growl");
        }
    }

    /**
     * Metodo encargado de recibir la secuencia del usuario de una pagina
     * anterior. Realiza la busqueda el usuario por la secuencia ingresada
     *
     * @param secuencia Secuencia del usuario
     */
    public void recibiriUsuarioConectado(BigInteger secuencia) {
        usuarioLogin = administrarMateriaUsuario.buscarUsuarioPorSecuencia(secuencia);
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

    public List<Materia> getListaMaterias() {
        listaMaterias = administrarMateriaUsuario.obtenerMaterias();
        return listaMaterias;
    }

    public void setListaMaterias(List<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public List<Materia> getFiltrarListaMaterias() {
        return filtrarListaMaterias;
    }

    public void setFiltrarListaMaterias(List<Materia> filtrarListaMaterias) {
        this.filtrarListaMaterias = filtrarListaMaterias;
    }

    public Materia getMateriaSeleccionada() {
        return materiaSeleccionada;
    }

    public void setMateriaSeleccionada(Materia materiaSeleccionada) {
        this.materiaSeleccionada = materiaSeleccionada;
    }

    public MateriaUsuario getNuevaAsociacionMateria() {
        return nuevaAsociacionMateria;
    }

    public void setNuevaAsociacionMateria(MateriaUsuario nuevaAsociacionMateria) {
        this.nuevaAsociacionMateria = nuevaAsociacionMateria;
    }

    public boolean isAceptar() {
        return aceptar;
    }

    public void setAceptar(boolean aceptar) {
        this.aceptar = aceptar;
    }

}
