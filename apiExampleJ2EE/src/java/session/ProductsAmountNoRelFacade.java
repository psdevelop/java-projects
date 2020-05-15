/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Product;
import entity.ProductsAmountNoRel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Poltarokov SP
 */
@Stateless
public class ProductsAmountNoRelFacade extends AbstractFacade<ProductsAmountNoRel> {

    @PersistenceContext(unitName = "EKFApiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductsAmountNoRelFacade() {
        super(ProductsAmountNoRel.class);
    }
    
    public List<ProductsAmountNoRel> limitedPrList(int startPosition, int maxResult) {
        return (List<ProductsAmountNoRel>)(em.createQuery("SELECT p FROM ProductsAmountNoRel p").
                setFirstResult(startPosition).
                setMaxResults(maxResult).getResultList());
    }
    
    public List<ProductsAmountNoRel> noneZeroPrList() {
        return (List<ProductsAmountNoRel>)(em.createQuery("SELECT p FROM ProductsAmountNoRel p WHERE p.quantity>0").
                getResultList());
    }
    
    public String storCount() {
        try {
            List li = em.createQuery("SELECT COUNT(p.id) FROM ProductsAmountNoRel p").getResultList();
            for(Object lit: li)    {
                return lit.toString();
            }
            return "-1";
        } catch(Exception e)   {
            return "-1";
        }
    }
    
}
