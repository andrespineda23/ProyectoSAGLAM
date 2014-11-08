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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANDRES PINEDA
 */
@Entity
@Table(name = "areatrabajo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AreaTrabajo.findAll", query = "SELECT a FROM AreaTrabajo a"),
    @NamedQuery(name = "AreaTrabajo.findBySecuencia", query = "SELECT a FROM AreaTrabajo a WHERE a.secuencia = :secuencia"),
    @NamedQuery(name = "AreaTrabajo.findByCodigo", query = "SELECT a FROM AreaTrabajo a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "AreaTrabajo.findByCantmaxpersonas", query = "SELECT a FROM AreaTrabajo a WHERE a.cantmaxpersonas = :cantmaxpersonas"),
    @NamedQuery(name = "AreaTrabajo.findByEstadouso", query = "SELECT a FROM AreaTrabajo a WHERE a.estadouso = :estadouso"),
    @NamedQuery(name = "AreaTrabajo.findByNombrearea", query = "SELECT a FROM AreaTrabajo a WHERE a.nombrearea = :nombrearea")})
public class AreaTrabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "secuencia")
    private BigInteger secuencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantmaxpersonas")
    private int cantmaxpersonas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estadouso")
    private boolean estadouso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombrearea")
    private String nombrearea;
    @OneToMany(mappedBy = "areatrabajo")
    private Collection<Prestamo> prestamoCollection;
    @Transient
    private String strEstado;

    public AreaTrabajo() {
    }

    public AreaTrabajo(BigInteger secuencia) {
        this.secuencia = secuencia;
    }

    public AreaTrabajo(BigInteger secuencia, String codigo, int cantmaxpersonas, boolean estadouso, String nombrearea) {
        this.secuencia = secuencia;
        this.codigo = codigo;
        this.cantmaxpersonas = cantmaxpersonas;
        this.estadouso = estadouso;
        this.nombrearea = nombrearea;
    }

    public BigInteger getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(BigInteger secuencia) {
        this.secuencia = secuencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantmaxpersonas() {
        return cantmaxpersonas;
    }

    public void setCantmaxpersonas(int cantmaxpersonas) {
        this.cantmaxpersonas = cantmaxpersonas;
    }

    public boolean getEstadouso() {
        return estadouso;
    }

    public void setEstadouso(boolean estadouso) {
        this.estadouso = estadouso;
    }

    public String getStrEstado() {
        getEstadouso();
        if (estadouso == false) {
            strEstado = "INACTIVA";
        } else {
            strEstado = "ACTIVA";
        }
        return strEstado;
    }

    public void setStrEstado(String strEstado) {
        this.strEstado = strEstado;
    }

    public String getNombrearea() {
        return nombrearea;
    }

    public void setNombrearea(String nombrearea) {
        this.nombrearea = nombrearea;
    }

    @XmlTransient
    public Collection<Prestamo> getPrestamoCollection() {
        return prestamoCollection;
    }

    public void setPrestamoCollection(Collection<Prestamo> prestamoCollection) {
        this.prestamoCollection = prestamoCollection;
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
        if (!(object instanceof AreaTrabajo)) {
            return false;
        }
        AreaTrabajo other = (AreaTrabajo) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.AreaTrabajo[ secuencia=" + secuencia + " ]";
    }

}
