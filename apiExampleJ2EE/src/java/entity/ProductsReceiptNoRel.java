/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author Poltarokov SP
 */
@Entity
@Table(name = "products_receipt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductsReceiptNoRel.findAll", query = "SELECT p FROM ProductsReceiptNoRel p"),
    @NamedQuery(name = "ProductsReceiptNoRel.findByQuantity", query = "SELECT p FROM ProductsReceiptNoRel p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "ProductsReceiptNoRel.findByDate", query = "SELECT p FROM ProductsReceiptNoRel p WHERE p.date = :date"),
    @NamedQuery(name = "ProductsReceiptNoRel.findByState", query = "SELECT p FROM ProductsReceiptNoRel p WHERE p.state = :state"),
    @NamedQuery(name = "ProductsReceiptNoRel.findById", query = "SELECT p FROM ProductsReceiptNoRel p WHERE p.id = :id")})
public class ProductsReceiptNoRel implements Serializable {

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
    @Column(name = "product_id")
    private String productId;
    @Column(name = "warehouse_id")
    private String warehouseId;

    public ProductsReceiptNoRel() {
    }

    public ProductsReceiptNoRel(Integer id) {
        this.id = id;
    }

    public ProductsReceiptNoRel(Integer id, BigDecimal quantity, Date date) {
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
        if (!(object instanceof ProductsReceiptNoRel)) {
            return false;
        }
        ProductsReceiptNoRel other = (ProductsReceiptNoRel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProductsReceiptNoRel[ id=" + id + " ]";
    }
    
    public String toJsonString()   {
        DecimalFormat df = new DecimalFormat("#.000");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "{\"id\":\""+id+"\",\"quantity\":\""+df.format(quantity)+"\",\"state\":\""+StringEscapeUtils.escapeJson(state)+
                "\",\"date\":\""+dateFormat.format(date)+"\",\"prod_id\":\""+productId+"\",\"whs_id\":\""+warehouseId+"\"}";
    }
    
}
