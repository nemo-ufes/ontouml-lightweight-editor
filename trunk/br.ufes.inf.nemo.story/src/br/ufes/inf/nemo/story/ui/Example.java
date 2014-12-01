package br.ufes.inf.nemo.story.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class Example {
	public static void main(String[] args) {
	    Display display = new Display();
	    final Shell shell = new Shell(display);
	    shell.setLayout(new FillLayout());
	    
	    //adaptar para se integrar melhor ao OLED
	    
	    StoryElementTimeline tl = new StoryElementTimeline(shell,SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
		final Tree tree = tl.getTree();
		tl.createWorld();
		tl.createWorld();
		tl.createWorld();
	    for (int i = 0; i < 4; i++) {
	      TreeItem item = tl.createStoryElement(tree);
	      
	      for (int j = 0; j < 4; j++) {
	        tl.createStoryElement(item);
	      }
	    }
	    
	    
	    shell.pack();
	    shell.open();
	    while (!shell.isDisposed()) {
	      if (!display.readAndDispatch()) {
	        display.sleep();
	      }
	    }
	    display.dispose();
	  }
}
