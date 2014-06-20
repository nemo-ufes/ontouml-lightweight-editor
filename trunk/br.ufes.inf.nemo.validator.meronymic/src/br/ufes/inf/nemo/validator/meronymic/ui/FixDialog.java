package br.ufes.inf.nemo.validator.meronymic.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import br.ufes.inf.nemo.validator.meronymic.MeronymicItem;

import java.awt.Component;

public class FixDialog<T extends MeronymicItem> extends JDialog {

	private static final long serialVersionUID = -7889331554525024052L;

	protected final JPanel contentPanel = new JPanel();
	
	private JPanel buttonPane;
	protected JButton saveButton;
	protected JButton cancelButton;
	protected JButton helpButton;
	protected JButton nextButton;
	protected JButton backButton;
	private JSeparator separator_1;
	private JLabel titleLabel;
	private JTextPane subTitleText;
	
	private FixDialog<?> next;
	private FixDialog<?> previous;
	protected T item;
	
	/**
	 * Create the dialog.
	 * @param parent 
	 * @param hasNextBack TODO
	 */
	public FixDialog(JDialog parent, T item, boolean hasNextBack) {
		super(parent);
		setTitle("Title");
		this.item = item;
		isDisposing = false;
		
		setBounds(100, 100, 624, 555);
		
		JPanel titlePane = new JPanel();
		titlePane.setBackground(Color.WHITE);
		
		titleLabel = new JLabel("Main Title");
		titleLabel.setBackground(Color.WHITE);
		
		titleLabel.setFont(new Font("Dialog", titleLabel.getFont().getStyle() | Font.BOLD, titleLabel.getFont().getSize() + 8));
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		buttonPane = new JPanel();
			
		saveButton = new JButton("Save");
		
		getRootPane().setDefaultButton(saveButton);
			
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(cancelAction);
			
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(titlePane, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
				.addComponent(contentPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
				.addComponent(buttonPane, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(titlePane, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(contentPanel, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
		);
		contentPanel.setLayout(null);
		
		separator_1 = new JSeparator();
		
		helpButton = new JButton("Help");
		helpButton.setIcon(new ImageIcon(FixDialog.class.getResource("/br/ufes/inf/nemo/meronymic_validation/resources/help.png")));
		
		nextButton = new JButton("Next >");
		nextButton.addActionListener(nextListener);
		nextButton.setEnabled(false);
		
		backButton = new JButton("< Back");
		backButton.addActionListener(backListener);
		backButton.setEnabled(false);
		
		if(!hasNextBack){
			nextButton.setVisible(false);
			backButton.setVisible(false);
		}
		
		GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
		gl_buttonPane.setHorizontalGroup(
			gl_buttonPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(helpButton)
					.addPreferredGap(ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
					.addComponent(backButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nextButton)
					.addGap(37)
					.addComponent(saveButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cancelButton)
					.addContainerGap())
				.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
		);
		gl_buttonPane.setVerticalGroup(
			gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addGap(2)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelButton)
						.addComponent(saveButton)
						.addComponent(helpButton)
						.addComponent(nextButton)
						.addComponent(backButton))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_buttonPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {saveButton, cancelButton, nextButton, backButton});
		buttonPane.setLayout(gl_buttonPane);
		
		subTitleText = new JTextPane();
		subTitleText.setEditable(false);
		subTitleText.setText("Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here... Subtitle goes here...");
		
		JSeparator separator = new JSeparator();
		GroupLayout gl_titlePane = new GroupLayout(titlePane);
		gl_titlePane.setHorizontalGroup(
			gl_titlePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titlePane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_titlePane.createParallelGroup(Alignment.TRAILING)
						.addComponent(subTitleText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
						.addComponent(titleLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
					.addContainerGap())
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
		);
		gl_titlePane.setVerticalGroup(
			gl_titlePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titlePane.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(subTitleText, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		titlePane.setLayout(gl_titlePane);
		getContentPane().setLayout(groupLayout);
	}
	
	
	
	
	
	public T getItem(){
		return item;
	}
	
	public void setMainTitle(String title){
		titleLabel.setText(title);
	}
	
	public void setSubtitle(String subtitle){
		subTitleText.setText(subtitle);
	}
	
	private ActionListener cancelAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			FixDialog.this.dispose();
			
		}
	};
	
	private ActionListener nextListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(next!=null){
				FixDialog.this.setVisible(false);
				next.setVisible(true);
			}
		}
	};
	
	private ActionListener backListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(previous!=null){
				FixDialog.this.setVisible(false);
				previous.setVisible(true);
			}
		}
	};

	private boolean isDisposing;
	
	public void setNextPage(FixDialog<?> next){
		this.next = next;
		nextButton.setEnabled(true);
		next.setPreviousPage(this);
		
	}
	
	private void setPreviousPage(FixDialog<?> previous){
		this.previous = previous;
		backButton.setEnabled(true);
	}
	
	public void setCanSave(boolean b){
		saveButton.setEnabled(b);
	}
	
	@Override
	public void dispose(){
		if(!isDisposing)
			isDisposing = true;
		else
			return;
		
		if(next!=null)
			next.dispose();
		if(previous!=null)
			previous.dispose();
		
		super.dispose();
	}
	
	
	
	
}
