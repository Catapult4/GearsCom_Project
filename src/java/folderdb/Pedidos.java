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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sebastian Castro
 */
@Entity
@Table(name = "pedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidos.findAll", query = "SELECT p FROM Pedidos p"),
    @NamedQuery(name = "Pedidos.findByIdPedido", query = "SELECT p FROM Pedidos p WHERE p.idPedido = :idPedido"),
    @NamedQuery(name = "Pedidos.findByFechaPedido", query = "SELECT p FROM Pedidos p WHERE p.fechaPedido = :fechaPedido"),
    @NamedQuery(name = "Pedidos.findByFechaEnvio", query = "SELECT p FROM Pedidos p WHERE p.fechaEnvio = :fechaEnvio")})
public class Pedidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPedido")
    private Integer idPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaPedido")
    @Temporal(TemporalType.DATE)
    private Date fechaPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaEnvio")
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;
    @JoinColumn(name = "idTransportadora", referencedColumnName = "idTransportadora")
    @ManyToOne(optional = false)
    private Transportadora idTransportadora;
    @JoinColumn(name = "idOrden", referencedColumnName = "idOrden")
    @ManyToOne
    private Ordenproduccion idOrden;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPedido")
    private Collection<Productopedido> productopedidoCollection;

    public Pedidos() {
    }

    public Pedidos(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Pedidos(Integer idPedido, Date fechaPedido, Date fechaEnvio) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.fechaEnvio = fechaEnvio;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Transportadora getIdTransportadora() {
        return idTransportadora;
    }

    public void setIdTransportadora(Transportadora idTransportadora) {
        this.idTransportadora = idTransportadora;
    }

    public Ordenproduccion getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Ordenproduccion idOrden) {
        this.idOrden = idOrden;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @XmlTransient
    public Collection<Productopedido> getProductopedidoCollection() {
        return productopedidoCollection;
    }

    public void setProductopedidoCollection(Collection<Productopedido> productopedidoCollection) {
        this.productopedidoCollection = productopedidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidos)) {
            return false;
        }
        Pedidos other = (Pedidos) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "folderdb.Pedidos[ idPedido=" + idPedido + " ]";
    }
    
}
