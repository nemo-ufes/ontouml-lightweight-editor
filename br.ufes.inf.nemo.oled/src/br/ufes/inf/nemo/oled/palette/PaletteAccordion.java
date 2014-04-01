package br.ufes.inf.nemo.oled.palette;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.palette.ColorPalette.ThemeColor;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditorCommandDispatcher;

/**
 * This class provides an accordion pane for accomodating the many allElements 
 * used by the editor.
 */
public class PaletteAccordion extends JPanel{

	private static final long serialVersionUID = 8265628368514182832L;

	private AppFrame frame;
	private Map<String, Palette> paletteMap = new LinkedHashMap<String, Palette>();
	private String openPalette = null;
	private JPanel openContent = null;

	private JPanel topTitles;
	private JPanel bottomTitles;

	private static int PALLETE_VSPACE = 20;
	private static Border resetBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
	private static Color resetBackground = null;

	private static Border selectedItemBorder = new LineBorder(ColorPalette.getInstance().getColor(ThemeColor.GREEN_DARK), 1, true);
	private static Color selectedItemBackground = ColorPalette.getInstance().getColor(ThemeColor.GREEN_LIGHT);

	private static Border hoverItemBorder = new LineBorder(ColorPalette.getInstance().getColor(ThemeColor.GREY_DARK), 1, true);
	private static Color hoverItemBackground = ColorPalette.getInstance().getColor(ThemeColor.GREY_LIGHT);

	private static Border selectedPaletteBorder = new LineBorder(ColorPalette.getInstance().getColor(ThemeColor.GREEN_DARK), 1, true);
	private static Color selectedPaletteBackground = ColorPalette.getInstance().getColor(ThemeColor.GREEN_MEDIUM);

	private static Border unselectedPaletteBorder = new LineBorder(ColorPalette.getInstance().getColor(ThemeColor.GREY_DARK), 1, true);
	private static Color unselectedPaletteBackground = ColorPalette.getInstance().getColor(ThemeColor.GREY_LIGHT);
		
	public AppFrame getFrame()
	{
		return frame;
	}
	public PaletteAccordion(AppFrame frame) {

		super();
		this.frame = frame;
		
		this.setLayout(new BorderLayout());		
		this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

		topTitles = new JPanel();
		bottomTitles = new JPanel();
		openContent = new JPanel();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(openContent);
		scrollPane.setBorder(null);
		
		topTitles.setLayout(new BoxLayout(topTitles, BoxLayout.Y_AXIS));
		bottomTitles.setLayout(new BoxLayout(bottomTitles, BoxLayout.Y_AXIS));
		openContent.setLayout(new BorderLayout());

		this.add(topTitles, BorderLayout.NORTH);
		this.add(bottomTitles, BorderLayout.SOUTH);
		this.add(scrollPane, BorderLayout.CENTER);

	}

	public void createStaticStructurePalettes(DiagramEditorCommandDispatcher editorDispatcher)
	{
		createStaticClassesPalette(editorDispatcher);		
		//createStaticRelationshipsPalette(editorDispatcher);
		//createMiscellaneousPalette(editorDispatcher);
		//createStaticRulesPalette(editorDispatcher);
		createPatternsPalette(editorDispatcher);
		render();
	}
	
	private void render()
	{
		boolean found = false;

		topTitles.removeAll();
		bottomTitles.removeAll();
		openContent.removeAll();

		for (String item : paletteMap.keySet()) {
			if(!found)
			{
				paletteMap.get(item).setUnselectedLayout();
				topTitles.add(paletteMap.get(item).getTitle());
				topTitles.add(Box.createRigidArea(new Dimension(0,1)));
			}
			else
			{
				paletteMap.get(item).setUnselectedLayout();
				bottomTitles.add(paletteMap.get(item).getTitle());
				bottomTitles.add(Box.createRigidArea(new Dimension(0,1)));
			}
			if(item == openPalette)
			{
				found = true;
				openContent.add(paletteMap.get(item).getContent(), BorderLayout.CENTER);
				paletteMap.get(item).setSelectedLayout();
			}			
		}

		this.validate();
	}

	public void setOpenPalette(String name)
	{
		if(openPalette != name)
		{
			openPalette = name;
			render();
		}
	}
	
	public Palette getOpenPalette()
	{
		return paletteMap.get(openPalette);
	}

