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
 * Interface del MateriaUsuario. Esta interface contiene la informacion
 * pertinente a las operaciones de MateriaUsuario.
 *
 * @author Andres Pineda
 */
public interface AdministrarMateriaUsuarioInterface {

    /**
     * Metodo encargado de obtener el usuario que actualmente se encuentra en el
     * sistema
     *
     * @parnam secuencia Secuencia del Usuario
     * @return Usuario conectado
     */
    public Usuario buscarUsuarioPorSecuencia(BigInteger secuencia);

    /**
     * Metodo encargado de obtener la lista de materias
     *
     * @return Lista de materias
     */
    public List<Materia> obtenerMaterias();

    /**
     * Metodo encargado de crear la asociacion entre una materia y un usuario
     *
     * @param materiaU MateriaUsuario a crear
     */
    public void registrarAsociacionMateriaUsuario(MateriaUsuario materiaU);

}
