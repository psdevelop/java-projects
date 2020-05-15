/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arzugurbanova
 */
@Entity
@Table(name = "cb_ekfgroup_new_pr_from_1c_wprops")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CbEkfgroupNewPrFrom1cWprops.findAll", query = "SELECT c FROM CbEkfgroupNewPrFrom1cWprops c"),
    @NamedQuery(name = "CbEkfgroupNewPrFrom1cWprops.findById", query = "SELECT c FROM CbEkfgroupNewPrFrom1cWprops c WHERE c.id = :id"),
    @NamedQuery(name = "CbEkfgroupNewPrFrom1cWprops.findByVendorCode", query = "SELECT c FROM CbEkfgroupNewPrFrom1cWprops c WHERE c.vendorCode = :vendorCode"),
    @NamedQuery(name = "CbEkfgroupNewPrFrom1cWprops.findByShortName", query = "SELECT c FROM CbEkfgroupNewPrFrom1cWprops c WHERE c.shortName = :shortName"),
    @NamedQuery(name = "CbEkfgroupNewPrFrom1cWprops.findByName", query = "SELECT c FROM CbEkfgroupNewPrFrom1cWprops c WHERE c.name = :name"),
    @NamedQuery(name = "CbEkfgroupNewPrFrom1cWprops.findByProductGroupId", query = "SELECT c FROM CbEkfgroupNewPrFrom1cWprops c WHERE c.productGroupId = :productGroupId"),
    @NamedQuery(name = "CbEkfgroupNewPrFrom1cWprops.findByJsonData", query = "SELECT c FROM CbEkfgroupNewPrFrom1cWprops c WHERE c.jsonData = :jsonData"),
    @NamedQuery(name = "CbEkfgroupNewPrFrom1cWprops.findByFp0", query = "SELECT c FROM CbEkfgroupNewPrFrom1cWprops c WHERE c.fp0 = :fp0"),
    @NamedQuery(name = "CbEkfgroupNewPrFrom1cWprops.findByFp1", query = "SELECT c FROM CbEkfgroupNewPrFrom1cWprops c WHERE c.fp1 = :fp1"),
    @NamedQuery(name = "CbEkfgroupNewPrFrom1cWprops.findByFp2", query = "SELECT c FROM CbEkfgroupNewPrFrom1cWprops c WHERE c.fp2 = :fp2"),
    @NamedQuery(name = "CbEkfgroupNewPrFrom1cWprops.findByFp3", query = "SELECT c FROM CbEkfgroupNewPrFrom1cWprops c WHERE c.fp3 = :fp3"),
    @NamedQuery(name = "CbEkfgroupNewPrFrom1cWprops.findByFp4", query = "SELECT c FROM CbEkfgroupNewPrFrom1cWprops c WHERE c.fp4 = :fp4"),
    @NamedQuery(name = "CbEkfgroupNewPrFrom1cWprops.findByQuant", query = "SELECT c FROM CbEkfgroupNewPrFrom1cWprops c WHERE c.quant = :quant"),
    @NamedQuery(name = "CbEkfgroupNewPrFrom1cWprops.findByBasePrice", query = "SELECT c FROM CbEkfgroupNewPrFrom1cWprops c WHERE c.basePrice = :basePrice"),
    @NamedQuery(name = "CbEkfgroupNewPrFrom1cWprops.findByIshopPrice", query = "SELECT c FROM CbEkfgroupNewPrFrom1cWprops c WHERE c.ishopPrice = :ishopPrice")})
public class CbEkfgroupNewPrFrom1cWprops implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Size(max = 36)
    @Column(name = "id")
    private String id;
    @Size(max = 200)
    @Column(name = "vendor_code")
    private String vendorCode;
    @Size(max = 150)
    @Column(name = "short_name")
    private String shortName;
    @Size(max = 512)
    @Column(name = "name")
    private String name;
    @Size(max = 36)
    @Column(name = "product_group_id")
    private String productGroupId;
    @Size(max = 2147483647)
    @Column(name = "json_data")
    private String jsonData;
    @Size(max = 1000)
    @Column(name = "fp0")
    private String fp0;
    @Size(max = 1000)
    @Column(name = "fp1")
    private String fp1;
    @Size(max = 1000)
    @Column(name = "fp2")
    private String fp2;
    @Size(max = 1000)
    @Column(name = "fp3")
    private String fp3;
    @Size(max = 1000)
    @Column(name = "fp4")
    private String fp4;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "quant")
    private BigDecimal quant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "base_price")
    private BigDecimal basePrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ishop_price")
    private BigDecimal ishopPrice;

    public CbEkfgroupNewPrFrom1cWprops() {
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

    public String getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(String productGroupId) {
        this.productGroupId = productGroupId;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public String getFp0() {
        return fp0;
    }

    public void setFp0(String fp0) {
        this.fp0 = fp0;
    }

    public String getFp1() {
        return fp1;
    }

    public void setFp1(String fp1) {
        this.fp1 = fp1;
    }

    public String getFp2() {
        return fp2;
    }

    public void setFp2(String fp2) {
        this.fp2 = fp2;
    }

    public String getFp3() {
        return fp3;
    }

    public void setFp3(String fp3) {
        this.fp3 = fp3;
    }

    public String getFp4() {
        return fp4;
    }

    public void setFp4(String fp4) {
        this.fp4 = fp4;
    }

    public BigDecimal getQuant() {
        return quant;
    }

    public void setQuant(BigDecimal quant) {
        this.quant = quant;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getIshopPrice() {
        return ishopPrice;
    }

    public void setIshopPrice(BigDecimal ishopPrice) {
        this.ishopPrice = ishopPrice;
    }
    
}
