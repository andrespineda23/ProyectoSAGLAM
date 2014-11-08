/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministrarInterface;

import Entidades.Materia;
import Entidades.MateriaUsuario;
import Entidades.Usuario;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ANDRES PINEDA
 */
public interface AdministrarMateriaUsuarioInterface {

    public Usuario buscarUsuarioPorSecuencia(BigInteger secuencia);

    public List<Materia> obtenerMaterias();

    public void registrarAsociacionMateriaUsuario(MateriaUsuario materiaU);

}
