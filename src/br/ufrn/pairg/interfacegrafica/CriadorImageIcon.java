package br.ufrn.pairg.interfacegrafica;

import javax.swing.ImageIcon;

public class CriadorImageIcon {

	  /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = CriadorImageIcon.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }


}
