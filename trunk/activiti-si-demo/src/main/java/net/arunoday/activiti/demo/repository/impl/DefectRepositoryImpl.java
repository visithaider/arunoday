/**
 *
 *
 */

package net.arunoday.activiti.demo.repository.impl;

import java.util.Collections;
import java.util.List;

import net.arunoday.activiti.demo.entity.Defect;
import net.arunoday.activiti.demo.repository.DefectRepository;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


/**
 * Hibernate based implementation for {@link DefectRepository}
 * 
 * @author Aparna Chaudhary
 */
@Repository("defectRepository")
public class DefectRepositoryImpl extends AbstractReadWriteRepository<Defect> implements DefectRepository {

    public DefectRepositoryImpl() {
        super(Defect.class);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Defect> findByIds(List<Long> defectIds) {
        if (defectIds.isEmpty()) {
            return Collections.emptyList();
        }
        return getSession().createCriteria(Defect.class).add(Restrictions.in("id", defectIds)).list();
    }
    

}
