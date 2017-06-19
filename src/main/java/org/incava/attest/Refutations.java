package org.incava.attest;

import org.junit.Assert;

/**
 * The opposite of an assertion.
 */
public class Refutations {
    public static <T> T refuteNull(T obj, String message) {
        Assert.assertEquals(message, true, obj != null);
        return obj;
    }
    
    public static <T> T refuteNull(T obj) {
        Assert.assertEquals(true, obj != null);
        return obj;
    }
}
