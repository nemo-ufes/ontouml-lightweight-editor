package br.ufes.inf.nemo.pattern.dynamic.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class Helper {
	private Shell shell;

	/**
	 * @wbp.parser.entryPoint
	 * */
	public Helper(ArrayList<String> stereotypes) {
		init();
		createContents(stereotypes.get(0));
		for (String stereotype : stereotypes) {
			addStereotype(stereotype);
		}
		list.setSelection(0);
	}
	
	public void open() {
		Display display = Display.getDefault();
		bringToFront(shell);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public void bringToFront(final Shell shell) {
		shell.getDisplay().asyncExec(new Runnable() {
			public void run() {
				shell.forceActive();
			}
		});
	}

	private List list;
	private Composite composite;
	/**
	 * Create contents of the window.
	 * @param firstStereotype 
	 */
	private void createContents(String firstStereotype) {
		shell = new Shell(SWT.CLOSE & (~SWT.RESIZE) );
		shell.setSize(599, 285);
		shell.setText("Helper");
		shell.setImage(SWTResourceManager.getImage(DynamicWindow.class,"/resources/icons/x16/sitemap.png"));
		
		composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 596, 260);
		
		list = new List(composite, SWT.BORDER);
		list.setBounds(4, 4, 171, 215);
		list.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event ev) {
				List l = (List)ev.widget;
				String stereotype = (String)l.getItem(l.getSelectionIndex());
				lbStereotype.setText(hash.get(stereotype)[0]);
				txDescription.setText(hash.get(stereotype)[1]);
			}
		});
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();
			}
		});
		btnNewButton.setBounds(260, 225, 75, 25);
		btnNewButton.setText("Close");
		
		currentComposite = new Composite(composite, SWT.BORDER);
		currentComposite.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		currentComposite.setBounds(181	, 4, 408, 215);
		
		lbStereotype = new Label(currentComposite, SWT.NONE);
		lbStereotype.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lbStereotype.setBounds(10, 10, 394, 21);
		lbStereotype.setText(hash.get(firstStereotype)[0]);
		
		txDescription = new Text(currentComposite, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		txDescription.setBounds(10, 37, 384, 164);
		txDescription.setText(hash.get(firstStereotype)[1]);
		txDescription.setEditable(false);
		
		shell.addListener(SWT.Traverse, new Listener(){
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
		
	}
	
	private Composite currentComposite;
	private Text txDescription;
	private Label lbStereotype;

	private HashMap<String,String[]> hash;
	private void init(){
		String moreInformation = "\n\nSee more in G. Guizzardi, Ontological foundations for structural conceptual models. Enschede: Telematica Instituut Fundamental Research Series, 2005.";
		
		hash = new HashMap<>();
		hash.put("Subkind", new String[]{"Subkind > Rigid Sortal > Sortal > Substantial","A <<Subkind>> is a rigid, relationally independent restriction of a substance sortal that carries the principle of identity supplied by it. An example could be the subkind MalePerson of the kind Person. In general, the stereotype <<Subkind>> can be omitted in conceptual models without loss of clarity."+moreInformation});
		hash.put("Kind", new String[]{"Kind > Substance Sortal > Rigid Sortal > Sortal > Substantial","A <<Kind>> represents a substance sortal whose instances are functional complexes. Examples include instances of Natural Kinds (such as Person, Dog, Tree) and of artifacts (Chair, Car, Television)."+moreInformation});
		hash.put("Phase", new String[]{"Phase > Anti Rigid Sortal > Sortal > Substantial","A <<Phase>> represents the phased-sortals phase, i.e. anti-rigid and relationally independent universals defined as part of a partition of a substance sortal. For instance, <Catterpillar, Butterfly> partitions the kind Lepdopterum."+moreInformation});
		hash.put("Collective", new String[]{"Collective > Substance Sortal > Rigid Sortal > Sortal > Substantial","A <<Collective>> represents a substance sortal whose instances are collectives, i.e., they are collections of complexes that have a uniform structure. Examples include a deck of cards, a forest, a group of people, a pile of bricks. Collectives can typically relate to complexes via a constitution relation. For example, a pile of bricks that constitutes a wall, a group of people that constitutes a football team. In this case, the collectives typically have an extensional principle of identity, in contrast to the complexes they constitute. For instance, The Beatles was in a given world w constituted by the collective {John, Paul, George, Pete} and in another world w' constituted by the collective {John, Paul, George, Ringo}. The replacement of Pete Best by Ringo Star does not alter the identity of the band, but creates a numerically different group of people."+moreInformation});
		hash.put("Quantity", new String[]{"Quantity > Substance Sortal > Rigid Sortal > Sortal > Substantial","A <<Quantity>>  represents a substance sortal whose instances are collectives, i.e., they are collections of complexes that have a uniform structure. Examples include a deck of cards, a forest, a group of people, a pile of bricks. Collectives can typically relate to complexes via a constitution relation. For example, a pile of bricks that constitutes a wall, a group of people that constitutes a football team. In this case, the collectives typically have an extensional principle of identity, in contrast to the complexes they constitute. For instance, The Beatles was in a given world w constituted by the collective {John, Paul, George, Pete} and in another world w' constituted by the collective {John, Paul, George, Ringo}. The replacement of Pete Best by Ringo Star does not alter the identity of the band, but creates a numerically different group of people."+moreInformation});
		hash.put("Role", new String[]{"Role > Anti Rigid Sortal > Sortal > Substantial","A <<Role>> represents a phased-sortal role, i.e. anti-rigid and relationally dependent universal. For instance, the role student is played by an instance of the kind Person."+moreInformation});
		hash.put("Category", new String[]{"Category > Rigid Mixin > Mixin > Substantial","A <<Category>> represents a rigid and relationally independent mixin, i.e., a dispersive universal that aggregates essential properties which are common to different substance sortals. For example, the category RationalEntity as a generalization of Person and IntelligentAgent."+moreInformation});
		hash.put("RoleMixin", new String[]{"RoleMixin > AntiRigidMixin > NonRigidMixin > Mixin > Substantial","A <<RoleMixin>> represents an anti-rigid and externally dependent non- sortal, i.e., a dispersive universal that aggregates properties which are common to different roles. In includes formal roles such as whole and part, and initiatior and responder."+moreInformation});
		hash.put("Mixin", new String[]{"Mixin > SemiRigidMixin > NonRigidMixin > Mixin > Substantial","A <<Mixin>> epresents properties which are essential to some of its instances and accidental to others (semi-rigidity). An example is the mixin Seatable, which represents a property that can be considered essential to the kinds Chair and Stool, but accidental to Crate, Paper Box or Rock."+moreInformation});
		hash.put("Mode", new String[]{"Mode > Moment", "A <<Mode>> universal is an intrinsic moment universal. Every instance of mode universal is existentially dependent of exactly one entity. Examples include skills, thoughts, beliefs, intentions, symptoms, private goals."+moreInformation});
		hash.put("Relator", new String[]{"Relator > Moment", "A <<Relator>> universal is an intrinsic moment universal. Every instance of mode universal is existentially dependent of exactly one entity. Examples include skills, thoughts, beliefs, intentions, symptoms, private goals."+moreInformation});
	}
	
	private void addStereotype(String stereotype){
		list.add(stereotype);
	}
}