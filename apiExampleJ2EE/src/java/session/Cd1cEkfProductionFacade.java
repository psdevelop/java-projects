/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Cd1cEkfProduction;
import entity.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arzugurbanova
 */
@Stateless
public class Cd1cEkfProductionFacade extends AbstractFacade<Cd1cEkfProduction> {

    @PersistenceContext(unitName = "EKFApiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Cd1cEkfProductionFacade() {
        super(Cd1cEkfProduction.class);
    }
    
    public List<Cd1cEkfProduction> limitedPrList(int startPosition, int maxResult) {
        return (List<Cd1cEkfProduction>)(em.createQuery("SELECT p FROM Cd1cEkfProduction p").
                setFirstResult(startPosition).
                setMaxResults(maxResult).getResultList());
    }
    
    public String ekfProductionCount() {
        try {
            List li = em.createQuery("SELECT COUNT(p.id) FROM Cd1cEkfProduction p").getResultList();
            for(Object lit: li)    {
                return lit.toString();
            }
            return "-1";
        } catch(Exception e)   {
            return "-1";
        }
    }
    
}
