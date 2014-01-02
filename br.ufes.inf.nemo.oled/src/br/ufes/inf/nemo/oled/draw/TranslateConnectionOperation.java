/**
 * Copyright 2007 Wei-ju Wu
 *
 * This file is part of TinyUML.
 *
 * TinyUML is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * TinyUML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TinyUML; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.draw;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


/**
 * This class translates a connection.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class TranslateConnectionOperation extends MoveOperation {

	private static final long serialVersionUID = 6842161064107530968L;
	private Connection connection;
	private double translationX, translationY;
	private List<Point2D> originalPoints;

	/**
	 * Constructor.
	 * @param conn the connection
	 * @param transx the translation x
	 * @param transy the translation y
	 */
	public TranslateConnectionOperation(Connection conn, double transx,
			double transy) {
		connection = conn;
		translationX = transx;
		translationY = transy;
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {
		translateConnection(connection);		
	}

	private void translateConnection(Connection connection){
		originalPoints = new ArrayList<Point2D>();
		List<Point2D> newPoints = new ArrayList<Point2D>();
		for (Point2D point : connection.getPoints()) {
			originalPoints.add((Point2D) point.clone());
			newPoints.add(new Point2D.Double(point.getX() + translationX,
					point.getY() + translationY));
		}
		connection.setPoints(newPoints);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		connection.setPoints(originalPoints);
	}

	/**
	 * {@inheritDoc}
	 */
	public void redo() {
		run();
	}
	
	public Connection getConnection() {
		return connection;
	}
}
