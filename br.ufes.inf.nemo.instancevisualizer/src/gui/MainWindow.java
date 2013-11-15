package gui;

import graph.GraphManager;
import org.graphstream.graph.Graph;

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
import javax.swing.JFileChooser;
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
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane_1;
	private JCheckBoxMenuItem chckbxmntmNewCheckItem;
	private JTabbedPane tabbedPane;
	
	private GraphManager xGraph;
	
	private boolean popOut;
	private JDialog popOutWindow;
	private JScrollPane scrollPane;
	private JPanel panel;
	
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
		
		JMenuItem mntmLoadTheme = new JMenuItem("Load Theme");
		mntmLoadTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new java.io.File("./"));
		    	if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		    		try {
						String theme = readFile(fileChooser.getSelectedFile().getAbsolutePath(), Charset.defaultCharset());
						System.out.println(theme);
						xGraph.getLegendManager().loadString(theme);
						xGraph.setGraphList(new ArrayList());
						xGraph.createSelectedWorldToList();
						for(Graph g : xGraph.getGraphList()) {
	                    	if(g.getId().equals(xGraph.getSelectedWorld())) {
	                    		xGraph.setSelectedGraph(g);
	                    		break;
	                    	}
	                    }
						setScrollPanes1();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
		    		
		    	}
			}
		});
		mnFile.add(mntmLoadTheme);
		
		JMenuItem mntmSaveTheme = new JMenuItem("Save Theme");
		mntmSaveTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String theme = xGraph.getLegendManager().saveToString();
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new java.io.File("./"));
		    	if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
		    		//File file = fileChooser.getSelectedFile();
		    		try {
						FileWriter fw = new FileWriter(fileChooser.getSelectedFile());
						fw.write(theme);
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
		    		
		    	}
			}
		});
		mnFile.add(mntmSaveTheme);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Zoom +");
		mntmNewMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_EQUALS, 0));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Camera cam = cam = xGraph.getSelectedView().getCamera();
				if(xGraph.getSelectedView().getCamera().getViewPercent() > 0.2)
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
		
		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setScrollPanes1();
			}
		});
		mntmRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mnView.add(mntmRefresh);
		
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
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(742, 11, 192, 192);
		
		getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		
		scrollPane = new JScrollPane();
		
		panel = new JPanel();
		
		GroupLayout groupLayout_1 = new GroupLayout(getContentPane());
		groupLayout_1.setHorizontalGroup(
			groupLayout_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout_1.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout_1.setVerticalGroup(
			groupLayout_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
						.addComponent(tabbedPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
						.addGroup(groupLayout_1.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Camera cam = cam = xGraph.getSelectedView().getCamera();
				if(xGraph.getSelectedView().getCamera().getViewPercent() > 0.2)
					cam.setViewPercent(cam.getViewPercent() - 0.1);
			}
		});
		btnNewButton.setIcon(new ImageIcon(".\\resources\\ui\\Zoom-In-icon.png"));
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Camera cam = xGraph.getSelectedView().getCamera();
				cam.setViewPercent(cam.getViewPercent() + 0.1);
			}
		});
		button.setIcon(new ImageIcon(".\\resources\\ui\\Zoom-Out-icon.png"));
		
		JButton button_1 = new JButton("New button");
		
		JButton button_2 = new JButton("New button");
		
		JButton button_3 = new JButton("New button");
		
		JButton button_4 = new JButton("New button");
		
		JButton button_5 = new JButton("New button");
		
		JButton button_6 = new JButton("New button");
		
		JButton button_7 = new JButton("New button");
		
		JButton button_8 = new JButton("New button");
		
		JButton button_9 = new JButton("New button");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_8, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_9, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_8, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_9, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(46, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
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
	
	public void setScrollPanesNoR() {
		scrollPane.getViewport().removeAll();
		scrollPane.setViewportView(xGraph.getSelectedView());
		scrollPane_1.setViewportView(xGraph.getWorldView());
	}
	
	public void setScrollPanes1() {
		scrollPane.getViewport().removeAll();
		scrollPane.setViewportView(xGraph.showSelectedGraph());
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
		popOutWindow.setVisible(true);
		popOutWindow.toFront();
		
	}
	
	public void addTabbedPane() {
		popOut = false;
		
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
	
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}
	
	public void callOpenXML() {
		new OpenXML(this, false);
	}
}