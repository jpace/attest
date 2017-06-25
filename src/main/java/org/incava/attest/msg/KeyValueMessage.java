package org.incava.attest.msg;

import java.util.Arrays;
import org.incava.attest.Message;

/**
 * A set of key/value pairs. The value of <code>toString</code> is calculated only when necessary,
 * so Messages can be used in logging without the overhead or side effects of <code>toString</code>
 * being called on the elements in the logging statement.
 */
public class KeyValueMessage implements Message {
    public static KeyValueMessage of(String key, Object value) {
        return new KeyValueMessage(new Object[] { key, value });
    }

    public static KeyValueMessage of(String key, Object[] ary) {
        return of(key, ary == null ? null : Arrays.asList(ary));
    }

    public static KeyValueMessage of(String k1, Object v1, String k2, Object v2) {
        return new KeyValueMessage(new Object[] { k1, v1, k2, v2 });
    }

    public static KeyValueMessage of(String k1, Object v1, String k2, Object v2, String k3, Object v3) {
        return new KeyValueMessage(new Object[] { k1, v1, k2, v2, k3, v3 });
    }

    public static KeyValueMessage of(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4) {
        return new KeyValueMessage(new Object[] { k1, v1, k2, v2, k3, v3, k4, v4 });
    }
    
    private final Object[] ary;    
    
    public KeyValueMessage(Object[] ary) {
        this.ary = ary;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < ary.length / 2; ++idx) {
            if (idx > 0) {
                sb.append("; ");
            }
            sb.append(ary[idx * 2]).append(" => ").append(ary[idx * 2 + 1]);
        }
        return sb.toString();
    }
}
