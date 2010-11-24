/**
 *
 *
 */

package net.arunoday.activiti.demo.service;

import java.util.List;

import net.arunoday.activiti.demo.entity.Defect;

/**
 * 
 * @author Aparna Chaudhary
 */
public interface DefectService {

    Defect createDefect(Defect defect);

    Defect getDefectById(Long defectId);

    Defect reviewDefect(Long defectId, String assignedTo);

    Defect markResolved(Long defectId, String assignedTo, String resolution);

    List<Defect> findDefectsForReview(String username);

    List<Defect> findDefectsForResolution(String username);

}
