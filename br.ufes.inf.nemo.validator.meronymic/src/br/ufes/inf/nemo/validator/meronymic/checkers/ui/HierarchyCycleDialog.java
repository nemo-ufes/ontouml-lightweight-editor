package br.ufes.inf.nemo.validator.meronymic.checkers.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import RefOntoUML.Generalization;
import br.ufes.inf.nemo.validator.meronymic.checkers.HierarchyCycleError;

public class HierarchyCycleDialog extends JDialog {

	private static final long serialVersionUID = -7889331554525024052L;

	private final JPanel contentPanel = new JPanel();
	
	private JList<String> reverseList;
	private JList<String> removeList;
	private JList<String> gList;
	private JButton g2RemoveButton;
	private JButton remove2GButton;
	private JButton remove2ReverseButton;
	private JButton reverse2RemoveButton;
	private JTextPane question;
	private JTextPane warning;

	private GeneralizationListModel listModel, removeModel, reverseModel;
	
	private HierarchyCycleError error;
	
	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public HierarchyCycleDialog(JDialog parent, HierarchyCycleError error) {
		super(parent);
		this.error = error;
		
		setBounds(100, 100, 779, 507);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBorder(null);
		
		g2RemoveButton = new JButton(">>");
		g2RemoveButton.addActionListener(fromG2Remove);
		
		remove2GButton = new JButton("<<");
		remove2GButton.addActionListener(fromRemove2G);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_2.setBorder(null);
		
		remove2ReverseButton = new JButton(">>");
		remove2ReverseButton.addActionListener(fromRemove2Reverse);
		
		reverse2RemoveButton = new JButton("<<");
		reverse2RemoveButton.addActionListener(fromReverse2Remove);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_3.setBorder(null);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 704, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(g2RemoveButton)
						.addComponent(remove2GButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(remove2ReverseButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(reverse2RemoveButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 234, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(72)
							.addComponent(remove2ReverseButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(reverse2RemoveButton))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(72)
							.addComponent(g2RemoveButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(remove2GButton))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
							.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
							.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)))
					.addGap(11))
		);
		
		JLabel label_2 = new JLabel("Reverse Ends:");
		scrollPane_3.setColumnHeaderView(label_2);
		
		reverseModel = new GeneralizationListModel(error.getReverseList());
		reverseList = new JList<String>(reverseModel);
		scrollPane_3.setViewportView(reverseList);
		
		JLabel label_1 = new JLabel("Remove:");
		scrollPane_2.setColumnHeaderView(label_1);
		
		removeModel = new GeneralizationListModel(error.getRemoveList());
		removeList = new JList<String>(removeModel);
		scrollPane_2.setViewportView(removeList);
		
		JLabel label = new JLabel("Generalizations:");
		scrollPane_1.setColumnHeaderView(label);
		
		listModel = new GeneralizationListModel(error.getKeepList());
		gList = new JList<String>(listModel);
		scrollPane_1.setViewportView(gList);
		panel.setLayout(gl_panel);
		
		warning = new JTextPane();
		warning.setText("If none of the options provided are suitable, please close this window and fix your model manually.");
		warning.setForeground(new Color(255, 102, 51));
		warning.setEditable(false);
		warning.setBackground(UIManager.getColor("Button.background"));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
						.addComponent(warning, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(warning, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(0))
		);
		
		
		
		String message ="The construct Generalization is used to represent that a type (the child, connected to arrowless end of the line) is a subtype of another type (the parent, connected to the arrow end of the line). " +
				"Whenever two types are connected through a Generalization it means that every individual that instantiates the child type must also instantiate the parent type." +
				"\r\n\r\n" +
				"Your model contains a generalization cycle, which characterizes an error. The types in the cycle are supertypes and subtypes of themselves. " +
				"\r\n\r\n" +
				"To fix your model you may: reverse the end types or/and remove one or more generalizations. Please choose the actions bellow:";

		StyleContext context = new StyleContext();
		StyledDocument document = new DefaultStyledDocument(context);
		Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(style, StyleConstants.ALIGN_JUSTIFIED);
		
		try {
			document.insertString(document.getLength(), message, style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
				
		question = new JTextPane(document);
		question.setBackground(UIManager.getColor("menu"));
		question.setText(message);
		question.setEditable(false);
		scrollPane.setViewportView(question);
		
		
		contentPanel.setLayout(gl_contentPanel);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton saveButton = new JButton("Save");
				saveButton.addActionListener(saveAction);
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(cancelAction);
				buttonPane.add(cancelButton);
			}
		}
		
		JPanel titlePane = new JPanel();
		getContentPane().add(titlePane, BorderLayout.NORTH);
		
		JLabel title = new JLabel("Hierarchy Cycle");
		
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 16));
		title.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout gl_titlePane = new GroupLayout(titlePane);
		gl_titlePane.setHorizontalGroup(
			gl_titlePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titlePane.createSequentialGroup()
					.addContainerGap()
					.addComponent(title, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_titlePane.setVerticalGroup(
			gl_titlePane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_titlePane.createSequentialGroup()
					.addContainerGap()
					.addComponent(title)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		titlePane.setLayout(gl_titlePane);
	}
	
private ActionListener fromG2Remove = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int selected = gList.getSelectedIndex();
			
			if(selected==-1) return;
			
			Generalization g = listModel.removeAt(selected);
			removeModel.add(g);
		}
	};
	
	private ActionListener fromRemove2G = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int selected = removeList.getSelectedIndex();
			
			if(selected==-1) return;
			
			Generalization g = removeModel.removeAt(selected);
			listModel.add(g);
		}
	};
	
	private ActionListener fromReverse2Remove = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int selected = reverseList.getSelectedIndex();
			
			if(selected==-1) return;
			
			Generalization g = reverseModel.removeAt(selected);
			removeModel.add(g);
		}
	};
	
	private ActionListener fromRemove2Reverse = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int selected = removeList.getSelectedIndex();
			
			if(selected==-1) return;
			
			Generalization g = removeModel.removeAt(selected);
			reverseModel.add(g);
		}
	};
	
	private ActionListener saveAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			error.addAllToRemoveList(removeModel.getAll());
			error.addAllToKeepList(listModel.getAll());
			error.addAllToReverseList(reverseModel.getAll());
			HierarchyCycleDialog.this.dispose();
			
		}
	};
	
	private ActionListener cancelAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			HierarchyCycleDialog.this.dispose();
			
		}
	};
	

}
