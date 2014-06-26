/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
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
package br.ufes.inf.nemo.oled.palette;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.ufes.inf.nemo.oled.AppCommandListener;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.IconLoader;
import br.ufes.inf.nemo.oled.util.IconLoader.IconType;

/**
 * @author Antognoni Albuquerque
 */
public class Palette extends JPanel
{
	private static final long serialVersionUID = -4131252046223204095L;
	private String name;
	private JPanel title;
	private JPanel content;
	private PaletteAccordion parent;
	private PaletteElement selectedElement = null;
	private List<AppCommandListener> listeners = new ArrayList<AppCommandListener>();
	private Map<String, PaletteElement> elementMap = new HashMap<String, PaletteElement>();
	private JLabel nameLabel;

	public Palette(PaletteAccordion parent, String name)
	{
		super();

		this.name = name;
		this.parent = parent;
		this.setLayout(new BorderLayout());

		createTitle();
		createContent();
	}

	private void createContent()
	{
		content = new JPanel();
		content.setPreferredSize(new Dimension(200, 600));
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));	
				
		this.add(content, BorderLayout.CENTER);
	}

	private void createTitle()
	{
		title = new JPanel();

		title.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				parent.setOpenPalette(name); 
			} 
		}); 

		title.setLayout(new BorderLayout());

		Icon icon = IconLoader.getInstance().getIcon(IconType.PALETTE_CLOSED);
		nameLabel = new JLabel(name, icon, JLabel.LEFT);
		title.add(nameLabel, BorderLayout.CENTER);

		title.setMaximumSize(new Dimension(32767, 24));
		Dimension size = new Dimension(200, 24);
		title.setSize(size);
		title.setPreferredSize(size);
		
		this.add(title, BorderLayout.NORTH);
	}

	public void createElement(String type, String name)
	{
		String prefix = type + "." + name;
		String command = getResourceString(prefix + ".command");
		String caption = getResourceString(prefix + ".caption");

		Icon icon = IconLoader.getInstance().getIcon(getResourceString(prefix + ".icon"));
		PaletteElement element = new PaletteElement(icon, caption, command, this);
		if (name.equals("derivation")) element.setEnabled(false);
		element.setToolTipText(getResourceString(prefix + ".tooltip"));

		elementMap.put(name, element);
		content.add(element);
		content.add(PaletteAccordion.getSpacer(0,1));
	}

	public void addSpacer(int width, int height)
	{
		content.add(PaletteAccordion.getSpacer(width, height));
	}
	
	public void selectDefault()
	{
		elementMap.get("select").setSelected(true);
	}
	
	public void unselectAllBut(PaletteElement item)
	{
		if(selectedElement != null && selectedElement != item)
			selectedElement.setSelected(false);
	}
	
	public void NotifySelection(PaletteElement item) {
		if(selectedElement != null)
		{
			if(item != selectedElement)
			{
				selectedElement.setSelected(false);
			}	
		}
		
		if(parent.getFrame().getDiagramManager().getCurrentDiagramEditor()!=null)
			parent.getFrame().getDiagramManager().getCurrentDiagramEditor().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		selectedElement = item;
		parent.NotifySelection(item);
		
		for (AppCommandListener listener : listeners) {
			listener.handleCommand(item.getCommand());
		}
	}

	public void addCommandListener(AppCommandListener listener) {
		listeners.add(listener);
	}

	public void removeCommandListener(AppCommandListener listener) {
		listeners.remove(listener);
	}
	
	public void setSelectedLayout()
	{
		title.setBorder(PaletteAccordion.getSelectedPaletteBorder());
		title.setBackground(PaletteAccordion.getSelectedPaletteBackground());
		Icon icon = IconLoader.getInstance().getIcon(IconType.PALETTE_OPEN);
		nameLabel.setIcon(icon);
	}

	public void setUnselectedLayout()
	{
		title.setBorder(PaletteAccordion.getUnselectedPaletteBorder());
		title.setBackground(PaletteAccordion.getUnselectedPaletteBackground());
		Icon icon = IconLoader.getInstance().getIcon(IconType.PALETTE_CLOSED);
		nameLabel.setIcon(icon);
	}
	
	private String getResourceString(String property) {
		return ApplicationResources.getInstance().getString(property);
	}

	public String getName()
	{
		return this.name;
	}

	public PaletteElement getPalleteElement(String name)
	{
		return elementMap.get(name);
	}
	
	public JPanel getTitle() {
		return title;
	}

	public JPanel getContent() {
		return content;
	}
	
	public PaletteElement getSelectedElement(){
		return selectedElement;
	}
}
