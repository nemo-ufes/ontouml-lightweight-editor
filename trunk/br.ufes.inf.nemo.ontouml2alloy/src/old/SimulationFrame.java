package old;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.AbstractListModel;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;

import br.ufes.inf.nemo.ontouml2alloy.scenarios.ui.InstantiationPanel;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.ui.StoryPanel;

public class SimulationFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimulationFrame frame = new SimulationFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SimulationFrame() {
		setTitle("Simulation Preferences");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 851);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnSimulate = new JButton("Close");
		
		JButton button = new JButton("Simulate");
		
		JButton btnOpenAnalyzer = new JButton("Open Analyzer");
		
		JButton btnCheck = new JButton("Check");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("Output:");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
							.addGap(6))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnOpenAnalyzer)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCheck, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSimulate)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 370, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSimulate)
						.addComponent(button)
						.addComponent(btnCheck)
						.addComponent(btnOpenAnalyzer))
					.addContainerGap())
		);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("General", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Axioms", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("OCL", null, panel_2, null);
		
		StoryPanel panel_3 = new StoryPanel();
		tabbedPane.addTab("Story", null, panel_3, null);
		
		JScrollPane panel_4 = new JScrollPane();
		tabbedPane.addTab("Population", null, panel_4, null);
		
		PopulationPanel panel_9 = new PopulationPanel();
		panel_4.setViewportView(panel_9);
		
		JScrollPane panel_5 = new JScrollPane();
		tabbedPane.addTab("Extension", null, panel_5, null);
		
		ExtensionPanel extensionPanel = new ExtensionPanel();
		panel_5.setViewportView(extensionPanel);
		
		JScrollPane panel_8 = new JScrollPane();
		tabbedPane.addTab("Instantiation", null, panel_8, null);
		
		InstantiationPanel instantiationPanel = new InstantiationPanel();
		panel_8.setViewportView(instantiationPanel);
		
		AntiRigidityPanel panel_6 = new AntiRigidityPanel();
		tabbedPane.addTab("Anti-Rigidity", null, panel_6, null);
		
		AssociationPanel panel_7 = new AssociationPanel();
		tabbedPane.addTab("Association", null, panel_7, null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(SystemColor.info);
		scrollPane_1.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
	}
}
