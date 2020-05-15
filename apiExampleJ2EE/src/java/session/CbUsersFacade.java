/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CbUsers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arzugurbanova
 */
@Stateless
public class CbUsersFacade extends AbstractFacade<CbUsers> {

    @PersistenceContext(unitName = "EKFApiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CbUsersFacade() {
        super(CbUsers.class);
    }
    
    public List<CbUsers> findByEkfapikey(String ukey)   {
        return (List<CbUsers>)em.createQuery("SELECT c FROM CbUsers c WHERE c.ekfapikey = :ekfapikey").
                setParameter("ekfapikey", ukey).getResultList();
    }
    
}
