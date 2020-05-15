/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.ProductsReceipt;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Poltarokov SP
 */
@Stateless
public class ProductsReceiptFacade extends AbstractFacade<ProductsReceipt> {

    @PersistenceContext(unitName = "EKFApiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductsReceiptFacade() {
        super(ProductsReceipt.class);
    }
    
}
