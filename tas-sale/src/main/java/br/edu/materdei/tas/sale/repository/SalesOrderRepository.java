package br.edu.materdei.tas.sale.repository;

import br.edu.materdei.tas.sale.entity.SalesOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author brunofontana
 */
public interface SalesOrderRepository extends JpaRepository<SalesOrderEntity, Integer> {
    
}
