/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Poltarokov SP
 */
@Entity
@Table(name = "products_amounts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductsAmountNoRel.findAll", query = "SELECT p FROM ProductsAmountNoRel p"),
    @NamedQuery(name = "ProductsAmountNoRel.findByQuantity", query = "SELECT p FROM ProductsAmountNoRel p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "ProductsAmountNoRel.findById", query = "SELECT p FROM ProductsAmountNoRel p WHERE p.id = :id")})
public class ProductsAmountNoRel implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private BigDecimal quantity;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "product_id")
    private String productId;
    @Column(name = "warehouse_id")
    private String warehouseId;

    public ProductsAmountNoRel() {
    }

    public ProductsAmountNoRel(Integer id) {
        this.id = id;
    }

    public ProductsAmountNoRel(Integer id, BigDecimal quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
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
        if (!(object instanceof ProductsAmountNoRel)) {
            return false;
        }
        ProductsAmountNoRel other = (ProductsAmountNoRel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProductsAmountNoRel[ id=" + id + " ]";
    }
    
    public String toJsonString()   {
        DecimalFormat df = new DecimalFormat("#.000");
        return "{\"id\":\""+id+"\",\"quantity\":\""+df.format(quantity)+"\",\"prod_id\":\""+productId+"\",\"whs_id\":\""+warehouseId+"\"}";
    }
    
}
