/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANDRES PINEDA
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findBySecuencia", query = "SELECT u FROM Usuario u WHERE u.secuencia = :secuencia"),
    @NamedQuery(name = "Usuario.findByCorreoelectronico", query = "SELECT u FROM Usuario u WHERE u.correoelectronico = :correoelectronico"),
    @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena"),
    @NamedQuery(name = "Usuario.findByTipousuario", query = "SELECT u FROM Usuario u WHERE u.tipousuario = :tipousuario"),
    @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Usuario.findByActivo", query = "SELECT u FROM Usuario u WHERE u.activo = :activo"),
    @NamedQuery(name = "Usuario.findByNumerodocumento", query = "SELECT u FROM Usuario u WHERE u.numerodocumento = :numerodocumento")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "secuencia")
    private BigInteger secuencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "correoelectronico")
    private String correoelectronico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "contrasena")
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "tipousuario")
    private String tipousuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numerodocumento")
    private String numerodocumento;
    @OneToMany(mappedBy = "usuario")
    private Collection<PrestamoUsuario> prestamoUsuarioCollection;
    @OneToMany(mappedBy = "usuario")
    private Collection<MateriaUsuario> materiaUsuarioCollection;
    @OneToMany(mappedBy = "usuario")
    private Collection<ResultadosActividades> resultadosActividadesCollection;

    public Usuario() {
    }

    public Usuario(BigInteger secuencia) {
        this.secuencia = secuencia;
    }

    public Usuario(BigInteger secuencia, String correoelectronico, String contrasena, String tipousuario, String nombres, String apellidos, boolean activo, String numerodocumento) {
        this.secuencia = secuencia;
        this.correoelectronico = correoelectronico;
        this.contrasena = contrasena;
        this.tipousuario = tipousuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.activo = activo;
        this.numerodocumento = numerodocumento;
    }

    public BigInteger getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(BigInteger secuencia) {
        this.secuencia = secuencia;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(String tipousuario) {
        this.tipousuario = tipousuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    @XmlTransient
    public Collection<PrestamoUsuario> getPrestamoUsuarioCollection() {
        return prestamoUsuarioCollection;
    }

    public void setPrestamoUsuarioCollection(Collection<PrestamoUsuario> prestamoUsuarioCollection) {
        this.prestamoUsuarioCollection = prestamoUsuarioCollection;
    }

    @XmlTransient
    public Collection<MateriaUsuario> getMateriaUsuarioCollection() {
        return materiaUsuarioCollection;
    }

    public void setMateriaUsuarioCollection(Collection<MateriaUsuario> materiaUsuarioCollection) {
        this.materiaUsuarioCollection = materiaUsuarioCollection;
    }

    @XmlTransient
    public Collection<ResultadosActividades> getResultadosActividadesCollection() {
        return resultadosActividadesCollection;
    }

    public void setResultadosActividadesCollection(Collection<ResultadosActividades> resultadosActividadesCollection) {
        this.resultadosActividadesCollection = resultadosActividadesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secuencia != null ? secuencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Usuario[ secuencia=" + secuencia + " ]";
    }

}
