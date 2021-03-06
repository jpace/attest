package org.incava.attest;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Assert;

/**
 * An enhanced set of low level assertions. All assertions return a value for potential chaining.
 *
 * @see org.incava.attest.Refutations
 */
public class Assertions {
    // assertEqual (not assertEquals, for distinction from JUnit methods)

    // more like Ruby (imperative; message as last parameter)
    public static <T> T assertEqual(T expected, T actual, String name, Object value) {
        return assertEqual(expected, actual, Message.of(name, value));
    }

    @SuppressWarnings("unchecked")
    public static <T> T assertEqual(T expected, T actual, String msg) {
        if (areArrays(expected, actual)) {
            assertEqual((T[])expected, (T[])actual, msg);
            return null;        // cannot cast T[] to T, of course
        }
        else {
            Assert.assertEquals(msg, expected, actual);
            return actual;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T assertEqual(T expected, T actual, Message msg) {
        if (areArrays(expected, actual)) {
            assertEqual((T[])expected, (T[])actual, msg);
            return null;        // cannot cast T[] to T, of course
        }
        else {
            Assert.assertEquals(msg.toString(), expected, actual);
            return actual;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T assertEqual(T expected, T actual) {
        if (expected != null && expected.getClass().isArray() && actual != null && actual.getClass().isArray()) {
            assertEqual((T[])expected, (T[])actual);
            return null;
        }
        else {
            Assert.assertEquals(expected, actual);
            return actual;
        }
    }

    public static <T> T[] assertEqual(T[] expected, T[] actual, String msg) {
        assertEqual(expected == null ? null : Arrays.asList(expected), actual == null ? null : Arrays.asList(actual), msg);
        return actual;
    }

    public static <T> T[] assertEqual(T[] expected, T[] actual, Message msg) {
        assertEqual(expected == null ? null : Arrays.asList(expected), actual == null ? null : Arrays.asList(actual), msg);
        return actual;
    }

    public static <T> T[] assertEqual(T[] expected, T[] actual) {
        assertEqual(expected == null ? null : Arrays.asList(expected), actual == null ? null : Arrays.asList(actual));
        return actual;
    }

    // object same as other (same reference/pointer)

    public static <T> T assertSame(T expected, T actual) {
        Assert.assertSame(expected, actual);
        return actual;
    }

    public static <T> T assertSame(T expected, T actual, String message) {
        Assert.assertSame(message, expected, actual);
        return actual;
    }

    // object == or != null

    public static <T> T assertNull(T obj, String message) {
        Assert.assertEquals(message, true, obj == null);
        return obj;
    }
    
    public static <T> T assertNull(T obj) {
        Assert.assertEquals(true, obj == null);
        return obj;
    }

    public static <T> T denyNull(T obj, String message) {
        Assert.assertEquals(message, true, obj != null);
        return obj;
    }
    
    public static <T> T denyNull(T obj) {
        Assert.assertEquals(true, obj != null);
        return obj;
    }    
    
    public static <T> T assertNotNull(boolean isExpected, T obj) {
        Assert.assertEquals(isExpected, obj != null);
        return obj;
    }

    public static <T> T assertNotNull(T obj, String message) {
        Assert.assertEquals(message, true, obj != null);
        return obj;
    }    
    
    public static <T> T assertNotNull(T obj) {
        Assert.assertEquals(true, obj != null);
        return obj;
    }
    
    public static <T> T assertIsNull(boolean isExpected, T obj) {
        Assert.assertEquals(isExpected, obj == null);
        return obj;
    }    
    
    public static <T> T assertIsNull(T obj, String message) {
        Assert.assertEquals(message, true, obj == null);
        return obj;
    }
    
    public static <T> T assertIsNull(T obj) {
        Assert.assertEquals(true, obj == null);
        return obj;
    }

    // collection == collection

    public static <C extends Collection<T>, T> C assertEqual(C expected, C actual) {
        if (expected == null) {
            Assert.assertNull(actual);
            return actual;
        }
        
        Iterator<T> ei = expected.iterator();
        Iterator<T> ai = actual.iterator();

        int idx = 0;

        while (ei.hasNext()) {
            T expObj = ei.next();
            if (ai.hasNext()) {
                T actObj = ai.next();
                assertEqual(expObj, actObj, message("index", idx));
            }
            else {
                String msg = "expected object " + expObj + " at index " + idx + ", but at the end of the collection";
                Assert.fail(msg);
            }
        }

        if (ai.hasNext()) {
            T actObj = ai.next();
            String msg = "unexpected object " + actObj + " at index " + idx;
            Assert.fail(msg);
        }
        
        return actual;
    }    

    // compareTo
    
    public static <T extends Comparable<T>> int assertCompareTo(int expected, T x, T y) {
        int result = x.compareTo(y);
        int cmp = result == 0 ? 0 : result / Math.abs(result); // -3 =>> -1, 5 =>> 1
        return assertEqual(expected, cmp, "x: " + x + "; y: " + y + "; result: " + result);
    }

    // better messages (message("name", value) => "name: #{value}")

    /**
     * Returns this key/value pair as a string, in the form "#{key} =&gt; #{value}".
     *
     * @return the key/value string
     */
    public static String toString(String key, Object value) {
        return String.valueOf(key) + " => " + String.valueOf(value);
    }    

    public static String message(String key, Object value) {
        return toString(key, value);
    }

    public static String message(String key, Object[] ary) {
        return toString(key, ary == null ? null : Arrays.asList(ary));
    }

    public static String message(String k1, Object v1, String k2, Object v2) {
        return message(k1, v1) + "; " + message(k2, v2);
    }

    public static String message(String k1, Object v1, String k2, Object v2, String k3, Object v3) {
        return message(k1, v1, k2, v2) + "; " + message(k3, v3);
    }

    public static String message(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4) {
        return message(k1, v1, k2, v2, k3, v3) + "; " + message(k4, v4);
    }

    public static Message msg(String key, Object value) {
        return Message.of(key, value);
    }

    public static Message msg(String key, Object[] ary) {
        return Message.of(key, ary);
    }

    public static Message msg(String k1, Object v1, String k2, Object v2) {
        return Message.of(k1, v1, k2, v2);
    }

    public static Message msg(String k1, Object v1, String k2, Object v2, String k3, Object v3) {
        return Message.of(k1, v1, k2, v2, k3, v3);
    }

    public static Message msg(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4) {
        return Message.of(k1, v1, k2, v2, k3, v3, k4, v4);
    }

    private static <T> boolean areArrays(T x, T y) {
        return x != null && x.getClass().isArray() && y != null && y.getClass().isArray();
    }
}
