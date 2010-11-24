/**
 *
 *
 */

package net.arunoday.activiti.demo.web.defect;

import java.util.List;

import net.arunoday.activiti.demo.entity.Defect;
import net.arunoday.activiti.demo.service.DefectService;
import net.arunoday.activiti.demo.web.BasePage;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * 
 * @author Aparna Chaudhary
 */
@SuppressWarnings("serial")
public class TaskListPage extends BasePage {

	@SpringBean
	private DefectService defectService;

	public TaskListPage() {
		showDefectsForReview();
		showDefectsForResolution();
	}

    private void showDefectsForReview() {
		List<Defect> reviewDefects = defectService
				.findDefectsForReview(getUserName());
		WebMarkupContainer wmc = new WebMarkupContainer(
				"reviewDefectsContainer");
		wmc.setOutputMarkupId(true);

		ListView<Defect> defectListView = new ListView<Defect>("reviewDefect",
				reviewDefects) {
			@Override
			protected void populateItem(final ListItem<Defect> item) {
				item.setOutputMarkupId(true);

				final Defect defect = item.getModelObject();
				item.setModel(new CompoundPropertyModel<Defect>(defect));

				item.add(new Label("description"));
				item.add(new Label("resolution"));
				item.add(new Label("createdDate"));
				item.add(new Label("createdBy"));
				item.add(new Label("assignedTo"));
				item.add(new Label("status"));

				Link<Object> reviewLink = new Link<Object>("review") {
					@Override
					public void onClick() {
						setResponsePage(new ReviewDefectPage(defect.getId()));
					}
				};
				item.add(reviewLink);
			}
		};
		wmc.add(defectListView);
		add(wmc);
	}

	private void showDefectsForResolution() {
		List<Defect> resolutionDefects = defectService
				.findDefectsForResolution(getUserName());
		WebMarkupContainer wmc = new WebMarkupContainer(
				"resolveDefectsContainer");
		wmc.setOutputMarkupId(true);

		ListView<Defect> defectListView = new ListView<Defect>("resolveDefect",
				resolutionDefects) {
			@Override
			protected void populateItem(final ListItem<Defect> item) {
				item.setOutputMarkupId(true);

				final Defect defect = item.getModelObject();
				item.setModel(new CompoundPropertyModel<Defect>(defect));

				item.add(new Label("description"));
				item.add(new Label("resolution"));
				item.add(new Label("createdDate"));
				item.add(new Label("createdBy"));
				item.add(new Label("assignedTo"));
				item.add(new Label("status"));

				Link<Object> resolveLink = new Link<Object>("resolve") {
					@Override
					public void onClick() {
						setResponsePage(new ResolveDefectPage(defect.getId()));
					}
				};
				item.add(resolveLink);
			}
		};
		wmc.add(defectListView);
		add(wmc);
	}

	@Override
	protected IModel<?> getTitleModel() {
		return new Model<String>("Task List");
	}

}
