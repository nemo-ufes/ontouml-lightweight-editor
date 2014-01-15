/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * licence terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package br.ufes.inf.nemo.oled;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import br.ufes.inf.nemo.oled.util.BinaryLoader;

/*
 * Features TO-DO
 * ======================================================
 * 
*
* * Colocar IRI no modelo RefOntoUML
 * Implementar nova transforma��o para OWL
 * Implementar Salvar como na visualiza�o do OWL? Mas sem fazer, desfazer.
 * 
 * Permitir sele��o para simula��o somente de classes v�lidas sintaticamente
 * Desci��o do ultimo comando executado na barra de status
 *   
 * Criar elementos com propriedades default
 * 		Mediation com ends read only.
 * 
 * Implementar associa��o entre rela��es
 * Implantar o reposicionamento de labels
 * Permitir auto-relacionamentos
 * Funcionalidade Copiar/Colar
 * Tela Sobre (About) 
 * 
 * Bot�o para determinar o tipo da conex�o Retilinear ou direta.
 * Atualizar nome do aruivo na aba?
 * Atalho para cria��o de generalization set
 * 		Sele��o de v�rias classes para gerar generalizations e generalization sets
 * 		Generalizations n�o criadas
 * 
 * 
 *  
 * http://archive.eclipse.org/eclipse/downloads/drops/S-3.7RC4-201106030909/index.php#SWT
 * 
 * OK Implementar tema : Ver cinza e Trapezio
 * OK Tela para sele��o de entidades na simula��o e das cores no tema
 * OK Gera��o de tema  
 * OK Retornar texto na simula��o, para ser exibido no output pane
 * OK Implementar o retorno OperationResult para valida��o
 * OK Integrar SBVR : ver deleteonexit pois os arquivos n�o est�o apagando
 * OK Abrir gera�ao de OWL no editor de Texto. 
 * OK Implementar seeting com nome default do arquivo 
 * 
 * Antes do release
 * ======================================================
 * 
 * Revis�o Geral do C�dgio - Limpeza
 * Reestrutura��o da interface usando form layout.
 * Revis�o Geral do C�dgio - Documenta��o
 * Revis�o Geral arquivo de captions
 * 
 * 
 * Sem previs�o
 * ======================================================
 * 
 * Localization
 * 
 * 
 * Problemas conhecidos
 * ======================================================
 * 
 * - Aberto : Melhorar o tratamento de DataTypes ao inserir no modelo.
 * 			  Ele permitiu a duplica��o de datatypes (mesmo nome e mesma id)
 * 			  Al�m disso, quando um datatype � inserido na janela de edi��o de classe
 * 			  N�o aparece nas dropdowns a menos que seja apertado OK.
 * 
 * - Aberto : o temp dir do diagrama n�o est� sendo exclu�do automaticamente
 * 
 * - Aberto : Tratar a simula��o quando a gera��o de alloy retorna vazia
 * 
 * - Aberto : Nomes das associa��es n�o est�o aparecendo
 * 		 	  Connection name labels n�o est�o sendo renderizadas?
 * 
 * - Aberto : Redimensionamento do canvas n�o est� respeitando a posi��o dos nodos
 * 
 * - Aberto : Classes n�o est�o redimensionando bem
 * 			: S� est� acontecendo quando a op��o snap to grid est� habilitada
 * 
 * - Aberto : Ordena��o dos atributos nas propriedades da classe n�o � respeitada  
 * 
 * - Aberto : Incluir os atributos das rela��es meronimicas na label da rela��o
 * 
 * - Aberto : Habilitar o clique com shift para selecionar ou desselecionar o elemento
 * 
 * - Aberto : Ao fazer Undo/Redo algumas a��es n�o tem efeito aparente - Ver linehandler
 * 			  Isso acontece pois o clique est� sendo tratado como um comando de mover / redimensionar com nenhuma modifica��o.
 * 			  Como saber se o usu�rio est� movendo, redimensionando ou selecionando os nodes? O SelectionHandler informar� isso.
 * 		      Selecionando = node selecionado ou n�o posicao do click release igual posi��o inicial
 * 			  Movendo = node previamente selecionado, posicao do click release diferente da posi��o inicial
 * 			  Redimensionando = node previamente selecionado...
 * 
 * - Aberto : Transforma��o para Alloy n�o est� gerando Quantity/SubQuantity
 *
 * - OK : A A4Solution n�o est� sendo gerada com o skolem $visible e 
 * 		: logo n�o est� implementando a associa��o visible
 * - OK : Tratar os eventos de mudan�a nas abas do editor para atualizar o redo/undo
 * - OK : Setas do output pane n�o est�o funcionando
 * - OK : Erro ao transferir dados na janela de edi��o de classe (invalid cast) 
 * - OK : Erro na gera��o de alloy (Bernardo) quando uma category (mixin) � supertipo
 * - OK : Generalizar idioma da janela de generaliza��o
 * - OK : Implementar a op��o abstract na janela de classe
 * - OK : Implantar novo sistema de serializa��o/desserializa��o baseado em Zip
 * - OK : Remover {disjoint, complete} da label das generalizations
 * - OK : Renomear itens de menu para open project e save project
 * - OK : Redimensionar tamanho inicial da janela principal para acomodar resol��es 1024x768
 * - OK : Undo/Redo para a��es visuais como redimensionamento n�o est�o funcionando bem
 * 
 * 
 * Testar se o objeto pertence � interface:
 * Collection.class.isInstance(o)
 */


