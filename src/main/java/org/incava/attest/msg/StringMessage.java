package org.incava.attest.msg;

import org.incava.attest.Message;

/**
 * A message is a single string, or is a set of key/value pairs.
 */
public class StringMessage implements Message {
    public static StringMessage of(String str) {
        return new StringMessage(str);
    }
    
    private final String str;
    
    public StringMessage(String str) {
        this.str = str;
    }

    public String toString() {
        return str;
    }
}
