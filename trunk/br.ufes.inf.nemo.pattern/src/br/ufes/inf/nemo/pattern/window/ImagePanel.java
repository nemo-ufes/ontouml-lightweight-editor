package br.ufes.inf.nemo.pattern.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6317159488424565296L;

	public enum PatternType {
		RelatorCreation,SubkindCreation,RoleMixinPattern,PrincipleIdentity, GeneralizationAndSpecialization_Sukind, GeneralizationAndSpecialization_Role, GeneralizationAndSpecialization_Category, GeneralizationAndSpecialization_Mixin, GeneralizationAndSpecialization_RoleMixin, PartitionPattern_Sortal, PartitionPattern_Rigid, AddSupertype_SubstanceSortal, AddSupertype_Subkind, AddSupertype_Role, AddSupertype_Category, AddSupertype_Mixin, AddSupertype_RoleMixin
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
		case GeneralizationAndSpecialization_RoleMixin:
			iconLogo = new ImageIcon(getClass().getClassLoader().getResource("resource/GeneralizationSpecialization_RoleMixin.png"));
			break;
		case PartitionPattern_Sortal:
			iconLogo = new ImageIcon(getClass().getClassLoader().getResource("resource/PartitionPattern_Sortal.png"));
			break;
		case PartitionPattern_Rigid:
			iconLogo = new ImageIcon(getClass().getClassLoader().getResource("resource/PartitionPattern_Rigid.png"));
			break;
		case AddSupertype_SubstanceSortal:
			iconLogo = new ImageIcon(getClass().getClassLoader().getResource("resource/AddSupertype_SubstanceSortal.png"));
			break;
		case AddSupertype_Subkind:
			iconLogo = new ImageIcon(getClass().getClassLoader().getResource("resource/AddSupertype_Subkind.png"));
			break;
		case AddSupertype_Role:
			iconLogo = new ImageIcon(getClass().getClassLoader().getResource("resource/AddSupertype_Role.png"));
			break;
		case AddSupertype_Category:
			iconLogo = new ImageIcon(getClass().getClassLoader().getResource("resource/AddSupertype_Category.png"));
			break;	
		case AddSupertype_Mixin:
			iconLogo = new ImageIcon(getClass().getClassLoader().getResource("resource/AddSupertype_Mixin.png"));
			break;	
		case AddSupertype_RoleMixin:
			iconLogo = new ImageIcon(getClass().getClassLoader().getResource("resource/AddSupertype_RoleMixin.png"));
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
