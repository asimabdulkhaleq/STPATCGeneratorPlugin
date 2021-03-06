package xstampp.stpatcgenerator.wizards.pages;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import xstampp.stpatcgenerator.util.TCGeneratorPluginUtils;
import xstampp.ui.wizards.AbstractWizardPage;

/**
 * 
 * This class initializes the wizard Generate Test Case Page
 * 
 * @author Ting Luk-He
 *
 */
public class ConfigurationPage extends AbstractWizardPage implements SelectionListener {

	private String projectPath;
	private Composite composite;
	private Button genTCWithSTPABtn;
	private Button genTCWithoutSTPABtn;
	private Text projectPathText;
	private PathComposite stateFlowPathChooser;
	private Button pathChooseButton;
	private String[] filters = { "*.xml" };
	private boolean genTCWithSTPA;

	public ConfigurationPage(String pageName, String projectPath) {
		super(pageName, projectPath);
		// TODO Auto-generated constructor stub
		this.projectPath = projectPath;
		this.genTCWithSTPA = true;
		setTitle("Configuration of STPA TCGenerator");
		setDescription("Select Stateflow and STPA Project");
	}

	@Override
	public void createControl(Composite parent) {
		// create the composite to hold the widgets
		composite = new Composite(parent, SWT.NONE);
		// create the desired layout for this wizard page
		FormLayout layout = new FormLayout();
		// layout.marginHeight = 100;
		// layout.marginWidth = 200;
		composite.setLayout(layout);

		FormData formData = new FormData();
		// radio button: Generate SMV Model With STPA Project
		genTCWithSTPABtn = new Button(composite, SWT.RADIO);
		genTCWithSTPABtn.setText("Generate Test Case with STPA Project");
		formData.top = new FormAttachment(20, 0);
		// formData.left = new FormAttachment(0, 0);
		// formData.width = 150;
		// formData.height = 25;
		genTCWithSTPABtn.setLayoutData(formData);
		genTCWithSTPABtn.setSelection(true);
		genTCWithSTPABtn.addSelectionListener(this);

		// radio button: Generate SMV Model Without STPA Project
		genTCWithoutSTPABtn = new Button(composite, SWT.RADIO);
		genTCWithoutSTPABtn.setText("Generate Test Case Without STPA Project");
		formData = new FormData();
		formData.top = new FormAttachment(genTCWithSTPABtn, 10);
		// formData.left = new FormAttachment(0,0);
		genTCWithoutSTPABtn.setLayoutData(formData);
		genTCWithoutSTPABtn.addSelectionListener(this);

		// Label and Text: STPA Project
		Label lbl = new Label(composite, SWT.LEFT);
		lbl.setText("STPA Project");
		formData = new FormData();
		formData.top = new FormAttachment(genTCWithoutSTPABtn, 20);
		lbl.setLayoutData(formData);
		projectPathText = new Text(composite, SWT.BORDER);
		formData = new FormData();
		formData.top = new FormAttachment(genTCWithoutSTPABtn, 20);
		// layout for IDE
		formData.left = new FormAttachment(lbl, 55);
		// layout for update site
//		formData.left = new FormAttachment(lbl, 50);
		formData.width = 333;
		projectPathText.setLayoutData(formData);
		projectPathText.setText(this.projectPath);

		// state flow simulink file chooser
		stateFlowPathChooser = new PathComposite(this.filters, this.filters, composite, PathComposite.PATH_DIALOG,
				"State Flow Simulink");
		String filePath = TCGeneratorPluginUtils.readLocationFromFile(TCGeneratorPluginUtils.STATEFLOW_LOCATION_FILE);
		stateFlowPathChooser.setText(filePath);
		formData = new FormData();
		formData.top = new FormAttachment(lbl, 20);
		stateFlowPathChooser.setLayoutData(formData);

		setControl(composite);
		setPageComplete(checkFinish());
	}

	@Override
	public boolean checkFinish() {
		this.setErrorMessage(null);
		if (((this.stateFlowPathChooser == null) || this.stateFlowPathChooser.getText().equals(""))) { //$NON-NLS-1$
			return false;
		}
		return true;
	}

	public void setProjectPath(String path) {
		this.projectPath = path;
	}

	public String getProjectPath() {
		return this.projectPath;
	}

	public String getStateFlowPath() {
		return this.stateFlowPathChooser.getText();
	}

	public Button getPathChooseButton() {
		return this.pathChooseButton;
	}

	public boolean getGenTCWichSTPA() {
		return this.genTCWithSTPA;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		if (e.widget == genTCWithSTPABtn) {
			projectPathText.setEnabled(true);
			this.genTCWithSTPA = true;
		} else if (e.widget == genTCWithoutSTPABtn) {
			projectPathText.setEnabled(false);
			this.genTCWithSTPA = false;
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
