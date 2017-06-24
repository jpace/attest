package org.incava.attest;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.describedAs;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.incava.attest.ContextMatcher.withContext;

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
            assertThat(status, describedAs("userName: " + userName + "; password: " + password, is(Status.INVALID_PASSWORD)));
        }
        catch (AssertionError ex) {
            String exp = "\n" +
                "Expected: userName: abc; password: bad\n" +
                "     but: was <CONNECTED>";
            assertThat(ex.getMessage(), is(exp));
        }
    }
    
    @Test
    public void context() {
        try {
            assertThat(status, withContext(is(Status.INVALID_PASSWORD), "userName: " + userName + "; password: " + password));
        }
        catch (AssertionError ex) {
            String exp = "\n" +
                "Expected: is <INVALID_PASSWORD> (userName: abc; password: bad)\n" +
                "     but: was <CONNECTED>";
            assertThat(ex.getMessage(), is(exp));
        }
    }
}
