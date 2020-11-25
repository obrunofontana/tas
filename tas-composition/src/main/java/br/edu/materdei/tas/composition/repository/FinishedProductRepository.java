package br.edu.materdei.tas.composition.repository;

import br.edu.materdei.tas.composition.entity.FinishedProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author brunofontana
 */
@Repository
public interface FinishedProductRepository extends JpaRepository<FinishedProductEntity, Integer> {
    
}
