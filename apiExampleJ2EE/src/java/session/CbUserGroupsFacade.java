/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CbUserGroups;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arzugurbanova
 */
@Stateless
public class CbUserGroupsFacade extends AbstractFacade<CbUserGroups> {

    @PersistenceContext(unitName = "EKFApiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CbUserGroupsFacade() {
        super(CbUserGroups.class);
    }
    
}
