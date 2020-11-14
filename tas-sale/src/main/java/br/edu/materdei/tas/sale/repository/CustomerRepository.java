package br.edu.materdei.tas.sale.repository;

import br.edu.materdei.tas.sale.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author brunofontana
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    
}