/**
 * This is the start class of the OLED application. OLED is based on the TinyUML project by Wei-ju Wu.
 * It also benefits from MIT's project called Alloy developed by Daniel Jackson (see http://alloy.mit.edu/)
 */
public final class Main {
	
	public static AppFrame frame; // why we need this Vitor?
	
	/** This caches the result of the call to get all fonts. */
	private static String[] allFonts = null;	
    	   
	/**
	 * Private constructor.
	 */
	private Main() { }

	/** Returns true iff running on Windows **/
	public static boolean onWindows() {
      return System.getProperty("os.name").toLowerCase(Locale.US).startsWith("windows");
	};

	public static String getOSx(){ if (onWindows()) return "win"; else if (onMac()) return "mac"; else return "linux"; }
			
	/** Returns true iff running on Mac OS X. **/
	public static boolean onMac() {
      return System.getProperty("mrj.version")!=null || System.getProperty("os.name").toLowerCase(Locale.US).startsWith("mac ");
	}	
	
	/** Returns true if a font with that name exists on the system (comparison is case-insensitive). */
	public synchronized static boolean hasFont(String fontname) {
		if (allFonts == null) allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for(int i = 0; i < allFonts.length; i++) if (fontname.compareToIgnoreCase(allFonts[i]) == 0) return true;
		return false;
	}

