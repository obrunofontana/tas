package br.edu.materdei.tas.production.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.production.entity.ProductionOrderEntity;
import br.edu.materdei.tas.production.repository.ProductionOrderRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author brunofontana
 */
 @Service
public class ProductionOrderService implements IBaseService<ProductionOrderEntity> {

    @Autowired
    private ProductionOrderRepository repository;

    @Override
    @Transactional
    public List<ProductionOrderEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public ProductionOrderEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public ProductionOrderEntity save(ProductionOrderEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
}
