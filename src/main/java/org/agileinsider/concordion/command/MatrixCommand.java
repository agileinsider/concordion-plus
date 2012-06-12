package org.agileinsider.concordion.command;

import org.concordion.api.CommandCall;
import org.concordion.api.Element;
import org.concordion.api.Evaluator;
import org.concordion.api.ResultRecorder;
import org.concordion.internal.Row;
import org.concordion.internal.Table;
import org.concordion.internal.command.AssertEqualsCommand;

public class MatrixCommand extends AssertEqualsCommand {
    public MatrixCommand() {
    }

    @Override
    public void verify(CommandCall commandCall, Evaluator evaluator, ResultRecorder resultRecorder) {
        Table table = new Table(commandCall.getElement());
        Row headerRow = table.getLastHeaderRow();
        Element[] headerRowCells = headerRow.getCells();
        Row[] rows = table.getDetailRows();
        for (Row detailsRow : rows) {
            Element[] cells = detailsRow.getCells();
            String rowText = cells[0].getText();
            evaluator.setVariable("#row", rowText);
            for (int i = 1; i < cells.length; i++) {
                String columnText = headerRowCells[i].getText();
                Element cell = detailsRow.getCells()[i];
                String cellText = cell.getText();

                evaluator.setVariable("#column", columnText);
                evaluator.setVariable("#cell", cellText);

                commandCall.setElement(cell);

                super.verify(commandCall, evaluator, resultRecorder);
            }
        }
    }
}
