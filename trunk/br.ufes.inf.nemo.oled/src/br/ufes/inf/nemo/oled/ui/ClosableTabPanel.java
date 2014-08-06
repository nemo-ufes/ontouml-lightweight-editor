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
package br.ufes.inf.nemo.oled.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicButtonUI;

import org.eclipse.emf.edit.provider.IDisposable;

import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.palette.ColorPalette;
import br.ufes.inf.nemo.oled.palette.ColorPalette.ThemeColor;
import br.ufes.inf.nemo.oled.popupmenu.TabPopupMenu;
import br.ufes.inf.nemo.oled.ui.diagram.ConstraintEditor;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditorWrapper;
import br.ufes.inf.nemo.oled.ui.diagram.Editor;
import br.ufes.inf.nemo.oled.ui.diagram.TextEditor;


/**
 * Internal class used to create closable tabs
 */
public class ClosableTabPanel extends JPanel {

	public static final long serialVersionUID = -9007086475434456589L;
	public final JTabbedPane pane;
	public JLabel label;
	public TabButton button;
	public boolean isTitleEditable = true;
	
	public JLabel getLabel() { return label; }
	
	public void setIcon()
	{
		//Includes Icon on the label		
		int i = pane.indexOfTabComponent(ClosableTabPanel.this);
		if (i != -1) {
			Component obj = (pane.getComponentAt(i));			
			if(obj instanceof DiagramEditorWrapper)
			{
				Icon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/x16/tree/diagram.png"));
				label.setIcon(icon);
				label.setIconTextGap(5);
				label.setHorizontalTextPosition(SwingConstants.RIGHT);
			}
			else if(obj instanceof ConstraintEditor)
			{
				Icon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/x16/text-editor.png"));
				label.setIcon(icon);
				label.setIconTextGap(5);
				label.setHorizontalTextPosition(SwingConstants.RIGHT);
			}
			else if(obj instanceof TextEditor)
			{
				Icon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/icons/x16/editor.png"));
				label.setIcon(icon);
				label.setIconTextGap(5);
				label.setHorizontalTextPosition(SwingConstants.RIGHT);
			}
		}
	}
	
	public ClosableTabPanel(final JTabbedPane pane, boolean isTitleEditable) {
		this(pane);
		this.isTitleEditable = isTitleEditable;
	}
	
	/**
	 * Constructor for the ClosableTab class.
	 * @param pane the parent {@link JTabbedPane}
	 */
	public ClosableTabPanel(final JTabbedPane pane) {
		//unset default FlowLayout' gaps
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		if (pane == null) {
			throw new NullPointerException("TabbedPane is null");
		}
		this.pane = pane;
		setOpaque(false);

		//make JLabel read titles from JTabbedPane
		label = new JLabel() {

			private static final long serialVersionUID = -5791363706451298026L;
			public String getText() {
				int i = pane.indexOfTabComponent(ClosableTabPanel.this);
				if (i != -1) {													
					return ((Editor) pane.getComponentAt(i)).isSaveNeeded() ? pane.getTitleAt(i).replace("*", "")+"*" : pane.getTitleAt(i);
				}
				return null;
			}
		};
		
		//set label editable through JTextField component
		label.addMouseListener(new MouseAdapter() 
        { 
            public void mouseClicked(MouseEvent e) 
            { 
                if (e.getClickCount() == 2) 
                {          
                	if(isTitleEditable){
                		int index = pane.indexOfTabComponent(ClosableTabPanel.this);
                		JTextField editor = getTextFieldComponent(index);  
                		pane.setTabComponentAt(index, editor);                                        
                		editor.requestFocus(); 
                		editor.selectAll();                     
                		if (editor.getPreferredSize().width < 100) editor.setPreferredSize(new Dimension(100, editor.getPreferredSize().height));
                	}
                } else if (SwingUtilities.isRightMouseButton(e)){
                	if(pane instanceof DiagramManager){                		
                		int index = pane.indexOfTabComponent(ClosableTabPanel.this);
                		Component comp = pane.getComponentAt(index);                		
                		TabPopupMenu popup = new TabPopupMenu(pane,comp);
                		popup.show(e.getComponent(),e.getX(),e.getY());
                	}
                } else { 
                    if (pane.getSelectedIndex() != pane.indexOfTabComponent(ClosableTabPanel.this)) pane.setSelectedIndex(pane.indexOfTabComponent(ClosableTabPanel.this)); 
                    pane.requestFocus(); 
                }                
            } 
        }); 
				
		add(label);
		//add more space between the label and the button
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		//tab button
		button = new TabButton(pane);
		add(button);
		//add more space to the top of the component
		setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
	}

