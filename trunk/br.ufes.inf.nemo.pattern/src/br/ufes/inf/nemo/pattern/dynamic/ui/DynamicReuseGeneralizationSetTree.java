package br.ufes.inf.nemo.pattern.dynamic.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;


/**
 * @author Victor Amorim
 */
public class DynamicReuseGeneralizationSetTree {

	protected Shell shell;

	/**
	 * Open the window.
	 * @param hash 
	 */
	public void open(HashMap<String, ArrayList<String>> hash) {
		stereotype = null;
		GS = null;
		Display display = Display.getDefault();
		createContents(hash);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	private String stereotype = null;
	private String GS = null;
	private Text queryString;
	private Tree tree;

	public String getStereotype() {
		return stereotype;
	}

	public String getGS() {
		return GS;
	}

	/**
	 * @param hash 
	 * @wbp.parser.entryPoint
	 */
	protected void createContents(HashMap<String, ArrayList<String>> hash) {
		shell = new Shell(SWT.CLOSE & (~SWT.RESIZE) | SWT.APPLICATION_MODAL);

		shell.addListener(SWT.Traverse, new Listener() {
			public void handleEvent(Event event) {
				switch (event.detail) {
				case SWT.TRAVERSE_ESCAPE:
					shell.close();
					event.detail = SWT.TRAVERSE_NONE;
					event.doit = false;
					break;
				}
			}
		});

		shell.setSize(248, 341);
		shell.setText("Reusing Generalization Set");
		shell.setImage(SWTResourceManager.getImage(DynamicReuseGeneralizationSetTree.class,"/resources/icons/x16/magnifier.png"));

		shell.setLayout(null);

		tree = new Tree(shell, SWT.BORDER | SWT.V_SCROLL);
		tree.setBounds(6, 37, 232, 228);

		TreeItem treeItem;
		TreeItem subItem;

		for(Entry<String, ArrayList<String>> entry : hash.entrySet()){
			treeItem = new TreeItem (tree, SWT.NONE);
			treeItem.setText (entry.getKey());
			treeItem.setData(null);

			for(String cls : entry.getValue()){
				subItem = new TreeItem(treeItem, SWT.NONE);
				subItem.setText(cls);
				subItem.setData(entry.getKey());
			}
		}

		final Button btnAdd = new Button(shell, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				shell.dispose();
			}
		});
		btnAdd.setBounds(81, 271, 75, 25);
		btnAdd.setText("Select");

		queryString = new Text(shell, SWT.BORDER);
		queryString.setBounds(6, 10, 165, 21);

		Button btnSearch = new Button(shell, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(TreeItem topItem: tree.getItems()){
					for(TreeItem treeItem: topItem.getItems()){
						if(treeItem.getText().toLowerCase().contains(queryString.getText().toLowerCase())){
							tree.setSelection(treeItem);
							return;
						}
					}
				}
			}
		});

		btnSearch.setBounds(177, 8, 61, 25);
		btnSearch.setText("Search");

		tree.addListener (SWT.Selection, new Listener () {
			@Override
			public void handleEvent (Event event) {
				TreeItem item = (TreeItem) event.item;
				if(item.getData() != null){
					btnAdd.setEnabled(true);

					GS = item.getText();
					stereotype = (String)item.getData();
				}else{
					btnAdd.setEnabled(false);
				}

			}
		});
		if(tree.getItemCount() == 0){
			btnAdd.setText("Close");
		}
		
		//bringing to front
		shell.getDisplay().asyncExec(new Runnable() {
			public void run() {
				shell.forceActive();
			}
		});
		
	}
}
