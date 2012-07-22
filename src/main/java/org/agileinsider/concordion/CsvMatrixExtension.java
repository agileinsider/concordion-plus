package org.agileinsider.concordion;

import org.agileinsider.concordion.command.CsvMatrixCommand;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.listener.AssertEqualsListener;
import org.concordion.internal.listener.AssertResultRenderer;

public class CsvMatrixExtension implements ConcordionExtension {

    public static final String CSV_MATRIX_COMMAND = "csv-matrix";

    private final CsvMatrixCommand csvMatrixCommand;
    private final AssertEqualsListener resultRenderer;

    public CsvMatrixExtension() {
        this(new CsvMatrixCommand(), new AssertResultRenderer());
    }

    protected CsvMatrixExtension(CsvMatrixCommand csvMatrixCommand, AssertEqualsListener resultRenderer) {
        this.csvMatrixCommand = csvMatrixCommand;
        this.resultRenderer = resultRenderer;
    }


    public void addTo(ConcordionExtender concordionExtender) {
        concordionExtender.withCommand(ConcordionPlusExtension.CONCORDION_PLUS_NAMESPACE, CSV_MATRIX_COMMAND, csvMatrixCommand);
        csvMatrixCommand.addAssertEqualsListener(resultRenderer);
    }

}
