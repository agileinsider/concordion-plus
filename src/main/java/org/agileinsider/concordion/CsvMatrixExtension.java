package org.agileinsider.concordion;

import org.agileinsider.concordion.command.CsvMatrixCommand;
import org.concordion.api.CommandCall;
import org.concordion.api.Evaluator;
import org.concordion.api.ResultRecorder;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.listener.AssertEqualsListener;
import org.concordion.internal.listener.AssertResultRenderer;

public class CsvMatrixExtension implements ConcordionExtension {

    public static final String CSV_MATRIX_COMMAND = "csv-matrix";

    private final CsvMatrixCommand csvMatrixCommand;
    private final AssertEqualsListener resultRenderer;

    public CsvMatrixExtension() {
        this(createCsvMatrixCommandSafely(), new AssertResultRenderer());
    }

    protected CsvMatrixExtension(CsvMatrixCommand csvMatrixCommand, AssertEqualsListener resultRenderer) {
        this.csvMatrixCommand = csvMatrixCommand;
        this.resultRenderer = resultRenderer;
    }


    public void addTo(ConcordionExtender concordionExtender) {
        concordionExtender.withCommand(ConcordionPlusExtension.CONCORDION_PLUS_NAMESPACE, CSV_MATRIX_COMMAND, csvMatrixCommand);
        csvMatrixCommand.addAssertEqualsListener(resultRenderer);
    }

    private static CsvMatrixCommand createCsvMatrixCommandSafely() {
        try {
            return new CsvMatrixCommand();
        } catch (Throwable t) {
            return new JCSVUnavailable();
        }
    }

    private static class JCSVUnavailable extends CsvMatrixCommand {
        @Override
        public void verify(CommandCall commandCall, Evaluator evaluator, ResultRecorder resultRecorder) {
            throw new IllegalStateException("Need to ensure that jcvs is available to use cvs-matrix.");
        }
    }
}
