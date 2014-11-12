/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministrarInterface;

import Entidades.Usuario;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface del laboratorista. Posee las operaciones para la capa de negocio
 * para bloquear a un usuario
 *
 * @author John Pineda
 */
@Local
public interface AdministrarBloquearUsuarioInterface {

    /**
     * Metodo encargado de modificar el usuario que se envia
     *
     * @param usuario Atributo de tipo usuario que sera modificado
     */
    public void modificarUsuario(Usuario usuario);

    /**
     * Metodo encargado de traer todos los usuarios que no estan bloqueados
     *
     * @return lista de usuarios
     */
    public List<Usuario> consultarUsuario();

    /**
     * Metodo encargado de obtener el usuario conectado en el sistema por medio
     * de su secuencia
     *
     * @param secEmpleado Secuencia del usuario
     * @return Usuario conectado
     */
    public Usuario consultarUsuarioPorSecuencia(BigInteger secEmpleado);
}
