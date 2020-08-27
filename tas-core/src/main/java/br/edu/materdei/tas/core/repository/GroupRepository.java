package br.edu.materdei.tas.core.repository;

import br.edu.materdei.tas.core.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author brunofontana
 */
@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {
       
}
