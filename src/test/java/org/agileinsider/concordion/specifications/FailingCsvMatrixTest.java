package org.agileinsider.concordion.specifications;

import org.agileinsider.concordion.ConcordionPlusAcceptanceTest;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class FailingCsvMatrixTest extends ConcordionPlusAcceptanceTest {
    public int multiply(int row, int column) {
        return row * column;
    }
}
