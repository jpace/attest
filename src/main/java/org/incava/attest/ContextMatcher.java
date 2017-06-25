package org.incava.attest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

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
public class ContextMatcher<T> extends BaseMatcher<T> {
    /**
     * Creates a context matcher. 
     */
    public static <T> ContextMatcher<T> withContext(Message context, Matcher<T> matcher) {
        return new ContextMatcher<T>(matcher, null, context);
    }
    
    /**
     * Creates a context matcher. 
     */
    public static <T> ContextMatcher<T> withContext(Matcher<T> matcher, Message context) {
        return new ContextMatcher<T>(matcher, null, context);
    }
    
    /**
     * Creates a context matcher. 
     */
    public static <T> ContextMatcher<T> withContext(String context, Matcher<T> matcher) {
        return new ContextMatcher<T>(matcher, context, null);
    }
    
    /**
     * Creates a context matcher. 
     */
    public static <T> ContextMatcher<T> withContext(Matcher<T> matcher, String context) {
        return new ContextMatcher<T>(matcher, context, null);
    }
    
    private final Matcher<T> matcher;
    private final String str;
    private final Message msg;
    
    public ContextMatcher(Matcher<T> matcher, String str, Message msg) {
        this.matcher = matcher;
        this.str = str;
        this.msg = msg;
    }

    @Override
    public boolean matches(Object obj) {
        return matcher.matches(obj);
    }

    @Override
    public void describeTo(Description description) {
        matcher.describeTo(description);
        description.appendText(" (");
        description.appendText(msg == null ? str : msg.toString());
        description.appendText(")");
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        matcher.describeMismatch(item, description);
    }   
}
