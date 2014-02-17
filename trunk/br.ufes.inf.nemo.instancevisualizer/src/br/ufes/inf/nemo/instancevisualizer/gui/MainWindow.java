package br.ufes.inf.nemo.instancevisualizer.gui;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.graphicGraph.GraphicElement;
import org.graphstream.ui.swingViewer.DefaultView;
import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.util.Camera;
import org.graphstream.ui.swingViewer.util.DefaultMouseManager;

import br.ufes.inf.nemo.instancevisualizer.apl.AplMainWindow;
import br.ufes.inf.nemo.instancevisualizer.util.DialogUtil;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.KeyAdapter;

public class MainWindow extends JFrame {
	
	// Window contents:
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JScrollPane selectedWorld;
	private JScrollPane worldMap;
	private JLabel lblStatus;
	
	// Menu items checkbox:
	private JCheckBoxMenuItem chckbxmntmNewCheckItem;
	private JMenuItem mntmNextInstance;
	
	// Control variables for the show/hide tabbed pane animation:
	boolean hiddenTabbedPane = true;
	boolean shownTabbedPane = false;
	
	/**
	 * 
	 * Create the main window.
	 * @param mainVar the main class
	 * @param args[] the launch arguments
	 * @throws InterruptedException
	 */
	public MainWindow(String args[]) {
		getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				if(shownTabbedPane)
					hideTabbedPane();
			}
		});
		
		AplMainWindow.mainWindow = this;
		
		setTitle("Instance Visualizer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		setMinimumSize(new Dimension(640, 480));
		setExtendedState(MAXIMIZED_BOTH);
		
		// Content pane:
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// Menu bar:
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// File menu:
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		// Open file menu item:
		JMenuItem mntmOpenFile = new JMenuItem("Open File");
		mntmOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			   	AplMainWindow.openFile("."+File.separator);
			}
		});
		mnFile.add(mntmOpenFile);
		
		// Files|Themes separator:
		mnFile.add(new JSeparator());
		
		// Load theme menu item:
		JMenuItem mntmLoadTheme = new JMenuItem("Load Theme");
		mntmLoadTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AplMainWindow.openTheme();
			}
		});
		mnFile.add(mntmLoadTheme);
		
		// Save theme menu item:
		JMenuItem mntmSaveTheme = new JMenuItem("Save Theme");
		mntmSaveTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AplMainWindow.saveTheme();
			}
		});
		mnFile.add(mntmSaveTheme);
		
		// Separator:
		mnFile.add(new JSeparator());
		
		JMenuItem mntmSaveImage = new JMenuItem("Save Image");
		mntmSaveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogUtil.errorDialog(AplMainWindow.mainWindow, DialogUtil.ERROR, "Not implemented", "This option is not implemented... yet!");
			}
		});
		mnFile.add(mntmSaveImage);
		
		// Separator:
		mnFile.add(new JSeparator());
		
		// Exit menu item:
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		// Menu View:
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		// Next Instance menu item:
		mntmNextInstance = new JMenuItem("Next Instance");
		mntmNextInstance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AplMainWindow.nextInstance();
			}
		});
		mntmNextInstance.setEnabled(false);
		mntmNextInstance.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnOptions.add(mntmNextInstance);
		
		// Next Instance | Zoom + separator: 
		mnOptions.add(new JSeparator());
		
		// Zoom + menu item:
		JMenuItem mntmNewMenuItem = new JMenuItem("Zoom +");
		//mntmNewMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_EQUALS, 0));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AplMainWindow.graphManager.getSelectedView().setAutoscrolls(true);
				//AplMainWindow.graphManager.getSelectedView().
				/*
				Camera cam = cam = xGraph.getSelectedView().getCamera();
				if(xGraph.getSelectedView().getCamera().getViewPercent() > 0.2)
					cam.setViewPercent(cam.getViewPercent() - 0.1);
					*/
			}
		});
		
		// Zoom - menu item:
		JMenuItem mntmZoom = new JMenuItem("Zoom -");
		//mntmZoom.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_MINUS, 0));
		mntmZoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				Camera cam = xGraph.getSelectedView().getCamera();
				cam.setViewPercent(cam.getViewPercent() + 0.1);
				*/
			}
		});
		mnOptions.add(mntmZoom);
		
		// Enable Auto-Layout menu item:
		chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Enable Auto-Layout");
		
		mnOptions.add(chckbxmntmNewCheckItem);
		chckbxmntmNewCheckItem.setSelected(true);
		chckbxmntmNewCheckItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		chckbxmntmNewCheckItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxmntmNewCheckItem.isSelected()) {
					AplMainWindow.graphManager.getSelectedViewer().enableAutoLayout();
				}else{
					AplMainWindow.graphManager.getSelectedViewer().disableAutoLayout();
				}
			}
		});
		mnOptions.add(mntmNewMenuItem);
		
		// Refresh menu item:
		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AplMainWindow.refreshGraphs();
			}
		});
		mntmRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mnOptions.add(mntmRefresh);
		
		// Separator:
		mnOptions.add(new JSeparator());
		
		JMenuItem mntmOpenMiniMap = new JMenuItem("Open Mini Map");
		mntmOpenMiniMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				miniMapProto();
			}
		});
		mnOptions.add(mntmOpenMiniMap);
		
		// Tabbed pane (it's where the legend, properties, etc are).
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				//if(hiddenTabbedPane)
					//showTabbedPane();
			}
		});
		
		worldMap = new JScrollPane();
		
		selectedWorld = new JScrollPane();
		selectedWorld.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				AplMainWindow.zoomSelectedView(arg0);
			}
		});
		selectedWorld.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				//if(shownTabbedPane)
					//hideTabbedPane();
			}
		});
				
		lblStatus = new JLabel("status");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
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
		//JTextArea x = new JTextArea("asçlfcjweaoiefhioshvoishvoirhrvoirehvoijenroivneroivoervoi");
		//selectedWorld.setViewportView(x);
		//selectedWorld.setPreferredSize(new Dimension(9999, 9999));
		//x.setPreferredSize(new Dimension(9999, 9999));
		
		//getSelectedWorld().getViewport().setViewSize(new Dimension(1000, 1000));
		//getSelectedWorld().getViewport().setBounds(0, 0, 1000, 1000);
		
		// Group layout definition:
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStatus, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1342, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(worldMap, Alignment.LEADING)
								.addComponent(tabbedPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(selectedWorld, GroupLayout.DEFAULT_SIZE, 1164, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(worldMap, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
						.addComponent(selectedWorld, GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(4))
		);
		/*
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStatus, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1344, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
								.addComponent(worldMap, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(selectedWorld, GroupLayout.DEFAULT_SIZE, 1214, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(worldMap, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
						.addComponent(selectedWorld, GroupLayout.PREFERRED_SIZE, 655, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		*/
		getContentPane().setLayout(groupLayout);
		/*
		ArrayList x = new ArrayList();
		try{
			AplMainWindow.graphManager.showSelectedGraph();
		} catch(Exception e) {
			DialogUtil.bugDialog(this, e);
		}*/
		//miniMapProto();
	}
	/**
	* 
	*/
	public void miniMapProto() {
		final JFrame x = new JFrame();
		x.setTitle("mini map prototype (IT WORKS!)");
		x.setAlwaysOnTop(true);
		x.setSize(200, 134);
		x.setResizable(false);
		
		AplMainWindow.graphManager.getSelectedViewer();
		//Object obj = AplMainWindow.graphManager.getSelectedView().clone();
		//AplMainWindow.graphManager.getSelectedViewer().addDefaultView(false);
		final DefaultView v = new DefaultView(AplMainWindow.graphManager.getSelectedViewer(), "A", Viewer.newGraphRenderer());
		AplMainWindow.graphManager.getSelectedViewer().addView(v);
		
		//v.setBounds(0, 0, 100, 100);
		final JScrollPane sp = new JScrollPane();
		sp.setViewportView(v);
		//x.add(sp);
		
		//v.setBounds(0,0,200,200);
		sp.setBounds(0, 0, 1164, 638);
		final JLabel lblImglabel = new JLabel("");
		lblImglabel.setBounds(0, 0, 1164/6, 638/6);
		v.setAutoscrolls(true);
		v.setMouseManager(new DefaultMouseManager() {
			protected void mouseButtonPress(MouseEvent event) {
			}

			protected void mouseButtonRelease(MouseEvent event,
					ArrayList<GraphicElement> elementsInArea) {
			}

			protected void mouseButtonPressOnElement(GraphicElement element,
					MouseEvent event) {
			}

			protected void elementMoving(GraphicElement element, MouseEvent event) {
			}

			protected void mouseButtonReleaseOffElement(GraphicElement element,
					MouseEvent event) {
			}

			public void mousePressed(MouseEvent event) {
			}

			public void mouseDragged(MouseEvent event) {
			}

			public void mouseReleased(MouseEvent event) {
			}
		});
		BufferedImage bfimg = ScreenImage.createImage(sp);
		lblImglabel.requestFocus();
		ImageIcon imgIcon;
		v.setSize(100, 100);
		x.getContentPane().setLayout(null);
		//x.add(lblImglabel);
		JLayeredPane pane = new JLayeredPane();
		pane.add(lblImglabel, 2, 0);
	    pane.add(sp, 1, 0);
	    x.setLayeredPane(pane);
		
		//JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setBounds(0, 0, 1164, 638);
		//x.getContentPane().add(scrollPane);
		x.setVisible(true);
		
		new Thread() {
			public void run() {
				System.out.println("lala");
				while(x.isVisible()) {
					try {
						//Point3 begin = AplMainWindow.graphManager.getSelectedView().getCamera().transformPxToGu(x, y);
						//Point3 end = AplMainWindow.graphManager.getSelectedView().getCamera().transformPxToGu(x+width-1, y+height-1);
						Camera cam = AplMainWindow.graphManager.getSelectedView().getCamera();
						Point3 centerOrig = AplMainWindow.graphManager.getSelectedView().getCamera().transformPxToGu(1164/2, 638/2);
						double deltaX = AplMainWindow.graphManager.getSelectedView().getCamera().transformPxToGu(0, 638/2).x - centerOrig.x;
						double deltaY = AplMainWindow.graphManager.getSelectedView().getCamera().transformPxToGu(1164/2, 0).y - centerOrig.y;
						/*
						double beginX = AplMainWindow.graphManager.getSelectedView().getCamera().transformPxToGu(0, 638/2).x - centerOrig.x;
						double beginY = AplMainWindow.graphManager.getSelectedView().getCamera().transformPxToGu(1164/2, 0).y - centerOrig.y;
						double endX = centerOrig.x + AplMainWindow.graphManager.getSelectedView().getCamera().transformPxToGu(1164, 638/2).x;
						double endY = centerOrig.y + AplMainWindow.graphManager.getSelectedView().getCamera().transformPxToGu(1164/2, 638).y;
						*/
						Point3 centerMini = v.getCamera().transformPxToGu(1164/2, 638/2);
						//System.out.println(centerMini +""+ centerOrig);
						//Point3 center = AplMainWindow.graphManager.getSelectedView().getCamera().getViewCenter();
						//System.out.println(begin.x+"x"+end.x);
						/*
						Point3 center = v.getCamera().getViewCenter();
						System.out.println(center.x+"xXx"+center.y);
						sleep(1000);
						Point3 center2 = v.getCamera().transformPxToGu(1164/2, 638/2);
						System.out.println(center2.x+"x"+center2.y);*/
						
						//v.beginSelectionAt(centerOrig.x, centerOrig.y);
						//v.selectionGrowsAt(centerOrig.x*cam.getViewPercent(), centerOrig.y + cam.getViewPercent());
						sleep(100);
						BufferedImage bfimg = ScreenImage.createImage(sp);
						ImageIcon imgIcon = new ImageIcon(bfimg.getScaledInstance(1164/6, 638/6, BufferedImage.SCALE_SMOOTH));
						lblImglabel.setIcon(imgIcon);
					} catch (InterruptedException | NullPointerException e) {
						run();
						// TODO Auto-generated catch block
						// e.printStackTrace();
					}
					
				}
				//System.out.println("ASDAFAWEG");
			}
		}.start();
		
		//x.getContentPane().add(new JLabel());
	}
	
	public JScrollPane getScrollPane() {
		return selectedWorld;
	}

	public void setScrollPane(JScrollPane selectedWorld) {
		this.selectedWorld = selectedWorld;
	}

	public JScrollPane getScrollPane_1() {
		return worldMap;
	}

	public void setScrollPane_1(JScrollPane worldMap) {
		this.worldMap = worldMap;
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
/*	
	public boolean isPopOut() {
		return popOut;
	}

	public void setScrollPanes() {
		//remove(this.worldMap);
		//remove(this.selectedWorld);
		selectedWorld.getViewport().removeAll();
		worldMap.getViewport().removeAll();
		
		selectedWorld.setViewportView(xGraph.showSelectedGraph());
		worldMap.setViewportView(xGraph.showWorldGraph());
		
		//new LegendWindow(xGraph).setVisible(true);
		getTabbedPane().addTab("Legend", null, new LegendPanel(xGraph));
		
	}
	
	public void setScrollPanesNoR() {
		selectedWorld.getViewport().removeAll();
		selectedWorld.setViewportView(xGraph.getSelectedView());
		worldMap.setViewportView(xGraph.getWorldView());
	}
	
	public void setScrollPanes1() {
		selectedWorld.getViewport().removeAll();
		selectedWorld.setViewportView(xGraph.showSelectedGraph());
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
						.addComponent(selectedWorld)
						.addComponent(worldMap, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout_1.setVerticalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabbedPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
						.addGroup(groupLayout_1.createSequentialGroup()
							.addComponent(selectedWorld, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(worldMap, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
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
						.addComponent(selectedWorld)
						.addComponent(worldMap, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout_1.setVerticalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabbedPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
						.addGroup(groupLayout_1.createSequentialGroup()
							.addComponent(selectedWorld, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(worldMap, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout_1);
		
	}
	*/
	
	public void setStatus(String status) {
		lblStatus.setText(status);
	}
	
	public void setStatusText(String status) {
		new StatusManager(this, status);
	}
	
	public JMenuItem getMntmNextInstance() {
		return mntmNextInstance;
	}

	public JScrollPane getSelectedWorld() {
		return selectedWorld;
	}

	public void setSelectedWorld(JScrollPane selectedWorld) {
		this.selectedWorld = selectedWorld;
	}

	public JScrollPane getWorldMap() {
		return worldMap;
	}

	public void setWorldMap(JScrollPane worldMap) {
		this.worldMap = worldMap;
	}

	public void showTabbedPane() {
		hiddenTabbedPane = false;
		new Thread () {
			public void run() {
				// Group layout definition:
				int tabbedPaneSize = 13;
				int scrollPanesSize = 1342;
				while(scrollPanesSize > 1160) {
					try {
						sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					GroupLayout groupLayout = new GroupLayout(getContentPane());
					groupLayout.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, 1342, Short.MAX_VALUE)
										.addContainerGap())
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, tabbedPaneSize, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(selectedWorld, GroupLayout.DEFAULT_SIZE, scrollPanesSize, Short.MAX_VALUE)
												.addGap(10))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(worldMap, GroupLayout.DEFAULT_SIZE, scrollPanesSize, Short.MAX_VALUE)
												.addContainerGap())))))
					);
					groupLayout.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGap(11)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(selectedWorld, GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(worldMap, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
									.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addGap(4))
					);
					getContentPane().setLayout(groupLayout);
					tabbedPaneSize += 32;
					scrollPanesSize -= 32;
				}
				shownTabbedPane = true;
			}
		}.start();
	}
	
	public void hideTabbedPane() {
		shownTabbedPane = false;
		new Thread () {
			public void run() {
				int tabbedPaneSize = 172;
				int scrollPanesSize = 1160;
				while(scrollPanesSize < 1342) {
					try {
						sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// Group layout definition:
					GroupLayout groupLayout = new GroupLayout(getContentPane());
					groupLayout.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, 1342, Short.MAX_VALUE)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, tabbedPaneSize, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(worldMap, GroupLayout.DEFAULT_SIZE, scrollPanesSize, Short.MAX_VALUE)
											.addComponent(selectedWorld, GroupLayout.DEFAULT_SIZE, scrollPanesSize, Short.MAX_VALUE))))
								.addContainerGap())
					);
					groupLayout.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(11)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(selectedWorld, GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(worldMap, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
									.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addGap(4))
					);
					getContentPane().setLayout(groupLayout);
					tabbedPaneSize -= 32;
					scrollPanesSize += 32;
				}
				hiddenTabbedPane = true;
			}
		}.start();
		
	}
}