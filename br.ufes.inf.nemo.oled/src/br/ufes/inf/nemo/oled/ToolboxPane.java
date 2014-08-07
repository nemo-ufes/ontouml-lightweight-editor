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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import br.ufes.inf.nemo.oled.palette.Palette;
import br.ufes.inf.nemo.oled.palette.PaletteAccordion;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditorCommandDispatcher;

/**
 * @author John Guerson
 */
public class ToolboxPane extends JPanel {

	private static final long serialVersionUID = 1752050268631906319L;
	@SuppressWarnings("unused")
	private AppFrame frame;
	private DiagramEditorCommandDispatcher editorDispatcher;
	private PaletteAccordion palettes;	

	public ToolboxPane(AppFrame frame, DiagramEditorCommandDispatcher editorDispatcher)
	{
		super();
		setBackground(Color.WHITE);
		setMinimumSize(new Dimension(0,0));
		
		this.frame = frame;
		this.editorDispatcher = editorDispatcher;
		
		setFocusable(false);
		setLayout(new BorderLayout(0,0));
		
		palettes = new PaletteAccordion(frame);
		palettes.setBackground(Color.WHITE);
		palettes.createStaticStructurePalettes(editorDispatcher);
		
		TitlePane panel = new TitlePane("Toolbox","/resources/icons/x16/hammer_screwdriver.png");	
				
		add(palettes,BorderLayout.CENTER);
		add(panel,BorderLayout.NORTH);
		
		//addTab("Toolbox", palettes); //TODO Localize these
		//setIconAt(indexOfComponent(palettes),new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/icons/x16/hammer_screwdriver.png")));
		
		//Assistent assistent = new Assistent();
		//Assistent patternsPanel = new Assistent();
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
