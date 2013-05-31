package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class MainWindow extends JFrame {

	//protected boolean loop = true;
	private JPanel contentPane;
	private XMLFile xmlFile;
	private XGraph xGraph;
	private int worldCounter;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
	}

	/**
	 * Create the frame.
	 * @throws InterruptedException 
	 */
	public MainWindow() throws InterruptedException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640+320, 360+180);
		
		setMinimumSize(new Dimension(960, 540));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open .xml/.refontouml...");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
		        fc.setCurrentDirectory(new File("./"));
		        fc.setFileFilter(new FileNameExtensionFilter("XML instances", "xml"));
		        int returnVal = fc.showOpenDialog(MainWindow.this);
		        
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            try {
		                xmlFile = new XMLFile(fc.getSelectedFile());
		                String fileNameWithoutExt = fc.getSelectedFile().getName().replaceFirst("[.][^.]+$", "");
		                String refontoPath = fc.getSelectedFile().getParent() + "\\" + fileNameWithoutExt + ".refontouml";
		                File refontoFile = new File(refontoPath);
		                System.out.println(refontoFile.getAbsolutePath());
		                OntoUMLParser onto = null;
		                
		                if(refontoFile.exists()) {
		                	onto = new OntoUMLParser(refontoPath);
		                    /*
		                    EObject eo = onto.getElement("Passenger");
		                    String estereotipo = TypeName.getTypeName(eo);
		                    System.out.println(estereotipo);
		                    */
		                    xGraph = new XGraph(xmlFile, onto, 0);
		                    xGraph.setGraphToSelectedWorld(xGraph.getXmlFile().findAtom("world_structure/CurrentWorld$0"));
		                    remove(scrollPane);
		                    scrollPane = new JScrollPane(xGraph.showSelectedGraph());
		                    
		                    remove(scrollPane_1);
		                    xGraph.setGraphToAllWorlds();
		                    scrollPane_1 = new JScrollPane(xGraph.showWorldGraph());

		                    GroupLayout gl_contentPane = new GroupLayout(contentPane);
		            		gl_contentPane.setHorizontalGroup(
		            			gl_contentPane.createParallelGroup(Alignment.LEADING)
		            				.addGroup(gl_contentPane.createSequentialGroup()
		            					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
		            					.addGap(8)
		            					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
		            		);
		            		gl_contentPane.setVerticalGroup(
		            			gl_contentPane.createParallelGroup(Alignment.LEADING)
		            				.addGroup(gl_contentPane.createSequentialGroup()
		            					.addGap(5)
		            					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
		            						.addGroup(gl_contentPane.createSequentialGroup()
		            							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
		            							.addGap(172))
		            						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)))
		            		);
		            		contentPane.setLayout(gl_contentPane);
		            		
		            		//new Clicks(xGraph);
		            		
		                    //xGraph.showGraph();
		                    //scrollPane.getcont.add(xGraph.showGraph());

		                    /*
		                    if(tabbedPane.getTabCount() > 1) {
		                        java.awt.Component c = tabbedPane.getTabComponentAt(0);
		                        System.out.println(tabbedPane.getComponentCount());
		                        tabbedPane.removeAll();
		                        tabbedPane.addTab("Welcome", c);
		                        System.out.println(tabbedPane.getComponentCount());
		                    }
		                    //getContentPane().add(xGraph.getView());
		                    panel_1.add(xGraph.getView());
		                    //tabbedPane.add(xGraph.getView());
		                     * 
		                    //tabbedPane.setSelectedIndex(1);
		                    */
		                    //tabbedPane.setTitleAt(1, "All Worlds");
		                    worldCounter = 0;
		                }else{
		                	System.out.println(".refontouml NOT FOUND... You need it on the same directory of the .xml.");
		                }
		            } catch (    ParserConfigurationException | SAXException | IOException ex) {
		                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
			}
		});
		mnFile.add(mntmOpen);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Zoom +");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Camera cam = xGraph.getView().getCamera();
				cam.setViewPercent(cam.getViewPercent() - 0.1);
			}
		});
		mnView.add(mntmNewMenuItem);
		
		JMenuItem mntmZoom = new JMenuItem("Zoom -");
		mntmZoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Camera cam = xGraph.getView().getCamera();
				cam.setViewPercent(cam.getViewPercent() + 0.1);
			}
		});
		mnView.add(mntmZoom);
		
		JSeparator separator = new JSeparator();
		mnView.add(separator);
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Auto Layout");
		mnView.add(chckbxmntmNewCheckItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		
		scrollPane_1 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(172))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)))
		);
		contentPane.setLayout(gl_contentPane);
		
		//new Clicks(xGraph);
		
	}
	
	public MainWindow(XGraph xGraphVar) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640+320, 360+180);
		
		setMinimumSize(new Dimension(960, 540));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open .xml/.refontouml...");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
		        fc.setCurrentDirectory(new File("./"));
		        fc.setFileFilter(new FileNameExtensionFilter("XML instances", "xml"));
		        int returnVal = fc.showOpenDialog(MainWindow.this);
		        
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            try {
		                xmlFile = new XMLFile(fc.getSelectedFile());
		                String fileNameWithoutExt = fc.getSelectedFile().getName().replaceFirst("[.][^.]+$", "");
		                String refontoPath = fc.getSelectedFile().getParent() + "\\" + fileNameWithoutExt + ".refontouml";
		                File refontoFile = new File(refontoPath);
		                System.out.println(refontoFile.getAbsolutePath());
		                OntoUMLParser onto = null;
		                
		                if(refontoFile.exists()) {
		                	onto = new OntoUMLParser(refontoPath);
		                    /*
		                    EObject eo = onto.getElement("Passenger");
		                    String estereotipo = TypeName.getTypeName(eo);
		                    System.out.println(estereotipo);
		                    */
		                    xGraph = new XGraph(xmlFile, onto, 0);
		                    xGraph.setGraphToSelectedWorld(xGraph.getXmlFile().findAtom("world_structure/CurrentWorld$0"));
		                    remove(scrollPane);
		                    scrollPane = new JScrollPane(xGraph.showSelectedGraph());
		                    
		                    remove(scrollPane_1);
		                    xGraph.setGraphToAllWorlds();
		                    scrollPane_1 = new JScrollPane(xGraph.showWorldGraph());

		                    GroupLayout gl_contentPane = new GroupLayout(contentPane);
		            		gl_contentPane.setHorizontalGroup(
		            			gl_contentPane.createParallelGroup(Alignment.LEADING)
		            				.addGroup(gl_contentPane.createSequentialGroup()
		            					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
		            					.addGap(8)
		            					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
		            		);
		            		gl_contentPane.setVerticalGroup(
		            			gl_contentPane.createParallelGroup(Alignment.LEADING)
		            				.addGroup(gl_contentPane.createSequentialGroup()
		            					.addGap(5)
		            					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
		            						.addGroup(gl_contentPane.createSequentialGroup()
		            							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
		            							.addGap(172))
		            						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)))
		            		);
		            		contentPane.setLayout(gl_contentPane);
		            		
		            		//new Clicks(xGraph);
		            		
		                    //xGraph.showGraph();
		                    //scrollPane.getcont.add(xGraph.showGraph());

		                    /*
		                    if(tabbedPane.getTabCount() > 1) {
		                        java.awt.Component c = tabbedPane.getTabComponentAt(0);
		                        System.out.println(tabbedPane.getComponentCount());
		                        tabbedPane.removeAll();
		                        tabbedPane.addTab("Welcome", c);
		                        System.out.println(tabbedPane.getComponentCount());
		                    }
		                    //getContentPane().add(xGraph.getView());
		                    panel_1.add(xGraph.getView());
		                    //tabbedPane.add(xGraph.getView());
		                     * 
		                    //tabbedPane.setSelectedIndex(1);
		                    */
		                    //tabbedPane.setTitleAt(1, "All Worlds");
		                    worldCounter = 0;
		                }else{
		                	System.out.println(".refontouml NOT FOUND... You need it on the same directory of the .xml.");
		                }
		            } catch (    ParserConfigurationException | SAXException | IOException ex) {
		                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
			}
		});
		mnFile.add(mntmOpen);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Zoom +");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Camera cam = xGraph.getView().getCamera();
				cam.setViewPercent(cam.getViewPercent() - 0.1);
			}
		});
		mnView.add(mntmNewMenuItem);
		
		JMenuItem mntmZoom = new JMenuItem("Zoom -");
		mntmZoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Camera cam = xGraph.getView().getCamera();
				cam.setViewPercent(cam.getViewPercent() + 0.1);
			}
		});
		mnView.add(mntmZoom);
		
		JSeparator separator = new JSeparator();
		mnView.add(separator);
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Auto Layout");
		mnView.add(chckbxmntmNewCheckItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane(xGraphVar.showSelectedGraph());
		
		scrollPane_1 = new JScrollPane(xGraphVar.showWorldGraph());
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(172))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)))
		);
		contentPane.setLayout(gl_contentPane);
		
		//new Clicks(xGraph);
	}
	
	
	
}
