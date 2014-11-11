package PersistenciaInterface;

import Entidades.Materia;
import java.util.List;

/**
 * Interface de la unidad de persistencia Materia. Posee los metodos de dicha
 * persistencia
 *
 * @author Andres Pineda
 */
public interface PersistenciaMateriaInterface {

    /**
     * Metodo encargado de consultar las Materias registradas en el sistema
     *
     * @return Lista de Materias registradas
     */
    public List<Materia> consultarMaterias();

}
