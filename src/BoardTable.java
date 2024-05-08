import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ComponentEvent;

public class BoardTable extends JTable {
    BoardTable(AbstractTableModel model) {
        super(model);
    }


    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex,
                                     int columnIndex) {
        BoardTableCellRenderer boardTableCellRenderer = new BoardTableCellRenderer();
        JComponent component = (JComponent) super.prepareRenderer(boardTableCellRenderer, rowIndex, columnIndex);

        return component;
    }

    @Override
    public void setCellSelectionEnabled(boolean cellSelectionEnabled) {
        super.setCellSelectionEnabled(cellSelectionEnabled);
    }
}
