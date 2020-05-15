/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Poltarokov SP
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "EKFApiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
    
    public List<Product> limitedPrList(int startPosition, int maxResult) {
        return (List<Product>)(em.createQuery("SELECT p FROM Product p").
                setFirstResult(startPosition).
                setMaxResults(maxResult).getResultList());
    }
    
}
