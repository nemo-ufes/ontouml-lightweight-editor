package br.ufes.inf.nemo.move.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.ufes.inf.nemo.move.util.ontoumlview.OntoUMLTreeNodeElem;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ClassPropertyDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboType;
	@SuppressWarnings("rawtypes")
	private JComboBox comboAbstract;
	@SuppressWarnings("rawtypes")
	private JComboBox comboExtensional;
	private JLabel lblAlias;
	private JTextField txtName;
	private JButton okButton;
	private JButton cancelButton;
	
	/**
	 * Launch the application.
	 */
	public static void open (OntoUMLTreeNodeElem elem) 
	{
		try {
			
			if (elem.getElement() instanceof RefOntoUML.Class)
			{
				ClassPropertyDialog dialog = new ClassPropertyDialog(elem);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ClassPropertyDialog (OntoUMLTreeNodeElem elem)
	{		
		this();

		if (elem.getElement() instanceof RefOntoUML.Class)
		{
			RefOntoUML.Classifier c = (RefOntoUML.Classifier)elem.getElement();
			txtName.setText(c.getName());
			lblAlias.setText("Alias: "+elem.getUniqueName()+" ");
			if (c.isIsAbstract()) comboAbstract.setSelectedIndex(2); else comboAbstract.setSelectedIndex(1);
			
			if (c instanceof RefOntoUML.Collective)
			{
				RefOntoUML.Collective col = (RefOntoUML.Collective)c;
				if(col.isIsExtensional()) comboExtensional.setSelectedIndex(2); else comboExtensional.setSelectedIndex(1);
			}
			String typename = elem.getTypeName().toLowerCase();
			
			if (typename.equals("kind")) comboType.setSelectedIndex(1);
			else if (typename.equals("collective")) comboType.setSelectedIndex(2);
			else if (typename.equals("quantity")) comboType.setSelectedIndex(3);
			else if (typename.equals("subkind")) comboType.setSelectedIndex(4);
			else if (typename.equals("category")) comboType.setSelectedIndex(5);
			else if (typename.equals("role")) comboType.setSelectedIndex(6);
			else if (typename.equals("phase")) comboType.setSelectedIndex(7);
			else if (typename.equals("mixin")) comboType.setSelectedIndex(8);
			else if (typename.equals("rolemixin")) comboType.setSelectedIndex(9);
			else if (typename.equals("relator")) comboType.setSelectedIndex(10);
			else if (typename.equals("mode")) comboType.setSelectedIndex(11);
			
			repaint();
			validate();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ClassPropertyDialog() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClassPropertyDialog.class.getResource("/resources/icon/ontouml/class.png")));
		setTitle("Class Properties");
				
		setBounds(100, 100, 359, 239);
		getContentPane().setLayout(new BorderLayout());
				
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblName = new JLabel("Name:");
		
		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.LEFT);
		txtName.setColumns(10);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setHorizontalAlignment(SwingConstants.LEFT);
		
		comboType = new JComboBox();
		comboType.setEnabled(false);
		comboType.setModel(new DefaultComboBoxModel(new String[] {"", "Kind", "Collective","Quantity","Subkind","Category","Role","Phase","Mixin","RoleMixin","Relator","Mode"}));
		
		lblAlias = new JLabel("");
		lblAlias.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblAlias.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Meta Attributes", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		{
			okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					dispose();
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAlias, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
							.addGap(16)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(comboType, 0, 262, Short.MAX_VALUE)
								.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))))
					.addGap(26))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAlias, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(19)
							.addComponent(okButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cancelButton))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(45))
		);
		
		JLabel lblIsabstract = new JLabel("isAbstract:");
		lblIsabstract.setHorizontalAlignment(SwingConstants.LEFT);
		
		comboAbstract = new JComboBox();
		comboAbstract.setEnabled(false);
		comboAbstract.setModel(new DefaultComboBoxModel(new String[] {"", "false", "true"}));
		
		JLabel lblIsextensional = new JLabel("isExtensional:");
		lblIsextensional.setHorizontalAlignment(SwingConstants.LEFT);
		
		comboExtensional = new JComboBox();
		comboExtensional.setEnabled(false);
		comboExtensional.setModel(new DefaultComboBoxModel(new String[] {"", "false", "true"}));	
	
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblIsextensional, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIsabstract, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(comboExtensional, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(comboAbstract, 0, 100, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIsabstract)
						.addComponent(comboAbstract, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIsextensional)
						.addComponent(comboExtensional, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		contentPanel.setLayout(gl_contentPanel);
		
	}
}
