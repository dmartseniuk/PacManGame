import javax.swing.table.AbstractTableModel;

public class BoardTableModel extends AbstractTableModel {
    private BoardCell[][] elements;

    public BoardTableModel(BoardCell[][] elements) {
        this.elements = elements;
    }

    @Override
    public int getRowCount() {
        return elements.length;
    }

    @Override
    public int getColumnCount() {
        return elements[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return elements[rowIndex][columnIndex];
    }
}
