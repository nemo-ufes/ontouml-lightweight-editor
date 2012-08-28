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

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.ufes.inf.nemo.oled.ui.AppFrame;

/*
 * Features TO-DO
 * ======================================================
 * 
 * Colocar IRI no modelo RefOntoUML
 * Implementar nova transformação para OWL
 * Implementar Salvar como na visualizaão do OWL? Mas sem fazer, desfazer.
 * 
 * Permitir seleção para simulação somente de classes válidas sintaticamente
 * Descição do ultimo comando executado na barra de status
 *   
 * Criar elementos com propriedades default
 * 		Mediation com ends read only.
 * 
 * Implementar associação entre relações
 * Implantar o reposicionamento de labels
 * Permitir auto-relacionamentos
 * Funcionalidade Copiar/Colar
 * Tela Sobre (About) 
 * 
 * Botão para determinar o tipo da conexão Retilinear ou direta.
 * Atualizar nome do aruivo na aba?
 * Atalho para criação de generalization set
 * 		Seleção de várias classes para gerar generalizations e generalization sets
 * 		Generalizations não criadas
 * 
 * 
 *  
 * http://archive.eclipse.org/eclipse/downloads/drops/S-3.7RC4-201106030909/index.php#SWT
 * 
 * OK Implementar tema : Ver cinza e Trapezio
 * OK Tela para seleção de entidades na simulação e das cores no tema
 * OK Geração de tema  
 * OK Retornar texto na simulação, para ser exibido no output pane
 * OK Implementar o retorno OperationResult para validação
 * OK Integrar SBVR : ver deleteonexit pois os arquivos não estão apagando
 * OK Abrir geraçao de OWL no editor de Texto. 
 * OK Implementar seeting com nome default do arquivo 
 * 
 * Antes do release
 * ======================================================
 * 
 * Revisão Geral do Códgio - Limpeza
 * Reestruturação da interface usando form layout.
 * Revisão Geral do Códgio - Documentação
 * Revisão Geral arquivo de captions
 * 
 * 
 * Sem previsão
 * ======================================================
 * 
 * Localization
 * 
 * 
 * Problemas conhecidos
 * ======================================================
 * 
 * - Aberto : Melhorar o tratamento de DataTypes ao inserir no modelo.
 * 			  Ele permitiu a duplicação de datatypes (mesmo nome e mesma id)
 * 			  Além disso, quando um datatype é inserido na janela de edição de classe
 * 			  Não aparece nas dropdowns a menos que seja apertado OK.
 * 
 * - Aberto : o temp dir do diagrama não está sendo excluído automaticamente
 * 
 * - Aberto : Tratar a simulação quando a geração de alloy retorna vazia
 * 
 * - Aberto : Nomes das associações não estão aparecendo
 * 		 	  Connection name labels não estão sendo renderizadas?
 * 
 * - Aberto : Redimensionamento do canvas não está respeitando a posição dos nodos
 * 
 * - Aberto : Classes não estão redimensionando bem
 * 			: Só está acontecendo quando a opção snap to grid está habilitada
 * 
 * - Aberto : Ordenação dos atributos nas propriedades da classe não é respeitada  
 * 
 * - Aberto : Incluir os atributos das relações meronimicas na label da relação
 * 
 * - Aberto : Habilitar o clique com shift para selecionar ou desselecionar o elemento
 * 
 * - Aberto : Ao fazer Undo/Redo algumas ações não tem efeito aparente - Ver linehandler
 * 			  Isso acontece pois o clique está sendo tratado como um comando de mover / redimensionar com nenhuma modificação.
 * 			  Como saber se o usuário está movendo, redimensionando ou selecionando os nodes? O SelectionHandler informará isso.
 * 		      Selecionando = node selecionado ou não posicao do click release igual posição inicial
 * 			  Movendo = node previamente selecionado, posicao do click release diferente da posição inicial
 * 			  Redimensionando = node previamente selecionado...
 * 
 * - Aberto : Transformação para Alloy não está gerando Quantity/SubQuantity
 *
 * - OK : A A4Solution não está sendo gerada com o skolem $visible e 
 * 		: logo não está implementando a associação visible
 * - OK : Tratar os eventos de mudança nas abas do editor para atualizar o redo/undo
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
 * 
 * 
 * Testar se o objeto pertence à interface:
 * Collection.class.isInstance(o)
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
		
		//Needed for the embedded SWT Browser in Linux systems
		System.setProperty("sun.awt.xembedserver", "true");
		
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
