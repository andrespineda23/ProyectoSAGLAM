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
@Table(name = "guiatrabajo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GuiaTrabajo.findAll", query = "SELECT g FROM GuiaTrabajo g"),
    @NamedQuery(name = "GuiaTrabajo.findBySecuencia", query = "SELECT g FROM GuiaTrabajo g WHERE g.secuencia = :secuencia"),
    @NamedQuery(name = "GuiaTrabajo.findByCodigo", query = "SELECT g FROM GuiaTrabajo g WHERE g.codigo = :codigo"),
    @NamedQuery(name = "GuiaTrabajo.findByNombre", query = "SELECT g FROM GuiaTrabajo g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GuiaTrabajo.findByRutaarchivo", query = "SELECT g FROM GuiaTrabajo g WHERE g.rutaarchivo = :rutaarchivo")})
public class GuiaTrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "secuencia")
    private BigInteger secuencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "rutaarchivo")
    private String rutaarchivo;
    @OneToMany(mappedBy = "guiatrabajo")
    private Collection<Prestamo> prestamoCollection;

    public GuiaTrabajo() {
    }

    public GuiaTrabajo(BigInteger secuencia) {
        this.secuencia = secuencia;
    }

    public GuiaTrabajo(BigInteger secuencia, String codigo, String nombre, String rutaarchivo) {
        this.secuencia = secuencia;
        this.codigo = codigo;
        this.nombre = nombre;
        this.rutaarchivo = rutaarchivo;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRutaarchivo() {
        return rutaarchivo;
    }

    public void setRutaarchivo(String rutaarchivo) {
        this.rutaarchivo = rutaarchivo;
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
        if (!(object instanceof GuiaTrabajo)) {
            return false;
        }
        GuiaTrabajo other = (GuiaTrabajo) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.GuiaTrabajo[ secuencia=" + secuencia + " ]";
    }
    
}
