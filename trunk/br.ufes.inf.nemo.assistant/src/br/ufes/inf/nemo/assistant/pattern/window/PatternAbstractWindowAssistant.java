package br.ufes.inf.nemo.assistant.pattern.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.assistant.pattern.window.selctionbox.ClassSelectionPanel;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;

public class PatternAbstractWindowAssistant extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8526414518990243355L;

	private JPanel contentPane;

	private ImagePanel imagePanel;
	private ClassSelectionPanel classPanel;
	
	public PatternAbstractWindowAssistant(JFrame owner, final double x, final double y, ClassSelectionPanel panel, ImagePanel image) {
		super(owner, true);
		setTitle("Pattern use");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 926, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0,0));
		
		imagePanel = image;
		//imagePanel.setPreferredSize(new Dimension(458, 299));
		contentPane.add(imagePanel,BorderLayout.CENTER);

		classPanel = panel;
		
		JScrollPane scroll = new JScrollPane(classPanel);
		scroll.setBorder(null);
		scroll.getVerticalScrollBar().setUnitIncrement(10);
		scroll.getHorizontalScrollBar().setUnitIncrement(10);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(422,265));
		//scroll.setViewportView(classPanel);
		
		//classPanel.setBounds(478, 10, 422, 265);
		contentPane.add(scroll,BorderLayout.EAST);
		
		JPanel btnPanel = new JPanel();
		contentPane.add(btnPanel,BorderLayout.SOUTH);
		
		JButton btnAddPattern = new JButton("Add Pattern");
		btnAddPattern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				classPanel.getRunPattern(x, y);
				dispose();
			}
		});
		btnAddPattern.setBounds(672, 284, 117, 23);
		btnPanel.add(btnAddPattern);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(561, 284, 89, 23);
		btnPanel.add(btnCancel);
	}
	public Fix getFix(){
		return classPanel.getFix();
	}

}
