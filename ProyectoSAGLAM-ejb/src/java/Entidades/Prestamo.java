/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANDRES PINEDA
 */
@Entity
@Table(name = "prestamo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p"),
    @NamedQuery(name = "Prestamo.findBySecuencia", query = "SELECT p FROM Prestamo p WHERE p.secuencia = :secuencia"),
    @NamedQuery(name = "Prestamo.findByFecha", query = "SELECT p FROM Prestamo p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Prestamo.findByHorainicial", query = "SELECT p FROM Prestamo p WHERE p.horainicial = :horainicial"),
    @NamedQuery(name = "Prestamo.findByHorafinal", query = "SELECT p FROM Prestamo p WHERE p.horafinal = :horafinal"),
    @NamedQuery(name = "Prestamo.findByTipoactividad", query = "SELECT p FROM Prestamo p WHERE p.tipoactividad = :tipoactividad"),
    @NamedQuery(name = "Prestamo.findByEstadosolicitud", query = "SELECT p FROM Prestamo p WHERE p.estadosolicitud = :estadosolicitud")})
public class Prestamo implements Serializable {
    @JoinColumn(name = "usuario", referencedColumnName = "secuencia")
    @ManyToOne
    private Usuario usuario;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "secuencia")
    private BigInteger secuencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horainicial")
    private int horainicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horafinal")
    private int horafinal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "tipoactividad")
    private String tipoactividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estadosolicitud")
    private String estadosolicitud;
    @JoinColumn(name = "guiatrabajo", referencedColumnName = "secuencia")
    @ManyToOne
    private GuiaTrabajo guiatrabajo;
    @JoinColumn(name = "areatrabajo", referencedColumnName = "secuencia")
    @ManyToOne
    private AreaTrabajo areatrabajo;
    @OneToMany(mappedBy = "prestamo")
    private Collection<PrestamoUsuario> prestamoUsuarioCollection;

    public Prestamo() {
    }

    public Prestamo(BigInteger secuencia) {
        this.secuencia = secuencia;
    }

    public Prestamo(BigInteger secuencia, Date fecha, int horainicial, int horafinal, String tipoactividad, String estadosolicitud) {
        this.secuencia = secuencia;
        this.fecha = fecha;
        this.horainicial = horainicial;
        this.horafinal = horafinal;
        this.tipoactividad = tipoactividad;
        this.estadosolicitud = estadosolicitud;
    }

    public BigInteger getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(BigInteger secuencia) {
        this.secuencia = secuencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getHorainicial() {
        return horainicial;
    }

    public void setHorainicial(int horainicial) {
        this.horainicial = horainicial;
    }

    public int getHorafinal() {
        return horafinal;
    }

    public void setHorafinal(int horafinal) {
        this.horafinal = horafinal;
    }

    public String getTipoactividad() {
        return tipoactividad;
    }

    public void setTipoactividad(String tipoactividad) {
        this.tipoactividad = tipoactividad;
    }

    public String getEstadosolicitud() {
        return estadosolicitud;
    }

    public void setEstadosolicitud(String estadosolicitud) {
        this.estadosolicitud = estadosolicitud;
    }

    public GuiaTrabajo getGuiatrabajo() {
        return guiatrabajo;
    }

    public void setGuiatrabajo(GuiaTrabajo guiatrabajo) {
        this.guiatrabajo = guiatrabajo;
    }

    public AreaTrabajo getAreatrabajo() {
        return areatrabajo;
    }

    public void setAreatrabajo(AreaTrabajo areatrabajo) {
        this.areatrabajo = areatrabajo;
    }

    @XmlTransient
    public Collection<PrestamoUsuario> getPrestamoUsuarioCollection() {
        return prestamoUsuarioCollection;
    }

    public void setPrestamoUsuarioCollection(Collection<PrestamoUsuario> prestamoUsuarioCollection) {
        this.prestamoUsuarioCollection = prestamoUsuarioCollection;
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
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Prestamo[ secuencia=" + secuencia + " ]";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
