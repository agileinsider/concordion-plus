package org.agileinsider.concordion.render;

/*
 * Copyright 2011 Mark Barnes (mark@agileinsider.org)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import org.agileinsider.concordion.event.ScenarioIgnoredEvent;

import org.concordion.api.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class ScenarioResultRendererTest {

    private ScenarioResultRenderer resultRenderer;

    @Before
    public void setUp() throws Exception {
        resultRenderer = new ScenarioResultRenderer();
    }

    @Test
    public void shouldAddSkipCssClassToElement() throws Exception {
        Element element = new Element("Element");
        ScenarioIgnoredEvent event = new ScenarioIgnoredEvent("not used", element);

        resultRenderer.ignoredReported(event);

        String actual = element.toXML();
        assertThat(actual, containsString("class=\"skip\""));
    }
}