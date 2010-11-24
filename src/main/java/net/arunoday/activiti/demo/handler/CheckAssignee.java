/**
 * 
 */
package net.arunoday.activiti.demo.handler;

import net.arunoday.activiti.demo.entity.Defect;
import net.arunoday.activiti.demo.service.DefectService;
import net.arunoday.activiti.demo.util.SpringApplicationContextUtils;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegation;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Aparna Chaudhary
 */
@Component("checkAssignee")
public class CheckAssignee implements JavaDelegation {

	private static final Logger logger = Logger.getLogger(CheckAssignee.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ApplicationContext context = SpringApplicationContextUtils
				.getApplicationContext();
		DefectService defectService = context.getBean(DefectService.class);

		Long defectId = (Long) execution.getVariable("defectId");
		Defect defect = defectService.getDefectById(defectId);
		
		logger.info("Defect Id: " + defectId + "Assigned To: "
				+ defect.getAssignedTo());

		execution.setVariable("assignee", defect.getAssignedTo());
	}

}
