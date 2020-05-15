/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author Poltarokov SP
 */
@Entity
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name = "Product.findByVendorCode", query = "SELECT p FROM Product p WHERE p.vendorCode = :vendorCode"),
    @NamedQuery(name = "Product.findByShortName", query = "SELECT p FROM Product p WHERE p.shortName = :shortName"),
    @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name")})
public class Product implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<ProductsReceipt> productsReceiptCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<ProductsAmount> productsAmountCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "id")
    private String id;
    @Size(max = 200)
    @Column(name = "vendor_code")
    private String vendorCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "short_name")
    private String shortName;
    @Size(max = 512)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "barcode")
    private String barcode;

    public Product() {
    }

    public Product(String id) {
        this.id = id;
    }

    public Product(String id, String shortName) {
        this.id = id;
        this.shortName = shortName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Product[ id=" + id + " ]";
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
        return "{\"id\":\""+id+"\",\"name\":\""+StringEscapeUtils.escapeJson(name)+"\",\"vendor_code\":\""+
                StringEscapeUtils.escapeJson(vendorCode)+"\",\"short_name\":\""+StringEscapeUtils.escapeJson(shortName)+"\"}";
    }
    
}
