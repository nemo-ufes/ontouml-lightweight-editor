/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OLED (OntoUML Lightweight Editor).
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

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.ufes.inf.nemo.oled.ui.AppFrame;

/*
 * TODO
 * 
 * Implementar associação entre relações
 * Implantar o reposicionamento de labels
 * Permitir auto-relacionamentos
 * Funcionalidade Copiar/Colar
 * Tela Sobre (About) 
 * 
 * Before release
 * 
 * Revisão Geral do Códgio - Limpeza
 * Revisão Geral do Códgio - Documentação
 * Revisão Geral arquivo de captions
 * 
 * Sem previsão
 * 
 * Localization
 * 
 * 
 *  ISSUES
 *  
 * - Aberto : Classes não estão redimensionando bem
 * 			: Só está acontecendo quando a opção snap to grid está habilitada
 * - Aberto : Ordenação dos atributos nas propriedades da classe não é respeitada  
 * - Aberto : Incluir os atributos das relações meronimicas na label da relação
 * - Aberto : Tratar os eventos de mudança nas abas do editor para atualizar o redo/undo
 * - Aberto : Habilitar o clique com shift para selecionar ou desselecionar o elemento
 * - Aberto : Tratar a simulação quando a geração de alloy retorna vazia
 * 
 * 
 * - OK : Setas do output pane não estão funcionando
 * - OK : Erro ao transferir dados na janela de edição de classe (invalid cast) 
 * - OK : Erro na geração de alloy (Bernardo) quando uma category (mixin) é supertipo
 * - OK : Generalizar idioma da janela de generalização
 * - OK : Implementar a opção abstract na janela de classe
 * - OK : Implantar novo sistema de serialização/desserialização baseado em Zip
 * - OK : Remover {disjoint, complete} da label das generalizations
 * - OK : Renomear itens de menu para open project e save project
 * - OK : Redimensionar tamanho inicial da janela principal para acomodar resolções 1024x768
 * - OK : Undo/Redo para ações visuais como redimensionamento não estão funcionando bem
 * - Aberto para relações Ver linehandler : Ao fazer Undo/Redo algumas ações não tem efeito aparente - Isso acontece pois o clique está sendo tratado como um comando de mover / redimensionar com nenhuma modificação.
 * 		: Como saber se o usuário está movendo, redimensionando ou selecionando os nodes? O SelectionHandler informará isso.
 * 		: Selecionando = node selecionado ou não posicao do click release igual posição inicial
 * 		: Movendo = node previamente selecionado, posicao do click release diferente da posição inicial
 * 		: Redimensionando = node previamente selecionado...
 * 
 */


/**
 * This is the start class of the OLED application. OLED is based on the TinyUML project by Wei-ju Wu.
 */
public final class Main {

	/**
	 * Private constructor.
	 */
	private Main() { }

	/**
	 * The start method for this application.
	 * @param args the command line parameters
	 */
	public static void main(String[] args) {

		// Mac OS X settings
		if (System.getProperty("mrj.version") != null) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("com.apple.eawt.CocoaComponent.CompatibilityMode",
			"false");
			System.setProperty("com.apple.mrj.application.apple.menu.about.name",
			"OLED");
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			/**
			 * {@inheritDoc}
			 */
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					UIManager.put("TabbedPane.focus", new Color(0, 0, 0, 0));
					JFrame frame = new AppFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}
}
