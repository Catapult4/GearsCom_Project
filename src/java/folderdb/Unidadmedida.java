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
@Table(name = "unidadmedida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unidadmedida.findAll", query = "SELECT u FROM Unidadmedida u"),
    @NamedQuery(name = "Unidadmedida.findByIdMedida", query = "SELECT u FROM Unidadmedida u WHERE u.idMedida = :idMedida"),
    @NamedQuery(name = "Unidadmedida.findByTipoMedida", query = "SELECT u FROM Unidadmedida u WHERE u.tipoMedida = :tipoMedida")})
public class Unidadmedida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMedida")
    private Integer idMedida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tipoMedida")
    private String tipoMedida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMedida")
    private Collection<Materiaprima> materiaprimaCollection;

    public Unidadmedida() {
    }

    public Unidadmedida(Integer idMedida) {
        this.idMedida = idMedida;
    }

    public Unidadmedida(Integer idMedida, String tipoMedida) {
        this.idMedida = idMedida;
        this.tipoMedida = tipoMedida;
    }

    public Integer getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(Integer idMedida) {
        this.idMedida = idMedida;
    }

    public String getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(String tipoMedida) {
        this.tipoMedida = tipoMedida;
    }

    @XmlTransient
    public Collection<Materiaprima> getMateriaprimaCollection() {
        return materiaprimaCollection;
    }

    public void setMateriaprimaCollection(Collection<Materiaprima> materiaprimaCollection) {
        this.materiaprimaCollection = materiaprimaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedida != null ? idMedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidadmedida)) {
            return false;
        }
        Unidadmedida other = (Unidadmedida) object;
        if ((this.idMedida == null && other.idMedida != null) || (this.idMedida != null && !this.idMedida.equals(other.idMedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tipoMedida;
    }
    
}
