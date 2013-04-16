package br.ufes.inf.nemo.move.util.oclview;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.Icon;

import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;

class OCLShorthandCompletion extends ShorthandCompletion {
	
	private static final Color SHORTHAND_COLOR	= new Color(0, 127, 174);

	/**
	 * Constructor.
	 *
	 * @param provider
	 * @param inputText
	 * @param replacementText
	 */
	public OCLShorthandCompletion(CompletionProvider provider,
			String inputText, String replacementText) {
		super(provider, inputText, replacementText);
	}


	/**
	 * Constructor.
	 *
	 * @param provider
	 * @param inputText
	 * @param replacementText
	 * @param shortDesc
	 */
	public OCLShorthandCompletion(CompletionProvider provider,
			String inputText, String replacementText, String shortDesc) {
		super(provider, inputText, replacementText, shortDesc);
	}


	/**
	 * {@inheritDoc}
	 */
	public Icon getIcon() {
		return IconFactory.get().getIcon(IconFactory.TEMPLATE_ICON);
	}

	/**
	 * {@inheritDoc}
	 */
	public void rendererText(Graphics g, int x, int y, boolean selected) {
		renderText(g, getInputText(), getReplacementText(), x, y, selected);
	}


	/**
	 * Renders a completion in the style of a short-hand completion.
	 * 
	 * @param g The graphics context.
	 * @param input The text the user enters to display this completion.
	 * @param shortDesc An optional short description of the completion.
	 * @param x The x-offset at which to paint.
	 * @param y The y-offset at which to paint.
	 * @param selected Whether this completion choice is selected.
	 */
	public static void renderText(Graphics g, String input, String shortDesc,
								int x, int y, boolean selected) {
		Color orig = g.getColor();
		if (!selected && shortDesc!=null) {
			g.setColor(SHORTHAND_COLOR);
		}
		g.drawString(input, x, y);
		if (shortDesc!=null) {
			x += g.getFontMetrics().stringWidth(input);
			if (!selected) {
				g.setColor(orig);
			}
			String temp = " - ";
			g.drawString(temp, x, y);
			x += g.getFontMetrics().stringWidth(temp);
			if (!selected) {
				g.setColor(Color.GRAY);
			}
			g.drawString(shortDesc, x, y);
		}
	}
}