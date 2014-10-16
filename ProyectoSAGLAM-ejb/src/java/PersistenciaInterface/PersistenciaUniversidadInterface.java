/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenciaInterface;

/**
 * Interface de la PersistenciaUniversidad.
 * Contiene la informacion pertinente a los metodos que posee esta persistencia.
 * @author Andres Pineda
 */
public interface PersistenciaUniversidadInterface {

    /**
     * Metodo que valida si un usuario registrado corresponde al tipo de usuario registrado en la universidad
     * @param correo Correo electronico
     * @param numeroDocumento Numero de documento
     * @param tipoUsuario Tipo de Usuario
     * @return Respuesta si el usuario corresponde al tipo de usuario seleccionado
     */
    public String validarUsuarioPorDatosUniversidad(String correo, String numeroDocumento, String tipoUsuario);

}
