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
package br.ufes.inf.nemo.oled.dialog.properties;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Comment;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.draw.DiagramElement;

/**
 * @author John Guerson
 */
public class CommentsEditionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private DiagramElement diagramElement;
	private Classifier element;
	private DiagramManager diagramManager;
	@SuppressWarnings("unused")
	private JFrame parent;
	private JTextArea descriptionText;
	private JScrollPane scrollPaneText;
	@SuppressWarnings("rawtypes")
	private JComboBox commentCombo;
	private JButton btnCreate;		
	private JButton btnDelete;
	private JButton btnSave;
	
	@SuppressWarnings({ "rawtypes" })
	public CommentsEditionPanel(DiagramManager diagramManager, DiagramElement diagramElement, Classifier element) 
	{
		setBorder(null);
		this.diagramManager = diagramManager;
		this.diagramElement =diagramElement;
		this.element = element;
		
		commentCombo = new JComboBox();
		commentCombo.setFocusable(false);
		//		((JLabel)commentCombo.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		//		commentCombo.addItemListener(new ItemListener(){
		//		    @Override
		//		    public void itemStateChanged(ItemEvent event) {
		//		       if (event.getStateChange() == ItemEvent.SELECTED) {		          
		//		    	   Object item = event.getItem();
		//		          		          
		//		           // do something with object
		//		    	   
		//		       }
		//		    }
		//		});
						
		commentCombo.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				CommentElement ce = (CommentElement) commentCombo.getSelectedItem();
				if(ce!=null) descriptionText.setText(ce.getComment().getBody());
			}
		});
		
		btnCreate = new JButton("");
		btnCreate.setToolTipText("Add a new comment to this class");
		btnCreate.setIcon(new ImageIcon(CommentsEditionPanel.class.getResource("/resources/icons/x16/new.png")));
		btnCreate.setFocusable(false);
		btnCreate.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createCommentActionPerformed();				
			}
		});
		
		btnSave = new JButton("");
		btnSave.setFocusable(false);
		btnSave.setToolTipText("Save selected comment");
		btnSave.setIcon(new ImageIcon(CommentsEditionPanel.class.getResource("/resources/icons/x16/disk.png")));
		btnSave.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveCommentActionPerformed();
			}
		});
		
		btnDelete = new JButton("");
		btnDelete.setFocusable(false);
		btnDelete.setToolTipText("Delete seletected comment");
		btnDelete.setIcon(new ImageIcon(CommentsEditionPanel.class.getResource("/resources/icons/x16/cross.png")));
		btnDelete.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteCommentActionPerformed();
			}
		});
		
		JLabel lblComment = new JLabel("Comment:");
		
		descriptionText = new JTextArea();	
		descriptionText.setToolTipText("Click here to start writing your comment");
		
		scrollPaneText = new JScrollPane();
		scrollPaneText.setToolTipText("Click here to start writing your comment");
		scrollPaneText.setViewportView(descriptionText);
		
		JLabel lblNewLabel = new JLabel("Description:");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblComment, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(scrollPaneText, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
							.addComponent(commentCombo, 0, 301, Short.MAX_VALUE)))
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(commentCombo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblComment))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPaneText, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(165))))
		);
		this.setLayout(groupLayout);		
		
		setInitialData();
		
		setSize(new Dimension(400, 218));
	}
	
	/** Private Class: Comment Element */
	private class CommentElement 
	{
		RefOntoUML.Comment c;
		public CommentElement(RefOntoUML.Comment c){
			this.c = c;
		}
		@Override
		public String toString(){
			String result = new String();
			if(c.getBody().length()>30)
				result += c.getBody().substring(0,30) + " (...)";
			else 
				result += c.getBody();			
			return result;
		}
		public Comment getComment(){
			return c;
		}
	}
	
	private void enableCommentArea(boolean value)
	{
		//descriptionText.setEnabled(value);
		//scrollPaneText.setEnabled(value);
		commentCombo.setEnabled(value);
		//lblSelectedComment.setEnabled(value);
		//if (!value) descriptionText.setBackground(UIManager.getColor("Panel.background"));
		//else descriptionText.setBackground(Color.WHITE);
		btnSave.setEnabled(value);
		btnDelete.setEnabled(value);
	}

	@SuppressWarnings("unchecked")
	public void setInitialData()
	{		
		if (commentCombo.getItemCount()>0) commentCombo.removeAllItems();
		
		for(Comment c: element.getOwnedComment()){			
			commentCombo.addItem(new CommentElement(c));			
		}		
		if (commentCombo.getItemCount()>0) {
			commentCombo.setSelectedIndex(0);
			descriptionText.setText(((CommentElement)commentCombo.getSelectedItem()).getComment().getBody()+"\n\n");			
		}else{
			enableCommentArea(false);
		}
	}

	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}

	@SuppressWarnings("unchecked")
	public void createCommentActionPerformed()
	{
		Comment c = diagramManager.getElementFactory().createComment();
		c.setBody("This is an empty comment...");
		CommentElement ce = new CommentElement(c);		
		commentCombo.addItem(ce);
		descriptionText.setText(c.getBody());
		commentCombo.setSelectedIndex(commentCombo.getItemCount()-1);
		enableCommentArea(true);
	}

	public void saveCommentActionPerformed()
	{
		Comment c = ((CommentElement)commentCombo.getSelectedItem()).getComment();
		c.setBody(descriptionText.getText());	
		commentCombo.repaint();
		commentCombo.validate();
	}

	public void deleteCommentActionPerformed()
	{		
		commentCombo.removeItem(commentCombo.getSelectedItem());
		commentCombo.invalidate();	
		descriptionText.setText("");
		if (commentCombo.getItemCount()>0) {
			enableCommentArea(true);
		}else{
			enableCommentArea(false);
		}
	}

	public ArrayList<Comment> getComments()
	{
		ArrayList<Comment> result = new ArrayList<Comment>();
		for(int i=0; i<commentCombo.getItemCount();i++){
			CommentElement ce = (CommentElement)commentCombo.getItemAt(i);
			if (ce!=null) result.add(ce.getComment());
		}
		return result;
	}

	public void transferCommentsData()
	{
		// added
		ArrayList<Comment> toBeAdded = new ArrayList<Comment>();
		for(Comment c: getComments()){
			if (!element.getOwnedComment().contains(c)){				
				toBeAdded.add(c);
			}
		}
		for(Comment cmt: toBeAdded) { diagramManager.addComment(cmt, element); } 
			
		//removed
		ArrayList<Comment> toBeDeleted = new ArrayList<Comment>();
		for(Comment c: element.getOwnedComment()){
			if (!getComments().contains(c)){
				toBeDeleted.add(c);
			}
		}
		for(Comment cmt: toBeDeleted) { diagramManager.deleteFromOLED(cmt,false); }	
	}
}
