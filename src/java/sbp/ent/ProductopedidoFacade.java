/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbp.ent;

import folderdb.Productopedido;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sebastian Castro
 */
@Stateless
public class ProductopedidoFacade extends AbstractFacade<Productopedido> {
    @PersistenceContext(unitName = "GearsCom_ProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductopedidoFacade() {
        super(Productopedido.class);
    }
    
}
