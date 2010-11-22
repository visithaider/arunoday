/**
 *
 *
 */

package net.arunoday.activiti.demo.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import net.arunoday.activiti.demo.service.HelloWorldService;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Aparna Chaudhary
 */
@Service("helloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService {

	private static final Logger logger = Logger
			.getLogger(HelloWorldServiceImpl.class);
	private static final String HELLO_WORLD_PROCESS_KEY = "HelloWorld";

	private List<String> processDefinitions;

	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	TaskService taskService;
	@Autowired
	HistoryService historyService;

	@PostConstruct
	public void setupProcessDefinitions() {
		try {
			for (String processDefinition : processDefinitions) {
				repositoryService
						.createDeployment()
						.addInputStream(
								processDefinition,
								new ClassPathResource(processDefinition)
										.getInputStream()).deploy();
			}
		} catch (Exception e) {
			logger.info("Exception occurred: ", e);
			throw new RuntimeException(
					"An error occured while trying to deploy a process definition",
					e);
		}
	}

	@Override
	public void sayHello() {
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey(HELLO_WORLD_PROCESS_KEY);
		logger.info("Process Instance Ended: "
				+ runtimeService.createProcessInstanceQuery()
						.processInstanceId(processInstance.getId())
						.singleResult());
		HistoricProcessInstance historicProcessInstance = historyService
				.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstance.getId()).singleResult();
		logger.info("Process Instance From History: "
				+ historicProcessInstance.getProcessDefinitionId()
				+ " Ended On: " + historicProcessInstance.getEndTime());
	}

	/**
	 * A simple mutator to facilitate configuration.
	 * 
	 * @param definitions
	 */
	public void setProcessDefinitions(List<String> definitions) {
		this.processDefinitions = definitions;
	}

}
