package org.incava.attest;

import java.util.List;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

/**
 * By extending this class, subclasses do not require the RunWith(JUnitParamsRunner.class)
 * annotation and the related import statements.
 */
@RunWith(JUnitParamsRunner.class)
public class Parameterized {
    public List<Object[]> paramsList(Object[] ... args) {
        return Parameters.paramsList(args);
    }

    public static Object[] params(Object ... args) {
        return Parameters.params(args);
    }
}
