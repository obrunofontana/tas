package br.edu.materdei.tas.core.service;

import br.edu.materdei.tas.core.entity.GroupEntity;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.repository.GroupRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author brunofontana
 */
@Service
public class GroupService implements IBaseService<GroupEntity> {

    @Autowired
    private GroupRepository repository;

    @Override
    @Transactional
    public List<GroupEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public GroupEntity findById(Integer id) throws ResourceNotFoundException {
        // funcao de callback -> elseThrow 
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public GroupEntity save(GroupEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }

}
