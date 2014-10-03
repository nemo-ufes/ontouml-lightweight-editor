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
package br.ufes.inf.nemo.oled.ui.diagram;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.ui.diagram.commands.AlignElementsCommand;
import br.ufes.inf.nemo.oled.ui.diagram.commands.AlignElementsCommand.Alignment;
import br.ufes.inf.nemo.oled.ui.diagram.commands.DiagramNotification;
import br.ufes.inf.nemo.oled.ui.diagram.commands.SetColorCommand;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;
import br.ufes.inf.nemo.oled.validator.antipattern.AntiPatternSearchDialog;

/**
 * @author John Guerson
 */
public class DiagramToolbar extends JToolBar {

	private static final long serialVersionUID = 1L;
	public DiagramEditor editor;
	public JButton btnAlignBottom;
	private JButton btnAlignRight;
	private JButton btnAlignTop;
	private JButton btnAlignLeft;
	private JButton btnAlignCenterVertically;
	private JButton btnAlignCenterHorizontally;
	private JButton btnBringToFront;
	private JButton btnPutToBack;
	private JToggleButton btnGrid;	
	private JButton btnExportPng;
	private JButton btnZoomOut;
	private JButton btnZoomIn;
	private JButton btnZoomStatus;
	private JButton btnNewDiagram;
	private JButton btnAlloy;
	private JButton btnOWL;
	private JButton btnAntiPattern;
	private JButton btnColor;
	private JButton btnMeronymic;
	private JButton btnFitToWindow;
	private JButton btnZoom100;
	
	public void update(){
		btnGrid.setSelected(editor.showGrid());
		btnZoomStatus.setText(editor.getZoomPercentualValue()+"%");		
	}
	
