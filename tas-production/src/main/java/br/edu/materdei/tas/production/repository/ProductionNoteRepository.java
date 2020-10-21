package br.edu.materdei.tas.production.repository;

import br.edu.materdei.tas.production.entity.ProductionNoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author brunofontana
 */
public interface ProductionNoteRepository extends JpaRepository<ProductionNoteEntity, Integer> {
    
}
