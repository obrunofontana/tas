package br.edu.materdei.tas.production.repository;

import br.edu.materdei.tas.production.entity.ProductionOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author brunofontana
 */
@Repository
public interface ProductionOrderRepository extends JpaRepository<ProductionOrderEntity, Integer> {}
