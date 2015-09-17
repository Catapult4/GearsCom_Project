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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "materiaprima")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materiaprima.findAll", query = "SELECT m FROM Materiaprima m"),
    @NamedQuery(name = "Materiaprima.findByIdMateriaPrima", query = "SELECT m FROM Materiaprima m WHERE m.idMateriaPrima = :idMateriaPrima"),
    @NamedQuery(name = "Materiaprima.findByNombre", query = "SELECT m FROM Materiaprima m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Materiaprima.findByCantidadMedida", query = "SELECT m FROM Materiaprima m WHERE m.cantidadMedida = :cantidadMedida")})
public class Materiaprima implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMateriaPrima")
    private Integer idMateriaPrima;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CantidadMedida")
    private Float cantidadMedida;
    @JoinColumn(name = "IdMedida", referencedColumnName = "idMedida")
    @ManyToOne(optional = false)
    private Unidadmedida idMedida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMateria")
    private Collection<Productos> productosCollection;

    public Materiaprima() {
    }

    public Materiaprima(Integer idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    public Materiaprima(Integer idMateriaPrima, String nombre) {
        this.idMateriaPrima = idMateriaPrima;
        this.nombre = nombre;
    }

    public Integer getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public void setIdMateriaPrima(Integer idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getCantidadMedida() {
        return cantidadMedida;
    }

    public void setCantidadMedida(Float cantidadMedida) {
        this.cantidadMedida = cantidadMedida;
    }

    public Unidadmedida getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(Unidadmedida idMedida) {
        this.idMedida = idMedida;
    }

    @XmlTransient
    public Collection<Productos> getProductosCollection() {
        return productosCollection;
    }

    public void setProductosCollection(Collection<Productos> productosCollection) {
        this.productosCollection = productosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMateriaPrima != null ? idMateriaPrima.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materiaprima)) {
            return false;
        }
        Materiaprima other = (Materiaprima) object;
        if ((this.idMateriaPrima == null && other.idMateriaPrima != null) || (this.idMateriaPrima != null && !this.idMateriaPrima.equals(other.idMateriaPrima))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
