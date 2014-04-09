package br.ufes.inf.nemo.meronymic_validation.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.checkers.HierarchyCycleChecker;
import br.ufes.inf.nemo.meronymic_validation.checkers.PreConditionChecker;
//import br.ufes.inf.nemo.oled.AppFrame;

public class PreConditionDialog extends JDialog {

	private static final long serialVersionUID = -7859965827033567316L;
	
//	private AppFrame frame;

	private static Color GREEN = new Color(50, 205, 50);
	private static Color RED = new Color(255, 0, 0);
	private static Color YELLOW = new Color(255, 165, 0);
	private static Color WHITE = new Color(255, 255, 255);
	
	private final JPanel contentPanel = new JPanel();
	private JButton btnNext;
	private JButton btnRun;
	private JButton btnStop;
	private JButton btnClose;
	private JTextField textProgress;
	private JLabel lblHierarchyCycles;
	private JLabel lblValidSpecializations;
	private JLabel lblValidIdentities;
	private JLabel lblAggregationKindsDefined;
	private JLabel lblWellFormedPartWhole;
	private JLabel lblPartWholeCycles;
	private JLabel lblHierarchyCycleResult;
	private JLabel lblValidSpecializationsResult;
	private JLabel lblValidIdentitiesResult;
	private JLabel lblAggregationKindResult;
	private JLabel lblWellFormedPartWholeResult;
	private JLabel lblPartWholeCyclesResult;
	private JProgressBar progressBar;
	private DefaultTableModel tableModel;
	private OntoUMLParser parser;
	private PreConditionChecker checker;
	
	private Thread updateUIThread;
	
	/**
	 * Create the dialog.
	 */
	public PreConditionDialog(OntoUMLParser parser/*AppFrame frame*/) {
		super();
//		super(frame);
//		this.frame = frame;
		this.parser = parser;
		checker = new PreConditionChecker(this.parser);
		
		setResizable(false);
		setTitle("Meronymic Transitivity Validation");
		setBounds(100, 100, 478, 478);
		getContentPane().setLayout(new GridLayout(1,1));
		
		int STD_X = 10;
		int STD_HEIGHT = 15;
		int STD_WIDTH = 145;
		int STD_RESULT_WIDTH = 58;
		getContentPane().add(contentPanel);
		
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contentPanel.setLayout(null);
		
		JLabel lblToValidateThe = new JLabel("To validate the transitivity of part-whole relations, the model must pass the following tests:");
		lblToValidateThe.setBounds(10, 10, 453, 15);
		contentPanel.add(lblToValidateThe);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(10, 32, 453, 145);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		lblHierarchyCycles = new JLabel("Hierarchy cycles");
		lblHierarchyCycles.setBounds(STD_X, 10, STD_WIDTH, STD_HEIGHT);
		panel.add(lblHierarchyCycles);
		
		lblValidSpecializations = new JLabel("Valid specializations");
		lblValidSpecializations.setBounds(STD_X, 32, STD_WIDTH, STD_HEIGHT);
		panel.add(lblValidSpecializations);
		
		lblValidIdentities = new JLabel("Valid identities");
		lblValidIdentities.setBounds(STD_X, 54, STD_WIDTH, STD_HEIGHT);
		panel.add(lblValidIdentities);
		
		lblAggregationKindsDefined = new JLabel("Aggregation Kinds defined");
		lblAggregationKindsDefined.setBounds(STD_X, 76, STD_WIDTH, STD_HEIGHT);
		panel.add(lblAggregationKindsDefined);
		
		lblWellFormedPartWhole = new JLabel("Well-formed part-whole relations");
		lblWellFormedPartWhole.setBounds(10, 98, 157, 15);
		panel.add(lblWellFormedPartWhole);
		
		lblPartWholeCycles = new JLabel("Part-whole cycles");
		lblPartWholeCycles.setBounds(STD_X, 120, STD_WIDTH, STD_HEIGHT);
		panel.add(lblPartWholeCycles);
		
		lblHierarchyCycleResult = new JLabel("");
		lblHierarchyCycleResult.setBounds(385, 10, STD_RESULT_WIDTH, STD_HEIGHT);
		panel.add(lblHierarchyCycleResult);
		
		lblValidSpecializationsResult = new JLabel("");
		lblValidSpecializationsResult.setBounds(385, 32, STD_RESULT_WIDTH, STD_HEIGHT);
		panel.add(lblValidSpecializationsResult);
		
		lblValidIdentitiesResult = new JLabel("");
		lblValidIdentitiesResult.setBounds(385, 54, STD_RESULT_WIDTH, STD_HEIGHT);
		panel.add(lblValidIdentitiesResult);
		
		lblAggregationKindResult = new JLabel("");
		lblAggregationKindResult.setBounds(385, 76, STD_RESULT_WIDTH, STD_HEIGHT);
		panel.add(lblAggregationKindResult);
		
		lblWellFormedPartWholeResult = new JLabel("");
		lblWellFormedPartWholeResult.setBounds(385, 98, STD_RESULT_WIDTH, STD_HEIGHT);
		panel.add(lblWellFormedPartWholeResult);
		
		lblPartWholeCyclesResult = new JLabel("");
		lblPartWholeCyclesResult.setBounds(385, 120, STD_RESULT_WIDTH, STD_HEIGHT);
		panel.add(lblPartWholeCyclesResult);
		
		JLabel lblResults = new JLabel("Errors found:");
		lblResults.setBounds(10, 188, 69, 15);
		contentPanel.add(lblResults);
		
		
		
		JTable errorTable = new JTable();
		errorTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
		errorTable.setFillsViewportHeight(true);
		
		tableModel = new DefaultTableModel(new Object[]{"#","Type","Description"},0);
	    errorTable.setModel(tableModel);
		
		JScrollPane scrollPane = new JScrollPane(errorTable);
		contentPanel.add(scrollPane);
		scrollPane.setBounds(10, 212, 453, 145);
		
		btnRun = new JButton("Run");
		btnRun.setBounds(197, 366, 60, 23);
		btnRun.addActionListener(actionRun);
		contentPanel.add(btnRun);
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(267, 366, 57, 23);
		contentPanel.add(btnStop);
		
		btnNext = new JButton("Next");
		btnNext.setBounds(334, 366, 60, 23);
		btnNext.setEnabled(false);
		contentPanel.add(btnNext);
		
		btnClose = new JButton("Close");
		btnClose.setBounds(404, 366, 59, 23);
		contentPanel.add(btnClose);
		
		JLabel lblProgress = new JLabel("Progress");
		lblProgress.setBounds(10, 397, 247, 15);
		contentPanel.add(lblProgress);
		
		textProgress = new JTextField();
		textProgress.setBackground(WHITE);
		textProgress.setEditable(false);
		textProgress.setBounds(10, 419, 326, 20);
		contentPanel.add(textProgress);
		textProgress.setColumns(10);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(346, 419, 117, 20);
		contentPanel.add(progressBar);
		
	}
	
