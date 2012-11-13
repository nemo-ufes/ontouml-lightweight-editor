package br.ufes.inf.nemo.move.output;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.ufes.inf.nemo.move.ui.TheFrame;
import java.awt.Toolkit;

/**
 * @author John Guerson
 */

public class OutputView extends JDialog {

	private static final long serialVersionUID = 8004289020434445559L;
	
	@SuppressWarnings("unused")
	private OutputModel outputmodel;
	
	private TheFrame frame;
	
	private final JPanel contentPanel = new JPanel();	
	private JTextField textAlloy;
	private JTextField textUML;
	private JButton btnAlloy;
	private JButton btnUML;
	private JButton btnClose;
	private JButton btnSave;
	
	/**
	 * Constructor.
	 * 
	 * @param outputmodel
	 * @param frame
	 */
	public OutputView (OutputModel outputmodel, TheFrame frame)
	{
		this();
		
		this.outputmodel = outputmodel;
		this.frame = frame;
		
		setLocationRelativeTo(frame);
		setOutputModel(outputmodel);		
	}	
	
	/**
	 * Constructor.
	 */
	public OutputView ()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(OutputView.class.getResource("/resources/br/ufes/inf/nemo/move/out.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Output");
		setBounds(100, 100, 397, 244);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		textAlloy = new JTextField();
		textAlloy.setText("*.als");
		textAlloy.setBackground(Color.WHITE);
		textAlloy.setEditable(false);
		textAlloy.setColumns(10);
		
		textUML = new JTextField();
		textUML.setText("*.uml");
		textUML.setBackground(Color.WHITE);
		textUML.setEditable(false);
		textUML.setColumns(10);
		
		btnAlloy = new JButton("...");
		btnAlloy.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			String path = AlloyOutputActionPerformed(event);
       			textAlloy.setText(path);
       		}
       	});
		
		btnUML = new JButton("...");
		btnUML.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			String path = AlloyOutputActionPerformed(event);
       			textUML.setText(path);
       		}
       	});
		btnClose = new JButton("Cancel");
		btnClose.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			dispose();
       		}
       	});
		
		JLabel lblAlloySpecification = new JLabel("Alloy Specification:");		
		JLabel lblUmlModel = new JLabel("UML Model:");		
		
		btnSave = new JButton("Save");
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblUmlModel, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
						.addComponent(lblAlloySpecification, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(textAlloy, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
								.addComponent(textUML, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnAlloy, GroupLayout.PREFERRED_SIZE, 34, Short.MAX_VALUE)
								.addComponent(btnUML, GroupLayout.PREFERRED_SIZE, 34, Short.MAX_VALUE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(22)
					.addComponent(lblAlloySpecification)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textAlloy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAlloy))
					.addGap(16)
					.addComponent(lblUmlModel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUML)
						.addComponent(textUML, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnClose)
						.addComponent(btnSave))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	public TheFrame getTheFrame()
	{
		return frame;
	}
	
	public void setOutputModel (OutputModel outputmodel)
	{
		if (outputmodel.getAlloyPath()!=null)
			textAlloy.setText(outputmodel.getAlloyPath());
		else
			textAlloy.setText("*.als");
		
		if (outputmodel.getUMLPath()!=null)
			textUML.setText(outputmodel.getUMLPath());
		else
			textUML.setText("*.uml");
	}
	
	public String getAlloyTextPath()
	{
		return textAlloy.getText();
	}
	
	public String getUMLTextPath()
	{
		return textUML.getText();
	}
	
	public void addSaveOutputLocationsListener(ActionListener actionListener) 
	{
		 btnSave.addActionListener(actionListener);
	}
	
	/**	
	 * Setting Alloy Output... 
	 */
	public String AlloyOutputActionPerformed (ActionEvent arg0)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Changing Alloy Output...");
		FileNameExtensionFilter alsFilter = new FileNameExtensionFilter("Alloy Specification (*.als)", "als");
		fileChooser.addChoosableFileFilter(alsFilter);
		fileChooser.setFileFilter(alsFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == alsFilter) 
			{					
				return fileChooser.getSelectedFile().getPath();
			}
		}
		return null;
	}
	
	/**	
	 * Setting UML Output... 
	 */
	public String UMLOutputActionPerformed (ActionEvent arg0)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Changing UML Output...");
		FileNameExtensionFilter umlFilter = new FileNameExtensionFilter("UML Model (*.uml)", "uml");
		fileChooser.addChoosableFileFilter(umlFilter);
		fileChooser.setFileFilter(umlFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == umlFilter) 
			{
				return fileChooser.getSelectedFile().getPath();
			}
		}
		return null;
	}
}
