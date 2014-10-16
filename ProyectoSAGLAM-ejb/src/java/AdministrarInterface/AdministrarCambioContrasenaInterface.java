/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministrarInterface;

import Entidades.Usuario;
import javax.ejb.Local;

/**
 *
 * @author John Pineda
 */
@Local
public interface AdministrarCambioContrasenaInterface {

    /**
     * Método encargado de modificar un Usuario de la base de datos. Este método
     * recibe la información del parámetro para hacer un 'merge' con la
     * información de la base de datos.
     *
     * @param usuario Con los cambios a realizar
     */
    public void editarUsuario(Usuario usuario);

    /**
     * *
     * Metodo encargado de validar que la contraseña antigua es la misma que la
     * que existe en la base de datos
     *
     * @param usuario Usuario con la contraseña antigua
     * @return Usuario con la informacion que se envio
     */
    public Usuario validarContrasenaAntigua(Usuario usuario);
}
