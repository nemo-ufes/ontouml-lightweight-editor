package br.ufes.inf.nemo.oled.ui;

import javax.swing.JTabbedPane;

import br.ufes.inf.nemo.oled.model.UmlProject;

public class ToolManager extends JTabbedPane {

	private static final long serialVersionUID = 1752050268631906319L;
	@SuppressWarnings("unused")
	private AppFrame frame;
	private DiagramEditorCommandDispatcher editorDispatcher;
	private PaletteAccordion palettes;	

	public ToolManager(AppFrame frame, DiagramEditorCommandDispatcher editorDispatcher)
	{
		super();
		
		this.frame = frame;
		this.editorDispatcher = editorDispatcher;
		
		setFocusable(false);
				
		palettes = new PaletteAccordion();
		palettes.createStaticStructurePalettes(editorDispatcher);
		//Assistent assistent = new Assistent();
		//Assistent patternsPanel = new Assistent();
				
		this.add("ToolBox", palettes); //TODO Localize these
		this.add("Model", null);
		//this.add("Assistent", assistent);
		//this.add("Patterns", patternsPanel);
	}

	public void showModelTree(UmlProject project)
	{
		ModelTree modelTree = ModelTree.getTreeFor(project);
		this.setComponentAt(indexOfTab("Model"), modelTree);
		repaint();
		revalidate();
	}
	
	public DiagramEditorCommandDispatcher getEditorDispatcher() {
		return editorDispatcher;
	}
	
	public Palette getOpenPalette() {
		return palettes.getOpenPalette();
	}



}
