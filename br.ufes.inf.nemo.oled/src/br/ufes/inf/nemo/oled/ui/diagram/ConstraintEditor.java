package br.ufes.inf.nemo.oled.ui.diagram;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.draw.Diagram;
import br.ufes.inf.nemo.oled.model.OCLDocument;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.tocl.editor.TOCLEditorPanel;

public class ConstraintEditor extends TOCLEditorPanel implements Editor {

	private static final long serialVersionUID = 7380862047111803466L;
	public OCLDocument oclDoc;
	public JMenuItem parserMenuItem = new JMenuItem("Parse");
	
	public ConstraintEditor(final Component parent) {
		super(parent);
		
		addDocumentListener(new DocumentListener() {			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
			}		
			@Override
			public void insertUpdate(DocumentEvent arg0) {
			}			
			@Override
			public void changedUpdate(DocumentEvent arg0) {				
				((AppFrame)parent).getDiagramManager().saveProjectNeeded(true);				
			}
		});
		
		parserMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		
		JMenuItem openMenuItem = new JMenuItem("Open");
		JMenuItem saveMenuItem = new JMenuItem("Save As");		
		getPopupMenu().add(parserMenuItem);
		getPopupMenu().add(openMenuItem);
		getPopupMenu().add(saveMenuItem);
		
		saveMenuItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				((AppFrame)parent).getDiagramManager().exportOCL();
			}
		});
		openMenuItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				((AppFrame)parent).getDiagramManager().importOCL();
			}
		});
		parserMenuItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				((AppFrame)parent).getDiagramManager().parseConstraints(true);
			}
		});
	}
		
	public OCLDocument getOCLDocument() { return oclDoc; }
	
	public ConstraintEditor(Component parent, OCLDocument oclDoc) {
		this(parent);
		this.oclDoc = oclDoc;
		setText(oclDoc.getContent());
	}
	
	@Override
	public void dispose() {

	}

	@Override
	public boolean isSaveNeeded() {
		return false;
	}

	@Override
	public EditorNature getEditorNature() {
		return EditorNature.OCL;
	}

	@Override
	public Diagram getDiagram() {
		return null;
	}

	@Override
	public UmlProject getProject() {
		return null;
	}
}
