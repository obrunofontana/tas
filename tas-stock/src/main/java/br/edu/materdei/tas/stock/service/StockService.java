package br.edu.materdei.tas.stock.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.stock.entity.StockEntity;
import br.edu.materdei.tas.stock.repository.StockRepository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author brunofontana
 */
@Service
public class StockService implements IBaseService<StockEntity> {

    @Autowired
    private StockRepository repository;

    @Override
    @Transactional
    public List<StockEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public StockEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public StockEntity save(StockEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
}
