package br.edu.materdei.tas.sale.repository;

import br.edu.materdei.tas.sale.entity.SalesOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author brunofontana
 */
@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrderEntity, Integer> {
    
}