	public DiagramToolbar (final DiagramEditor editor)
	{
		this.editor = editor;
		setFloatable(false);
		setMargin(new Insets(5,5,5,5));
				
		btnNewDiagram = new JButton("");
		btnNewDiagram.setToolTipText("New diagram");
		btnNewDiagram.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {        		
        		editor.getDiagramManager().newDiagram();
        	}
        });
		btnNewDiagram.setFocusable(false);
		btnNewDiagram.setBorderPainted(false);
		btnNewDiagram.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/application_add.png")));
		add(btnNewDiagram);
		
		btnGrid = new JToggleButton("");
		btnGrid.setSelected(editor.showGrid());
		btnGrid.setToolTipText("Grid Lines");
		btnGrid.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {        		
        		editor.showGrid(btnGrid.isSelected());
        	}
        });
		btnGrid.setFocusable(false);
		btnGrid.setBorderPainted(false);
		btnGrid.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/grid.png")));
		add(btnGrid);
		
		btnAlignBottom = new JButton("");
		btnAlignBottom.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.execute(new AlignElementsCommand((DiagramNotification)editor,(ArrayList<DiagramElement>) editor.getSelectedElements(),editor.getProject(),Alignment.BOTTOM));
        	}
        });
		
		btnAlignTop = new JButton("");
		btnAlignTop.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.execute(new AlignElementsCommand((DiagramNotification)editor,(ArrayList<DiagramElement>) editor.getSelectedElements(),editor.getProject(),Alignment.TOP));
        	}
        });
		btnAlignTop.setToolTipText("Align Top");
		btnAlignTop.setFocusable(false);
		btnAlignTop.setBorderPainted(false);
		btnAlignTop.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_top.png")));
		add(btnAlignTop);
		
		btnAlignCenterHorizontally = new JButton("");
		btnAlignCenterHorizontally.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.execute(new AlignElementsCommand((DiagramNotification)editor,(ArrayList<DiagramElement>) editor.getSelectedElements(),editor.getProject(),Alignment.CENTER_HORIZONTAL));
        	}
        });	
		btnAlignCenterHorizontally.setToolTipText("Align Center Horizontally");
		btnAlignCenterHorizontally.setFocusable(false);
		btnAlignCenterHorizontally.setBorderPainted(false);
		btnAlignCenterHorizontally.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_middle.png")));
		add(btnAlignCenterHorizontally);
				
		btnAlignBottom.setToolTipText("Align Bottom");
		btnAlignBottom.setFocusable(false);
		btnAlignBottom.setBorderPainted(false);
		btnAlignBottom.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_bottom.png")));
		add(btnAlignBottom);
		
		btnAlignLeft = new JButton("");
		btnAlignLeft.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.execute(new AlignElementsCommand((DiagramNotification)editor,(ArrayList<DiagramElement>) editor.getSelectedElements(),editor.getProject(),Alignment.LEFT));
        	}
        });
		btnAlignLeft.setToolTipText("Align Left");
		btnAlignLeft.setFocusable(false);
		btnAlignLeft.setBorderPainted(false);
		btnAlignLeft.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_left.png")));
		add(btnAlignLeft);
		
		btnAlignRight = new JButton("");
		btnAlignRight.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.execute(new AlignElementsCommand((DiagramNotification)editor,(ArrayList<DiagramElement>) editor.getSelectedElements(),editor.getProject(),Alignment.RIGHT));
        	}
        });
		
		btnAlignCenterVertically = new JButton("");
		btnAlignCenterVertically.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.execute(new AlignElementsCommand((DiagramNotification)editor,(ArrayList<DiagramElement>) editor.getSelectedElements(),editor.getProject(),Alignment.CENTER_VERTICAL));
        	}
        });
		btnAlignCenterVertically.setToolTipText("Align Center Vertically");
		btnAlignCenterVertically.setFocusable(false);
		btnAlignCenterVertically.setBorderPainted(false);
		btnAlignCenterVertically.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_center.png")));
		add(btnAlignCenterVertically);
		btnAlignRight.setToolTipText("Align Right");
		btnAlignRight.setFocusable(false);
		btnAlignRight.setBorderPainted(false);
		btnAlignRight.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_right.png")));
		add(btnAlignRight);
		
		btnBringToFront = new JButton("");
		btnBringToFront.setToolTipText("Bring to Front");
		btnBringToFront.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.bringToFront();
        	}
        });		
		btnBringToFront.setFocusable(false);
		btnBringToFront.setBorderPainted(false);
		btnBringToFront.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_move_front.png")));
		add(btnBringToFront);
			
		btnPutToBack = new JButton("");
		btnPutToBack.setToolTipText("Put to Back");
		btnPutToBack.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.putToBack();
        	}
        });	
		btnPutToBack.setFocusable(false);
		btnPutToBack.setBorderPainted(false);
		btnPutToBack.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_move_back.png")));
		add(btnPutToBack);
		
		btnExportPng = new JButton("");
		btnExportPng.setToolTipText("<html>Save diagram as PNG<br><br>TIP: Move your diagram as close as possible <br>to the upper left side of the grid.<br><br> </hmtl>");
		btnExportPng.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {        		
        		editor.getDiagramManager().exportGfx();
        	}
        });	
		
		btnColor = new JButton("");
		btnColor.setToolTipText("Set the background color for selected classes");
		btnColor.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/control_wheel.png")));
		btnColor.addActionListener(new ActionListener() {				
        	private Color color;        	
			@Override
        	public void actionPerformed(ActionEvent e) { 
				if(color==null) color = JColorChooser.showDialog(editor.getDiagramManager().getFrame(), "Select a Background Color", Color.LIGHT_GRAY);
				else color = JColorChooser.showDialog(editor.getDiagramManager().getFrame(), "Select a Background Color", color);
        		if (color != null){
        			editor.execute(new SetColorCommand((DiagramNotification)editor,(ArrayList<DiagramElement>) editor.getSelectedElements(),editor.getProject(),color));        			
        		}        		        		
        	}
        });	
		add(btnColor);
		btnColor.setFocusable(false);
		btnColor.setBorderPainted(false);
								
		btnExportPng.setFocusable(false);
		btnExportPng.setBorderPainted(false);
		btnExportPng.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/photo.png")));
		add(btnExportPng);
		
		btnZoom100 = new JButton("");
		btnZoom100.setToolTipText("Zoom at 100%");
		btnZoom100.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {        		
        		editor.zoom100();
        	}
        });
		btnZoom100.setFocusable(false);
		btnZoom100.setBorderPainted(false);
		btnZoom100.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/zoom-100.png")));
		add(btnZoom100);
		
		btnZoomOut = new JButton("");
		btnZoomOut.setToolTipText("Zoom out");
		btnZoomOut.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {        		
        		editor.zoomOut();        		
        	}
        });	
		btnZoomOut.setFocusable(false);
		btnZoomOut.setBorderPainted(false);
		btnZoomOut.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/zoom-out.png")));
		add(btnZoomOut);
		
		btnZoomIn = new JButton("");
		btnZoomIn.setToolTipText("Zoom in");
		btnZoomIn.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {        		
        		editor.zoomIn();        		
        	}
        });
		btnZoomIn.setFocusable(false);
		btnZoomIn.setBorderPainted(false);
		btnZoomIn.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/zoom-in.png")));
		add(btnZoomIn);
				
		btnFitToWindow = new JButton("");
		btnFitToWindow.setToolTipText("Fit to Window");
		btnFitToWindow.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {        		
        		editor.fitToWindow();
        	}
        });
		btnFitToWindow.setFocusable(false);
		btnFitToWindow.setBorderPainted(false);
		btnFitToWindow.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/arrow_in.png")));
		add(btnFitToWindow);
		
		btnZoomStatus = new JButton("100%");
		btnZoomStatus.setContentAreaFilled(false);		
		btnZoomStatus.setFocusable(false);
		btnZoomStatus.setBorderPainted(false);
		add(btnZoomStatus);
		
		addSeparator();
		
		btnAlloy = new JButton("");
		btnAlloy.setToolTipText("Simulation & Checking: Validate your ontology using Alloy and its Analyzer.");
		btnAlloy.addActionListener(new ActionListener() {				
	    	@Override
	    	public void actionPerformed(ActionEvent e) {       
	    		ArrayList<StructureDiagram> diagrams = new ArrayList<StructureDiagram>();
	    		for(DiagramEditor de: editor.getDiagramManager().getDiagramEditors()) diagrams.add(de.getDiagram());
	    		editor.getDiagramManager().workingOnlyWith(diagrams);
	    		editor.getDiagramManager().openAlloySettings();        		
	    	}
        });	
		btnAlloy.setFocusable(false);
		btnAlloy.setBorderPainted(false);
		btnAlloy.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/alloy/alloy.png")));
		add(btnAlloy);
		
		btnOWL = new JButton("");
		btnOWL.setToolTipText("Semantic Web: Publish and Reason over your ontology using OWL/SWRL.");
		btnOWL.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {        		
        		ArrayList<StructureDiagram> diagrams = new ArrayList<StructureDiagram>();
	    		for(DiagramEditor de: editor.getDiagramManager().getDiagramEditors()) diagrams.add(de.getDiagram());
	    		editor.getDiagramManager().workingOnlyWith(diagrams);
        		editor.getDiagramManager().openOwlSettings();
        	}
        });
		btnOWL.setFocusable(false);
		btnOWL.setBorderPainted(false);
		btnOWL.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/sw-cube.png")));
		add(btnOWL);
		
		btnAntiPattern = new JButton("");
		btnAntiPattern.setToolTipText("Anti-Patterns: Check and Fix your ontology detecting Anti-Patterns occurrences, if any.");
		btnAntiPattern.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {        		
        		ArrayList<StructureDiagram> diagrams = new ArrayList<StructureDiagram>();
	    		for(DiagramEditor de: editor.getDiagramManager().getDiagramEditors()) diagrams.add(de.getDiagram());
	    		editor.getDiagramManager().workingOnlyWith(diagrams);
        		AntiPatternSearchDialog.open(editor.getDiagramManager().getFrame());
        	}
        });
		btnAntiPattern.setFocusable(false);
		btnAntiPattern.setBorderPainted(false);
		btnAntiPattern.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/antipattern16.png")));
		add(btnAntiPattern);
		
		btnMeronymic = new JButton("");
		btnMeronymic.setToolTipText("Part-Whole Relations: Evaluate your ontology regarding the transitivity of part-whole relations.");
		btnMeronymic.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {        		
        		ArrayList<StructureDiagram> diagrams = new ArrayList<StructureDiagram>();
	    		for(DiagramEditor de: editor.getDiagramManager().getDiagramEditors()) diagrams.add(de.getDiagram());
	    		editor.getDiagramManager().workingOnlyWith(diagrams);
        		editor.getDiagramManager().validatesParthood();
        	}
        });
		btnMeronymic.setFocusable(false);
		btnMeronymic.setBorderPainted(false);
		btnMeronymic.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/wall_break.png")));
		add(btnMeronymic);
	}
}