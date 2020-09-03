package br.edu.materdei.tas.purchase.repository;

import br.edu.materdei.tas.purchase.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author brunofontana
 */
@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Integer> {
    
}
