package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.AppFrame;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;

/**
 * 
 * @author John Guerson
 *
 */
public class AutoCompletionDialog extends JDialog {

	private static final long serialVersionUID = -251319551154959770L;
	
	private AppFrame frame;
	private UmlProject project;
	
	private final JPanel contentPanel = new JPanel();
	private JButton btnOk;
	private JButton btnCancel;
	private ButtonGroup group;
	private JRadioButton rbMandatory;
	private JRadioButton rbAllAncestors;
	private JRadioButton rbAllDescendants;
	private JRadioButton rbAllAncestorsDescendants;
	private JRadioButton rbAllAncestorsUntil;	
	
	/**
	 * Launch the Dialog.
	 */
	public static void open(AppFrame frame, UmlProject project) 
	{
		try {
			
			AutoCompletionDialog dialog = new AutoCompletionDialog(frame,project);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(frame);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Auto Selection Action performed.
	 * 
	 * @param event
	 */
	public void AutoSelectionActionPerformed(ActionEvent event)
	{
		if (rbAllAncestors.isSelected())
		{
			if (ProjectBrowser.getProjectBrowserFor(frame,project)==null) return;       		    	
		   	String msg = frame.getDiagramManager().autoCompleteSelection(OntoUMLParser.ALL_ANCESTORS,project);       	   				    	
	   		JOptionPane.showMessageDialog(
	   			frame,msg,"All ancestors",JOptionPane.INFORMATION_MESSAGE,
	   			new ImageIcon(AutoCompletionDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/completion36x36.png"))
	   		); 
		}
		if (rbAllAncestorsDescendants.isSelected())
		{
			if (ProjectBrowser.getProjectBrowserFor(frame,project)==null) return;       		    	
		   	String msg = frame.getDiagramManager().autoCompleteSelection(OntoUMLParser.COMPLETE_HIERARCHY,project);       	   				    	
	   		JOptionPane.showMessageDialog(
	   			frame,msg,"All ancestors and descendants",JOptionPane.INFORMATION_MESSAGE,
	   			new ImageIcon(AutoCompletionDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/completion36x36.png"))
	   		);        				
		}
		if (rbAllAncestorsUntil.isSelected())
		{
			if (ProjectBrowser.getProjectBrowserFor(frame,project)==null) return;       		    	
		   	String msg = frame.getDiagramManager().autoCompleteSelection(OntoUMLParser.SORTAL_ANCESTORS,project);       	   				    	
	   		JOptionPane.showMessageDialog(
	   			frame,msg,"All ancestors until a Substance Sortal",JOptionPane.INFORMATION_MESSAGE,
	   			new ImageIcon(AutoCompletionDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/completion36x36.png"))
	   		); 
		}
		if (rbAllDescendants.isSelected())
		{
			if (ProjectBrowser.getProjectBrowserFor(frame,project)==null) return;       		    	
		   	String msg = frame.getDiagramManager().autoCompleteSelection(OntoUMLParser.ALL_DESCENDANTS,project);       	   				    	
	   		JOptionPane.showMessageDialog(
	   			frame,msg,"All descendants",JOptionPane.INFORMATION_MESSAGE,
	   			new ImageIcon(AutoCompletionDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/completion36x36.png"))
	   		); 
		}
		if (rbMandatory.isSelected())
		{
			if (ProjectBrowser.getProjectBrowserFor(frame,project)==null) return;       		    	
		   	String msg = frame.getDiagramManager().autoCompleteSelection(OntoUMLParser.NO_HIERARCHY,project);       	   				    	
	   		JOptionPane.showMessageDialog(
	   			frame,msg,"Only mandatory dependencies",JOptionPane.INFORMATION_MESSAGE,
	   			new ImageIcon(AutoCompletionDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/completion36x36.png"))
	   		); 
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public AutoCompletionDialog(AppFrame frame, UmlProject project) 
	{
		super(frame);
		
		this.frame = frame;
		this.project = project;
		
		//setIconImage(Toolkit.getDefaultToolkit().getImage(AutoCompletionDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/completion36x36.png")));
		setTitle("Complete selection");
		setBounds(100, 100, 270, 258);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setPreferredSize(new Dimension(100, 170));
		
		rbMandatory = new JRadioButton("Only mandatory dependencies");		
		rbAllAncestors = new JRadioButton("All ancestors");		
		rbAllDescendants = new JRadioButton("All descendants");		
		rbAllAncestorsDescendants = new JRadioButton("All ancestors and descendants");		
		rbAllAncestorsUntil = new JRadioButton("All ancestors until a Substance Sortal");
		
		group = new ButtonGroup();
		group.add(rbMandatory);
		group.add(rbAllAncestors);
		group.add(rbAllDescendants);
		group.add(rbAllAncestorsDescendants);
		group.add(rbAllAncestorsUntil);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(rbAllAncestors, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
						.addComponent(rbMandatory, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
						.addComponent(rbAllDescendants, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
						.addComponent(rbAllAncestorsDescendants, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
						.addComponent(rbAllAncestorsUntil, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(20)
					.addComponent(rbMandatory)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rbAllAncestors)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rbAllDescendants)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rbAllAncestorsDescendants)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rbAllAncestorsUntil)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(50, 50));
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			dispose();
       			AutoSelectionActionPerformed(event);       			
       		}
		});
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			dispose();
       		}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(52)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(31, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk)
						.addComponent(btnCancel))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
}