	private void createPatternsPalette(DiagramEditorCommandDispatcher editorDispatcher)
	{
		Palette palette =  new Palette(this, "Derived Types Patterns");
		palette.createElement("staticpalette.classes", "select");
		//palette.addSpacer(0,PALLETE_VSPACE);
		palette.createElement("staticpalette.patterns", "derivationbyunion");
		palette.createElement("staticpalette.patterns2", "derivationbyexclusion");
//		palette.createElement("staticpalette.classes", "quantity");
//		palette.createElement("staticpalette.classes", "collective");
//		palette.createElement("staticpalette.classes", "subkind");
//		//palette.addSpacer(0,PALLETE_VSPACE);
//		palette.createElement("staticpalette.classes", "phase");
//		palette.createElement("staticpalette.classes", "role");
//		//palette.addSpacer(0,PALLETE_VSPACE);
//		palette.createElement("staticpalette.classes", "category");
//		palette.createElement("staticpalette.classes", "rolemixin");
//		palette.createElement("staticpalette.classes", "mixin");
//		//palette.addSpacer(0,PALLETE_VSPACE);
//		palette.createElement("staticpalette.classes", "mode");
//		palette.createElement("staticpalette.classes", "relator");
//		//palette.addSpacer(0,PALLETE_VSPACE);
//		palette.createElement("staticpalette.classes", "datatype");
//		
//		//palette.addSpacer(0,PALLETE_VSPACE);		
//		
//		palette.createElement("staticpalette.relations", "generalization");
//		//palette.addSpacer(0,PALLETE_VSPACE);
//		palette.createElement("staticpalette.relations", "material");
//		palette.createElement("staticpalette.relations", "formal");
//		//palette.addSpacer(0,PALLETE_VSPACE);
//		palette.createElement("staticpalette.relations", "characterization");
//		palette.createElement("staticpalette.relations", "mediation");
//		palette.createElement("staticpalette.relations", "derivation");
//		//palette.addSpacer(0,PALLETE_VSPACE);
//		palette.createElement("staticpalette.relations", "componentof");
//		palette.createElement("staticpalette.relations", "memberof");	
//		palette.createElement("staticpalette.relations", "subcollectionof");
//		palette.createElement("staticpalette.relations", "subquantityof");
//		//palette.addSpacer(0,PALLETE_VSPACE);
//		palette.createElement("staticpalette.relations", "association");
		
		palette.addCommandListener(editorDispatcher);
		
		paletteMap.put("Derived Types Patterns", palette);

		if(openPalette == null)
			openPalette = "Derived Types Patterns";
	}
	
	private void createStaticClassesPalette(DiagramEditorCommandDispatcher editorDispatcher)
	{
		Palette palette =  new Palette(this, "Elements");
		palette.createElement("staticpalette.classes", "select");
		//palette.addSpacer(0,PALLETE_VSPACE);
		palette.createElement("staticpalette.classes", "kind");
		palette.createElement("staticpalette.classes", "quantity");
		palette.createElement("staticpalette.classes", "collective");
		palette.createElement("staticpalette.classes", "subkind");
		//palette.addSpacer(0,PALLETE_VSPACE);
		palette.createElement("staticpalette.classes", "phase");
		palette.createElement("staticpalette.classes", "role");
		//palette.addSpacer(0,PALLETE_VSPACE);
		palette.createElement("staticpalette.classes", "category");
		palette.createElement("staticpalette.classes", "rolemixin");
		palette.createElement("staticpalette.classes", "mixin");
		//palette.addSpacer(0,PALLETE_VSPACE);
		palette.createElement("staticpalette.classes", "mode");
		palette.createElement("staticpalette.classes", "relator");
		//palette.addSpacer(0,PALLETE_VSPACE);
		palette.createElement("staticpalette.classes", "datatype");
		
		//palette.addSpacer(0,PALLETE_VSPACE);		
		
		palette.createElement("staticpalette.relations", "generalization");
		//palette.addSpacer(0,PALLETE_VSPACE);
		palette.createElement("staticpalette.relations", "material");
		palette.createElement("staticpalette.relations", "formal");
		//palette.addSpacer(0,PALLETE_VSPACE);
		palette.createElement("staticpalette.relations", "characterization");
		palette.createElement("staticpalette.relations", "mediation");
		palette.createElement("staticpalette.relations", "derivation");
		//palette.addSpacer(0,PALLETE_VSPACE);
		palette.createElement("staticpalette.relations", "componentof");
		palette.createElement("staticpalette.relations", "memberof");	
		palette.createElement("staticpalette.relations", "subcollectionof");
		palette.createElement("staticpalette.relations", "subquantityof");
		//palette.addSpacer(0,PALLETE_VSPACE);
		palette.createElement("staticpalette.relations", "association");
		
		palette.addCommandListener(editorDispatcher);
		
		paletteMap.put("Elements", palette);

		if(openPalette == null)
			openPalette = "Elements";
	}

