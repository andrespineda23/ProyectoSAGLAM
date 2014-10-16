package Persistencia;

import Entidades.Universidad;
import PersistenciaInterface.PersistenciaUniversidadInterface;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Persistencia Universidad, la cual contiene la informacion de los procesos realizados por dicha persistencia
 * @author Andres Pineda
 */
@Stateless
public class PersistenciaUniversidad implements PersistenciaUniversidadInterface {

    /**
     * Atributo EntityManager. Representa la comunicación con la base de datos
     */
    @PersistenceContext(unitName = "ProyectoSAGLAM-ejbPU")
    private EntityManager em;

    @Override
    public String validarUsuarioPorDatosUniversidad(String correo, String numeroDocumento, String tipoUsuario) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT u FROM Universidad u WHERE u.correoelectronico = :correo AND u.numerodocumento =:numeroDocumento AND u.tipousuario=:tipoUsuario");
            query.setParameter("correo", correo);
            query.setParameter("numeroDocumento", numeroDocumento);
            query.setParameter("tipoUsuario", tipoUsuario);
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            Universidad registro = (Universidad) query.getSingleResult();
            if (registro != null) {
                return "S";
            } else {
                return "N";
            }
        } catch (Exception e) {
            System.out.println("Error validarUsuarioPorDatosUniversidad PersistenciaUniversidad : " + e.toString());
            return null;
        }
    }

}
