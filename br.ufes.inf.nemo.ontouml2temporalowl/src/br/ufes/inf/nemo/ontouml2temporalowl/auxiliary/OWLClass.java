package br.ufes.inf.nemo.ontouml2temporalowl.auxiliary;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import br.ufes.inf.nemo.ontouml2temporalowl.verbose.MainVerbose;
import br.ufes.inf.nemo.ontouml2temporalowl.verbose.OWLVerbose;

public class OWLClass {
	/***********************************************************
	 * General functions for dealing with the owl Classes for mapping
	 * 
	 * Main features: 
	 * @param name: the class name, 
	 * @param superClasses: list of super-classes
	 * @param completeClasses: list of groups of complete-classes
	 * @param disjointClasses: list of disjoint classes
	 * @param restrictions: list of restriction 
	 */
		public String name;
		public List<String> superClasses = null;
		public List<List<String>> completeClasses = null;
		public List<String> disjointClasses = null;
		public List<OWLRestriction> restrictions = null;
		
		/*************************************************************
	 	 * Procedures for creating an OWL Class */
		public OWLClass (String className, String superClass)
		{
			name = className;
			superClasses = new LinkedList<String>();
			
			if (superClass != null)
			{
				superClasses.add(superClass);
			}
		}

		public OWLClass (String className, List<String> superClassesNames, List<List<String>> completeClassesNames, List<String> disjointClassesNames)
		{
			name = className;
			superClasses = superClassesNames;
			disjointClasses = disjointClassesNames;
			completeClasses = completeClassesNames;
		}
		
		/*************************************************************
	 	 * Procedures for adding a super-class to an OWL Class */
		public void addSuperClass(String superClass)
		{
			if (superClass == null) return;
			if (superClasses == null)
				superClasses = new LinkedList<String>();
			superClasses.add(superClass);
		}

		public void addSuperClass(List<String> lsuperClasses)
		{
			if (lsuperClasses == null) return;
			if (superClasses == null)
				superClasses = lsuperClasses;
			else
				superClasses.addAll(lsuperClasses);
		}
		/*************************************************************
	 	 * Procedures for adding a disjoint-class to an OWL Class */
		public void addDisjointClass(String disjointClass)
		{
			if (disjointClass == null) return;			
			if (disjointClasses == null)
				disjointClasses = new LinkedList<String>();
			disjointClasses.add(disjointClass);
		}

		/*************************************************************
	 	 * Procedures for adding disjoint classes to an OWL Class */
		public void addDisjointClasses(String[] disjointClassesNames)
		{
			if (disjointClassesNames == null) return;
			if (disjointClasses == null)
				disjointClasses = Arrays.asList(disjointClassesNames);
			else
				for (String s : disjointClassesNames)
					disjointClasses.add(s);
		}

		/*************************************************************
	 	 * Procedures for adding a group of complete-classes to an OWL Class */
		public void addCompleteClasses(String[] completeClassesNames)
		{
			if (completeClassesNames == null) return;

			if (completeClasses == null)
				completeClasses = new LinkedList<List<String>>();

			completeClasses.add(Arrays.asList(completeClassesNames));
		}

		public void addCompleteClasses(List<String> completeClassesNames)
		{
			if (completeClassesNames == null) return;
			if (completeClassesNames.size() == 0) return;
			if (completeClasses == null)
				completeClasses = new LinkedList<List<String>>();

			completeClasses.add(completeClassesNames);
		}

		/*************************************************************
	 	 * Procedures for adding a restriction to an OWL Class */
		public void addRestriction(String OWLrestrType,
				String OWLpropertyName,
				String OWLpropertyClass,
				Boolean OWLinvProperty,
				String OWLmin, String OWLmax)
		{
			if ((OWLpropertyName == null) || (OWLpropertyClass == null)) return;

			if (restrictions == null)
				restrictions = new LinkedList<OWLRestriction>();

			OWLRestriction r = new OWLRestriction(OWLrestrType, OWLpropertyName,
					OWLpropertyClass, OWLinvProperty, OWLmin, OWLmax);
			
			restrictions.add(r); 
		}

		public void addRestriction(String OWLgeneralType,
				String OWLcombiningType,
				String OWLrestrType,
				List<OWLRestriction> OWLsubRestrictions,
				String OWLpropertyName,
				String OWLpropertyClass,
				Boolean OWLinvProperty,
				List<String> OWLclassNames, 
				String OWLmin, String OWLmax)
		{

			if (restrictions == null)
				restrictions = new LinkedList<OWLRestriction>();

			OWLRestriction r = new OWLRestriction(OWLgeneralType,
					OWLcombiningType, OWLrestrType, OWLsubRestrictions,
					OWLpropertyName, OWLpropertyClass, OWLinvProperty,
					OWLclassNames, OWLmin, OWLmax);
			
			restrictions.add(r); 
			
		}

