package br.edu.materdei.tas.sale.repository;

import br.edu.materdei.tas.sale.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author brunofontana
 */
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    
}
