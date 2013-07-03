package gui;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Robot;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JCheckBoxMenuItem;
import javax.xml.parsers.ParserConfigurationException;

import org.graphstream.ui.swingViewer.util.Camera;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.ViewerListener;
import org.graphstream.ui.swingViewer.ViewerPipe;
import org.xml.sax.SAXException;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import obj.*;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame {

	//protected boolean loop = true;
	private Main main;
	private JPanel contentPane;
	private XMLFile xmlFile;
	private XGraph xGraph;
	private int worldCounter;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JCheckBoxMenuItem chckbxmntmNewCheckItem;
	private Dimension d1, d2;
	private boolean mode;
	private boolean popOut;
	private JDialog popOutWindow;
	private JTabbedPane tabbedPane;
	
	/**
	 * Create the frame.
	 * @param mainVar the main class
	 * @throws InterruptedException 
	 */
	public MainWindow(Main mainVar) throws InterruptedException {
		setTitle("Instance Visualizer");
		mode = false;
		popOut = false;
		popOutWindow = null;
		main = mainVar;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640+320, 360+180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setMinimumSize(new Dimension(960, 540));
		
		setExtendedState(MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		/*
		JMenuItem mntmOpen = new JMenuItem("Open .xml/.refontouml...");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				callOpenXML();
				System.out.println(xGraph);
				setScrollPanes();
				*//*
				try {
					new Main(false);
				} catch (HeadlessException | InterruptedException e) {
					// Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnFile.add(mntmOpen);
		*/
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Zoom +");
		mntmNewMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_EQUALS, 0));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Camera cam = xGraph.getSelectedView().getCamera();
				cam.setViewPercent(cam.getViewPercent() - 0.1);
			}
		});
		mnView.add(mntmNewMenuItem);
		
		JMenuItem mntmZoom = new JMenuItem("Zoom -");
		mntmZoom.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_MINUS, 0));
		mntmZoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Camera cam = xGraph.getSelectedView().getCamera();
				cam.setViewPercent(cam.getViewPercent() + 0.1);
			}
		});
		mnView.add(mntmZoom);
		
		JSeparator separator = new JSeparator();
		mnView.add(separator);
		/*
		JMenuItem mntmHideunhideWorldMap = new JMenuItem("Hide World Map");
		//mntmHideunhideWorldMap.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, 0));
		mntmHideunhideWorldMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				d1 = scrollPane_1.getSize();
				d2 = scrollPane.getSize();
				d2.setSize(d1.getWidth() + d2.getWidth(), d2.getHeight());
				//scrollPane_1.setSize(0, 0);
				scrollPane.setSize(d2);
				scrollPane_1.setSize(0, 0);
				scrollPane.setViewportView(xGraph.getView());
				//setScrollPanesNoR();
			}
		});
		mnView.add(mntmHideunhideWorldMap);
		*/
		JMenu mnLayout = new JMenu("Attributes");
		menuBar.add(mnLayout);
		
		chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Enable Auto-Layout");
		mnLayout.add(chckbxmntmNewCheckItem);
		chckbxmntmNewCheckItem.setSelected(true);
		chckbxmntmNewCheckItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, 0));
		chckbxmntmNewCheckItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxmntmNewCheckItem.isSelected()) {
					xGraph.getSelectedViewer().enableAutoLayout();
				}else{
					xGraph.getSelectedViewer().disableAutoLayout();
				}
			}
		});
		
		JMenuItem mntmChangeAutolayout = new JMenuItem("Change Auto-Layout");
		mnLayout.add(mntmChangeAutolayout);
		
		JMenuItem mntmChangeWeights = new JMenuItem("Change Weights");
		mntmChangeWeights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ChangeWeightsWindow(xGraph).setVisible(true);
			}
		});
		mnLayout.add(mntmChangeWeights);
		
		JMenuItem mntmDisablePopout = new JMenuItem("Disable Pop-out");
		mntmDisablePopout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeTabbedPane();
			}
		});
		
		JMenuItem mntmEnablePopout = new JMenuItem("Enable Pop-out");
		mntmEnablePopout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTabbedPane();
			}
		});
		mnLayout.add(mntmEnablePopout);
		mnLayout.add(mntmDisablePopout);
		
		JSeparator separator_1 = new JSeparator();
		mnLayout.add(separator_1);
		
		JMenuItem mntmGraphAttributes = new JMenuItem("Graph Attributes...");
		mnLayout.add(mntmGraphAttributes);
		
		scrollPane = new JScrollPane();
		/*
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			// TODO
			public void mousePressed(MouseEvent arg0) {
				try {
					Robot r = new Robot();
					if(arg0.getButton() == MouseEvent.BUTTON1) {
						r.mousePress(InputEvent.BUTTON2_DOWN_MASK);
					}else{
						r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					}
					
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					Robot r = new Robot();
					if(arg0.getButton() == MouseEvent.BUTTON1) {
						r.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
					}else{
						r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					}
					
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		*/
		scrollPane.setBounds(10, 11, 389, 469);
		
		scrollPane_1 = new JScrollPane();
		
		scrollPane_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				System.out.println("FOCUSs");
			}
		});
		scrollPane_1.setBounds(742, 11, 192, 192);
		
		getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		
		GroupLayout groupLayout_1 = new GroupLayout(getContentPane());
		groupLayout_1.setHorizontalGroup(
			groupLayout_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout_1.setVerticalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabbedPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
						.addGroup(groupLayout_1.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		class LastRowBold extends javax.swing.table.DefaultTableCellRenderer {  
			   public java.awt.Component getTableCellRendererComponent(JTable table, 
					   Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				   javax.swing.JLabel parent = (javax.swing.JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  
				   if(column == 0)
					   parent.setFont(parent.getFont().deriveFont(java.awt.Font.BOLD));
				   if(row == 1)
					   parent.setHorizontalAlignment(javax.swing.JLabel.CENTER);
				   if(row == 3 || row <= 1)
					   parent.setHorizontalAlignment(javax.swing.JLabel.LEFT);
				   return parent;
			   }
		}
		getContentPane().setLayout(groupLayout_1);
		getContentPane().add(scrollPane);
		getContentPane().add(scrollPane_1);
		
		
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JScrollPane getScrollPane_1() {
		return scrollPane_1;
	}

	public void setScrollPane_1(JScrollPane scrollPane_1) {
		this.scrollPane_1 = scrollPane_1;
	}

	public XGraph getxGraph() {
		return xGraph;
	}

	public void setxGraph(XGraph xGraph) {
		this.xGraph = xGraph;
	}

	public XMLFile getXmlFile() {
		return xmlFile;
	}

	public void setXmlFile(XMLFile xmlFile) {
		this.xmlFile = xmlFile;
	}

	public JCheckBoxMenuItem getChckbxmntmNewCheckItem() {
		return chckbxmntmNewCheckItem;
	}

	public void setChckbxmntmNewCheckItem(JCheckBoxMenuItem chckbxmntmNewCheckItem) {
		this.chckbxmntmNewCheckItem = chckbxmntmNewCheckItem;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}
	
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public boolean getMode() {
		return mode;
	}
	
	public boolean isPopOut() {
		return popOut;
	}

	public void setScrollPanes() {
		//remove(this.scrollPane_1);
		//remove(this.scrollPane);
		scrollPane.getViewport().removeAll();
		scrollPane_1.getViewport().removeAll();
		
		scrollPane.setViewportView(xGraph.showSelectedGraph());
		scrollPane_1.setViewportView(xGraph.showWorldGraph());
		
		//new LegendWindow(xGraph).setVisible(true);
		getTabbedPane().addTab("Legend", null, new LegendPanel(xGraph), null);
		
		scrollPane.getViewport().getComponent(0).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				mode = false;
				main.setMode(false);
				scrollPane.repaint();
				System.out.println(main.getMode());
			}
		});
		
		scrollPane_1.getViewport().getComponent(0).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				mode = true;
				scrollPane_1.repaint();
				main.setMode(true);
				System.out.println(main.getMode());
			}
		});
	}
	
	public void setScrollPanesNoR() {
		scrollPane.getViewport().removeAll();
		scrollPane.setViewportView(xGraph.getSelectedView());
		scrollPane_1.setViewportView(xGraph.getWorldView());
	}
	
	public void setScrollPanes1() {
		scrollPane.getViewport().removeAll();
		scrollPane.setViewportView(xGraph.showSelectedGraph());
		//scrollPane_1.getViewport().add(xGraph.showWorldGraph());
	}
	
	public void removeTabbedPane() {
		popOut = true;
		GroupLayout groupLayout_1 = new GroupLayout(getContentPane());
		groupLayout_1.setHorizontalGroup(
			groupLayout_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout_1.setVerticalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabbedPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
						.addGroup(groupLayout_1.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout_1);
		
		this.remove(tabbedPane);
		popOutWindow = new JDialog(this);
		popOutWindow.setTitle("Legend");
		popOutWindow.add(new LegendPanel(xGraph));
		popOutWindow.setVisible(true);
		popOutWindow.toFront();
		
	}
	
	public void addTabbedPane() {
		popOut = true;
		
		//popOutWindow.dispose();
		//popOutWindow.setVisible(false);
		//popOutWindow = null;
		getContentPane().add(tabbedPane);
		GroupLayout groupLayout_1 = new GroupLayout(getContentPane());
		groupLayout_1.setHorizontalGroup(
			groupLayout_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout_1.setVerticalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabbedPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
						.addGroup(groupLayout_1.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout_1);
		
	}
	
	public void callOpenXML() {
		new OpenXML(this, false);
	}
}
