package org.agileinsider.concordion.specifications;

import org.agileinsider.concordion.ConcordionPlusAcceptanceTest;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class FailingMatrixTest extends ConcordionPlusAcceptanceTest {
    public int multiply(String row, String column) {
        return Integer.parseInt(row) * Integer.parseInt(column);
    }
}
