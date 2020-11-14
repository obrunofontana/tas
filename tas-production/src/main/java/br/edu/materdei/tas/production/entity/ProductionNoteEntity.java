package br.edu.materdei.tas.production.entity;

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
@Table(name = "productionnote")
public class ProductionNoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date appointmentDate;
    
    @ManyToOne
    @JoinColumn(nullable = false)   
    private ProductionOrderEntity op;
    
    @Column(nullable = false)
    private Double quanitty;

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
     * @return the appointmentDate
     */
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * @param appointmentDate the appointmentDate to set
     */
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * @return the op
     */
    public ProductionOrderEntity getOp() {
        return op;
    }

    /**
     * @param op the op to set
     */
    public void setOp(ProductionOrderEntity op) {
        this.op = op;
    }

    /**
     * @return the quanitty
     */
    public Double getQuanitty() {
        return quanitty;
    }

    /**
     * @param quanitty the quanitty to set
     */
    public void setQuanitty(Double quanitty) {
        this.quanitty = quanitty;
    }
}
