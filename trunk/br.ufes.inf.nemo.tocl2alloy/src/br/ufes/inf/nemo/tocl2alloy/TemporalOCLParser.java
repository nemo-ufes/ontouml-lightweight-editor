package br.ufes.inf.nemo.tocl2alloy;

import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ocl.examples.pivot.OCL;
import org.eclipse.ocl.examples.pivot.ParserException;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;
import org.eclipse.ocl.examples.pivot.model.OCLstdlib;
import org.eclipse.ocl.examples.pivot.utilities.BaseResource;
import org.eclipse.ocl.examples.pivot.utilities.PivotEnvironment;
import org.eclipse.ocl.examples.pivot.utilities.PivotEnvironmentFactory;
import org.eclipse.ocl.examples.xtext.base.baseCST.AnnotationCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.AnnotationElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.AttributeCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ClassCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ClassifierCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.CollectionTypeRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ConstraintCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.DataTypeCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.DetailCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.DocumentationCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ElementRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.EnumerationCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.EnumerationLiteralCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ImportCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.LambdaTypeCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.LibraryCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ModelElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ModelElementRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.MultiplicityBoundsCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.MultiplicityStringCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.NamedElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.OperationCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.PackageCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ParameterCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.PathElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.PathNameCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.PivotableElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.PrimitiveTypeRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ReferenceCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.RootPackageCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.SpecificationCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.StructuralFeatureCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TemplateBindingCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TemplateParameterCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TemplateParameterSubstitutionCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TemplateSignatureCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TuplePartCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TupleTypeCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TypeParameterCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TypeRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TypedElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TypedRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TypedTypeRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.WildcardTypeRefCS;
import org.eclipse.ocl.examples.xtext.base.util.VisitableCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.BodyCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.ClassifierContextDeclCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.CompleteOCLDocumentCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.ContextConstraintCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.ContextDeclCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.ContextSpecificationCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.DefCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.DefFeatureCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.DefOperationCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.DefPropertyCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.DerCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.FeatureContextDeclCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.IncludeCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.InitCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.InvCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.OCLMessageArgCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.OperationContextDeclCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.PackageDeclarationCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.PathNameDeclCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.PostCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.PreCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.PropertyContextDeclCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.BinaryOperatorCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.BooleanLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.CollectionLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.CollectionLiteralPartCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.CollectionTypeCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.ConstructorExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.ConstructorPartCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.ContextCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.ExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.ExpSpecificationCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.IfExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.IndexExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.InfixExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.InvalidLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.InvocationExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.LetExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.LetVariableCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.LiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.NameExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.NavigatingArgCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.NavigationOperatorCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.NestedExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.NullLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.NumberLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.OperatorCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.PrefixExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.PrimitiveLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.SelfExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.StringLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.TupleLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.TupleLiteralPartCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.TypeLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.TypeNameExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.UnaryOperatorCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.UnlimitedNaturalLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.VariableCS;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;

import fr.supelec.temporalocl.TemporalOCLStandaloneSetup;
import fr.supelec.temporalocl.temporalOCLCST.AnyCallEventCS;
import fr.supelec.temporalocl.temporalOCLCST.AtomicEventCS;
import fr.supelec.temporalocl.temporalOCLCST.BecomesTrueEventCS;
import fr.supelec.temporalocl.temporalOCLCST.CallEventCS;
import fr.supelec.temporalocl.temporalOCLCST.EventCS;
import fr.supelec.temporalocl.temporalOCLCST.EventChainCS;
import fr.supelec.temporalocl.temporalOCLCST.TagCS;
import fr.supelec.temporalocl.temporalOCLCST.TempAbsenceCS;
import fr.supelec.temporalocl.temporalOCLCST.TempAfterCS;
import fr.supelec.temporalocl.temporalOCLCST.TempAfterUnlessCS;
import fr.supelec.temporalocl.temporalOCLCST.TempBeforeCS;
import fr.supelec.temporalocl.temporalOCLCST.TempBetweenCS;
import fr.supelec.temporalocl.temporalOCLCST.TempCS;
import fr.supelec.temporalocl.temporalOCLCST.TempExistenceCS;
import fr.supelec.temporalocl.temporalOCLCST.TempFollowingCS;
import fr.supelec.temporalocl.temporalOCLCST.TempGloballyCS;
import fr.supelec.temporalocl.temporalOCLCST.TempPatternCS;
import fr.supelec.temporalocl.temporalOCLCST.TempPrecedingCS;
import fr.supelec.temporalocl.temporalOCLCST.TempScopeCS;
import fr.supelec.temporalocl.temporalOCLCST.TempSpecificationCS;
import fr.supelec.temporalocl.temporalOCLCST.TempUniversalityCS;
import fr.supelec.temporalocl.temporalOCLCST.TempWhenCS;
import fr.supelec.temporalocl.temporalOCLCST.TemporalOCLDocumentCS;
import fr.supelec.temporalocl.util.AbstractTemporalOCLCSVisitor;

