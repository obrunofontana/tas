package br.edu.materdei.tas.sale.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.sale.entity.InvoiceEntity;
import br.edu.materdei.tas.sale.entity.SalesOrderEntity;
import br.edu.materdei.tas.sale.repository.InvoiceRepository;
import br.edu.materdei.tas.sale.repository.SalesOrderRepository;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author brunofontana
 */
@Service
public class InvoiceService implements IBaseService<InvoiceEntity> {

    @Autowired
    private InvoiceRepository repository;
    
    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Override
    @Transactional
    public List<InvoiceEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public InvoiceEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public InvoiceEntity save(InvoiceEntity entity) {
        // Retorno o primeiro registro por ordem de codigo decrescente
        InvoiceEntity lastRecord = this.repository.findFirstByOrderByCodeDesc();
        
        // Incremento o codigo utilizando uma variavel de controle do tipo integer
        Integer code = (lastRecord == null) ? 0 : Integer.valueOf(lastRecord.getCode());
        code++;
        
        // Atribuo o novo codigo a venda que está sendo salva
        // Isto, faz isto: 6 -> 000006
        entity.setCode(String.format("%06d", code)); 
                
        // Salvo a venda
        InvoiceEntity invoice = this.repository.saveAndFlush(entity);
        
        // Marco o pedido como faturado
        SalesOrderEntity salesOrder = invoice.getSalesOrder();
        salesOrder.setBillingDate(new Date());
        
        this.salesOrderRepository.save(salesOrder);
        
        return invoice;
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
         // Pego a venda pelo ID
        InvoiceEntity invoice = repository.getOne(id);
//        InvoiceEntity invoice = this.repository.getOne(id);
        
        // Excluo a venda
        repository.deleteById(invoice.getId());
        
        // Pego o pedido da venda para remover a data de faturado
        SalesOrderEntity salesOrder = invoice.getSalesOrder();
        salesOrder.setBillingDate(null);
        
        // Salvo a alteração feita no pedido
        this.salesOrderRepository.save(salesOrder);
    }
}
