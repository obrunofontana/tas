package br.edu.materdei.tas.purchase.repository;

import br.edu.materdei.tas.purchase.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author brunofontana
 */
@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Integer> {
    
}