	/**
	 * Editing the Tab Label.
	 */	
	private JTextField getTextFieldComponent(final int index) 
    { 
        final JTextField editor = new JTextField(); 
        editor.setText(label.getText());
        editor.addKeyListener(new KeyAdapter() 
        { 
            public void keyReleased(KeyEvent e) 
            { 
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE) 
                {                    
                    pane.setTitleAt(index, editor.getText());
                    pane.setTabComponentAt(index, ClosableTabPanel.this);
                    Editor currentEditor = ((DiagramManager)pane).getCurrentEditor();
                    if(currentEditor instanceof DiagramEditor) ((DiagramEditor)currentEditor).getDiagram().setName(editor.getText());
                    if(currentEditor instanceof ConstraintEditor)((ConstraintEditor)currentEditor).getOCLDocument().setName(editor.getText());
                    ((DiagramManager)pane).getFrame().getBrowserManager().getProjectBrowser().refreshTree();
                } 
            } 
        }); 
        editor.addFocusListener(new FocusAdapter() 
        { 
            public void focusLost(FocusEvent e) 
            {             	
            	pane.setTitleAt(index, editor.getText());
            	pane.setTabComponentAt(index, ClosableTabPanel.this); 
            	Editor currentEditor = ((DiagramManager)pane).getCurrentEditor();
                if(currentEditor instanceof DiagramEditor) ((DiagramEditor)currentEditor).getDiagram().setName(editor.getText());
                if(currentEditor instanceof ConstraintEditor)((ConstraintEditor)currentEditor).getOCLDocument().setName(editor.getText());
                ((DiagramManager)pane).getFrame().getBrowserManager().getProjectBrowser().refreshTree();
            } 
        }); 
        return editor; 
    } 
	
	/**
	 * Internal class representing the "x" button in the right side of the tab.
	 */
	private class TabButton extends JButton implements ActionListener {

		private static final long serialVersionUID = -3362039507300806289L;
		private JTabbedPane tabbedpane;
		
		/**
		 * Constructor for the TabButton class.
		 * */
		public TabButton(JTabbedPane pane) {
			int size = 17;
			setPreferredSize(new Dimension(size, size));
			setToolTipText("Close this tab"); //TODO Localize this
			this.tabbedpane = pane;
			//Make the button looks the same for all Laf's
			setUI(new BasicButtonUI());
			//Make it transparent
			setContentAreaFilled(false);
			//No need to be focusable
			setFocusable(false);
			//setBorder(BorderFactory.createEtchedBorder());
			setBorderPainted(false);
			//Making nice rollover effect
			//we use the same listener for all buttons
			addMouseListener(buttonMouseListener);
			setRolloverEnabled(true);
			//Close the proper tab by clicking the button
			addActionListener(this);
		}

		/**
		 * Handles the action events, closing the tab.
		 * @param e the triggered {@link ActionEvent}
		 * */
		public void actionPerformed(ActionEvent e) {
			if(tabbedpane instanceof DiagramManager){
				Editor editor = ((DiagramManager)tabbedpane).getCurrentEditor();
				if(editor!=null){
					if(editor instanceof DiagramEditorWrapper){
						if(((DiagramEditorWrapper) editor).getDiagramEditor().isSaveNeeded()) 
						{				
							int option = JOptionPane.showConfirmDialog(((DiagramManager)tabbedpane).getFrame(), "Your diagram has been modified. Save changes?","Save Project", JOptionPane.YES_NO_CANCEL_OPTION);
							if (option== JOptionPane.YES_OPTION) {((DiagramManager)tabbedpane).saveProject(); }
							else if (option==JOptionPane.CANCEL_OPTION) { return; }
						}
					}
					if(editor instanceof ConstraintEditor){
						if(((ConstraintEditor) editor).isSaveNeeded()) 
						{
							int option = JOptionPane.showConfirmDialog(((DiagramManager)tabbedpane).getFrame(), "Your constraints has been modified. Save changes?","Save Project", JOptionPane.YES_NO_CANCEL_OPTION);
							if (option== JOptionPane.YES_OPTION) {((DiagramManager)tabbedpane).saveProject(); }
							else if (option==JOptionPane.CANCEL_OPTION) { return; }
						}
					}
				}
			}
			removeTab();
		}

		public void removeTab()
		{
			int i = pane.indexOfTabComponent(ClosableTabPanel.this);
			if (i != -1) {

				IDisposable disposable = (IDisposable) pane.getComponentAt(i);
				if(disposable != null)
				{
					disposable.dispose();
				}
				pane.remove(i);
			}
		}
		
		/**
		 * Updates the UI. 
		 * */
		public void updateUI() {
			//we don't want to update UI for this button
		}

		/**
		 * Draws the cross
		 * @param g the {@link Graphics} object used in when rendering 
		 * */
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g.create();
			//shift the image for pressed buttons
			if (getModel().isPressed()) {
				g2.translate(1, 1);
			}
			g2.setStroke(new BasicStroke(1,BasicStroke.JOIN_ROUND,BasicStroke.CAP_ROUND));
			g2.setColor(Color.BLACK);
			if (getModel().isRollover()) {
				g2.setColor(ColorPalette.getInstance().getColor(ThemeColor.GREEN_DARK));
			}
			int delta = 5;

			g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
			g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);

			g2.dispose();
		}
	}

	private static final MouseListener buttonMouseListener = new MouseAdapter() {
		public void mouseEntered(MouseEvent e) {
			Component component = e.getComponent();
			if (component instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) component;
				button.setBorderPainted(true);
			}
		}

		public void mouseExited(MouseEvent e) {
			Component component = e.getComponent();
			if (component instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) component;
				button.setBorderPainted(false);
			}
		}
	};
}
