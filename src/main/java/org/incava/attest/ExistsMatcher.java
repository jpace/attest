package org.incava.attest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsNull;

/**
 * Similar to DescribedAsMatcher, ContextMatcher wraps a matcher with context, displayed as
 * "&lt;expected&gt; (context)". This is mainly for use in parameterized tests, such as (from ijdk):
 *
 * <pre>
 * {@literal @}Test
 * {@literal @}Parameters
 * {@literal @}TestCaseName("{method} {index} {params}")
 * public &lt;T extends Comparable&lt;T&gt;&gt; void compare(int expected, T x, T y) {
 *     int result = Comp.compare(x, y);
 *     assertThat(result, withContext(equalTo(expected), "x: " + x + "; y: " + y));
 * }
 * </pre>
 *
 * The optional usage of instances of Message will result in <code>toString</code> being called only
 * when necessary, that is, for a failed matcher. Therefore there is less overhead than using simple
 * strings.
 */
public class ExistsMatcher {
    /**
     * Creates an exists matcher. 
     */
    public static Matcher<Object> exists(boolean expected) {
        if (expected) {
            return IsNull.notNullValue();
        }
        else { 
            return IsNull.nullValue();
        }
    }
}
