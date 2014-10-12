/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ANDRES PINEDA
 */
@Entity
@Table(name = "resultadosactividades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultadosActividades.findAll", query = "SELECT r FROM ResultadosActividades r"),
    @NamedQuery(name = "ResultadosActividades.findBySecuencia", query = "SELECT r FROM ResultadosActividades r WHERE r.secuencia = :secuencia"),
    @NamedQuery(name = "ResultadosActividades.findByFecha", query = "SELECT r FROM ResultadosActividades r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "ResultadosActividades.findByHora", query = "SELECT r FROM ResultadosActividades r WHERE r.hora = :hora"),
    @NamedQuery(name = "ResultadosActividades.findByTrabajorealizado", query = "SELECT r FROM ResultadosActividades r WHERE r.trabajorealizado = :trabajorealizado"),
    @NamedQuery(name = "ResultadosActividades.findByAreatrabajo", query = "SELECT r FROM ResultadosActividades r WHERE r.areatrabajo = :areatrabajo")})
public class ResultadosActividades implements Serializable {
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
    @Column(name = "hora")
    private int hora;
    @Size(max = 30)
    @Column(name = "trabajorealizado")
    private String trabajorealizado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "areatrabajo")
    private String areatrabajo;
    @JoinColumn(name = "usuario", referencedColumnName = "secuencia")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "archivoresultado", referencedColumnName = "secuencia")
    @ManyToOne
    private ArchivosResultados archivoresultado;

    public ResultadosActividades() {
    }

    public ResultadosActividades(BigInteger secuencia) {
        this.secuencia = secuencia;
    }

    public ResultadosActividades(BigInteger secuencia, Date fecha, int hora, String areatrabajo) {
        this.secuencia = secuencia;
        this.fecha = fecha;
        this.hora = hora;
        this.areatrabajo = areatrabajo;
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

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public String getTrabajorealizado() {
        return trabajorealizado;
    }

    public void setTrabajorealizado(String trabajorealizado) {
        this.trabajorealizado = trabajorealizado;
    }

    public String getAreatrabajo() {
        return areatrabajo;
    }

    public void setAreatrabajo(String areatrabajo) {
        this.areatrabajo = areatrabajo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArchivosResultados getArchivoresultado() {
        return archivoresultado;
    }

    public void setArchivoresultado(ArchivosResultados archivoresultado) {
        this.archivoresultado = archivoresultado;
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
        if (!(object instanceof ResultadosActividades)) {
            return false;
        }
        ResultadosActividades other = (ResultadosActividades) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ResultadosActividades[ secuencia=" + secuencia + " ]";
    }
    
}
