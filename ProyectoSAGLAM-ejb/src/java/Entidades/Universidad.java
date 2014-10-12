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
@Table(name = "universidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Universidad.findAll", query = "SELECT u FROM Universidad u"),
    @NamedQuery(name = "Universidad.findBySecuencia", query = "SELECT u FROM Universidad u WHERE u.secuencia = :secuencia"),
    @NamedQuery(name = "Universidad.findByCorreoelectronico", query = "SELECT u FROM Universidad u WHERE u.correoelectronico = :correoelectronico"),
    @NamedQuery(name = "Universidad.findByNumerodocumento", query = "SELECT u FROM Universidad u WHERE u.numerodocumento = :numerodocumento"),
    @NamedQuery(name = "Universidad.findByTipousuario", query = "SELECT u FROM Universidad u WHERE u.tipousuario = :tipousuario")})
public class Universidad implements Serializable {
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
    @Column(name = "numerodocumento")
    private String numerodocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "tipousuario")
    private String tipousuario;

    public Universidad() {
    }

    public Universidad(BigInteger secuencia) {
        this.secuencia = secuencia;
    }

    public Universidad(BigInteger secuencia, String correoelectronico, String numerodocumento, String tipousuario) {
        this.secuencia = secuencia;
        this.correoelectronico = correoelectronico;
        this.numerodocumento = numerodocumento;
        this.tipousuario = tipousuario;
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

    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public String getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(String tipousuario) {
        this.tipousuario = tipousuario;
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
        if (!(object instanceof Universidad)) {
            return false;
        }
        Universidad other = (Universidad) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Universidad[ secuencia=" + secuencia + " ]";
    }
    
}
