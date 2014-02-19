package temp.old;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;

import br.ufes.inf.nemo.assistant.manager.ManagerNode;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Warning extends AbstractWindow{
	JLabel lbWarning = new JLabel("<WarningMenssage>");
	JButton btGoBack = new JButton("Go back");

	/**
	 * Create the application.
	 */
	public Warning() {
		instance = this;
		initialize();
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		this.setBounds(100, 100, 575, 330);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		lbWarning.setBounds(63, 34, 411, 167);
		this.getContentPane().add(lbWarning);
		
		btGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerNode.goBack(myNode);
				instance.killMySelf();
			}
		});
		btGoBack.setBounds(220, 203, 111, 25);
		this.getContentPane().add(btGoBack);
	}
	
	public void setWarning(String w){
		lbWarning.setText(w);
	}
}
