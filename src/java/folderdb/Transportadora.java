/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folderdb;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author Sebastian Castro
 */
@Entity
@Table(name = "transportadora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transportadora.findAll", query = "SELECT t FROM Transportadora t"),
    @NamedQuery(name = "Transportadora.findByIdTransportadora", query = "SELECT t FROM Transportadora t WHERE t.idTransportadora = :idTransportadora"),
    @NamedQuery(name = "Transportadora.findByNombreTransportadora", query = "SELECT t FROM Transportadora t WHERE t.nombreTransportadora = :nombreTransportadora"),
    @NamedQuery(name = "Transportadora.findByTelefonoTransportadora", query = "SELECT t FROM Transportadora t WHERE t.telefonoTransportadora = :telefonoTransportadora")})
public class Transportadora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTransportadora")
    private Integer idTransportadora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombreTransportadora")
    private String nombreTransportadora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "telefonoTransportadora")
    private int telefonoTransportadora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransportadora")
    private Collection<Pedidos> pedidosCollection;

    public Transportadora() {
    }

    public Transportadora(Integer idTransportadora) {
        this.idTransportadora = idTransportadora;
    }

    public Transportadora(Integer idTransportadora, String nombreTransportadora, int telefonoTransportadora) {
        this.idTransportadora = idTransportadora;
        this.nombreTransportadora = nombreTransportadora;
        this.telefonoTransportadora = telefonoTransportadora;
    }

    public Integer getIdTransportadora() {
        return idTransportadora;
    }

    public void setIdTransportadora(Integer idTransportadora) {
        this.idTransportadora = idTransportadora;
    }

    public String getNombreTransportadora() {
        return nombreTransportadora;
    }

    public void setNombreTransportadora(String nombreTransportadora) {
        this.nombreTransportadora = nombreTransportadora;
    }

    public int getTelefonoTransportadora() {
        return telefonoTransportadora;
    }

    public void setTelefonoTransportadora(int telefonoTransportadora) {
        this.telefonoTransportadora = telefonoTransportadora;
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
        hash += (idTransportadora != null ? idTransportadora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transportadora)) {
            return false;
        }
        Transportadora other = (Transportadora) object;
        if ((this.idTransportadora == null && other.idTransportadora != null) || (this.idTransportadora != null && !this.idTransportadora.equals(other.idTransportadora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreTransportadora;
    }
    
}
