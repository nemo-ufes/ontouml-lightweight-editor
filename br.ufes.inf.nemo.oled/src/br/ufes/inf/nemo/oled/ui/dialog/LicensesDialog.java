package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.ufes.inf.nemo.oled.ui.AppFrame;


/**
 * This Dialog is used to displays the Copyright Licenses.
 * 
 * @author John
 *
 */
public class LicensesDialog extends JDialog {

	private static final long serialVersionUID = 3853224908119790266L;
	
	private AppFrame frame;
	@SuppressWarnings("rawtypes")
	private JComboBox choices;
	private JTextArea textArea;
	private JButton btnClose;
	private JScrollPane scrollPane;
	
	/**
	 * Launch the Dialog.
	 */
	public static void open(AppFrame frame) 
	{
		try {
			
			LicensesDialog dialog = new LicensesDialog(frame);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(frame);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * Creates a License Dialog from the main frame application.
	 * 
	 * @param frame
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LicensesDialog (AppFrame frame)
	{	
		super(frame);
		
		this.frame = frame;
		
		choices = new JComboBox();
		choices.setModel(new DefaultComboBoxModel(new String[] {"Alloy", "Kodkod", "JavaCup", "SAT4J", "ZChaff", "MiniSat","AutoComplete","RSyntaxTextArea"}));
		choices.setPreferredSize(new Dimension(150, 20));
		choices.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{       
       			String value = (String)choices.getSelectedItem();
       			setLicense(value);
       		}
		});		
		
		JLabel labelSee = new JLabel("See copyrights: ");
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textArea.setEditable(false);
		textArea.setCaretPosition(0);
		
		scrollPane = new JScrollPane();		
		scrollPane.setPreferredSize(new Dimension(400, 200));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);		
		scrollPane.setViewportView(textArea);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			dispose();
       		}
		});
		
		Panel buttonsPanel = new Panel();
		buttonsPanel.add(btnClose);
		
		JPanel choicesPanel = new JPanel();		
		FlowLayout flowLayout = (FlowLayout) choicesPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);	
		choicesPanel.add(labelSee);		
		choicesPanel.add(BorderLayout.NORTH,choices);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		getContentPane().add(choicesPanel, BorderLayout.NORTH);
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);		
		
		setTitle("Licenses for OLED");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(600, 400));
		Image icon = new BufferedImage(1, 1,BufferedImage.TYPE_INT_ARGB_PRE);
		setIconImage(icon);
		
		choices.setSelectedIndex(1);		
		setLicense((String)choices.getSelectedItem());
		
		repaint();
		validate();
		pack();		
	}
	
	/**
	 * Set License Text to Text Area.
	 * 
	 * @param value
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public void setLicense(String value) 	
	{		
		try{
			
			InputStream is = LicensesDialog.class.getClassLoader().getResourceAsStream("resources/licenses/"+ value + ".txt");
			if(is == null) 
				is = new FileInputStream("src/resources/licenses/"+ value + ".txt");
			
			StringBuffer result = new StringBuffer();
			byte[] b = new byte[is.available()];
			is.read(b);
	        String text = new String(b);
			
			textArea.setText(text);
			textArea.setCaretPosition(0);
			
		}catch(IOException e){
			frame.showErrorMessageDialog("IO", e.getLocalizedMessage());
		}
	}
}
