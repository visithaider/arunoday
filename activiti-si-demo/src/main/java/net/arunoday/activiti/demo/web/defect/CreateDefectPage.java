package net.arunoday.activiti.demo.web.defect;

import net.arunoday.activiti.demo.entity.Defect;
import net.arunoday.activiti.demo.service.DefectService;
import net.arunoday.activiti.demo.web.BasePage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * 
 * @author Aparna Chaudhary (aparna.chaudhary@gmail.com)
 */
@SuppressWarnings("serial")
@MountPath(path = "create")
public class CreateDefectPage extends BasePage {

    @SpringBean
    private DefectService defectService;

    private Defect defect;

    public CreateDefectPage() {
        this(null);
    }

    public CreateDefectPage(Long id) {
        defect = new Defect();

        FeedbackPanel msgs = new FeedbackPanel("msgs");
        add(msgs);

        Form<Defect> form = new Form<Defect>("form") {
            protected void onSubmit() {
                Defect defect = CreateDefectPage.this.defect;
                defect.setCreatedBy(getUserName());

                defect = defectService.createDefect(CreateDefectPage.this.defect);
                setResponsePage(new DefectDetailPage(defect.getId()));
            }
        };
        form.setModel(new CompoundPropertyModel<Defect>(defect));

        TextField<String> description = new TextField<String>("description");
        description.setRequired(true);
        form.add(description);

        TextField<String> assignedTo = new TextField<String>("assignedTo");
        assignedTo.setRequired(true);
        form.add(assignedTo);

        add(form);

    }

    @Override
    protected IModel<String> getTitleModel() {
        return new Model<String>("Create / Edit Defect");
    }

}
