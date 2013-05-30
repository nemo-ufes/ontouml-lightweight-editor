package br.ufes.inf.nemo.ontouml.editor.ui.diagram;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;


import br.ufes.inf.nemo.ontouml.editor.plugin.ApplicationHandler;
import br.ufes.inf.nemo.ontouml.editor.plugin.Editor;
import br.ufes.inf.nemo.ontouml.editor.plugin.EditorListener;
import br.ufes.inf.nemo.ontouml.editor.plugin.Tool;

import com.mxgraph.examples.swing.GraphEditor;
import com.mxgraph.examples.swing.editor.BasicGraphEditor;
import com.mxgraph.examples.swing.editor.EditorPalette;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

public class ClassDiagramEditor extends BasicGraphEditor implements Editor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClassDiagramEditor()
	{
		super("Example", new ClassDiagramGraphComponent(new mxGraph()
		{
			/**
			 * Allows expanding tables
			 */
			public boolean isCellFoldable(Object cell, boolean collapse)
			{
				return model.isVertex(cell);
			}
		})

		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * Disables folding icons.
			 */
			public ImageIcon getFoldingIcon(mxCellState state)
			{
				return null;
			}

		});
	
		// Creates a single shapes palette
		EditorPalette shapesPalette = insertPalette("Schema");
		
		graphOutline.setVisible(false);

		mxCell tableTemplate = new mxCell("New Table", new mxGeometry(0, 0, 200, 280), null);
		tableTemplate.getGeometry().setAlternateBounds(new mxRectangle(0, 0, 140, 25));
		tableTemplate.setVertex(true);
		
		shapesPalette.addTemplate("Table",new ImageIcon(GraphEditor.class.getResource("/com/mxgraph/examples/swing/images/rectangle.png")),tableTemplate);
		
		getGraphComponent().getGraph().setCellsResizable(false);
		getGraphComponent().setConnectable(true);
		getGraphComponent().getGraphHandler().setCloneEnabled(false);
		getGraphComponent().getGraphHandler().setImagePreview(false);
		
		
		// Prefers default JComponent event-handling before mxCellHandler handling
		//getGraphComponent().getGraphHandler().setKeepOnTop(false);

		mxGraph graph = getGraphComponent().getGraph();
		Object parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();
		
		try
		{
			mxCell v1 = (mxCell) graph.insertVertex(parent, null, "Customers", 20, 20, 200, 280);
			v1.getGeometry().setAlternateBounds(new mxRectangle(0, 0, 140, 25));
			
			mxCell v2 = (mxCell) graph.insertVertex(parent, null, "Orders", 280, 20, 200, 280);
			v2.getGeometry().setAlternateBounds(new mxRectangle(0, 0, 140, 25));
			
			graph.insertEdge(parent, null, "Edge", v1, v2);
		}
		finally
		{
			graph.getModel().endUpdate();
		}
	}
	
	
	@Override
	public void addEditorListener(EditorListener editorlistener) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean canRedo() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean canUndo() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<Class<? extends Tool>> getTools() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JComponent getUIComponent() {
		
//		mxGraphComponent graphComponent = new mxGraphComponent(graph);
//		return graphComponent;
		return getGraphComponent();
	}

	@Override
	public boolean isSaveNeeded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean redo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerApplicationHandler(ApplicationHandler applicationHandler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeEditorListener(EditorListener editorlistener) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean undo() {
		// TODO Auto-generated method stub
		return false;
	}

}
