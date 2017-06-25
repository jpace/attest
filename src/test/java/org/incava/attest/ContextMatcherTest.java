package org.incava.attest;

import java.util.ArrayList;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.describedAs;
import static org.hamcrest.Matchers.is;
import static org.incava.attest.ContextMatcher.withContext;

@RunWith(JUnitParamsRunner.class)
public class ContextMatcherTest {
    enum Status {
        CONNECTED,
        INVALID_PASSWORD;
    }

    private String userName;
    private String password;
    private Status status;

    @Before
    public void setup() {
        userName = "abc";
        password = "bad";
        status = Status.CONNECTED;
    }
    
    @Test
    public void bare() {
        try {
            assertThat(status, is(Status.INVALID_PASSWORD));
        }
        catch (AssertionError ex) {
            String exp = "\n" +
                "Expected: is <INVALID_PASSWORD>\n" +
                "     but: was <CONNECTED>";
            assertThat(ex.getMessage(), is(exp));
        }
    }    
    
    @Test
    public void described() {
        try {
            String msgStr = "userName: " + userName + "; password: " + password;
            assertThat(status, describedAs(msgStr, is(Status.INVALID_PASSWORD)));
        }
        catch (AssertionError ex) {
            String exp = "\n" +
                "Expected: userName: abc; password: bad\n" +
                "     but: was <CONNECTED>";
            assertThat(ex.getMessage(), is(exp));
        }
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void context(String expected, AssertionError ae) {
        assertThat(ae.getMessage(), is(expected));
    }
    
    private List<Object[]> parametersForContext() {
        List<Object[]> params = new ArrayList<Object[]>();

        String msgStr = "userName: abc; password: bad";        
        String expStr = "\n" +
            "Expected: is <INVALID_PASSWORD> (userName: abc; password: bad)\n" +
            "     but: was <CONNECTED>";
        
        try {
            assertThat(Status.CONNECTED, withContext(is(Status.INVALID_PASSWORD), msgStr));
        }
        catch (AssertionError ex) {        
            params.add(new Object[] { expStr, ex });
        }
        try {
            assertThat(Status.CONNECTED, withContext(msgStr, is(Status.INVALID_PASSWORD)));
        }
        catch (AssertionError ex) {        
            params.add(new Object[] { expStr, ex });
        }

        Message msg = Assertions.msg("userName", "abc", "password", "bad");
        String expMsg = "\n" +
            "Expected: is <INVALID_PASSWORD> (userName => abc; password => bad)\n" +
            "     but: was <CONNECTED>";
        
        try {
            assertThat(Status.CONNECTED, withContext(is(Status.INVALID_PASSWORD), msg));
        }
        catch (AssertionError ex) {        
            params.add(new Object[] { expMsg, ex });
        }
        try {
            assertThat(Status.CONNECTED, withContext(msg, is(Status.INVALID_PASSWORD)));
        }
        catch (AssertionError ex) {        
            params.add(new Object[] { expMsg, ex });
        }
        return params;
    }
}
