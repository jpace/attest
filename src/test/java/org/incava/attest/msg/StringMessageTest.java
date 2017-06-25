package org.incava.attest.msg;

import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(JUnitParamsRunner.class)
public class StringMessageTest {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toStringTest(String expected, StringMessage msg) {
        String result = msg.toString();
        assertThat(expected, equalTo(result));
    }
    
    private Object[][] parametersForToStringTest() {
        return new Object[][] {
            new Object[] { "abc", new StringMessage("abc") },
            new Object[] { "abc", StringMessage.of("abc") },
        };
    }
}
