/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folderdb;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author Sebastian Castro
 */
@Entity
@Table(name = "productopedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productopedido.findAll", query = "SELECT p FROM Productopedido p"),
    @NamedQuery(name = "Productopedido.findByIdTabla", query = "SELECT p FROM Productopedido p WHERE p.idTabla = :idTabla"),
    @NamedQuery(name = "Productopedido.findByCantidad", query = "SELECT p FROM Productopedido p WHERE p.cantidad = :cantidad")})
public class Productopedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTabla")
    private Integer idTabla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad")
    private int cantidad;
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
    @ManyToOne(optional = false)
    private Productos idProducto;
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido")
    @ManyToOne(optional = false)
    private Pedidos idPedido;

    public Productopedido() {
    }

    public Productopedido(Integer idTabla) {
        this.idTabla = idTabla;
    }

    public Productopedido(Integer idTabla, int cantidad) {
        this.idTabla = idTabla;
        this.cantidad = cantidad;
    }

    public Integer getIdTabla() {
        return idTabla;
    }

    public void setIdTabla(Integer idTabla) {
        this.idTabla = idTabla;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    public Pedidos getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedidos idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTabla != null ? idTabla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productopedido)) {
            return false;
        }
        Productopedido other = (Productopedido) object;
        if ((this.idTabla == null && other.idTabla != null) || (this.idTabla != null && !this.idTabla.equals(other.idTabla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "folderdb.Productopedido[ idTabla=" + idTabla + " ]";
    }
    
}
