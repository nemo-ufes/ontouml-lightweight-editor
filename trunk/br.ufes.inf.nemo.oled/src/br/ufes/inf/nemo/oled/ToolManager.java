package br.ufes.inf.nemo.oled;

import javax.swing.JTabbedPane;

import br.ufes.inf.nemo.oled.palette.Palette;
import br.ufes.inf.nemo.oled.palette.PaletteAccordion;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditorCommandDispatcher;

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
				
		palettes = new PaletteAccordion(frame);
		palettes.createStaticStructurePalettes(editorDispatcher);
		//Assistent assistent = new Assistent();
		//Assistent patternsPanel = new Assistent();
				
		addTab("Toolbox", palettes); //TODO Localize these		
		//this.addTab("Assistent", assistent);
		//this.addTab("Patterns", patternsPanel);
	}
	
	public DiagramEditorCommandDispatcher getEditorDispatcher() {
		return editorDispatcher;
	}
	
	public Palette getOpenPalette() {
		return palettes.getOpenPalette();
	}

	public Palette getElementsPalette()
	{
		return palettes.getElementsPalette();
	}

	public Palette getDerivationPatternsPalette()
	{
		return palettes.getDerivationPatternsPalette();
	}
	
	public Palette getPatternsPalette()
	{
		return palettes.getPatternsPalette();
	}
	
	public PaletteAccordion getPalleteAccordion()
	{
		return palettes;
	}


}
