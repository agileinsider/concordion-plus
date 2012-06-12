package org.agileinsider.concordion;

import org.agileinsider.concordion.junit.ConcordionPlus;
import org.junit.runner.RunWith;

@RunWith(ConcordionPlus.class)
public class Example {
    public String getText() {
        return "success";
    }

    public String getUnexpectedAssertionMessage() {
        return "unexpected message";
    }

    public int multiply(String row, String column, String expected) {
        return Integer.parseInt(row) * Integer.parseInt(column);
    }
}
