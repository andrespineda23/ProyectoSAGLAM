/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministrarInterface;

import Entidades.AreaTrabajo;
import Entidades.Usuario;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ANDRES PINEDA
 */
public interface AdministrarAreaTrabajoInterface {

    public Usuario buscarUsuarioPorSecuencia(BigInteger secuencia);

    public void crearAreaDeTrabajo(AreaTrabajo area);

    public void editarAreaDeTrabajo(AreaTrabajo area);

    public void borrarAreaDeTrabajo(AreaTrabajo area);

    public List<AreaTrabajo> buscarAreasDeTrabajo();

}
