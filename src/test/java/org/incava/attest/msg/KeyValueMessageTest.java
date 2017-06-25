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
public class KeyValueMessageTest {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toStringTest(String expected, KeyValueMessage msg) {
        String result = msg.toString();
        assertThat(expected, equalTo(result));
    }
    
    private Object[][] parametersForToStringTest() {
        return new Object[][] {
            new Object[] { "abc => 123", KeyValueMessage.of("abc", 123) },
            new Object[] { "abc => [1, 2, 3]", KeyValueMessage.of("abc", new String[] { "1", "2", "3" }) },
            new Object[] { "abc => 123", new KeyValueMessage(new Object[] { "abc", 123 }) },
            new Object[] { "abc => 123; def => 456", KeyValueMessage.of("abc", 123, "def", 456) },
        };
    }
}
