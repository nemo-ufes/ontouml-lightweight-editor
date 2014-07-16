package br.ufes.inf.nemo.assistant.pattern.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	public enum PatternType {
		RelatorCreation,SubkindCreation,RoleMixinPattern,PrincipleIdentity, GeneralizationAndSpecialization_Sukind, GeneralizationAndSpecialization_Role, GeneralizationAndSpecialization_Category, GeneralizationAndSpecialization_Mixin
	}

	public ImagePanel(PatternType type){
		ImageIcon iconLogo = null; 

		switch (type) {
		case RelatorCreation:
			iconLogo = new ImageIcon(getClass().getClassLoader().getResource("resource/RelatorCreation.png"));
			break;
		case SubkindCreation:
			iconLogo = new ImageIcon(getClass().getClassLoader().getResource("resource/SubkindCreation.png"));
			break;
		case RoleMixinPattern:
			iconLogo = new ImageIcon(getClass().getClassLoader().getResource("resource/RoleMixinCreation.png"));
			break;
		case PrincipleIdentity:
			iconLogo = new ImageIcon(getClass().getClassLoader().getResource("resource/PrincipleIdentity.png"));
			break;
		case GeneralizationAndSpecialization_Sukind:
			iconLogo = new ImageIcon(getClass().getClassLoader().getResource("resource/GeneralizationSpecialization_Subkind.png"));
			break;
		case GeneralizationAndSpecialization_Role:
			iconLogo = new ImageIcon(getClass().getClassLoader().getResource("resource/GeneralizationSpecialization_Role.png"));
			break;
		case GeneralizationAndSpecialization_Category:
			iconLogo = new ImageIcon(getClass().getClassLoader().getResource("resource/GeneralizationSpecialization_Category.png"));
			break;
		case GeneralizationAndSpecialization_Mixin:
			iconLogo = new ImageIcon(getClass().getClassLoader().getResource("resource/GeneralizationSpecialization_Mixin.png"));
			break;
		}

		setLayout(null);

		Image img = iconLogo.getImage() ;  
		Image newimg = img.getScaledInstance( 445, 259,  java.awt.Image.SCALE_SMOOTH ) ;  
		iconLogo = new ImageIcon( newimg );

		JPanel imagePanel = new JPanel();
		imagePanel.setBackground(Color.WHITE);
		imagePanel.setBounds(0, 18, iconLogo.getIconWidth(), iconLogo.getIconHeight());
		add(imagePanel);
		JLabel lblImg = new JLabel(iconLogo);
		imagePanel.add(lblImg);

		JLabel lblPatternStructure = new JLabel("Pattern Structure");
		lblPatternStructure.setBounds(3, 1, 166, 14);
		add(lblPatternStructure);

		setSize(new Dimension(iconLogo.getIconWidth(),iconLogo.getIconHeight()+20));
	}

}
