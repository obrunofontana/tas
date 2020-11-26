package br.edu.materdei.tas.sale.entity;

import br.edu.materdei.tas.core.entity.ProductEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author brunofontana
 */
@Entity
@Table(name = "itemssalesorder")
public class ItemSalesOrderEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private Double quantity;
    
    @Column(nullable = false)
    private Double price;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private ProductEntity product;

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
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
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
