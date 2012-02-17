package br.ufes.inf.nemo.oled.ui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.ufes.inf.nemo.oled.draw.Diagram;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/

public class HTMLVisualizer extends JPanel implements Editor {

	private static final long serialVersionUID = -4750748112017027745L;
	private StructureDiagram diagram; 
	final BrowserCanvas browserCanvas = new BrowserCanvas();
	
	public HTMLVisualizer(StructureDiagram diagram) {
		super();
		initGUI();
		
		this.diagram = diagram;
	}
		
	public boolean loadPage(String url)
	{
		try {
			
			final String newUrl = url;
			if(browserCanvas.getBrowser() == null)
			{
			    SwingUtilities.invokeLater(new Runnable() {
				    	@Override
						public void run() {
							browserCanvas.connect();
							browserCanvas.getBrowser().getDisplay().asyncExec(new Runnable() {
							      @Override
							      public void run() {
							        browserCanvas.getBrowser().setUrl(newUrl);
							        browserCanvas.getBrowser().getShell().setFullScreen(true);
							        browserCanvas.getBrowser().getShell().pack();
							      }
							});
						}
				    }
			    );
			}
			else
			{
				
				browserCanvas.getBrowser().getDisplay().asyncExec(new Runnable() {
				      @Override
				      public void run() {
				        browserCanvas.getBrowser().setUrl(newUrl);
				        browserCanvas.getBrowser().getShell().setFullScreen(true);
				        browserCanvas.getBrowser().getShell().pack();
				      }
				});
			}

			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean loadLocalPage(String url)
	{
		return loadPage("file://" + url);
	}
	
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			
			browserCanvas.setPreferredSize(new Dimension(800, 600));
		    this.add(browserCanvas, BorderLayout.CENTER);
		    this.setVisible(true);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isSaveNeeded() {
		return false;
	}

	@Override
	public EditorNature getEditorNature() {
		return EditorNature.HTML;
	}

	@Override
	public Diagram getDiagram() {
		return diagram;
	}

	@Override
	public void dispose() {
		browserCanvas.disconnect();
	}
}
