package org.incava.attest;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class StringAssertionsTest {
    private void assertException(String expMsg, AssertionError ae) {
        Assert.assertEquals(expMsg, ae.getMessage());
    }
    
    @Test
    public void startsWithTrue() {
        StringAssertions.assertStartsWith("abc", "a");
    }
    
    @Test
    public void startsWithFalse() {
        try {
            StringAssertions.assertStartsWith("abc", "b");
            Assert.fail("expected assertion failure");
        }
        catch (AssertionError ae) {
            assertException("str => abc; substr => b expected:<true> but was:<false>", ae);
        }
    }
    
    @Test
    public void endsWithTrue() {
        StringAssertions.assertEndsWith("abc", "c");
    }
    
    @Test
    public void endsWithFalse() {
        try {
            StringAssertions.assertEndsWith("abc", "a");
            Assert.fail("expected assertion failure");
        }
        catch (AssertionError ae) {
            assertException("str => abc; substr => a expected:<true> but was:<false>", ae);
        }
    }
    
    @Test
    public void containsTrue() {
        StringAssertions.assertContains("abc", "a");
    }
    
    @Test
    public void containsFalse() {
        try {
            StringAssertions.assertContains("abc", "d");
            Assert.fail("expected assertion failure");
        }
        catch (AssertionError ae) {
            assertException("str => abc; substr => d expected:<true> but was:<false>", ae);
        }
    }
}