public class TemporalOCLParser {

	public static void main(String[]args) throws ParserException
	{		
		String toclPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.tocl2alloy\\model\\project.tocl";
		URI toclURI = URI.createFileURI(toclPath);
		
		String umlPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.tocl2alloy\\model\\project.uml";
		URI umlURI = URI.createFileURI(umlPath);
			    
		// Standard Library Configuration
		OCLstdlib.install();
				
		// Temporal OCL Configuration
		TemporalOCLStandaloneSetup.doSetup();		

		// UML Usage Configuration
		ResourceSet resourceSet = new ResourceSetImpl();
		UMLResourcesUtil.init(resourceSet);
		org.eclipse.ocl.examples.pivot.uml.UML2Pivot.initialize(resourceSet);
		
		// OCL Usage Configuration
		OCL.initialize(resourceSet);

		// URIs StandAlone Configuration
		org.eclipse.ocl.examples.domain.utilities.StandaloneProjectMap.getAdapter(resourceSet);
		
		// UML Model Load				
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);	
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);			
		Resource umlResource = resourceSet.getResource(umlURI,true);
		org.eclipse.uml2.uml.Package umlmodel = (org.eclipse.uml2.uml.Package) umlResource.getContents().get(0);
		umlResource.getResourceSet().getPackageRegistry().put(null,umlmodel);
		resourceSet.getResource(umlURI, true);
		
		// ### Missing Step: Transforming Uml to Pivot
		
		//OCL Facade for UML Model		
		PivotEnvironmentFactory environmentFactory = new PivotEnvironmentFactory(
				umlResource.getResourceSet().getPackageRegistry(),
				new MetaModelManager()
		);
	    PivotEnvironment env = environmentFactory.createEnvironment();
	    OCL ocl = OCL.newInstance(env);
			    
		//CS to Pivot
		BaseResource oclBaseResource = ocl.load(toclURI);
		ocl.cs2pivot(oclBaseResource);
		
		// Loading TOCL file		
		Resource toclResource = resourceSet.getResource(toclURI, true);
		TemporalOCLDocumentCS toclDocument = (TemporalOCLDocumentCS) toclResource.getContents().get(0);
		
		// collecting all rules of the TOCL file
		ArrayList<ContextConstraintCS>  tocl_rules = new  ArrayList<ContextConstraintCS>();
		
		
		
		// collecting constraints outside the package declaration
		for (ContextDeclCS cd : toclDocument.getContexts()) 
		{			 
			 tocl_rules.addAll(cd.getRules()); 
		}
		
		// collecting constraints inside the package declaration
		for (PackageDeclarationCS pd : toclDocument.getPackages())
		{
			for (ContextDeclCS cd : pd.getContexts()) 
			{ 							
				 tocl_rules.addAll(cd.getRules());
			}
		}
				
		// do something with constraints
		for (ContextConstraintCS tocl_prop : tocl_rules)
		{
			if (tocl_prop instanceof TempCS)
			{
				SpecificationCS spec = tocl_prop.getSpecification();
		
				System.out.println(spec);
								
			}else if (tocl_prop instanceof InvCS)
			{
				System.out.println(tocl_prop.getSpecification());
			}else if (tocl_prop instanceof DerCS)
			{
				System.out.println(tocl_prop.getSpecification());
			}
		}
		System.out.println("executed");
		
		Visitor visitor = new Visitor(toclDocument);
		String result = (String)visitor.visit(toclDocument);
		System.out.println(result);
	}
	
	static class Visitor extends AbstractTemporalOCLCSVisitor<Object, Object>
	{

		protected Visitor(Object context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Object visitAnyCallEventCS(AnyCallEventCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitAtomicEventCS(AtomicEventCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitBecomesTrueEventCS(BecomesTrueEventCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitCallEventCS(CallEventCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitEventCS(EventCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitEventChainCS(EventChainCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTagCS(TagCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTempAbsenceCS(TempAbsenceCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTempAfterCS(TempAfterCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTempAfterUnlessCS(TempAfterUnlessCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTempBeforeCS(TempBeforeCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTempBetweenCS(TempBetweenCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTempCS(TempCS arg0) {
			// TODO Auto-generated method stub
			System.out.println("isuhiuhiduh");
			return null;
		}

		@Override
		public Object visitTempExistenceCS(TempExistenceCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTempFollowingCS(TempFollowingCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTempGloballyCS(TempGloballyCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTempPatternCS(TempPatternCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTempPrecedingCS(TempPrecedingCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTempScopeCS(TempScopeCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTempSpecificationCS(TempSpecificationCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTempUniversalityCS(TempUniversalityCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTempWhenCS(TempWhenCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTemporalOCLDocumentCS(TemporalOCLDocumentCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitBodyCS(BodyCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitClassifierContextDeclCS(ClassifierContextDeclCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitCompleteOCLDocumentCS(CompleteOCLDocumentCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitContextConstraintCS(ContextConstraintCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitContextDeclCS(ContextDeclCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitContextSpecificationCS(ContextSpecificationCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitDefCS(DefCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitDefFeatureCS(DefFeatureCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitDefOperationCS(DefOperationCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitDefPropertyCS(DefPropertyCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitDerCS(DerCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitFeatureContextDeclCS(FeatureContextDeclCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitIncludeCS(IncludeCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitInitCS(InitCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitInvCS(InvCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitOCLMessageArgCS(OCLMessageArgCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitOperationContextDeclCS(OperationContextDeclCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitPackageDeclarationCS(PackageDeclarationCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitPathNameDeclCS(PathNameDeclCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitPostCS(PostCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitPreCS(PreCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitPropertyContextDeclCS(PropertyContextDeclCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitBinaryOperatorCS(BinaryOperatorCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitBooleanLiteralExpCS(BooleanLiteralExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitCollectionLiteralExpCS(CollectionLiteralExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitCollectionLiteralPartCS(CollectionLiteralPartCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitCollectionTypeCS(CollectionTypeCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitConstructorExpCS(ConstructorExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitConstructorPartCS(ConstructorPartCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitContextCS(ContextCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitExpCS(ExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitExpSpecificationCS(ExpSpecificationCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitIfExpCS(IfExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitIndexExpCS(IndexExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitInfixExpCS(InfixExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitInvalidLiteralExpCS(InvalidLiteralExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitInvocationExpCS(InvocationExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitLetExpCS(LetExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitLetVariableCS(LetVariableCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitLiteralExpCS(LiteralExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitNameExpCS(NameExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitNavigatingArgCS(NavigatingArgCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitNavigationOperatorCS(NavigationOperatorCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitNestedExpCS(NestedExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitNullLiteralExpCS(NullLiteralExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitNumberLiteralExpCS(NumberLiteralExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitOperatorCS(OperatorCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitPrefixExpCS(PrefixExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitPrimitiveLiteralExpCS(PrimitiveLiteralExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitSelfExpCS(SelfExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitStringLiteralExpCS(StringLiteralExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTupleLiteralExpCS(TupleLiteralExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTupleLiteralPartCS(TupleLiteralPartCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTypeLiteralExpCS(TypeLiteralExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTypeNameExpCS(TypeNameExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitUnaryOperatorCS(UnaryOperatorCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitUnlimitedNaturalLiteralExpCS(
				UnlimitedNaturalLiteralExpCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitVariableCS(VariableCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitAnnotationCS(AnnotationCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitAnnotationElementCS(AnnotationElementCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitAttributeCS(AttributeCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitClassCS(ClassCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitClassifierCS(ClassifierCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitCollectionTypeRefCS(CollectionTypeRefCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitConstraintCS(ConstraintCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitDataTypeCS(DataTypeCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitDetailCS(DetailCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitDocumentationCS(DocumentationCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitElementCS(ElementCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitElementRefCS(ElementRefCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitEnumerationCS(EnumerationCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitEnumerationLiteralCS(EnumerationLiteralCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitImportCS(ImportCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitLambdaTypeCS(LambdaTypeCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitLibraryCS(LibraryCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitModelElementCS(ModelElementCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitModelElementRefCS(ModelElementRefCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitMultiplicityBoundsCS(MultiplicityBoundsCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitMultiplicityStringCS(MultiplicityStringCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitNamedElementCS(NamedElementCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitOperationCS(OperationCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitPackageCS(PackageCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitParameterCS(ParameterCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitPathElementCS(PathElementCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitPathNameCS(PathNameCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitPivotableElementCS(PivotableElementCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitPrimitiveTypeRefCS(PrimitiveTypeRefCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitReferenceCS(ReferenceCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitRootPackageCS(RootPackageCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitSpecificationCS(SpecificationCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitStructuralFeatureCS(StructuralFeatureCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTemplateBindingCS(TemplateBindingCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTemplateParameterCS(TemplateParameterCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTemplateParameterSubstitutionCS(
				TemplateParameterSubstitutionCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTemplateSignatureCS(TemplateSignatureCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTuplePartCS(TuplePartCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTupleTypeCS(TupleTypeCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTypeParameterCS(TypeParameterCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTypeRefCS(TypeRefCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTypedElementCS(TypedElementCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTypedRefCS(TypedRefCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitTypedTypeRefCS(TypedTypeRefCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visitWildcardTypeRefCS(WildcardTypeRefCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object visiting(VisitableCS arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}

		
