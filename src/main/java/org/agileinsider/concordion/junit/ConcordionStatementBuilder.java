package org.agileinsider.concordion.junit;

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

import org.junit.runner.notification.RunNotifier;

public class ConcordionStatementBuilder {
    private final Class fixtureClass;
    private Object fixture;
    private RunNotifier runNotifier;

    public ConcordionStatementBuilder(Class<?> fixtureClass) {
        this.fixtureClass = fixtureClass;
    }

    public ConcordionStatementBuilder withFixture(Object fixture) {
        this.fixture = fixture;
        return this;
    }

    public ConcordionStatementBuilder withRunNotifier(RunNotifier runNotifier) {
        this.runNotifier = runNotifier;
        return this;
    }

    public ConcordionStatement buildStatement() {
        return new ConcordionStatement(fixtureClass, fixture, runNotifier);
    }
}
