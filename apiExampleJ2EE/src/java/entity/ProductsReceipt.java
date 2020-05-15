/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Poltarokov SP
 */
@Entity
@Table(name = "products_receipt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductsReceipt.findAll", query = "SELECT p FROM ProductsReceipt p"),
    @NamedQuery(name = "ProductsReceipt.findByQuantity", query = "SELECT p FROM ProductsReceipt p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "ProductsReceipt.findByDate", query = "SELECT p FROM ProductsReceipt p WHERE p.date = :date"),
    @NamedQuery(name = "ProductsReceipt.findByState", query = "SELECT p FROM ProductsReceipt p WHERE p.state = :state"),
    @NamedQuery(name = "ProductsReceipt.findById", query = "SELECT p FROM ProductsReceipt p WHERE p.id = :id")})
public class ProductsReceipt implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private BigDecimal quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Size(max = 50)
    @Column(name = "state")
    private String state;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Product productId;
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Category warehouseId;

    public ProductsReceipt() {
    }

    public ProductsReceipt(Integer id) {
        this.id = id;
    }

    public ProductsReceipt(Integer id, BigDecimal quantity, Date date) {
        this.id = id;
        this.quantity = quantity;
        this.date = date;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Category getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Category warehouseId) {
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
        if (!(object instanceof ProductsReceipt)) {
            return false;
        }
        ProductsReceipt other = (ProductsReceipt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProductsReceipt[ id=" + id + " ]";
    }
    
}