	@SuppressWarnings("rawtypes")
	public static void setUIFont(FontUIResource f) 
	{
       Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                FontUIResource orig = (FontUIResource) value;
                Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
                UIManager.put(key, new FontUIResource(font));
            }
        }
    }
	    
	/** Asks the user to choose a font; returns "" if the user cancels the request. */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized static String askFont() {
		if (allFonts == null) allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		JComboBox jcombo = new JComboBox(allFonts);
		Object ans = show("Font", JOptionPane.INFORMATION_MESSAGE,
				new Object[] {"Please choose the new font:", jcombo}, new Object[] {"Ok", "Cancel"}, "Cancel"
				);
		Object value = jcombo.getSelectedItem();
		if (ans=="Ok" && (value instanceof String)) return (String)value; else return "";
	}
	
	/** Helper method for constructing an always-on-top modal dialog. */
	private static Object show(String title, int type, Object message, Object[] options, Object initialOption) {
		if (options == null) { options = new Object[]{"Ok"};  initialOption = "Ok"; }
		JOptionPane p = new JOptionPane(message, type, JOptionPane.DEFAULT_OPTION, null, options, initialOption);
		p.setInitialValue(initialOption);
		JDialog d = p.createDialog(null, title);
		p.selectInitialValue();
		d.setAlwaysOnTop(true);
		d.setVisible(true);
		d.dispose();
		return p.getValue();
	}
	
	/** Load the correct SWT Jar to the classpath according to the OS*/
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public static void loadSwtJar(URL[] swtJarURLs){		
		try {					
			URLClassLoader child = new URLClassLoader (swtJarURLs, Main.class.getClassLoader());
			Class classToLoad = Class.forName ("org.eclipse.swt.widgets.Display", true, child);			
			Method method = classToLoad.getDeclaredMethod ("getDefault");
			Object instance = classToLoad.newInstance ();
			Object result = method.invoke (instance);
		} catch (Exception e) {
			e.printStackTrace();		
		}
	}

	public static String getSwtWorkingDir()
	{
		String dir = System.getProperty("user.dir");
		if (dir.contains("br.ufes.inf.nemo.oled")) { 
			dir = dir.replace("br.ufes.inf.nemo.oled","org.eclipse.swt").concat(File.separator).concat("src"+File.separator);			
		}else{
			dir = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		}
		return dir;
	}
	
	public static String getSwtFileName()
	{
		String swtFileName = "<empty>";
        String osName = System.getProperty("os.name").toLowerCase();
        String osArch = System.getProperty("os.arch").toLowerCase();
        
        String os = 
            osName.contains("win") ? "win" :
            osName.contains("mac") ? "mac" :
            osName.contains("linux") || osName.contains("nix") ? "linux" :
            ""; // throw new RuntimeException("Unknown OS name: "+osName)

        String arch = osArch.contains("64") ? "x64" : "x86";
        
        swtFileName = "swt"+File.separator+os+File.separator+arch+File.separator+"swt.jar";
        return swtFileName;
		
	}
	
	/** Add the correct SWT Jar to the classpath according to the OS*/
	public static URL addSwtJarToClassPath() 
	{
		String swtFileName = "<empty>";
	    try {
	    	
	        swtFileName = getSwtFileName();  		
	        	        	        
	        URLClassLoader classLoader = (URLClassLoader) Main.class.getClassLoader ();
	        Method addUrlMethod = URLClassLoader.class.getDeclaredMethod ("addURL", URL.class);
	        addUrlMethod.setAccessible (true);
	        	        
	        URL swtFileUrl = null;
	        try{
	        	swtFileUrl = new URL("rsrc:"+swtFileName);
	        }catch(MalformedURLException e){
	        	String workingDir = getSwtWorkingDir();		        
	        	File file = new File(workingDir.concat(swtFileName));
	        	swtFileUrl = file.toURI().toURL();
	        	if (!file.exists ()) System.out.println("Can't locate SWT Jar File" + file.getAbsolutePath());
	    	}
	    	
            System.out.println("Adding to classpath: " + swtFileUrl);
            
            addUrlMethod.invoke (classLoader, swtFileUrl);
            
            return swtFileUrl;
	    }
	    catch(Exception e) {
	        System.out.println("Unable to add the swt jar to the class path: "+swtFileName);
	        e.printStackTrace();
	    }	    
	    return null;
	}
	
	/**  
	 * The start method for this application.
	 * @param args the command line parameters
	 */
	public static void main(String[] args) 
	{        
		SwingUtilities.invokeLater(new Runnable() {
			/**
			 * {@inheritDoc}
			 */
			public void run() {
				try {					
					
					//Needed for the embedded SWT Browser in Linux systems
					System.setProperty("sun.awt.xembedserver", "true");
					
					 // Enable better look-and-feel
			        if (onMac() || onWindows()) {
			            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "OLED");
			            System.setProperty("com.apple.mrj.application.growbox.intrudes","true");
			            System.setProperty("com.apple.mrj.application.live-resize","true");
			            System.setProperty("com.apple.macos.useScreenMenuBar","true");
			            System.setProperty("apple.laf.useScreenMenuBar","true");                        
			            System.setProperty("com.apple.eawt.CocoaComponent.CompatibilityMode","false");
			            System.setProperty("apple.awt.fileDialogForDirectories", "true");
			        }			        
			        
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					
					if (!onMac()&&!onWindows()) UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
					
					UIManager.put("TabbedPane.focus", new Color(0, 0, 0, 0));					
					
					//binary files 
					BinaryLoader loader = new BinaryLoader("oled.jar");					
					loader.extractBinaryFiles(getOSx());
					loader.addBinariesToJavaPath2();
					
				     // Choose the appropriate font		
					int fontSize=11;
			        String fontName="Tahoma";
			        while(true) {
			            if (!hasFont(fontName)) { fontName="Arial"; fontSize = 12;} else break; 
			            if (!hasFont(fontName)) { fontName="Verdana"; fontSize=10; } else break; 
			            if (!hasFont(fontName)) { fontName="Courier New"; } else break; 
			            if (!hasFont(fontName)) { fontName="Lucida Grande"; fontSize=10; } 
			            break;
			        }			        
			        setUIFont(new FontUIResource(new Font(fontName, 0, fontSize)));
	
					frame = new AppFrame();
					
					// this makes System.out content to be printed in the output pane of the app.
					//frame.createSysOutInterceptor();
					
			        //add and load the appropriate SWT jar to the classpath according to the OS
			        URL swtJarURL = addSwtJarToClassPath();
			        URL[] urls = new URL[1];
			        urls[0] = swtJarURL;
			        loadSwtJar(urls);
			        
			        //Extracts alloy and initialize it. We need to fix this ASAP.
					frame.initializeAlloyAnalyzer();
					
					frame.setLocationByPlatform(true);
					frame.setVisible(true);
					frame.toFront();					
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}				
			}
		});		
	}
}
