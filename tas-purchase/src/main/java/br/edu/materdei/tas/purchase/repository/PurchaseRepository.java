package br.edu.materdei.tas.purchase.repository;

import br.edu.materdei.tas.purchase.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author brunofontana
 */
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Integer> {
    
}