		public void addEquivClassCardRestriction(String propName, List<String> propClasses, Boolean inverse, Integer min, Integer max)
		{
			if ((min >= 1) || (max >= 1))
			{
				String OWLrestrType; 
				// defining the type of restriction
				if (min == max)
					OWLrestrType = "exactly";
				else
					OWLrestrType = "min/max";
				
				if (propClasses != null)
					if (propClasses.size() > 0)
						addRestriction("equivalentClass", "union", OWLrestrType, null, propName, null, inverse, propClasses, "" + min, "" + max);
			}
		}
		
		public void addEquivClassCardRestriction(String propName, String propClass, Boolean inverse, Integer min, Integer max)
		{
			if ((min >= 1) || (max >= 1))
			{
				String OWLrestrType; 
				// defining the type of restriction
				if (min == max)
					OWLrestrType = "exactly";
				else
					OWLrestrType = "min/max";
				
				if (propClass != null)
					addRestriction("equivalentClass", "none", OWLrestrType, null, propName, propClass, inverse, null, "" + min, "" + max);
			}
		}

		public void addSubClassCardRestriction(String propName, List<String> propClasses, Boolean inverse, Integer min, Integer max)
		{
			if ((min >= 1) || (max >= 1))
			{
				String OWLrestrType; 
				// defining the type of restriction
				if (min == max)
					OWLrestrType = "exactly";
				else
					OWLrestrType = "min/max";
				
				if (propClasses != null)
					if (propClasses.size() > 0)
						addRestriction("subClass", "union", OWLrestrType, null, propName, null, inverse, propClasses, "" + min, "" + max);
			}
		}

		public void addSubClassCardRestriction(String propName, String propClass, Boolean inverse, Integer min, Integer max)
		{
			if ((min >= 1) || (max >= 1))
			{
				String OWLrestrType; 
				// defining the type of restriction
				if (min == max)
					OWLrestrType = "exactly";
				else
					OWLrestrType = "min/max";
				
				if (propClass != null)
					addRestriction(OWLrestrType, propName, propClass, inverse, "" + min, "" + max);
			}
		}

		/*************************************************************
	 	 * Procedures for printing an OWL Class */
		public String verbose()
		{
			String out = MainVerbose.header(name.replace(" ", "_")) +
						OWLVerbose.openClass(name.replace(" ", "_"));

			if (completeClasses != null)
				for (List<String> l : completeClasses)
				{
					out += OWLVerbose.openEquivalentClass() +
						OWLVerbose.openClass() +
						OWLVerbose.openUnionOf("Collection");

					for (String n : l)
						out += OWLVerbose.openCloseDescription(n.replace(" ", "_"));

					out += 	OWLVerbose.closeUnionOf() +
						OWLVerbose.closeClass() +
						OWLVerbose.closeEquivalentClass();
				}
			
			if (superClasses != null)
				for (String s: superClasses)
					out += 	OWLVerbose.openCloseSubClassOf(s.replace(" ", "_"));
			
			if (disjointClasses != null)
				for (String s: disjointClasses)
					out += OWLVerbose.openCloseDisjointWith(s.replace(" ", "_"));

			if (restrictions != null)
				for (OWLRestriction r: restrictions)
					out += r.verbose();
			
			out += OWLVerbose.closeClass() +
					MainVerbose.sectionBreak() ;
			return "\n" + out + "\n"; 
		}

		/***********************************************************
		 * Procedures for dealing with the owl Classes Restrictions
		 * 
		 */
		public class OWLRestriction
		{
			String generalType = "subClass"; // equivalentClass or subClass or none
			String combiningType = "none"; // union or intersection or none
			String restrType; // some, all, exactly, min/max
			List<OWLRestriction> subRestrictions = null; //in case of union or intersection
			String propertyName, propertyClass = null;
			Boolean invProperty = false;
			List<String> classNames = null; 
			String min = "0";
			String max = "-1"; //in case of exac or min/max
			
			/*************************************************************
		 	 * Procedures for creating an OWL Restriction */
			public OWLRestriction (	String restrType,
									String propertyName,
									String propertyClass,
									Boolean invProperty,
									String min, String max)
			{
				this.restrType = restrType;
				this.propertyName = propertyName;
				//if ((propertyClass != null) && (propertyClass.equals(""))) propertyClass = null;
				this.propertyClass = propertyClass;
				this.invProperty = invProperty; 
				if (restrType.equals("exactly") || restrType.equals("min/max"))
				{
					this.min = min;
					this.max = max; //in case of exac or min/max
				}
			}
			
