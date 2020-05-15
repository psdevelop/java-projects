/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software
 * except in compliance with the terms of the license at:
 * http://developer.sun.com/berkeley_license.html
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author tgiunipero
 */
@Entity
@Table(name = "warehouses")
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "Category.findById", query = "SELECT c FROM Category c WHERE c.id = :id"),
    @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name")})
    //@NamedQuery(name = "Category.findAllIms2Active", query = "SELECT c FROM Category c WHERE c.ims2Active = :ims2_active")})
@XmlRootElement
public class Category implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouseId")
    private Collection<ProductsReceipt> productsReceiptCollection;

    @Basic(optional = false)
    @NotNull
    @Column(name = "ims2_active")
    private short ims2Active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouseId")
    private Collection<ProductsAmount> productsAmountCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "short_name")
    private String shortName;
    @Basic(optional = false)
    @Column(name = "location")
    private String location;
    @Basic(optional = false)
    @Column(name = "ims2_name")
    private String ims2Name;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    //private Collection<Product> productCollection;

    public Category() {
    }

    public Category(String id) {
        this.id = id;
    }

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public Short getIms2Active() {
        return ims2Active;
    }

    public void setIms2Active(Short ims2Active) {
        this.ims2Active = ims2Active;
    }
    
    public String getIms2Name() {
        return ims2Name;
    }

    public void setIms2Name(String ims2Name) {
        this.ims2Name = ims2Name;
    }

    //public Collection<Product> getProductCollection() {
    //    return productCollection;
    //}

    //public void setProductCollection(Collection<Product> productCollection) {
    //    this.productCollection = productCollection;
    //}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Category[id=" + id + "]";
    }

    @XmlTransient
    public Collection<ProductsAmount> getProductsAmountCollection() {
        return productsAmountCollection;
    }

    public void setProductsAmountCollection(Collection<ProductsAmount> productsAmountCollection) {
        this.productsAmountCollection = productsAmountCollection;
    }

    @XmlTransient
    public Collection<ProductsReceipt> getProductsReceiptCollection() {
        return productsReceiptCollection;
    }

    public void setProductsReceiptCollection(Collection<ProductsReceipt> productsReceiptCollection) {
        this.productsReceiptCollection = productsReceiptCollection;
    }
    
    public String toJsonString()   {
        return "{\"id\":\""+id+"\",\"name\":\""+StringEscapeUtils.escapeJson(name)+"\",\"location\":\""+
                StringEscapeUtils.escapeJson(location)+"\"}";//"\",\"short_name\":\""+StringEscapeUtils.escapeJson(shortName)+
                //"\",\"ims2_name\":\""+StringEscapeUtils.escapeJson(ims2Name)+"\",\"ims2_active\":\""+ims2Active+
    }

}