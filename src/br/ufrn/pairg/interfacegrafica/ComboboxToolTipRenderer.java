package br.ufrn.pairg.interfacegrafica;

/*classe responsavel por colocar uma tooltip assim que mouse fica posicionado em cima de um elemento da jcombobox da gui inicial da ferramenta*/
import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
public class ComboboxToolTipRenderer extends DefaultListCellRenderer {
    ArrayList tooltips;

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                        int index, boolean isSelected, boolean cellHasFocus) {

        JComponent comp = (JComponent) super.getListCellRendererComponent(list,
                value, index, isSelected, cellHasFocus);

        if (-1 < index && null != value && null != tooltips) {
                    list.setToolTipText((String) tooltips.get(index));
                }
        return comp;
    }

    public void setTooltips(ArrayList tooltips) {
        this.tooltips = tooltips;
    }
}
