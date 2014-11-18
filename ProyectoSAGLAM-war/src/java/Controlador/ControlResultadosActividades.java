/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AdministrarInterface.AdministrarResultadosActividadesInterface;
import Entidades.ResultadosActividades;
import Entidades.Usuario;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author John Pineda
 */
@ManagedBean
@SessionScoped
public class ControlResultadosActividades implements Serializable {

    @EJB
    AdministrarResultadosActividadesInterface administrarResultadosActividades;

    private List<ResultadosActividades> listResultadosActividades;
    private List<ResultadosActividades> filtrarResultadosActividades;
    private ResultadosActividades guiaTrabajoSeleccionada;
    private ResultadosActividades nuevaResultadosActividades;
    private int cualCelda, tipoLista, index;
    private boolean guardado;
    private boolean permitirIndex;

    private int tamano;
    private Usuario usuarioLogin;

    private boolean permisoReservar, permisoPrestamo, permisoDocPracticas, permisoGuias, permisoEstadisticas, permisoUsuario, permisoMateria, permisoCerrarSesion, permisoLaboratorio;
    private boolean permisoIngresar;
    private String infoUsuarioConectado;

    private final String server = "192.168.0.15";
    private final int port = 21;
    private final String user = "ANDRESPINEDA";
    private final String pass = "andres.pin51";

    private FTPClient ftpClient;
    private DefaultStreamedContent download;

    public ControlResultadosActividades() {

        ftpClient = new FTPClient();
        infoUsuarioConectado = "Información Usuario Conectado";
        permisoIngresar = false;
        permisoReservar = true;
        permisoPrestamo = true;
        permisoDocPracticas = true;
        permisoLaboratorio = true;
        permisoEstadisticas = true;
        permisoGuias = true;
        permisoUsuario = true;
        permisoMateria = true;
        permisoCerrarSesion = true;

        listResultadosActividades = null;
        permitirIndex = true;
        guardado = true;
        tamano = 270;
        nuevaResultadosActividades = new ResultadosActividades();
    }

    public void conectarAlFTP() {
        try {
            ftpClient.connect(server, port);
            System.out.println("2");
            //ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            System.out.println("3");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            System.out.println("4");
            int reply = ftpClient.getReplyCode();
            System.out.println("Respuesta recibida de conexión FTP:" + reply);
            if (FTPReply.isPositiveCompletion(reply)) {
                System.out.println("Conectado Satisfactoriamente");
            } else {
                System.out.println("Imposible conectarse al servidor");
            }
        } catch (Exception e) {
            System.out.println("Error en conectarAlFTP ControlResultadosActividades : " + e.toString());
        }
    }

    public void descargarArchivoFTP() throws IOException {
        try {
            conectarAlFTP();

            System.out.println("ftpClient : " + ftpClient.getRemoteAddress().getHostAddress());
            final String rutaX = "/archivospractica/prueba.txt";
            String remoteFile1 = rutaX;
            File downloadFile1 = new File("C:pruebaaaaaa.txt");
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            System.out.println("remoteFile1 : " + remoteFile1);
            System.out.println("ftpClient.isConnected() : " + ftpClient.isConnected());
            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
            int replicode = ftpClient.getReplyCode();
            System.out.println("replicode : " + replicode);
            System.out.println("success : " + success);
            if (success) {
                System.out.println("File #2 has been downloaded successfully.");
            } else {
                System.out.println("Ni Mierda !.");
            }
            outputStream1.close();
            ftpClient.logout();
            /*
             File file = new File("HV AFPM.pdf");
             InputStream input = new FileInputStream(file);
             ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
             setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));

             FileOutputStream fos = null;
             fos = new FileOutputStream("HV AFPM.pdf");
             boolean validarDescarga = ftpClient.retrieveFile("/ArchivosResultados/HV AFPM.pdf", fos);
             if (validarDescarga == true) {
             System.out.println("Yeah !");
             } else {
             System.out.println("No :S");
             }
             ftpClient.logout();
             */
        } catch (Exception e) {
            System.out.println("Error descargarArchivoFTP  ControlResultadosActividades : " + e.toString());
        }
    }

