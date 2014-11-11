/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenciaInterface;

import Entidades.MateriaUsuario;
import java.util.List;

/**
 * Interface de la unidad de persistencia MateriaUsuario. Posee los metodos de
 * dicha persistencia
 *
 * @author Andres Pineda
 */
public interface PersistenciaMateriaUsuarioInterface {

    /**
     * Metodo encargado de crear una asociacion entre el usuario y la materia.
     * Se crea un nuevo registro de MateriaUsuario
     *
     * @param materiaUsuario
     */
    public void crearMateriaUsuario(MateriaUsuario materiaUsuario);

    /**
     * Metodo encargado de buscar todas los registros de MateriaUsuario
     * registrados en el sistema.
     *
     * @return Lista de MateriaUsuario
     */
    public List<MateriaUsuario> buscarMateriasUsuarios();

}
