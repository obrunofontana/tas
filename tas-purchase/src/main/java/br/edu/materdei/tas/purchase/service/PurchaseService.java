package br.edu.materdei.tas.purchase.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.purchase.entity.PurchaseEntity;
import br.edu.materdei.tas.purchase.repository.PurchaseRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author brunofontana
 */
@Service
public class PurchaseService implements IBaseService<PurchaseEntity> {

    @Autowired
    private PurchaseRepository repository;

    @Override
    @Transactional
    public List<PurchaseEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public PurchaseEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public PurchaseEntity save(PurchaseEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
}
