package br.edu.materdei.tas.sale.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "salesorder")
public class SalesOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 6, nullable = false)
    private String code;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date salesDate;
    
    @Temporal(TemporalType.DATE)
    private Date billingDate;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private CustomerEntity customer;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemSalesOrderEntity> items;

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
     * @return the salesDate
     */
    public Date getSalesDate() {
        return salesDate;
    }

    /**
     * @param salesDate the salesDate to set
     */
    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

    /**
     * @return the billingDate
     */
    public Date getBillingDate() {
        return billingDate;
    }

    /**
     * @param billingDate the billingDate to set
     */
    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    /**
     * @return the customer
     */
    public CustomerEntity getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    /**
     * @return the items
     */
    public List<ItemSalesOrderEntity> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<ItemSalesOrderEntity> items) {
        this.items = items;
    }
    
    

    
}
