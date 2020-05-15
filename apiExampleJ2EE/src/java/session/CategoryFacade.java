/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software
 * except in compliance with the terms of the license at:
 * http://developer.sun.com/berkeley_license.html
 */

package session;

import entity.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tgiunipero
 */
@Stateless
public class CategoryFacade extends AbstractFacade<Category> {
    @PersistenceContext(unitName = "EKFApiPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryFacade() {
        super(Category.class);
    }
    
    public List<Category> findAllIms2Active(Short ims2_active) {
    return em.createQuery(
        "SELECT c FROM Category c WHERE c.ims2Active = :ims2_active")
        .setParameter("ims2_active", ims2_active)
        .getResultList();
    }

}