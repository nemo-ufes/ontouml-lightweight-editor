/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import br.ufes.inf.nemo.oled.palette.Palette;
import br.ufes.inf.nemo.oled.palette.PaletteAccordion;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditorCommandDispatcher;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditorWrapper;

/**
 * @author John Guerson
 */
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
		setIconAt(indexOfComponent(palettes),new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/hammer_screwdriver.png")));
		//this.addTab("Assistent", assistent);
		//this.addTab("Patterns", patternsPanel);
	}
	
	public DiagramEditorCommandDispatcher getEditorDispatcher() {
		return editorDispatcher;
	}
	
	public Palette getElementPalette() {
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
