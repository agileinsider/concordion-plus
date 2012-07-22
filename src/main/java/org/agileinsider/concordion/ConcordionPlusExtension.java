package org.agileinsider.concordion;

import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;

public class ConcordionPlusExtension implements ConcordionExtension {
    public static final String CONCORDION_PLUS_NAMESPACE = "http://www.agileinsider.org/concordion/plus";
    public static final String CONCORDION_PLUS_CSS = "/org/agileinsider/concordion/concordion-plus.css";

    private final ScenarioExtension scenarioExtension;
    private final MatrixExtension matrixExtension;
    private final CsvMatrixExtension csvMatrixExtension;

    public ConcordionPlusExtension() {
        this(new ScenarioExtension(), new MatrixExtension(), new CsvMatrixExtension());
    }

    public ConcordionPlusExtension(ScenarioExtension scenarioExtension, MatrixExtension matrixExtension, CsvMatrixExtension csvMatrixExtension) {
        this.scenarioExtension = scenarioExtension;
        this.matrixExtension = matrixExtension;
        this.csvMatrixExtension = csvMatrixExtension;
    }

    public void addTo(ConcordionExtender concordionExtender) {
        scenarioExtension.addTo(concordionExtender);
        matrixExtension.addTo(concordionExtender);
        csvMatrixExtension.addTo(concordionExtender);
    }
}
