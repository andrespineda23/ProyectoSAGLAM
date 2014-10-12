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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ANDRES PINEDA
 */
@Entity
@Table(name = "prestamousuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestamoUsuario.findAll", query = "SELECT p FROM PrestamoUsuario p"),
    @NamedQuery(name = "PrestamoUsuario.findBySecuencia", query = "SELECT p FROM PrestamoUsuario p WHERE p.secuencia = :secuencia")})
public class PrestamoUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "secuencia")
    private BigInteger secuencia;
    @JoinColumn(name = "usuario", referencedColumnName = "secuencia")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "prestamo", referencedColumnName = "secuencia")
    @ManyToOne
    private Prestamo prestamo;

    public PrestamoUsuario() {
    }

    public PrestamoUsuario(BigInteger secuencia) {
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

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
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
        if (!(object instanceof PrestamoUsuario)) {
            return false;
        }
        PrestamoUsuario other = (PrestamoUsuario) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PrestamoUsuario[ secuencia=" + secuencia + " ]";
    }
    
}
