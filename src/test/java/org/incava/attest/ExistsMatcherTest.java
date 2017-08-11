package org.incava.attest;

import java.util.ArrayList;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.incava.attest.ExistsMatcher.exists;

@RunWith(JUnitParamsRunner.class)
public class ExistsMatcherTest {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void existsTest(String expected, Object obj, boolean objExpected) {
        String excMsg = null;
        try {
            assertThat(obj, exists(objExpected));
        }
        catch (AssertionError ae) {
            excMsg = ae.getMessage();
        }
        assertThat(excMsg, expected == null ? nullValue() : startsWith(expected));
    }
    
    private List<Object[]> parametersForExistsTest() {
        List<Object[]> params = new ArrayList<Object[]>();
        
        params.add(new Object[] { null,
                                  new Object(),
                                  true
            });

        params.add(new Object[] { "\n" +
                                  "Expected: not null\n" +
                                  "     but: was null",
                                  null,
                                  true
            });

        params.add(new Object[] { "\n" +
                                  "Expected: null\n" +
                                  "     but: was <", // class and hashCode here, which obviously vary
                                  new Object(),
                                  false
            });

        params.add(new Object[] { null,
                                  null,
                                  false
            });
        
        return params;
    }
}
