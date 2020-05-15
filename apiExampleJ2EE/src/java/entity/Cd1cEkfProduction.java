/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author arzugurbanova
 */
@Entity
@Table(name = "cd_1c_ekf_production")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cd1cEkfProduction.findAll", query = "SELECT c FROM Cd1cEkfProduction c"),
    @NamedQuery(name = "Cd1cEkfProduction.findById", query = "SELECT c FROM Cd1cEkfProduction c WHERE c.id = :id"),
    @NamedQuery(name = "Cd1cEkfProduction.findByVendorCode", query = "SELECT c FROM Cd1cEkfProduction c WHERE c.vendorCode = :vendorCode"),
    @NamedQuery(name = "Cd1cEkfProduction.findByShortName", query = "SELECT c FROM Cd1cEkfProduction c WHERE c.shortName = :shortName"),
    @NamedQuery(name = "Cd1cEkfProduction.findByName", query = "SELECT c FROM Cd1cEkfProduction c WHERE c.name = :name")})
public class Cd1cEkfProduction implements Serializable {

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
    @Size(max = 13)
    @Column(name = "barcode")
    private String barcode;

    public Cd1cEkfProduction() {
    }

    public Cd1cEkfProduction(String id) {
        this.id = id;
    }

    public Cd1cEkfProduction(String id, String shortName) {
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
        if (!(object instanceof Cd1cEkfProduction)) {
            return false;
        }
        Cd1cEkfProduction other = (Cd1cEkfProduction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Cd1cEkfProduction[ id=" + id + " ]";
    }
    
    public String toJsonString()   {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"id\":\"").append(id).append("\",\"name\":\"").append(StringEscapeUtils.escapeJson(name)).append("\",\"vendor_code\":\"").
                append(StringEscapeUtils.escapeJson(vendorCode)).append("\",\"short_name\":\"").append(StringEscapeUtils.escapeJson(shortName)).append("\"}");
        return sb.toString();
    }
    
}
