package br.edu.materdei.tas.production.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.production.entity.ProductionNoteEntity;
import br.edu.materdei.tas.production.repository.ProductionNoteRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author brunofontana
 */
@Service
public class ProductionNoteService implements IBaseService<ProductionNoteEntity> {

    @Autowired
    private ProductionNoteRepository repository;

    @Override
    @Transactional
    public List<ProductionNoteEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public ProductionNoteEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public ProductionNoteEntity save(ProductionNoteEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
}
