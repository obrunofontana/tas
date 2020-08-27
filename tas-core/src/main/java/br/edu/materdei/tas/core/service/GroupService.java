package br.edu.materdei.tas.core.service;

import br.edu.materdei.tas.core.entity.GroupEntity;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.repository.GroupRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author brunofontana
 */
@Service
public class GroupService implements IBaseService<GroupEntity>{
    
    @Autowired
    private GroupRepository repository;

    @Override
    public List<GroupEntity> findAll() {
        return repository.findAll();
        
    }

    @Override
    public GroupEntity findById(Integer id) throws ResourceNotFoundException {
        
    }

    @Override
    public GroupEntity save(GroupEntity entity) {
        
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        
    }
    
}
