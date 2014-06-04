package br.ufes.inf.nemo.meronymic_validation.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.forbidden.ui.ForbiddenMemberOfPanel;

public class ForbiddenFrame extends JFrame {

	private static final long serialVersionUID = -5308326636264156703L;

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ForbiddenFrame(OntoUMLParser parser) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 427);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setVisible(true);
		
		ForbiddenMemberOfPanel panel = new ForbiddenMemberOfPanel(parser);
		contentPane.add(panel, BorderLayout.CENTER);
	}

}
