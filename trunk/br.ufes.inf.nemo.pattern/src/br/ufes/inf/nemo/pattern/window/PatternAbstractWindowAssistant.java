package br.ufes.inf.nemo.pattern.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.pattern.window.selctionbox.ClassSelectionPanel;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;

public class PatternAbstractWindowAssistant extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8526414518990243355L;


	private ClassSelectionPanel classPanel;

	public PatternAbstractWindowAssistant(JFrame owner, final double x, final double y, ClassSelectionPanel panel, ImagePanel image) {
		super(owner, true);
		setTitle("Pattern use");
		setResizable(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		//Calculates the frame size
		int frameWidth = panel.getWidth()+image.getWidth()+20;
		int frameHeight = (panel.getHeight() > image.getHeight()?panel.getHeight():image.getHeight())+60;


		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		classPanel = panel;

		JPanel topPanel = new JPanel(new GridLayout(0, 2));
		
		topPanel.add(image);
		topPanel.add(classPanel);
		
		contentPane.add(topPanel,BorderLayout.CENTER);
		
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
		
		frameHeight += btnAddPattern.getHeight();
		setSize(frameWidth, frameHeight);
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int _x = (int) ((screen.getWidth() - getWidth()) /2);
		int _y = (int) ((screen.getHeight() -getHeight()) /2);
		
		setLocation(_x, _y);
	}
	
	public Fix getFix(){
		return classPanel.getFix();
	}

}
