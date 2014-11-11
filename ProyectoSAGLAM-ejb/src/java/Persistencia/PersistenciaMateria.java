package Persistencia;

import Entidades.Materia;
import PersistenciaInterface.PersistenciaMateriaInterface;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * PersistenciaMateria - Encargada de las operaciones de los datos de la tabla
 * Materia
 *
 * @author Andres Pineda
 */
@Stateless
public class PersistenciaMateria implements PersistenciaMateriaInterface {

    /**
     * Atributo EntityManager. Representa la comunicación con la base de datos
     */
    @PersistenceContext(unitName = "ProyectoSAGLAM-ejbPU")
    private EntityManager em;

    @Override
    public List<Materia> consultarMaterias() {
        try {
            em.clear();
            Query query = em.createQuery("SELECT m FROM Materia m");
            List<Materia> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error consultarMaterias PersistenciaMateria : " + e.toString());
            return null;
        }
    }
}
