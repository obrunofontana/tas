package br.edu.materdei.tas.sale.repository;

import br.edu.materdei.tas.sale.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author brunofontana
 */
@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Integer> {
    InvoiceEntity findFirstByOrderByCodeDesc();
}
