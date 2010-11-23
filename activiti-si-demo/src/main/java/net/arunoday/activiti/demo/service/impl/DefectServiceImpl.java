/**
 *
 *
 */

package net.arunoday.activiti.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import net.arunoday.activiti.demo.entity.Defect;
import net.arunoday.activiti.demo.entity.DefectStatus;
import net.arunoday.activiti.demo.repository.DefectRepository;
import net.arunoday.activiti.demo.service.DefectService;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Aparna Chaudhary
 */
@Service("defectService")
@Transactional(readOnly = true)
public class DefectServiceImpl implements DefectService {

    private static final Logger logger = Logger.getLogger(DefectServiceImpl.class);

    private static final String DEFECT_TRACKING_PROCESS_KEY = "DefectTracking";

    private List<String> processDefinitions;

    @Autowired
    private DefectRepository defectRepository;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;

    @PostConstruct
    public void setupProcessDefinitions() {
        try {
            for (String processDefinition : processDefinitions) {
                repositoryService.createDeployment()
                        .addInputStream(processDefinition, new ClassPathResource(processDefinition).getInputStream())
                        .deploy();
            }
        }
        catch (Exception e) {
            logger.info("Exception occurred: ", e);
            throw new RuntimeException("An error occured while trying to deploy a process definition", e);
        }
    }

    @Override
    public Defect getDefectById(Long defectId) {
        return defectRepository.getById(defectId);
    }

    @Override
    @Transactional(readOnly = false)
    public Defect createDefect(Defect defect) {
        defect.setCreatedDate(new LocalDate());
        defect.setStatus(DefectStatus.NEW);
        Defect newDefect = defectRepository.save(defect);

        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("defectId", newDefect.getId());
        vars.put("assignee", defect.getAssignedTo());
        runtimeService.startProcessInstanceByKey(DEFECT_TRACKING_PROCESS_KEY, newDefect.getId().toString(), vars);
        return defect;
    }

    @Override
    @Transactional(readOnly = false)
    public Defect reviewDefect(Long defectId, String assignedTo) {
        Defect existingDefect = defectRepository.getById(defectId);
        String existingAsignee = existingDefect.getAssignedTo();

        existingDefect.setStatus(DefectStatus.ASSIGNED);
        existingDefect.setAssignedTo(assignedTo);
        defectRepository.update(existingDefect);

        for (Task task : taskService.createTaskQuery().taskAssignee(existingAsignee).list()) {
            if (task.getName().equalsIgnoreCase("reviewDefect")) {
                Long taskDefectId = (Long) runtimeService.getVariable(task.getExecutionId(), "defectId");
                if (taskDefectId.equals(defectId)) {
                    taskService.complete(task.getId());
                    logger.info("Reviewed Defect: " + task.getName() + " Defect Id: " + taskDefectId);
                }
            }
        }
        logger.info("Task assigned to: " + existingAsignee + " : "
                + taskService.createTaskQuery().taskAssignee(existingAsignee));
        logger.info("Task assigned to: " + assignedTo + " : " + taskService.createTaskQuery().taskAssignee(assignedTo));

        return existingDefect;
    }

    @Override
    @Transactional(readOnly = false)
    public Defect markResolved(Long defectId, String assignedTo, String resolution) {
        Defect existingDefect = getDefectById(defectId);
        String existingAssignee = existingDefect.getAssignedTo();
        existingDefect.setResolution(resolution);
        existingDefect.setStatus(DefectStatus.RESOLVED);
        existingDefect.setAssignedTo(assignedTo);
        defectRepository.update(existingDefect);

        for (Task task : taskService.createTaskQuery().taskAssignee(existingAssignee).list()) {
            if (task.getName().equalsIgnoreCase("resolveDefect")) {
                Long taskDefectId = (Long) runtimeService.getVariable(task.getExecutionId(), "defectId");
                if (taskDefectId.equals(defectId)) {
                    taskService.complete(task.getId());
                    logger.info("Resolved Defect: " + task.getName() + " Defect Id: " + taskDefectId);
                }
            }
        }

        return existingDefect;
    }

    /**
     * A simple mutator to facilitate configuration.
     * 
     * @param definitions
     */
    public void setProcessDefinitions(List<String> definitions) {
        this.processDefinitions = definitions;
    }

    @Override
    public List<Defect> findDefectsForReview(String username) {
        return findDefects(username, "reviewDefect");
    }

    @Override
    public List<Defect> findDefectsForResolution(String username) {
        return findDefects(username, "resolveDefect");
    }

    private List<Defect> findDefects(String username, String taskName) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(username).list();
        List<Long> defectIds = new ArrayList<Long>();
        for (Task task : tasks) {
            if (task.getName().equalsIgnoreCase(taskName)) {
                Long defectId = (Long) runtimeService.getVariable(task.getExecutionId(), "defectId");
                defectIds.add(defectId);
            }
        }
        return defectRepository.findByIds(defectIds);
    }

}
