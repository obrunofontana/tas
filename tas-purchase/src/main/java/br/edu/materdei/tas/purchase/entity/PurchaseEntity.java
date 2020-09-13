package br.edu.materdei.tas.purchase.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author brunofontana
 */
@Entity
@Table(name = "purchase")
public class PurchaseEntity {
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(length = 6, nullable = false)
    private String code;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date datePurchased;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private SupplierEntity supplier;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemPurchaseEntity> items;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the datePurchased
     */
    public Date getDatePurchased() {
        return datePurchased;
    }

    /**
     * @param datePurchased the datePurchased to set
     */
    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = datePurchased;
    }

    /**
     * @return the supplier
     */
    public SupplierEntity getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the items
     */
    public List<ItemPurchaseEntity> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<ItemPurchaseEntity> items) {
        this.items = items;
    }

    
}
