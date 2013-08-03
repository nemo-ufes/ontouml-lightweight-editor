package gui;

import graph.GraphManager;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;


import org.graphstream.ui.swingViewer.util.Camera;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JCheckBoxMenuItem chckbxmntmNewCheckItem;
	private JTabbedPane tabbedPane;
	private JTabbedPane tabbedPane_1;
	
	private GraphManager xGraph;
	
	private boolean popOut;
	private JDialog popOutWindow;
	
	/**
	 * Create the frame.
	 * @param mainVar the main class
	 * @throws InterruptedException 
	 */
	public MainWindow() throws InterruptedException {
		setTitle("Instance Visualizer");
		popOut = false;
		popOutWindow = null;
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
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Zoom +");
		mntmNewMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_EQUALS, 0));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Camera cam = null;
				switch(tabbedPane_1.getSelectedIndex()) {
					case 0:
						cam = xGraph.getSelectedView().getCamera();
						break;
					case 1:
						cam = xGraph.getRmView().getCamera();
						break;
					default:
						System.exit(2);
				}
				cam.setViewPercent(cam.getViewPercent() - 0.1);
			}
		});
		mnView.add(mntmNewMenuItem);
		
		JMenuItem mntmZoom = new JMenuItem("Zoom -");
		mntmZoom.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_MINUS, 0));
		mntmZoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Camera cam = null;
				switch(tabbedPane_1.getSelectedIndex()) {
					case 0:
						cam = xGraph.getSelectedView().getCamera();
						break;
					case 1:
						cam = xGraph.getRmView().getCamera();
						break;
					default:
						System.exit(2);
				}
				cam.setViewPercent(cam.getViewPercent() + 0.1);
			}
		});
		mnView.add(mntmZoom);
		
		JSeparator separator = new JSeparator();
		mnView.add(separator);
		
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
		
		JMenuItem mntmDisablePopout = new JMenuItem("Enable Pop-out");
		mntmDisablePopout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeTabbedPane();
			}
		});
		
		JMenuItem mntmEnablePopout = new JMenuItem("Disable Pop-out");
		mntmEnablePopout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTabbedPane();
			}
		});
		mnLayout.add(mntmEnablePopout);
		mnLayout.add(mntmDisablePopout);
		
		JMenu mnPerspective = new JMenu("Perspective");
		menuBar.add(mnPerspective);
		
		JMenuItem mntmRelationalMaterial = new JMenuItem("Relational Material");
		mntmRelationalMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame win = new JFrame();
				win.setContentPane(new RelationalMaterialPanel(getxGraph()));
				win.setBounds(100, 100, 100, 200);
				win.setVisible(true);
				win.setAlwaysOnTop(true);
				setRm();
			}
		});
		mnPerspective.add(mntmRelationalMaterial);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 389, 469);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(742, 11, 192, 192);
		
		getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		
		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		
		GroupLayout groupLayout_1 = new GroupLayout(getContentPane());
		groupLayout_1.setHorizontalGroup(
			groupLayout_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabbedPane_1)
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
							.addComponent(tabbedPane_1, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
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
		tabbedPane_1.addTab("Default", scrollPane);
		getContentPane().add(tabbedPane_1);
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

	public GraphManager getxGraph() {
		return xGraph;
	}

	public void setxGraph(GraphManager xGraph) {
		this.xGraph = xGraph;
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
		getTabbedPane().addTab("Legend", null, new LegendPanel(xGraph));
		
	}
	
	public void setRm() {
		JScrollPane scroll = new JScrollPane();
		
		scroll.setViewportView(xGraph.showRmGraph());
		
		tabbedPane_1.addTab("Relational Material", scroll);
		tabbedPane_1.setSelectedIndex(1);
		
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
		popOutWindow.setBounds(0, 50, 150, 300);
		//popOutWindow.add(new LegendPanel(xGraph.getLegendManager(), xGraph.getNodeManager(), xGraph.getOntoUmlParser()));
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
