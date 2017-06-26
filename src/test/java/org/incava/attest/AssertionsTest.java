package org.incava.attest;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class AssertionsTest {
    private void assertException(String expMsg, AssertionError ae) {
        Assert.assertEquals(expMsg, ae.getMessage());
    }
    
    @Test
    public void assertEqualObjectsTrue() {
        Assertions.assertEqual("abc", "abc");
    }
    
    @Test
    public void assertEqualObjectsFalse() {
        try {
            Assertions.assertEqual("abc", "def");
            Assert.fail("expected assertion failure");
        }
        catch (AssertionError ae) {
            assertException("expected:<[abc]> but was:<[def]>", ae);
        }
    }
}