			public OWLRestriction (	String generalType,
					String combiningType,
					String restrType,
					List<OWLRestriction> subRestrictions,
					String propertyName,
					String propertyClass,
					Boolean invProperty,
					List<String> classNames, 
					String min, String max)
			{
				this.generalType = generalType;
				this.combiningType = combiningType;
				this.restrType = restrType;
				if ((this.combiningType != null) && (this.combiningType.equals("none")))
					this.subRestrictions = subRestrictions;
				this.propertyName = propertyName;
				//if ((propertyClass != null) && (propertyClass.equals(""))) propertyClass = null;
				this.propertyClass = propertyClass;
				this.invProperty = invProperty; 
				this.classNames = classNames;
				if ((this.restrType != null) 
					&& (this.restrType.equals("exactly") || this.restrType.equals("min/max")))
				{
					this.min = min;
					this.max = max; //in case of exac or min/max
				}
			}

			
			/*************************************************************
		 	 * Procedures for printing an OWL Restriction */
			public String verbose()
			{
				String out = "";
				if (generalType.equals("equivalentClass"))
					out = OWLVerbose.openEquivalentClass();
				else if (generalType.equals("subClass"))
					out = OWLVerbose.openSubClassOf();
				//else // if (generalType == "none")
				
				// it is an onProperty restriction
				if (propertyName != null)
				{
					out += OWLVerbose.openRestriction();
					
					if (invProperty)
					{
					       out += OWLVerbose.openOnProperty() +
					    		   OWLVerbose.openObjectProperty() +
					    		   OWLVerbose.openCloseInverseOf(propertyName.replace(" ", "_")) +
					    		   OWLVerbose.closeObjectProperty() +
					    		   OWLVerbose.closeOnProperty();
					}
					else
						out += OWLVerbose.openCloseOnProperty(propertyName.replace(" ", "_"));

					// its an onProperty restriction for one class
					if ((propertyClass != null) ||
						 ( (classNames != null) && (classNames.size() == 1) ))
					{
						if (propertyClass == null) propertyClass = classNames.get(0);  
						if (restrType.equals("some"))
							out += OWLVerbose.openCloseSomeValuesFrom(propertyClass.replace(" ", "_"));
						else if (restrType.equals("only"))
							out += OWLVerbose.openCloseAllValuesFrom(propertyClass.replace(" ", "_"));
						else if (restrType.equals("exactly") || restrType.equals("min/max"))
							out += OWLVerbose.openCloseOnClass(propertyClass.replace(" ", "_"));
		
					}
					// its an onProperty restriction for many classes
					else  if (classNames != null)
					{
						if (restrType.equals("some"))
							out += OWLVerbose.openSomeValuesFrom();
						else if (restrType.equals("only"))
							out += OWLVerbose.openAllValuesFrom();

						out += OWLVerbose.openOnClass();
						out += OWLVerbose.openClass();
						
						if (combiningType.equals("union"))
							out += OWLVerbose.openUnionOf("Collection");
						else if (combiningType.equals("intersection"))
							out += OWLVerbose.openIntersectionOf("Collection");
						//else return; ??

						for (String s : classNames)
							out += OWLVerbose.openCloseDescription(s.replace(" ", "_"));
						
						if (combiningType.equals("union"))
							out += OWLVerbose.closeUnionOf();
						else if (combiningType.equals("intersection"))
							out += OWLVerbose.closeIntersectionOf();
						
						out += OWLVerbose.closeClass();
						out += OWLVerbose.closeOnClass();
						
						if (restrType.equals("some"))
							out += OWLVerbose.closeSomeValuesFrom();
						else if (restrType.equals("only"))
							out += OWLVerbose.closeAllValuesFrom();
					}
		            
					if (restrType.equals("exactly"))
						out += OWLVerbose.openCloseQualifiedCardinality(min);
					else
						if (!min.equals("0"))
							out += OWLVerbose.openCloseMinQualifiedCardinality(min);
						else if (!max.equals("-1"))
							out += OWLVerbose.openCloseMaxQualifiedCardinality(max);

					out += OWLVerbose.closeRestriction();

				}
				//FIXME: ver se vai ser usado
				// its not the case of onProperty restriction
/*				else 
				{
					if (combiningType.equals("union"))
					{
						out += OWLVerbose.openClass() +
								OWLVerbose.openUnionOf("Collection");
					}
					else if (combiningType.equals("intersection"))
					{
						out += OWLVerbose.openClass() +
							   OWLVerbose.openIntersectionOf("Collection");
					}

					if (subRestrictions != null)
					{
						//out += OWLVerbose.openRestriction();
						for (OWLRestriction sr : subRestrictions)
							out += sr.verbose();
						//out += OWLVerbose.closeRestriction();
					}

					if (classNames != null)
					{
						for (String s : classNames)
							out += OWLVerbose.openCloseDescription(s.replace(" ", "_"));
					}

					if (combiningType.equals("union"))
					{
						out += OWLVerbose.closeUnionOf() +
							   OWLVerbose.closeClass();
					}
					else if (combiningType.equals("intersection"))
					{
						out +=  OWLVerbose.closeIntersectionOf() +
								OWLVerbose.closeClass();
					}
				}
*/				

				if (generalType.equals("equivalentClass"))
					out += OWLVerbose.closeEquivalentClass();
				else if (generalType.equals("subClass"))
					out += OWLVerbose.closeSubClassOf();

				return out;
			}
		}
		
	}

