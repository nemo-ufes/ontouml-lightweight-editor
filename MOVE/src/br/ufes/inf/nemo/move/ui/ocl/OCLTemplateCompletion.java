package br.ufes.inf.nemo.move.ui.ocl;

import java.awt.Graphics;

import javax.swing.Icon;

import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.TemplateCompletion;

public class OCLTemplateCompletion extends TemplateCompletion {
	
	private String icon;

	public OCLTemplateCompletion(CompletionProvider provider,
			String inputText, String definitionString, String template) {
		this(provider, inputText, definitionString, template, null);
	}


	public OCLTemplateCompletion(CompletionProvider provider,
			String inputText, String definitionString, String template,
			String shortDesc) {
		this(provider, inputText, definitionString, template, shortDesc, null);
	}


	public OCLTemplateCompletion(CompletionProvider provider,
			String inputText, String definitionString, String template,
			String shortDesc, String summary) {
		super(provider, inputText, definitionString, template, shortDesc, summary);
		setIcon(IconFactory.TEMPLATE_ICON);
	}


	public Icon getIcon() {
		return IconFactory.get().getIcon(icon);
	}


	public void rendererText(Graphics g, int x, int y, boolean selected) {
		OCLShorthandCompletion.renderText(g, getInputText(),
				getShortDescription(), x, y, selected);
	}


	public void setIcon(String iconId) {
		this.icon = iconId;
	}

}
