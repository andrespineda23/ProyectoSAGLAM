/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministrarInterface;

import Entidades.Usuario;
import java.math.BigInteger;

/**
 * Interface del adminitrador Usuario. Posee las operaciones relacionadas con la
 * capa de negocio de la pagina Usuario
 *
 * @author John Pineda
 */
public interface AdministrarUsuarioInterface {

    /**
     * Metodo encargado de editar la información de un Usuario
     *
     * @param usuario Usuario a editar
     */
    public void editarUsuario(Usuario usuario);

    /**
     * Metodo encargado de realizar la busqueda de un usuario por su secuencia
     *
     * @param secEmpleado Secuencia del Empleado
     * @return Usuario identificado con la secuencia
     */
    public Usuario consultarUsuarioPorSecuencia(BigInteger secEmpleado);
}
