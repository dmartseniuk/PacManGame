import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class BoardTableCellRenderer implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                            boolean isSelected, boolean hasFocus,
                                            int row, int column) {
        int cellSize = table.getRowHeight();

        BoardCell cellValue = (BoardCell)value;

        BoardRenderCell boardRenderCell = GameManager.emptyCell;

        if(cellValue.isWall) {
            boardRenderCell = GameManager.wallCell;
        }
        else if (cellValue.isCoin) {
            boardRenderCell = GameManager.coinCell;
        }
        if(GameManager.pacman.getPositionX() == column && GameManager.pacman.getPositionY() == row) {
            boardRenderCell = GameManager.pacman.getRenderCell();
        }
        else {
            for(Upgrade upgrade : GameManager.upgrades) {
                if (column == upgrade.getPositionX() && row == upgrade.getPositionY()) {
                    boardRenderCell = upgrade.getRenderCell();
                }
            }
            for (int i = 0; i < GameManager.ghosts.length; i++) {
                if (column == GameManager.ghosts[i].getPositionX() && row == GameManager.ghosts[i].getPositionY()) {
                    boardRenderCell = GameManager.ghosts[i].getRenderCell();
                }
            }
        }

        boardRenderCell.setSize(cellSize,cellSize);
        return  boardRenderCell;
    }
}
