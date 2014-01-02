package br.ufes.inf.nemo.oled.antipattern.wizard.test;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class FirstPage extends WizardPage {
	
  public Text text1;
  public Composite container;
  public Button btnPage2;
  public Button btnPage3;

  public FirstPage() {
    super("First Page");
    setTitle("First Page");
    setDescription("Fake Wizard. First page");
  }

  @Override
  public void createControl(Composite parent) {
    container = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    container.setLayout(layout);
    layout.numColumns = 2;
    Label label1 = new Label(container, SWT.NONE);
    label1.setText("Put here a value");

    text1 = new Text(container, SWT.BORDER | SWT.SINGLE);
    text1.setText("");
    text1.addKeyListener(new KeyListener() {

      @Override
      public void keyPressed(KeyEvent e) {
      }

      @Override
      public void keyReleased(KeyEvent e) {
        if (!text1.getText().isEmpty()) {
          setPageComplete(true);

        }
      }

    });
    text1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    // Required to avoid an error in the system
    setControl(container);

    btnPage2 = new Button(container, SWT.CHECK);
    btnPage2.setText("Page2");
    new Label(container, SWT.NONE);
    
    btnPage3 = new Button(container, SWT.CHECK);
    btnPage3.setText("Page3");
    new Label(container, SWT.NONE);
    
    setPageComplete(false);

  }

  public String getText1() {
    return text1.getText();
  }
  
  	@Override
	public IWizardPage getNextPage() {
	  	if (btnPage3.getSelection()) return ((MyWizard)getWizard()).third;
	  	else if (btnPage2.getSelection()) return ((MyWizard)getWizard()).two;
	  	else return super.getNextPage();
	}
}
 