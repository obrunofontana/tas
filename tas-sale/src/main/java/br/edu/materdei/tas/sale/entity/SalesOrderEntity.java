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

    
}
