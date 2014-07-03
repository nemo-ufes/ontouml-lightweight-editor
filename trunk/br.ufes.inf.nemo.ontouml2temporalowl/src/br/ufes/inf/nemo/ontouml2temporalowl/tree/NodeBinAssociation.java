package br.ufes.inf.nemo.ontouml2temporalowl.tree;

import java.util.LinkedList;
import java.util.List;

import br.ufes.inf.nemo.ontouml2temporalowl.auxiliary.MappingType;

import RefOntoUML.Association;
import RefOntoUML.Characterization;
import RefOntoUML.Class;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Property;

public class NodeBinAssociation {
	public Association myAssociation;
	Boolean existDep = false;
	Boolean invExistDep = false;
	public String mappingName = null;
	public List<String> listSuperAssociations = null;
	public List<Restriction> listRestrictions = null;
	Property sourceEnd, targetEnd;
	
	public NodeBinAssociation (Association a)
	{
		myAssociation = a;
		if (a instanceof Meronymic)
		{
			//originally the part is the range and whole is the domain
			//exchanging domain and range
			sourceEnd = myAssociation.getMemberEnd().get(1); 
			targetEnd = myAssociation.getMemberEnd().get(0);
		}
		else
		{
			sourceEnd = myAssociation.getMemberEnd().get(0); 
			targetEnd = myAssociation.getMemberEnd().get(1);
		}
		Boolean especDep = ((targetEnd.lowerBound() >= 1) && (targetEnd.isIsReadOnly()));
		Boolean invEspecDep = ((sourceEnd.lowerBound() >= 1) && (sourceEnd.isIsReadOnly()));
		existDep = (especDep && hasRigidDomain());
		invExistDep = (invEspecDep && hasRigidRange());
	}

	public NodeClass getDomain()
	{
		return TreeProcessor.getNode((Class) sourceEnd.getType());
	}
	
	public NodeClass getRange()
	{
		return TreeProcessor.getNode((Class) targetEnd.getType());
	}

	private Boolean hasRigidDomain()
	{
		return getDomain().isRigid();
	}
	
	private Boolean hasRigidRange()
	{
		return  getRange().isRigid();
	}	

	public void addSuperAssociation(String superAssociation)
	{
		if (superAssociation == null) return;
		if (listSuperAssociations == null)
			listSuperAssociations = new LinkedList<String>();
		listSuperAssociations.add(superAssociation);
	}
	
	//if it implies existential dependence
	public Boolean isOntFormalRelation()
	{
		return (existDep || invExistDep); 
	}
	

