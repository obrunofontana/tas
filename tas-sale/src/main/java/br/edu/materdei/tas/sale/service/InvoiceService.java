package br.edu.materdei.tas.sale.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.sale.entity.InvoiceEntity;
import br.edu.materdei.tas.sale.repository.InvoiceRepository;

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
        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
}
