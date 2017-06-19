package org.incava.test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;

public class StringAssertions {
    public void assertStartsWith(String str, String substr) {
        assertEqual(true, str.startsWith(substr), message("str", str, "substr", substr));
    }
}
