package org.agileinsider.concordion.command;

import ognl.DefaultMemberAccess;
import ognl.Ognl;
import ognl.OgnlContext;
import org.concordion.api.Evaluator;

public class ExposeFixtureClassHack {
    static Class getFixtureClass(Evaluator evaluator) {
        try {
            OgnlContext permissiveContext = new OgnlContext();
            permissiveContext.setMemberAccess(new DefaultMemberAccess(true));
            Object rootObject = Ognl.getValue("rootObject", permissiveContext, evaluator);
            return rootObject.getClass();
        } catch (Exception e) {
            throw new RuntimeException("Nasty hack exposed!!!", e);
        }

    }
}
