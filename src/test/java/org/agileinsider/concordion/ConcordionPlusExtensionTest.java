package org.agileinsider.concordion;

import org.concordion.api.extension.ConcordionExtender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConcordionPlusExtensionTest {
    @Mock
    private ScenarioExtension scenarioExtension;
    @Mock
    private MatrixExtension matrixExtension;
    @Mock
    private ConcordionExtender concordionExtender;

    private ConcordionPlusExtension concordionPlusExtension;

    @Before
    public void setUp() throws Exception {
        concordionPlusExtension = new ConcordionPlusExtension(scenarioExtension, matrixExtension);
    }

    @Test
    public void shouldRegisterExtensions() throws Exception {
        concordionPlusExtension.addTo(concordionExtender);

        verify(scenarioExtension).addTo(concordionExtender);
        verify(matrixExtension).addTo(concordionExtender);
    }
}
