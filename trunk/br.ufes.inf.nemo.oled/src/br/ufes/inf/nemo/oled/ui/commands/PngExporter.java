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
package br.ufes.inf.nemo.oled.ui.commands;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import br.ufes.inf.nemo.oled.Main;
import br.ufes.inf.nemo.oled.draw.DrawingContext;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.ui.diagram.DiagramEditor;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;
import br.ufes.inf.nemo.oled.util.ApplicationResources;

/**
 * This class exports a diagram to a Portable Network Graphics file.
 *
 * @author Wei-ju Wu, Victor Amorim
 */
public class PngExporter extends FileWriter {

	/**
	 * Export the editor graphics to a file in PNG format.
	 * Edited: exporting now, just the used parts of the model.
	 * @param editor the editor
	 * @param file the file to write
	 * @throws IOException if error occurred
	 */
	public void writePNG(DiagramEditor editor, File file) throws IOException {
		//Dimension size = editor.getTotalCanvasSize();
		ArrayList<Point> points = editor.getUsedCanvasSize();
		Point origin = points.get(0);
		Point end = points.get(1);
		File theFile = getFileWithExtension(file);
		if (canWrite(editor, theFile)) {
			BufferedImage image = new BufferedImage((int) end.x+20, (int) end.y+20, BufferedImage.TYPE_INT_RGB);
			editor.paintComponentNonScreen(image.getGraphics());
			BufferedImage croped = image.getSubimage(origin.x - 20, origin.y - 20, (end.x + 40 - origin.x), (end.y + 40 - origin.y));
			ImageIO.write(croped, "png", theFile);

		}
	}

	/**
	 * Get the used part of the model in a BufferedImage
	 * @author Victor Amorim
	 * */
	public static BufferedImage getPNGImage(StructureDiagram diagram){
		try{
		ArrayList<Point> points = diagram.getUsedCanvasSize();
		Point origin = points.get(0);
		Point end = points.get(1);
		BufferedImage image = new BufferedImage((int) end.x+20, (int) end.y+20, BufferedImage.TYPE_INT_RGB);

		Graphics g =  image.getGraphics();
		Rectangle bounds = new Rectangle(origin.x - 21, origin.y - 21, (end.x + 40 - origin.x), (end.y + 40 - origin.y));
		g.setClip(bounds);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.WHITE);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
		diagram.setGridVisible(false);
		
		DrawingContext dw = ProjectBrowser.frame.getDiagramManager().getDrawingContext();
		dw.setGraphics2D(g2d, bounds);
		diagram.draw(dw, false);

		BufferedImage croped = image.getSubimage(origin.x - 21, origin.y - 21, (end.x + 40 - origin.x), (end.y + 40 - origin.y));
		
		return croped;
		}catch(Exception e){
			Main.printOutLine("Error to generate an intern image from a diagram: "+e.getMessage());	
			JOptionPane.showMessageDialog(null, "Error to generate an intern image from a diagram:\n"+e.getMessage(), ApplicationResources.getInstance().getString("error.readfile.title"), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	protected String getSuffix() { return ".png"; }
}
