package br.edu.materdei.tas.composition.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.composition.entity.FinishedProductEntity;
import br.edu.materdei.tas.composition.repository.FinishedProductRepository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author brunofontana
 */
@Service
public class FinishedProductService implements IBaseService<FinishedProductEntity> {

    @Autowired
    private FinishedProductRepository repository;

    @Override
    @Transactional
    public List<FinishedProductEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public FinishedProductEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public FinishedProductEntity save(FinishedProductEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
}
