package br.ufes.inf.nemo.instancevisualizer.gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.MenuComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import br.ufes.inf.nemo.instancevisualizer.apl.AplMainWindow;
import br.ufes.inf.nemo.instancevisualizer.graph.EdgeLegend;
import br.ufes.inf.nemo.instancevisualizer.graph.GraphManager;
import br.ufes.inf.nemo.instancevisualizer.graph.NodeLegend;
import br.ufes.inf.nemo.instancevisualizer.graph.NodeManager;
import br.ufes.inf.nemo.instancevisualizer.util.DialogUtil;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class LegendEditorMenu extends JPopupMenu {
	
	final private JLabel buttonLabel;
	final private JButton legendButton;
	final private GraphManager graphManager;
	final private NodeLegend nodeLegend;
	final private EdgeLegend edgeLegend;
	
	public LegendEditorMenu(final JLabel buttonLabel,
			final JButton legendButton, 
			final GraphManager graphManager, 
			final NodeLegend nodeLegend, 
			final EdgeLegend edgeLegend) 
					throws NoSuchMethodException, 
					SecurityException, 
					IllegalAccessException, 
					IllegalArgumentException, 
					InvocationTargetException {
		super();
		this.buttonLabel = buttonLabel;
		this.legendButton = legendButton;
		this.graphManager = graphManager;
		this.nodeLegend = nodeLegend;
		this.edgeLegend = edgeLegend;
		
		String[] choices = {"none$TODO", "plain$TODO", "image-scaled$TODO"};
		addChoiceMenu("Fill Mode", choices);
		
		JMenuItem mntmFillImage = new JMenuItem("Fill Image");
		mntmFillImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Font font = buttonLabel.getFont();
				Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize()+2);
				buttonLabel.setFont(boldFont);
				File imageFile = DialogUtil.fileDialog("Open", "."+File.separator, null, false);
				if(imageFile != null) {
					nodeLegend.setFillImage(imageFile.getAbsolutePath());
					legendButton.setIcon(new ImageIcon(imageFile.getAbsolutePath()));
					graphManager.update();
				}
				buttonLabel.setFont(font);
			}
		});
		add(mntmFillImage);
		
		String[] choicesShape = {"circle$The node or sprite is drawn as a single circle or oval (depending on the size property).",
				"box$The node or sprite is drawn as rectangle, whose dimensions are given by the size property.",
				"diamond$The node or sprite is drawn as a rhombus.",
				"cross$The node or sprite is drawn as a cross."};
		addChoiceMenu("Shape", choicesShape);
		
		addTextMenu("Size");
		
		String[] choicesSizeMode = {"normal$The size is taken from the size property.",
				"fit$The size of the element tries to fit its label. In this case the size property is ignored unless the element has no label."};
		addChoiceMenu("Size Mode", choicesSizeMode);
		
		String[] choicesStrokeMode = {"none$No outline will be drawn.",
		"plain$The shape outline will be drawn according to the stroke-color and the stroke-width properties."};
		addChoiceMenu("Stroke Mode", choicesStrokeMode);
		
		addTextMenu("Stroke Width");
		
		String[] choicesTextVisibilityMode = {"normal$The label is always printed",
				"hidden$The label is never printed.",
				"at-zoom$The label is printed only if the zoom equals the first value of text-visibility.",
				"under-zoom$The label is printed only if the zoom value is less than the first value of text-visibility.",
				"over-zoom$The label is printed only if the zoom value is larger than the first value of text-visibility.",
				"zoom-range$The label is printed only if the zoom value is comprised between the two first values of text-visibility.",
				"zooms$The label is printed only if the zoom value is one of the values of text-visibility."};
		addChoiceMenu("Text Visibility Mode", choicesTextVisibilityMode);
		
		addTextMenu("Text Visibility");
		
		addTextMenu("Text Offset");
		
		String[] choicesVisibilityMode = {"normal$The element is always visible.",
				"hidden$The element is not visible.",
				"at-zoom$The element is only visible when at the zoom level specified by visibility.",
				"under-zoom$The element is only visible when at a zoom level less than visibility.",
				"over-zoom$The element is only visible when at a zoom level greater than visibility.",
				"zoom-range$The element is only visible when the zoom level is between the two first values of visibility.",
				"zooms$The element is only visible when the zoom level is exactly one of the values in visibility."};
		addChoiceMenu("Visibility Mode", choicesVisibilityMode);
		
		addTextMenu("Visibility");
		
		addTextMenu("z Index");
			
	}
	
	private void addChoiceMenu(String title, String[] choices) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		JMenu mnFillMode = new JMenu(title);
		//String[] choices = {"none$TODO", "plain$TODO", "image-scaled$TODO"};
		String newTitle = "";
		for(char c : title.toCharArray()) {
			if(c != Character.valueOf(' ')) {
				newTitle += c;
			}
		}
		final String methodName = "get" + newTitle;
		final Method getMethod = nodeLegend.getClass().getMethod(methodName);
		String value = (String) getMethod.invoke(nodeLegend);
		
		for(String choice : choices) {
			int div = choice.indexOf('$');
			final String choiceTitle = choice.substring(0, div);
			String choiceTip = choice.substring(div+1);
			JMenuItem menuItem = new JMenuItem(choiceTitle);
			menuItem.setToolTipText(choiceTip);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Method setMethod;
						setMethod = nodeLegend.getClass().getMethod("s" + methodName.substring(1), String.class);
						setMethod.invoke(nodeLegend, choiceTitle);
						graphManager.update();
					} catch (NoSuchMethodException | SecurityException |
							IllegalAccessException | IllegalArgumentException |
							InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			if(getMethod.invoke(nodeLegend).equals(choiceTitle))
				menuItem.setIcon(new ImageIcon("./resources/green_check_mark.gif"));
			mnFillMode.add(menuItem);
		}
		add(mnFillMode);
	}
	
	private void addTextMenu(String title) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		JMenu size = new JMenu(title);
		String newTitle = "";
		for(char c : title.toCharArray()) {
			if(c != Character.valueOf(' ')) {
				newTitle += c;
			}
		}
		final String methodName = "get" + newTitle;
		final Method getMethod = nodeLegend.getClass().getMethod(methodName);
		String value = (String) getMethod.invoke(nodeLegend);
		final JTextField txtfld = new JTextField(value + "          ");
		txtfld.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					String text = txtfld.getText();
					int i;
					for(i=text.length()-1; i>=0; i--)
						if(text.charAt(i) != ' ')
							break;
					try {
						Method setMethod = nodeLegend.getClass().getMethod("s" + methodName.substring(1), String.class);
						setMethod.invoke(nodeLegend, txtfld.getText().substring(0, i+1));
						graphManager.update();
					} catch (IllegalAccessException | IllegalArgumentException |
							InvocationTargetException | NoSuchMethodException | SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		size.add(txtfld);
		add(size);
	}
}
