package org.incava.attest;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;

public class StringAssertions {
    public static void assertStartsWith(String str, String substr) {
        assertEqual(true, str.startsWith(substr), message("str", str, "substr", substr));
    }

    public static void assertEndsWith(String str, String substr) {
        assertEqual(true, str.endsWith(substr), message("str", str, "substr", substr));
    }
    
    public static void assertContains(String str, String substr) {
        assertEqual(true, str.contains(substr), message("str", str, "substr", substr));
    }    
}
