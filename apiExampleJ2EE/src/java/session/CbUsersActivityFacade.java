/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CbUsers;
import entity.CbUsersActivity;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arzugurbanova
 */
@Stateless
public class CbUsersActivityFacade extends AbstractFacade<CbUsersActivity> {

    @PersistenceContext(unitName = "EKFApiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CbUsersActivityFacade() {
        super(CbUsersActivity.class);
    }
    
    public void createActivity(CbUsers userId, String objectName)   {
        try {
            CbUsersActivity uAct = new CbUsersActivity();
            uAct.setId(-1);
            uAct.setUserId(userId);
            uAct.setObjectName(objectName);
            uAct.setDate(new Date());
            this.create(uAct);
            //this.em.flush();
        }   catch(Exception e)  {
            System.out.println("<<==!!||||||!!==>>Error CbUsersActivity.create "+e);
        }
    }
    
    public void fixClear()  {
        try {
            
            em.flush();
            em.clear();
            
        } catch(Exception e)    { 
            System.out.println("<<==!!||||||!!==>>Error CbUsersActivity.em.clear() "+e);
        }
    }
    
    public List<CbUsersActivity> getCbUsersActivityCollection(CbUsers uid)   {
        em.clear();
        return (List<CbUsersActivity>)em.createQuery("SELECT c FROM CbUsersActivity c WHERE c.userId=:uid").setParameter("uid", uid).getResultList();
    }
        
    
}
