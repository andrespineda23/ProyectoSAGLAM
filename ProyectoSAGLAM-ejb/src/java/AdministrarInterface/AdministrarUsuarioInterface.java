/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministrarInterface;

import Entidades.Usuario;
import java.math.BigInteger;
import javax.ejb.Local;

/**
 *
 * @author John Pineda
 */
@Local
public interface AdministrarUsuarioInterface {

    public void editarUsuario(Usuario usuario);

    public Usuario consultarUsuarioPorSecuencia(BigInteger secEmpleado);
}
