package net.arunoday.activiti.demo.handler;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import net.arunoday.activiti.demo.entity.Defect;
import net.arunoday.activiti.demo.service.DefectService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Activator component to process email messages. Creates defects into the system based on the email message
 * content.
 * 
 * @author Aparna Chaudhary
 */
@Component("gmailMessageActivator")
public class GmailMessageActivator {

    private static final Logger logger = Logger.getLogger(GmailMessageActivator.class);

    @Autowired
    DefectService defectService;

    @Transactional(readOnly = false)
    public void process(MimeMessage mimeMessage) throws Exception {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Message: " + mimeMessage.getSubject() + " Content: " + mimeMessage.getContent());
            }
            String[] fromHeaders = mimeMessage.getHeader("From");
            String from = "";
            if (fromHeaders != null && fromHeaders.length > 0) {
                from = fromHeaders[0];
            }
            Defect defect = new Defect();
            defect.setDescription(mimeMessage.getSubject());
            defect.setCreatedBy(from);
            defect.setAssignedTo("manager");
            defect = defectService.createDefect(defect);
        }
        catch (MessagingException e) {
            throw new Exception("Exception occurred during message receiption ", e);
        }
    }
}