	public void setRestrictions(MappingType mtype)
	{
		Integer tmin = targetEnd.lowerBound(),
				tmax = targetEnd.upperBound(),
				smin = sourceEnd.lowerBound(),
				smax = sourceEnd.upperBound();
		NodeClass ndomain = getDomain(), 
			 nrange = getRange();
		String restrictedProperty = mappingName, //default
			   restrictedDomain, restrictedRange;
		List<String> restrictedList = new LinkedList<String>();
		Boolean addTS = false, inverseProp, equivRestr;
		
		if (myAssociation instanceof Characterization)
			restrictedProperty = "inheresIn";
		else if (myAssociation instanceof Mediation)
			restrictedProperty = "mediates";
		
		if ((mtype == MappingType.REIFICATION) ||
		   ((mtype == MappingType.WORM_VIEW_A2) && (existDep || invExistDep) ))
		{
			if (!targetEnd.isIsReadOnly())
				tmax = -1;
			if (!sourceEnd.isIsReadOnly())
				smax = -1;
		}

		addTS = ((mtype == MappingType.WORM_VIEW_A0) ||
				((mtype == MappingType.WORM_VIEW_A1) && !(existDep && invExistDep) ) ||
				((mtype == MappingType.WORM_VIEW_A2) && !(existDep || invExistDep) ) );

		// if both classes are rigid or 
		// if it is neither the case of an ic-level property of 4D-A2 nor reification
		// then the restriction are imposed over the original domain and range
		if ( (ndomain.isRigid() && nrange.isRigid()) ||
			 !(((mtype == MappingType.WORM_VIEW_A2) && (existDep || invExistDep)) ||
			   (mtype == MappingType.REIFICATION) ) ) 
		{
			//adding cardinality restriction over the domain class
			if ((tmin > 0) || (tmax > 0))
			{
				restrictedDomain = ndomain.getName(addTS); 
				restrictedRange = nrange.getName(addTS);
				inverseProp = false;
				equivRestr = false;
				addRestriction(restrictedDomain, restrictedProperty, restrictedRange, equivRestr, inverseProp, tmin, tmax);
				if (myAssociation instanceof Meronymic)
					if (!((Meronymic) myAssociation).isIsShareable())
						addRestriction(restrictedDomain, listSuperAssociations.get(0), restrictedRange, equivRestr, inverseProp, 0, 1);
			}
				
			//adding cardinality restriction over the range class
			if ((smin > 0) || (smax > 0))
			{
				restrictedDomain = nrange.getName(addTS); 
				restrictedRange = ndomain.getName(addTS);
				inverseProp = true;
				equivRestr = false;
				addRestriction(restrictedDomain, restrictedProperty, restrictedRange, equivRestr, inverseProp, smin, smax);
			}
		}
		//otherwise, some of them, domain or range, must be rigid
		//since it is either the ic-level of 4D-A2 view, and thus the existential dependence holds for some of them
		//or the reification view, and thus the property must imply existential dependence
		else //(dinamicView == DinamicView.REIFICATION) || (dinamicView == DinamicView.WORM_VIEW_A2))
		{
			if (!ndomain.isRigid() && (nrange.isRigid())) 
			{
				restrictedList = ndomain.getRigidParentsNames(addTS);
				
				if ((smin > 0) || (smax > 0))
				{
					restrictedDomain = nrange.getName(addTS); 
					inverseProp = true;
					equivRestr = false;
					addRestriction(restrictedDomain, restrictedProperty, restrictedList, equivRestr, inverseProp, smin, smax);
				}
			}
			else if (!nrange.isRigid() && (ndomain.isRigid()))
			{
				restrictedList = nrange.getRigidParentsNames(addTS);
				//we do not impose minimal card restr over the parents, since this relation must be optional to them
				if ((tmin > 0) || (tmax > 0))
				{
					restrictedDomain = ndomain.getName(addTS); 
					inverseProp = false;
					equivRestr = false;
					addRestriction(restrictedDomain, restrictedProperty, restrictedList, equivRestr, inverseProp, tmin, tmax);
				}
			}
			if (mtype == MappingType.REIFICATION) 
			{
				tmax = targetEnd.upperBound();
				smax = sourceEnd.upperBound();
				if (myAssociation instanceof MaterialAssociation)
					if (!nrange.isRigid() && !ndomain.isRigid())
					{
						restrictedProperty = "existentiallyDependentOfQua";
						inverseProp = false;
						equivRestr = false;
						if ((smin > 0) || (smax > 0))
						{
							restrictedDomain = nrange.getReifiedName();
							restrictedRange = ndomain.getReifiedName();
							addRestriction(restrictedDomain, restrictedProperty, restrictedRange, equivRestr, inverseProp, smin, smax);
						}
						if ((tmin > 0) || (tmax > 0))
						{
							restrictedDomain = ndomain.getReifiedName();
							restrictedRange = nrange.getReifiedName();
							addRestriction(restrictedDomain, restrictedProperty, restrictedRange, equivRestr, inverseProp, tmin, tmax);
						}
					}
				if (myAssociation instanceof Mediation) //always implies exist. dep.
					if (!nrange.isRigid())
					{
						restrictedProperty = "partOfRelator";
						restrictedDomain = nrange.getReifiedName();
						restrictedRange = ndomain.getName();
						inverseProp = false;
						equivRestr = false;
						addRestriction(restrictedDomain, restrictedProperty, restrictedRange, equivRestr, inverseProp, 1, 0);
						if ((tmin > 0) || (tmax > 0))
						{
							restrictedDomain = ndomain.getName(addTS); 
							restrictedRange = nrange.getReifiedName();
							inverseProp = true;
							addRestriction(restrictedDomain, restrictedProperty, restrictedRange, equivRestr, inverseProp, tmin, tmax);
						}
					}
			}
		}
		
	}