    /**
     * *
     * Metodo encargado de capturar la posicion en la que el usuario a
     * seleccionado
     *
     * @param indice fila
     * @param celda columna
     */
    public void cambiarIndice(int indice, int celda) {
        index = indice;
        cualCelda = celda;
    }

    /**
     * *
     * Metodo encargado de cambiar la bandera tipoLista para el manejo de la
     * lista que se esta usando
     */
    public void eventoFiltrar() {
        if (tipoLista == 0) {
            tipoLista = 1;
        }
    }

    public void borrarResultadosActividades() {
        /**
         * *
         * FALTAS VALIDACIONES
         */
        if (index >= 0) {
            if (tipoLista == 0) {
                administrarResultadosActividades.eliminarResultadosActividades(listResultadosActividades.get(index));
            }
            if (tipoLista == 1) {
                administrarResultadosActividades.eliminarResultadosActividades(filtrarResultadosActividades.get(index));
            }

            listResultadosActividades = null;
            getListResultadosActividades();
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("form:datosClasesPensiones");
            index = -1;

            if (guardado == true) {
                guardado = false;
            }
            context.update("form:ACEPTAR");
        }

    }

    /**
     * Metodo encargado de disparar el dialogo agregar una guia
     */
    public void dispararDialogoAgregar() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:RegistroNuevaResultadosActividades");
        context.execute("RegistroNuevaResultadosActividades.show()");
    }

    public void agregarNuevaResultadosActividades() {
    }

    /**
     * Metodo encargado de activar las funciones registradas para el usuario que
     * se encuentra en el sistema
     */
    public void activarFuncionesUsuario() {

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
        permisoIngresar = true;
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form:PanelOpciones");
    }

    /**
     * Metodo encargado de recibir la secuencia del usuario de una pagina
     * anterior. Realiza la busqueda el usuario por la secuencia ingresada
     *
     * @param secuencia Secuencia del usuario
     */
    public void recibiriUsuarioConectado(BigInteger secuencia) {
        usuarioLogin = administrarResultadosActividades.buscarUsuarioPorSecuencia(secuencia);
        activarFuncionesUsuario();
    }

    public List<ResultadosActividades> getListResultadosActividades() {
        if (listResultadosActividades == null) {
            listResultadosActividades = administrarResultadosActividades.consultarResultadosActividades();
        }
        return listResultadosActividades;
    }

    public void setListResultadosActividades(List<ResultadosActividades> listResultadosActividades) {
        this.listResultadosActividades = listResultadosActividades;
    }

    public List<ResultadosActividades> getFiltrarResultadosActividades() {
        return filtrarResultadosActividades;
    }

    public void setFiltrarResultadosActividades(List<ResultadosActividades> filtrarResultadosActividades) {
        this.filtrarResultadosActividades = filtrarResultadosActividades;
    }

    public ResultadosActividades getResultadosActividadesSeleccionada() {
        return guiaTrabajoSeleccionada;
    }

    public void setResultadosActividadesSeleccionada(ResultadosActividades guiaTrabajoSeleccionada) {
        this.guiaTrabajoSeleccionada = guiaTrabajoSeleccionada;
    }

    public ResultadosActividades getNuevaResultadosActividades() {
        return nuevaResultadosActividades;
    }

    public void setNuevaResultadosActividades(ResultadosActividades nuevaResultadosActividades) {
        this.nuevaResultadosActividades = nuevaResultadosActividades;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public ResultadosActividades getGuiaTrabajoSeleccionada() {
        return guiaTrabajoSeleccionada;
    }

    public void setGuiaTrabajoSeleccionada(ResultadosActividades guiaTrabajoSeleccionada) {
        this.guiaTrabajoSeleccionada = guiaTrabajoSeleccionada;
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

    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

    public DefaultStreamedContent getDownload() throws Exception {
        return download;
    }
}