	private ActionListener actionRun = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) {
			RunButtonActionPerformed(event);
		}
	};

	private void setFailedTest(JLabel currentLabel, ArrayList<JLabel> labelList){
		textProgress.setText("The model contains errors.");
		setLabelToFailed(currentLabel);
		setLabelListToAborted(labelList);
		btnNext.setEnabled(false);
	}
	
	public static void hideLabel(JLabel label){
		if(label==null)
			return;
		
		label.setText("");
		label.setVisible(false);
	}
	
	private void hideAllLabels() {
		lblHierarchyCycleResult.setVisible(false);
		lblValidSpecializationsResult.setVisible(false);
		lblValidIdentitiesResult.setVisible(false);
		lblAggregationKindResult.setVisible(false);
		lblWellFormedPartWholeResult.setVisible(false);
		lblPartWholeCyclesResult.setVisible(false);
	}
	
	public static void setLabelToPassed(JLabel label){
		if(label == null)
			return;
		
		label.setText("PASSED");
		label.setForeground(GREEN);
		label.setVisible(true);
	}
	
	public static void setLabelToFailed(JLabel label){
		if(label == null)
			return;
		
		label.setText("FAILED");
		label.setForeground(RED);
		label.setVisible(true);
	}
	
	public static void setLabelToAborted(JLabel label){
		if(label == null)
			return;
		
		label.setText("ABORTED");
		label.setForeground(YELLOW);
		label.setVisible(true);
	}
	
	private void setLabelListToAborted(ArrayList<JLabel> resultLabelList) {
		for (JLabel label : resultLabelList) {
			setLabelToAborted(label);
		}	
	}
	
	 /** Open the Dialog.
	 */
	public static void open (OntoUMLParser parser/*AppFrame parent*/)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			PreConditionDialog dialog = new PreConditionDialog( parser/*parent*/);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
//			dialog.setLocationRelativeTo(parent);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Identifying AntiPatterns...
	 * 
	 * @param event
	 */
	public void RunButtonActionPerformed(ActionEvent event)
	{
		try{
			ArrayList<JLabel> labelList = new ArrayList<JLabel>();
			labelList.add(lblHierarchyCycleResult);
			labelList.add(lblValidSpecializationsResult);
			labelList.add(lblValidIdentitiesResult);
			labelList.add(lblAggregationKindResult);
			labelList.add(lblWellFormedPartWholeResult);
			labelList.add(lblPartWholeCyclesResult);
			
			
			if (updateUIThread!=null) 
				updateUIThread.interrupt();
		
			updateUIThread = new Thread(new Runnable() {			
				@Override
				public void run() {
					SwingUtilities.invokeLater(new Runnable() {					
						@Override
						public void run() {
							hideAllLabels();
							textProgress.setText("Checking Hierachy Cycles...");
						}
					});
				}
			});
			updateUIThread.run();

			checker.getHierachyCycleChecker().check();
			
			labelList.remove(lblHierarchyCycleResult);
			if(checker.getHierachyCycleChecker().getCycles().size()>0){
				setFailedTest(lblHierarchyCycleResult,labelList);			
					for (int i = 1; i <= checker.getHierachyCycleChecker().getCycles().size(); i++) {
						String cycle = checker.getHierachyCycleChecker().getCycleStringById(i-1);
						String typeDescription = HierarchyCycleChecker.errorType();
						tableModel.addRow(new Object[]{i,cycle.toString(),typeDescription});
					}
					
					return;
				}
				setLabelToPassed(lblHierarchyCycleResult);
							
									
						}
					});
				}
			});

			
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
