package br.ufes.inf.nemo.story.ui;

import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import RefOntoUML.Package;
import RefOntoUML.parser.OntoUMLParser;
import RefOntoUML.util.RefOntoUMLResourceUtil;

public class Example {
	public static void main(String[] args) throws IOException {
	    Display display = new Display();
	    final Shell shell = new Shell(display);
	    shell.setLayout(new GridLayout(1,false));
	    
	    //adaptar para se integrar melhor ao OLED
	    Resource res = RefOntoUMLResourceUtil.loadModel("test_data/input/cmto.refontouml");
	    RefOntoUML.Package root= (Package) res.getContents().get(0);
	    OntoUMLParser parser = new OntoUMLParser(root);
	    StoryElementTimeline tl = new StoryElementTimeline(parser,shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
		final Tree tree = tl.getTree();
		tl.createWorld();
		tl.createWorld();
		tl.createWorld();
		tl.createNode(tree);
		
		for (int i = 0; i < 1; i++) {
	      TreeItem item = tl.createNode(tree);
	      
	      for (int j = 0; j < 1; j++) {
	        tl.createNode_state(item,j);
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
