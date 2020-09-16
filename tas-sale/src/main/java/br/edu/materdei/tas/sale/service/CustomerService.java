package br.edu.materdei.tas.sale.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.sale.entity.CustomerEntity;
import br.edu.materdei.tas.sale.repository.CustomerRepository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author brunofontana
 */
public class CustomerService implements IBaseService<CustomerEntity> {

    @Autowired
    private CustomerRepository repository;

    @Override
    @Transactional
    public List<CustomerEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public CustomerEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public CustomerEntity save(CustomerEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
}
