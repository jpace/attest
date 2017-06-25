package org.incava.attest;

import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.msg.KeyValueMessage;
import org.incava.attest.msg.StringMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(JUnitParamsRunner.class)
public class MessageTest {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void of(Class<?> expected, Message msg) {
        assertThat(msg, instanceOf(expected));
    }
    
    private Object[][] parametersForOf() {
        return new Object[][] {
            new Object[] { StringMessage.class, Message.of("abc") },
            new Object[] { KeyValueMessage.class, Message.of("abc", 123) },
        };
    }
}

