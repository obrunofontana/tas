package br.edu.materdei.tas.purchase.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.purchase.entity.SupplierEntity;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.purchase.repository.SupplierRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author brunofontana
 */
@Service
public class SupplierService implements IBaseService<SupplierEntity> {

    @Autowired
    private SupplierRepository repository;

    @Override
    @Transactional
    public List<SupplierEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public SupplierEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public SupplierEntity save(SupplierEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
}
