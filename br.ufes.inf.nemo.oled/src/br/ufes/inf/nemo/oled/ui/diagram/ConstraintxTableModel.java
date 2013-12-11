package br.ufes.inf.nemo.oled.ui.diagram;

import java.util.ArrayList;
import java.util.List;

import RefOntoUML.Classifier;
import RefOntoUML.Constraintx;
import RefOntoUML.PackageableElement;
import RefOntoUML.StringExpression;
import RefOntoUML.impl.ConstraintxImpl;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * This class implements a BaseTableModel for holding the element's constraints
 *
 * @author Antognoni Albuquerque
 * @version 0.1
 */
public class ConstraintxTableModel extends BaseTableModel {
	
	private static final long serialVersionUID = 156864519388945910L;
	private Classifier owner;
	private RefOntoUML.Package model;
	private List<Constraintx> entries;
	
	public ConstraintxTableModel(Classifier owner, RefOntoUML.Package model)
	{
		super(new String[]{"Type", "Constraint", ""});
		this.owner = owner;
		this.model = model;
		entries = new ArrayList<Constraintx>();
		findOwnedConstraints();
	}
	
	//These are the types of constraintx added to the model 
	public String[] getConstrainxTypes()
	{
		return new String[]{"OCL", "OWL", "DOC"};
	}
	
	private void findOwnedConstraints()
	{
		if(model != null)
		{
			for (PackageableElement elm : model.getPackagedElement()) {
	
				if (elm instanceof ConstraintxImpl) {
					Constraintx cnx = (Constraintx) elm;
					if (cnx.getConstrainedElement().contains(owner))
						entries.add(cnx);
				}
			}
		}
	}
	
	/**
	 * Adds an entry.
	 * @param entry the entry to add
	 */
	@Override
	public void addEntry(Object entry) {
		if(entry instanceof ConstraintxImpl)
		{
			int size = entries.size();
			((Constraintx) entry).getConstrainedElement().add(owner);
			model.getPackagedElement().add((Constraintx) entry);
			findOwnedConstraints();
			fireTableRowsInserted(size, size);
		}
	}

	public void addEmptyEntry() {
		Constraintx cnx = ModelHelper.getFactory().createConstraintx();
		StringExpression expr = ModelHelper.getFactory().createStringExpression();
		expr.setSymbol("");
		cnx.setSpecification(expr);
		addEntry(cnx);
	}
	
	/**
	 * Returns the entries.
	 * @return the entries
	 */
	public List<Constraintx> getEntries() {
		return entries;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(entries.size() > 0)
		{
			Constraintx cnx = (Constraintx) entries.get(rowIndex);
			
			switch(columnIndex) {
				case 0: return cnx.getName();
				case 1: return ((StringExpression)cnx.getSpecification()).getSymbol();
				case 2: 
				{
					if(cnx.getName() != null)
					{
						//We only validate OCL and OWL constraints
						if(cnx.getName().toUpperCase().equals("OCL"))
							return validateOCLConstraint(((StringExpression)cnx.getSpecification()).getSymbol());
						
						if(cnx.getName().toUpperCase().equals("OWL"))
							return validateOWLConstraint(((StringExpression)cnx.getSpecification()).getSymbol());
					}
					
					return null;	
				}
			}
		}
		return null;
	} 

	/**
	 * {@inheritDoc}
	 */
	public Class<?> getColumnClass(int columnIndex) {
        if(entries.size() > 0)
		{
        	switch(columnIndex) {
				case 0: return String.class;
				case 1: return String.class;
				case 2: return Boolean.class;
			}
		}
		return Object.class;
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Constraintx cnx = (Constraintx) entries.get(rowIndex);
		if(columnIndex == 0)
		{
			cnx.setName((String) value);
			fireTableDataChanged();
		}
		else if(columnIndex == 1)
		{
			StringExpression expr = ModelHelper.getFactory().createStringExpression();
			expr.setSymbol((String) value);
			cnx.setSpecification(expr);
			fireTableDataChanged();
		}	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeEntryAt(int index) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) 
	{ 
		if(columnIndex < 2)
			return true;
		return false;
	}	
	
	private boolean validateOCLConstraint(String expr)
	{
		return false;
	}
	
	private boolean validateOWLConstraint(String expr)
	{
		return true;
	}
	
	@Override
	public int getRowCount() {
		return entries.size();
	}
	
	@Override
	public void moveUpEntry(int index) {
	}

	@Override
	public void moveDownEntry(int index) {
	}

}
