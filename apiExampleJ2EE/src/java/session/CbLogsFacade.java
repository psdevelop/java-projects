/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CbLogs;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arzugurbanova
 */
@Stateless
public class CbLogsFacade extends AbstractFacade<CbLogs> {

    @PersistenceContext(unitName = "EKFApiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CbLogsFacade() {
        super(CbLogs.class);
    }
    
    public void insertLog(String type, String caption, String text) {
        try {
            CbLogs log = new CbLogs();
            log.setId(-1);
            log.setDt(new Date());
            log.setCaption(caption);
            log.setLogText(text);
            log.setLogType(type);
            this.create(log);
        } catch(Exception lge)  {
                
        }
    }
    
}
