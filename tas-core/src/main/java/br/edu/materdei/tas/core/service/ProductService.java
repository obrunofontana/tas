package br.edu.materdei.tas.core.service;

import br.edu.materdei.tas.core.entity.ProductEntity;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author brunofontana
 */
@Service
public class ProductService implements IBaseService<ProductEntity> {

    @Autowired
    private ProductRepository repository;

    @Override
    @Transactional
    public List<ProductEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public ProductEntity findById(Integer id) throws ResourceNotFoundException {
        // funcao de callback -> elseThrow 
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public ProductEntity save(ProductEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }

}
