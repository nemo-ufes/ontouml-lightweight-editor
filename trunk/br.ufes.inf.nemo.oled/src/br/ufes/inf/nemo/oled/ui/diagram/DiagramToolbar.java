package br.ufes.inf.nemo.oled.ui.diagram;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import br.ufes.inf.nemo.oled.antipattern.AntiPatternSearchDialog;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;

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
	private JToggleButton btnToolBox;
	private JButton btnExportPng;
	private JButton btnZoomOut;
	private JButton btnZoomIn;
	private JButton btnZoomStatus;
	private JButton btnNewDiagram;
	private JButton btnAlloy;
	private JButton btnOWL;
	private JButton btnAntiPattern;
	
	public void update(){
		btnGrid.setSelected(editor.showGrid());
		btnZoomStatus.setText(editor.getZoomPercentualValue()+"%");
		btnToolBox.setSelected(editor.getDiagramManager().getFrame().showToolBox());
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
		btnGrid.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/grid.png")));
		add(btnGrid);
		
		btnToolBox = new JToggleButton("");
		btnToolBox.setSelected(editor.getDiagramManager().getFrame().showToolBox());
		btnToolBox.setToolTipText("Show/hide Toolbox");
		btnToolBox.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {        		
        		editor.getDiagramManager().getFrame().showToolBox(btnToolBox.isSelected());
        	}
        });
		btnToolBox.setFocusable(false);
		btnToolBox.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/hammer_screwdriver.png")));
		add(btnToolBox);
		
		btnAlignBottom = new JButton("");
		btnAlignBottom.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.alignBottom();
        	}
        });
		btnAlignBottom.setToolTipText("Align Bottom");
		btnAlignBottom.setFocusable(false);
		btnAlignBottom.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_bottom.png")));
		add(btnAlignBottom);
		
		btnAlignTop = new JButton("");
		btnAlignTop.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.alignTop();
        	}
        });
		btnAlignTop.setToolTipText("Align Top");
		btnAlignTop.setFocusable(false);
		btnAlignTop.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_top.png")));
		add(btnAlignTop);
		
		btnAlignLeft = new JButton("");
		btnAlignLeft.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.alignLeft();
        	}
        });
		btnAlignLeft.setToolTipText("Align Left");
		btnAlignLeft.setFocusable(false);
		btnAlignLeft.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_left.png")));
		add(btnAlignLeft);
		
		btnAlignRight = new JButton("");
		btnAlignRight.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.alignRight();
        	}
        });
		btnAlignRight.setToolTipText("Align Right");
		btnAlignRight.setFocusable(false);
		btnAlignRight.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_right.png")));
		add(btnAlignRight);
		
		btnAlignCenterVertically = new JButton("");
		btnAlignCenterVertically.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.alignCenterVertically();
        	}
        });
		btnAlignCenterVertically.setToolTipText("Align Center Vertically");
		btnAlignCenterVertically.setFocusable(false);
		btnAlignCenterVertically.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_center.png")));
		add(btnAlignCenterVertically);
		
		btnAlignCenterHorizontally = new JButton("");
		btnAlignCenterHorizontally.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.alignCenterHorizontally();
        	}
        });	
		btnAlignCenterHorizontally.setToolTipText("Align Center Horizontally");
		btnAlignCenterHorizontally.setFocusable(false);
		btnAlignCenterHorizontally.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/shape_aling_middle.png")));
		add(btnAlignCenterHorizontally);
		
		btnBringToFront = new JButton("");
		btnBringToFront.setToolTipText("Bring to Front");
		btnBringToFront.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		editor.bringToFront();
        	}
        });		
		btnBringToFront.setFocusable(false);
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
		btnExportPng.setFocusable(false);
		btnExportPng.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/photo.png")));
		add(btnExportPng);
		
		btnZoomOut = new JButton("");
		btnZoomOut.setToolTipText("Zoom out");
		btnZoomOut.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {        		
        		editor.zoomOut();
        		btnZoomStatus.setText(editor.getZoomPercentualValue()+"%");
        	}
        });	
		btnZoomOut.setFocusable(false);
		btnZoomOut.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/zoom-out.png")));
		add(btnZoomOut);
		
		btnZoomIn = new JButton("");
		btnZoomIn.setToolTipText("Zoom in");
		btnZoomIn.addActionListener(new ActionListener() {				
        	@Override
        	public void actionPerformed(ActionEvent e) {        		
        		editor.zoomIn();
        		btnZoomStatus.setText(editor.getZoomPercentualValue()+"%");
        	}
        });
		btnZoomIn.setFocusable(false);
		btnZoomIn.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/zoom-in.png")));
		add(btnZoomIn);
				
		btnZoomStatus = new JButton("100%");
		btnZoomStatus.setContentAreaFilled(false);		
		btnZoomStatus.setFocusable(false);
		add(btnZoomStatus);
		
		btnAlloy = new JButton("");
		btnAlloy.setToolTipText("Transform all OPENED diagrams and all axioms into Alloy");
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
		btnAlloy.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/alloy/alloy.png")));
		add(btnAlloy);
		
		btnOWL = new JButton("");
		btnOWL.setToolTipText("Transform all OPENED diagrams and all axioms into OWL");
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
		btnOWL.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/x16/owl.png")));
		add(btnOWL);
		
		btnAntiPattern = new JButton("");
		btnAntiPattern.setToolTipText("Identify antipattern occurrences in all OPENED diagrams");
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
		btnAntiPattern.setIcon(new ImageIcon(DiagramToolbar.class.getResource("/resources/icons/antipattern16.png")));
		add(btnAntiPattern);
	}
}