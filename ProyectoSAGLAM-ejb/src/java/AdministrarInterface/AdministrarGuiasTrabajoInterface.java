/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdministrarInterface;

import Entidades.GuiaTrabajo;
import Entidades.Usuario;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface para profesor o laboratorista. contiene las opereaciones para las
 * guias de trabajo
 *
 * @author John Pineda
 */
@Local
public interface AdministrarGuiasTrabajoInterface {

    /**
     * Metodo encargado de crar una nueva guia de trabajo
     *
     * @param guiaTrabajo guia de trabajo que se va crear
     */
    public void crearGuiaTrabajo(GuiaTrabajo guiaTrabajo);

    /**
     * Metodo encargado de eliminar una GuiaTrabajo seleccionada
     *
     * @param guiaTrabajo guia de trabajo a eliminar
     */
    public void eliminarGuiaTrabajo(GuiaTrabajo guiaTrabajo);

    /**
     * Metodo encargado de traer todas las guias que se encuentrar en la base de
     * datos
     *
     * @return lista de guias de trabajo
     */
    public List<GuiaTrabajo> consultarGuiasTrabajo();

    /**
     * Metodo encargado de obtener el usuario conectado en el sistema por medio
     * de su secuencia
     *
     * @param secUsuario Secuencia del usuario
     * @return Usuario conectado
     */
    public Usuario consultarUsuarioPorSecuencia(BigInteger secUsuario);
}
