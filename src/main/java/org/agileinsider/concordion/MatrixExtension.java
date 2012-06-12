package org.agileinsider.concordion;

import org.agileinsider.concordion.command.MatrixCommand;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.listener.AssertEqualsListener;
import org.concordion.internal.listener.AssertResultRenderer;

public class MatrixExtension implements ConcordionExtension {
    public static final String MATRIX_COMMAND = "matrix";

    private final MatrixCommand matrixCommand;
    private final AssertEqualsListener resultRenderer;

    public MatrixExtension() {
        this(new MatrixCommand(), new AssertResultRenderer());
    }

    protected MatrixExtension(MatrixCommand matrixCommand, AssertEqualsListener resultRenderer) {
        this.matrixCommand = matrixCommand;
        this.resultRenderer = resultRenderer;
    }


    public void addTo(ConcordionExtender concordionExtender) {
        concordionExtender.withCommand(ConcordionPlusExtension.CONCORDION_PLUS_NAMESPACE, MATRIX_COMMAND, matrixCommand);
        matrixCommand.addAssertEqualsListener(resultRenderer);
    }
}
