package br.edu.materdei.tas.production.repository;

import br.edu.materdei.tas.production.entity.FinishedProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author brunofontana
 */
public interface FinishedProductRepository extends JpaRepository<FinishedProductEntity, Integer> {
    
}
