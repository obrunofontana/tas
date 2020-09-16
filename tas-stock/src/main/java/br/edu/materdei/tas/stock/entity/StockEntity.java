package br.edu.materdei.tas.stock.entity;

import br.edu.materdei.tas.core.entity.ProductEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author brunofontana
 */
@Entity
@Table(name = "stock")
public class StockEntity {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Integer id;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateMove;
    
    @Column(nullable = false)
    private Double quantity;
    
    @Column(nullable = false)
    private String history;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private ProductEntity product;

    public StockEntity() {
        this.dateMove = new Date();
    }

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
     * @return the dateMove
     */
    public Date getDateMove() {
        return dateMove;
    }

    /**
     * @param dateMove the dateMove to set
     */
    public void setDateMove(Date dateMove) {
        this.dateMove = dateMove;
    }

    /**
     * @return the quantity
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the history
     */
    public String getHistory() {
        return history;
    }

    /**
     * @param history the history to set
     */
    public void setHistory(String history) {
        this.history = history;
    }

    /**
     * @return the product
     */
    public ProductEntity getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(ProductEntity product) {
        this.product = product;
    }
            
   
    
}
