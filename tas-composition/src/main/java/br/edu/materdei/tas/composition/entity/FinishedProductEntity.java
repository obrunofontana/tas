package br.edu.materdei.tas.composition.entity;

import br.edu.materdei.tas.core.entity.ProductEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author brunofontana
 */
@Entity
@Table(name = "finishedproduct")
public class FinishedProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(nullable = false)
    private ProductEntity product;

    @Column(nullable = false)
    private Integer stockMin;

    @Column(nullable = false)
    private Integer stockMax;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<RawMaterialEntity> ingredients;

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

    /**
     * @return the stockMin
     */
    public Integer getStockMin() {
        return stockMin;
    }

    /**
     * @param stockMin the stockMin to set
     */
    public void setStockMin(Integer stockMin) {
        this.stockMin = stockMin;
    }

    /**
     * @return the stockMax
     */
    public Integer getStockMax() {
        return stockMax;
    }

    /**
     * @param stockMax the stockMax to set
     */
    public void setStockMax(Integer stockMax) {
        this.stockMax = stockMax;
    }

    /**
     * @return the ingredients
     */
    public List<RawMaterialEntity> getIngredients() {
        return ingredients;
    }

    /**
     * @param ingredients the ingredients to set
     */
    public void setIngredients(List<RawMaterialEntity> ingredients) {
        this.ingredients = ingredients;
    }

}
