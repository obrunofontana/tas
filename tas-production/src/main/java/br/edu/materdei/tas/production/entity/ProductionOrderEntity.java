package br.edu.materdei.tas.production.entity;

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
@Table(name = "productionorder")
public class ProductionOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date initialDate;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date endDate;
    
    @Column(nullable = false)
    private Double quantity;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private ProductEntity product;

}
