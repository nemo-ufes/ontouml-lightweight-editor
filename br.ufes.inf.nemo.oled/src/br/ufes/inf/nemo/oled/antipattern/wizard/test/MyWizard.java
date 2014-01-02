package br.ufes.inf.nemo.oled.antipattern.wizard.test;

import org.eclipse.jface.wizard.Wizard;

public class MyWizard extends Wizard {

  public FirstPage one;
  public SecondPage two;
  public ThirdPage third;
  public boolean canFinish;

  public MyWizard() {
    super();
    canFinish=false;
    setNeedsProgressMonitor(true);    
  }

  @Override
  public boolean canFinish() {	 
	return canFinish;
	  
  };
  
  @Override
  public void addPages() {
    one = new FirstPage();
    two = new SecondPage();
    third = new ThirdPage();
    addPage(one);
    addPage(two);
    addPage(third);
  }

  @Override
  public boolean performFinish() {
    // Print the result to the console
    System.out.println(one.getText1());
    System.out.println(two.getText1());

    return true;
  }
}
 