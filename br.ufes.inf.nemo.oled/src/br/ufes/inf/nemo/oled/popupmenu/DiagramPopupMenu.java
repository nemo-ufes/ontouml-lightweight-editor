package br.ufes.inf.nemo.oled.popupmenu;

import javax.swing.JPopupMenu;

import br.ufes.inf.nemo.oled.AppFrame;

public class DiagramPopupMenu extends JPopupMenu {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private AppFrame frame;
//	private JMenuItem showGrid; 
	
	//private JMenuItem inferenceItem = new JMenuItem("Infer Relationships");
	
	public DiagramPopupMenu(final AppFrame frame)
    {        		
    	this.frame = frame;    	
    	    	
//    	showGrid = new JCheckBoxMenuItem("Show Grid");
//		showGrid.setSelected(frame.getDiagramManager().getCurrentDiagramEditor().showGrid());
//		add(showGrid);
//		showGrid.addActionListener(new ActionListener() {			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				frame.getDiagramManager().getEditorDispatcher().showGrid();				
//			}
//		});

//    	add(inferenceItem);
//    	inferenceItem.setToolTipText("Automatically derive relationships such as material, derivation and meronymic relationships");    	    	
//    	inferenceItem.addMouseListener(new MouseAdapter()
//	    {
//    		@Override
//    		public void mousePressed(MouseEvent e) 
//    		{   
//    			frame.getDiagramManager().deriveRelations();
//    		}
//	    });
    	
    }	
}
