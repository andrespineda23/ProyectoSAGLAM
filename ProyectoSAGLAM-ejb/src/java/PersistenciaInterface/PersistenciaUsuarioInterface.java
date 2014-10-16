package PersistenciaInterface;

import Entidades.Usuario;
import java.math.BigInteger;
import java.util.List;

/**
 * Interface encargada de determinar las operaciones que se realizan sobre la
 * tabla 'Usuario' de la base de datos.
 *
 * @author Andres Pineda - John Pineda
 */
public interface PersistenciaUsuarioInterface {

    /**
     * Metodo encargado de crear un usuario en el sistema
     *
     * @param usuario Usuario a crear
     */
    public void crearUsuario(Usuario usuario);

    /**
     * Metodo encargado de editar un usuario del sistema
     *
     * @param usuario Usuario a editar
     */
    public void editarUsuario(Usuario usuario);

    /**
     * Metodo encargado de borrar un usuario del sistema
     *
     * @param usuario Usuario a eliminar
     */
    public void borrarUsuario(Usuario usuario);

    /**
     * Metodo encargado de obtener todos los usuarios registrados en el sistema
     *
     * @return Lista de los usuarios registrados en el sistema
     */
    public List<Usuario> buscarUsuarios();

    /**
     * Metodo encargado de buscar a un usuario por medio de la secuencia unica
     *
     * @param secuencia Secuencia del usuario
     * @return Usuario que corresponde a la secuencia ingresada
     */
    public Usuario buscarUsuarioSecuencia(BigInteger secuencia);

    /**
     * Metodo encargado de buscar a un usuario por medio de su correo y
     * contraseña (Proceso de Login)
     *
     * @param correo Correo del usuario
     * @param contrasena Contraseña del usuario
     * @return Usuario que corresponde a las credenciales ingresadas
     */
    public Usuario buscarUsuarioPorCorreo_Contrasena(String correo, String contrasena);

    /**
     * Metodo encargado de buscar a un usuario por medio de su correo
     * electronico
     *
     * @param correo Correo electronico
     * @return Usuario que posee el correo electronico ingresado
     */
    public Usuario buscarUsuarioPorCorreo(String correo);

    /**
     * Metodo encargado de buscar la contraseña de un usuario por medio de su
     * correo y numero de documento
     *
     * @param correo Correo electronico
     * @param numDocumento Numero de documento
     * @return Contraseña del usuario que posee las credenciales ingresdas
     */
    public String buscarContrasenaUsuarioPorCorreo_NumeroDocumento(String correo, String numDocumento);

    /**
     * Metodo encargado de buscar un usuario del sistema por medio de su correo
     * y numero de documento
     *
     * @param correo Correo electronico
     * @param numDocumento Numero de documento
     * @return Usuario que corresponde a las credenciales ingresdas
     */
    public Usuario buscarUsuarioRegistradoEnSistema(String correo, String numDocumento);

    /**
     * *
     * Metodo encargado de buscar todos los usuarios que no estan bloqueados en
     * la base de datos
     *
     * @return una lista de usuarios
     */
    public List<Usuario> buscarUsuariosNOBloquedaos();

    /**
     * *
     * Metodo encargado de buscar el usuario con los datos ingresados
     *
     * @param correo Correo del usuario
     * @param contrasena Contrasena del usuario
     * @return Retorna un objeto usuario
     */
    public Usuario validarCambioContrasenaUsuario(String correo, String contrasena);
}
