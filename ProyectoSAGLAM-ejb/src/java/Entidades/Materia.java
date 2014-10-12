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
@Table(name = "materia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m"),
    @NamedQuery(name = "Materia.findBySecuencia", query = "SELECT m FROM Materia m WHERE m.secuencia = :secuencia"),
    @NamedQuery(name = "Materia.findByNombre", query = "SELECT m FROM Materia m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Materia.findByCodigo", query = "SELECT m FROM Materia m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "Materia.findByPlanestudio", query = "SELECT m FROM Materia m WHERE m.planestudio = :planestudio")})
public class Materia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "secuencia")
    private BigInteger secuencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "planestudio")
    private String planestudio;
    @OneToMany(mappedBy = "materia")
    private Collection<MateriaUsuario> materiaUsuarioCollection;

    public Materia() {
    }

    public Materia(BigInteger secuencia) {
        this.secuencia = secuencia;
    }

    public Materia(BigInteger secuencia, String nombre, String codigo, String planestudio) {
        this.secuencia = secuencia;
        this.nombre = nombre;
        this.codigo = codigo;
        this.planestudio = planestudio;
    }

    public BigInteger getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(BigInteger secuencia) {
        this.secuencia = secuencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPlanestudio() {
        return planestudio;
    }

    public void setPlanestudio(String planestudio) {
        this.planestudio = planestudio;
    }

    @XmlTransient
    public Collection<MateriaUsuario> getMateriaUsuarioCollection() {
        return materiaUsuarioCollection;
    }

    public void setMateriaUsuarioCollection(Collection<MateriaUsuario> materiaUsuarioCollection) {
        this.materiaUsuarioCollection = materiaUsuarioCollection;
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
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Materia[ secuencia=" + secuencia + " ]";
    }
    
}
