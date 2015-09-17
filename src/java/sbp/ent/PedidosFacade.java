/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbp.ent;

import folderdb.Pedidos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sebastian Castro
 */
@Stateless
public class PedidosFacade extends AbstractFacade<Pedidos> {
    @PersistenceContext(unitName = "GearsCom_ProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidosFacade() {
        super(Pedidos.class);
    }
    
}
