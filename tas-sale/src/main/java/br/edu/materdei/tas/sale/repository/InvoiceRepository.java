package br.edu.materdei.tas.sale.repository;

import br.edu.materdei.tas.sale.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author brunofontana
 */
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Integer> {
    
}
