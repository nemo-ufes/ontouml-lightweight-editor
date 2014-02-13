package br.ufes.inf.nemo.oled.ui.diagram;

import org.eclipse.emf.common.util.EList;

import RefOntoUML.Comment;
import RefOntoUML.NamedElement;
import br.ufes.inf.nemo.oled.ui.dialog.BaseTableModel;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * This class implements a BaseTableModel for class RefOntoUML.Proprties
 * @author Antognoni Albuquerque
 * @version 1.0
 */
public class CommentTableModel extends BaseTableModel {
	
	private static final long serialVersionUID = 156864519388945910L;
	private NamedElement owner;
	//private EList<Comment> comments;
	
	public CommentTableModel(NamedElement owner)
	{
		super(new String[]{"Annotation"});
		//this.owner = owner;
		this.owner = owner;
		//comments = owner.getOwnedComment();
	}

	//@SuppressWarnings("unchecked")
	public EList<Comment> getEntries()
	{
		return owner.getOwnedComment();
	}
	
	/**
	 * Adds an entry (item) to the model.
	 * @param entry the entry to add
	 */
	public void addEntry(Object entry)
	{
		int size = owner.getOwnedComment().size();
		owner.getOwnedComment().add((Comment) entry);
		fireTableRowsInserted(size, size);
	}

	@Override
	public void moveUpEntry(int index) {
	}

	@Override
	public void moveDownEntry(int index) {
	}

	@Override
	public void removeEntryAt(int index) {
		owner.getOwnedComment().remove(index);
		int size = owner.getOwnedComment().size();
		fireTableRowsDeleted(size, size);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addEmptyEntry() {
		Comment cmt = ModelHelper.getFactory().createComment();
		addEntry(cmt);		
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(owner.getOwnedComment().size() > 0)
		{
			Comment cmt = owner.getOwnedComment().get(rowIndex);
			
			switch(columnIndex) {
				case 0: return cmt.getBody();
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Class<?> getColumnClass(int columnIndex) {
        if(owner.getOwnedComment().size() > 0)
		{
        	switch(columnIndex) {
				case 0: return String.class;
			}
		}
		return Object.class;
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Comment cmt = (Comment) owner.getOwnedComment().get(rowIndex);
		if(columnIndex == 0)
		{
			cmt.setBody((String) value);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) 
	{ 
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getRowCount() { return owner.getOwnedComment().size(); }

	/**
	 * {@inheritDoc}
	 */
	public int getColumnCount() { return columns.length; }

}
