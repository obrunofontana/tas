package br.edu.materdei.tas.stock.repository;

import br.edu.materdei.tas.stock.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author brunofontana
 */
@Repository
public interface StockRepository extends JpaRepository<StockEntity, Integer>{
    
}
