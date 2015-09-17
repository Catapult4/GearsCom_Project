/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folderdb;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sebastian Castro
 */
@Entity
@Table(name = "ordenproduccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordenproduccion.findAll", query = "SELECT o FROM Ordenproduccion o"),
    @NamedQuery(name = "Ordenproduccion.findByIdOrden", query = "SELECT o FROM Ordenproduccion o WHERE o.idOrden = :idOrden"),
    @NamedQuery(name = "Ordenproduccion.findByFechaOrden", query = "SELECT o FROM Ordenproduccion o WHERE o.fechaOrden = :fechaOrden"),
    @NamedQuery(name = "Ordenproduccion.findByFechaInicio", query = "SELECT o FROM Ordenproduccion o WHERE o.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Ordenproduccion.findByFechaEntrega", query = "SELECT o FROM Ordenproduccion o WHERE o.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "Ordenproduccion.findByTotalMateria", query = "SELECT o FROM Ordenproduccion o WHERE o.totalMateria = :totalMateria")})
public class Ordenproduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrden")
    private Integer idOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaOrden")
    @Temporal(TemporalType.DATE)
    private Date fechaOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaEntrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TotalMateria")
    private int totalMateria;
    @OneToMany(mappedBy = "idOrden")
    private Collection<Pedidos> pedidosCollection;

    public Ordenproduccion() {
    }

    public Ordenproduccion(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public Ordenproduccion(Integer idOrden, Date fechaOrden, Date fechaInicio, Date fechaEntrega, int totalMateria) {
        this.idOrden = idOrden;
        this.fechaOrden = fechaOrden;
        this.fechaInicio = fechaInicio;
        this.fechaEntrega = fechaEntrega;
        this.totalMateria = totalMateria;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getTotalMateria() {
        return totalMateria;
    }

    public void setTotalMateria(int totalMateria) {
        this.totalMateria = totalMateria;
    }

    @XmlTransient
    public Collection<Pedidos> getPedidosCollection() {
        return pedidosCollection;
    }

    public void setPedidosCollection(Collection<Pedidos> pedidosCollection) {
        this.pedidosCollection = pedidosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrden != null ? idOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordenproduccion)) {
            return false;
        }
        Ordenproduccion other = (Ordenproduccion) object;
        if ((this.idOrden == null && other.idOrden != null) || (this.idOrden != null && !this.idOrden.equals(other.idOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "folderdb.Ordenproduccion[ idOrden=" + idOrden + " ]";
    }
    
}