	public void addRestriction(String restrictedClass, String propertyName,
			String propertyClass, Boolean equivalent,
			Boolean inverse, Integer min, Integer max)
	{
		if (listRestrictions == null)
			listRestrictions = new LinkedList<Restriction>();
		List<String> listClasses = new LinkedList<String>();
		listClasses.add(propertyClass);
		Restriction r = new Restriction(restrictedClass, propertyName,
							listClasses, equivalent, inverse, min, max);
		listRestrictions.add(r);
	}

	public void addRestriction(String restrictedClass, String propertyName,
			List<String> listClasses, Boolean equivalent,
			Boolean inverse, Integer min, Integer max)
	{
		if (listRestrictions == null)
			listRestrictions = new LinkedList<Restriction>();
		Restriction r = new Restriction(restrictedClass, propertyName,
							listClasses, equivalent, inverse, min, max);
		listRestrictions.add(r);
	}
	
	public class Restriction {
		public String restrictedClass,
			   propertyName;
		public List<String> listClasses;
		public Boolean equivalent = false;
		public Boolean inverse = false;
		public Integer min, max;
		
		public Restriction (String restrictedClass, String propertyName,
							List<String> listClasses, Boolean equivalent,
							Boolean inverse, Integer min, Integer max)
		{
			this.restrictedClass = restrictedClass; 
			this.propertyName = propertyName;
			this.listClasses = listClasses;
			this.equivalent = equivalent;
			this.inverse = inverse;
			this.min = min;
			this.max = max;
		}
	}

	public List<String> getDomainList(MappingType mtype)
	{
		List<String> domainList = new LinkedList<String>();
		NodeClass domain = getDomain();
		if (mtype == MappingType.REIFICATION)
			domainList.add(domain.getReifiedName());
		else
		{
			Boolean addTS = ((mtype == MappingType.WORM_VIEW_A0) ||
					((mtype == MappingType.WORM_VIEW_A1) && !(existDep && invExistDep) ) ||
					((mtype == MappingType.WORM_VIEW_A2) && !(existDep || invExistDep) ) );
			domainList.add(domain.getName(addTS));
		}
		if (!domainList.isEmpty())
			return domainList;
		else
			return null;
	}
	
	public List<String> getRangeList(MappingType mtype)
	{
		List<String> rangeList = new LinkedList<String>();
		NodeClass range = getRange();
		if (mtype == MappingType.REIFICATION)
			rangeList.add(range.getReifiedName());
		else
		{
			Boolean addTS = ((mtype == MappingType.WORM_VIEW_A0) ||
					((mtype == MappingType.WORM_VIEW_A1) && !(existDep && invExistDep) ) ||
					((mtype == MappingType.WORM_VIEW_A2) && !(existDep || invExistDep) ) );
			rangeList.add(range.getName(addTS));
		}
			
		if (!rangeList.isEmpty())
			return rangeList;
		else
			return null;
	}
	
	public List<String> getSuperAssocList(MappingType mtype)
	{
		if (existDep)
			addSuperAssociation("existentiallyDependentOf");
		if (invExistDep)
			addSuperAssociation("invExistentiallyDependentOf");
		if ( (existDep && invExistDep && (mtype == MappingType.WORM_VIEW_A1)) ||
			((existDep || invExistDep) && (mtype == MappingType.WORM_VIEW_A2)))
			addSuperAssociation("objPropertyIC");
		else if ((mtype != null) && (mtype != MappingType.REIFICATION))
			addSuperAssociation("objPropertyTS");
		return listSuperAssociations;
	}
	
}
