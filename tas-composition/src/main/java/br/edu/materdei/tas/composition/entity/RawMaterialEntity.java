package br.edu.materdei.tas.composition.entity;

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
@Table(name = "ingredients")
public class RawMaterialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Double quantity;

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
