package net.arunoday.activiti.demo.service.impl;

import net.arunoday.activiti.demo.entity.Defect;
import net.arunoday.activiti.demo.service.DefectService;
import net.arunoday.activiti.demo.service.HelloWorldService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This class is the launching point of a simple jBPM based service case
 * 
 */
public class Main {

	public static void main(String[] args) throws Throwable {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		applicationContext.start();

		HelloWorldService helloWorldService = applicationContext
				.getBean(HelloWorldService.class);
		helloWorldService.sayHello();

		DefectService defectService = (DefectService) applicationContext
				.getBean("defectService");
		Defect defect = defectService.createDefect(new Defect());
		defectService.reviewDefect(defect.getId(), "developer1");
		defectService.markResolved(defect.getId(), defect.getAssignedTo(), "Defect Resolved");
		applicationContext.close();

	}
}