	@SuppressWarnings("unused")
	private void createStaticRelationshipsPalette(DiagramEditorCommandDispatcher editorDispatcher)
	{
		Palette palette =  new Palette(this, "Relationships");
		palette.createElement("staticpalette.relations", "select");
		palette.addSpacer(0,PALLETE_VSPACE);
		palette.createElement("staticpalette.relations", "generalization");
		palette.addSpacer(0,PALLETE_VSPACE);
		palette.createElement("staticpalette.relations", "material");
		palette.createElement("staticpalette.relations", "formal");
		palette.addSpacer(0,PALLETE_VSPACE);
		palette.createElement("staticpalette.relations", "characterization");
		palette.createElement("staticpalette.relations", "mediation");
		palette.createElement("staticpalette.relations", "derivation");
		palette.addSpacer(0,PALLETE_VSPACE);
		palette.createElement("staticpalette.relations", "componentof");
		palette.createElement("staticpalette.relations", "memberof");	
		palette.createElement("staticpalette.relations", "subcollectionof");
		palette.createElement("staticpalette.relations", "subquantityof");
		palette.addSpacer(0,PALLETE_VSPACE);
		palette.createElement("staticpalette.relations", "association");
		
		palette.addCommandListener(editorDispatcher);

		paletteMap.put("Relationships", palette);

		if(openPalette == null)
			openPalette = "Relationships";
	}

	@SuppressWarnings("unused")
	private void createMiscellaneousPalette(DiagramEditorCommandDispatcher editorDispatcher)
	{
		Palette palette =  new Palette(this, "Miscellaneous");
		palette.createElement("staticpalette.misc", "select");
		palette.createElement("staticpalette.misc", "package");
		palette.createElement("staticpalette.misc", "note");
		palette.createElement("staticpalette.misc", "noteconnector");
		
		palette.addCommandListener(editorDispatcher);

		paletteMap.put("Miscellaneous", palette);

		if(openPalette == null)
			openPalette = "Miscellaneous";
	}
	
	@SuppressWarnings("unused")
	private void createStaticRulesPalette(DiagramEditorCommandDispatcher editorDispatcher)
	{
		Palette palette =  new Palette(this, "Rules");
		palette.createElement("staticpalette.rules", "select");
		palette.createElement("staticpalette.rules", "condition");
		palette.createElement("staticpalette.rules", "derivationrule");
		palette.createElement("staticpalette.rules", "conclusion");
		
		palette.addCommandListener(editorDispatcher);

		paletteMap.put("Rules", palette);

		if(openPalette == null)
			openPalette = "Rules";
	}
	
	public void NotifySelection(PaletteElement item) {
		for (Palette palette : paletteMap.values()) {
			palette.unselectAllBut(item);
		}
	}

	public static Border getResetBorder() {
		return resetBorder;
	}

	public static Color getResetBackground() {
		return resetBackground;
	}

	public static Border getSelectedItemBorder() {
		return selectedItemBorder;
	}

	public static Color getSelectedItemBackground() {
		return selectedItemBackground;
	}

	public static Border getHoverItemBorder() {
		return hoverItemBorder;
	}

	public static Color getHoverItemBackground() {
		return hoverItemBackground;
	}

	public static Border getSelectedPaletteBorder() {
		return selectedPaletteBorder;
	}

	public static Color getSelectedPaletteBackground() {
		return selectedPaletteBackground;
	}

	public static Border getUnselectedPaletteBorder() {
		return unselectedPaletteBorder;
	}

	public static Color getUnselectedPaletteBackground() {
		return unselectedPaletteBackground;
	}

	public static Component getSpacer(int width, int height) {
		return Box.createRigidArea(new Dimension(width, height));
	}	
}
