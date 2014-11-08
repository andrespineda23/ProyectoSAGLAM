/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ANDRES PINEDA
 */
@Entity
@Table(name = "materiausuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MateriaUsuario.findAll", query = "SELECT m FROM MateriaUsuario m"),
    @NamedQuery(name = "MateriaUsuario.findBySecuencia", query = "SELECT m FROM MateriaUsuario m WHERE m.secuencia = :secuencia")})
public class MateriaUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "secuencia")
    private BigInteger secuencia;
    @JoinColumn(name = "usuario", referencedColumnName = "secuencia")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "materia", referencedColumnName = "secuencia")
    @ManyToOne
    private Materia materia;
    @Size(max = 5)
    @Column(name = "grupo")
    private String grupo;

    public MateriaUsuario() {
    }

    public MateriaUsuario(BigInteger secuencia) {
        this.secuencia = secuencia;
    }

    public BigInteger getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(BigInteger secuencia) {
        this.secuencia = secuencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
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
        if (!(object instanceof MateriaUsuario)) {
            return false;
        }
        MateriaUsuario other = (MateriaUsuario) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.MateriaUsuario[ secuencia=" + secuencia + " ]";
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    
}
