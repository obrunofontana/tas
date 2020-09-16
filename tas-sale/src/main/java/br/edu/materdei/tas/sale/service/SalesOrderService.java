package br.edu.materdei.tas.sale.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.sale.entity.SalesOrderEntity;
import br.edu.materdei.tas.sale.repository.SalesOrderRepository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author brunofontana
 */
public class SalesOrderService implements IBaseService<SalesOrderEntity> {

    @Autowired
    private SalesOrderRepository repository;

    @Override
    @Transactional
    public List<SalesOrderEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public SalesOrderEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public SalesOrderEntity save(SalesOrderEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
}
