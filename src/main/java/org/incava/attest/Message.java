package org.incava.attest;

import java.util.Arrays;
import org.incava.attest.msg.KeyValueMessage;
import org.incava.attest.msg.StringMessage;

/**
 * A message is a single string, or is a set of key/value pairs. If the latter, then the 
 */
public interface Message {
    public static Message of(String str) {
        // for speed, bypass the factory method in StringMessage
        return new StringMessage(str);
    }

    public static Message of(String key, Object value) {
        // for speed, bypass the factory method in KeyValueMessage
        return new KeyValueMessage(new Object[] { key, value });
    }

    public static Message of(String key, Object[] ary) {
        return new KeyValueMessage(new Object[] { key, ary == null ? null : Arrays.asList(ary) });
    }

    public static Message of(String k1, Object v1, String k2, Object v2) {
        return new KeyValueMessage(new Object[] { k1, v1, k2, v2 });
    }

    public static Message of(String k1, Object v1, String k2, Object v2, String k3, Object v3) {
        return new KeyValueMessage(new Object[] { k1, v1, k2, v2, k3, v3 });
    }

    public static Message of(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4) {
        return new KeyValueMessage(new Object[] { k1, v1, k2, v2, k3, v3, k4, v4 });
    }
}
