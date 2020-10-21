package br.edu.materdei.tas.production.repository;

import br.edu.materdei.tas.production.entity.ProductionOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author brunofontana
 */
public interface ProductionOrderRepository extends JpaRepository<ProductionOrderEntity, Integer> {
    
}
