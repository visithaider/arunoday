/**
 *
 *
 */

package net.arunoday.activiti.demo.repository;

import java.util.List;

import net.arunoday.activiti.demo.entity.Defect;

/**
 *
 * @author Aparna Chaudhary
 */
public interface DefectRepository extends ReadWriteRepository<Defect>{

    List<Defect> findByIds(List<Long> defectIds);

}
