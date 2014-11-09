/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenciaInterface;

import Entidades.MateriaUsuario;
import java.util.List;

/**
 *
 * @author ANDRES PINEDA
 */
public interface PersistenciaMateriaUsuarioInterface {

    public void crearMateriaUsuario(MateriaUsuario materiaUsuario);

    public List<MateriaUsuario> buscarMateriasUsuarios();

}
