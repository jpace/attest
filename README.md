# Overview

Attest is an extension of JUnit and JUnitParams, modeled somewhat on `test/unit` in Ruby.

# Usage

## Assertions

`org.incava.attest.Assertions` contains alternatives to JUnit4 org.junit.Assert. Consistent with the
test/unit and minitest libraries in Ruby, the primary assertion method is of the form:

```java
   public static <T> T assertEqual(T expected, T actual, String msg);
```

The message is the last parameter, in comparision to it being the first parameter in JUnit4
`Assert.assertEquals`. That's because the message is considered of lesser importance than the
expected value and the result -- my preference is to read the expected value as the first element in
the assertion.

In comparision to `Assert.assertEquals`, which has a void return type, `assertEqual` returns the
`actual` value. That supports a more concise and elegant style of instantiating objects "inside" the
call to `assertEqual`.

```java
   Type expected = new Type(expArgs);
   Type result = assertEqual(expected, new Type(args));
   // work with result
```

Among the variations of `assertEqual`, one supports assertions of C-style arrays (Object[]),
including with better output on failure.

The `message` method converts key/value pairs to more descriptive output on failure of an assertion,
providing more context than just the values being compared:

```java
    import static org.incava.test.Assertions.message;
    // ...
    
    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public <T> void equalsTest(boolean expected, NullableObject<T> nobj, Object obj) {
        boolean result = nobj.equals(obj);
        assertEqual(expected, result, message("nobj", nobj, "obj", obj));
    }
```

## Hamcrest Context Matcher

Hamcrest is great, as are Parameterized tests. But they don't blend especially well sometimes, since
the Hamcrest output is limited in scope, and there is no (evident?) way to pass a message along with
the assertions/matchers, as one can do in plain old JUnit:

```java
    String userName = "one";
    String password = "bad";
    Status status = db.connect(userName, password);
    assertEquals("userName: " + userName + "; password: " + password, Status.CONNECTED, status);
```

The closest in Hamcrest, using describedAs, would be:

```java
    Status status = db.connect(userName, password);
    assertThat(status, describedAs("userName: " + userName + "; password: " + password, is(Status.CONNECTED));
```

But that loses the value of the expected value, with the output of the form:

```
Expected: userName: abc; password: bad
     but: was <INVALID_PASSWORD>
```

So ContextMatcher was written, providing more output, yet retaining the original comparison value:


```java
    assertThat(status, withContext(is(Status.CONNECTED), "userName: " + userName + "; password: " + password));
```

Output:

```
Expected: is <CONNECTED> (userName: abc; password: bad)
     but: was <INVALID_PASSWORD>

```

ContextMatcher accepts strings, as above, and also, in the interest of performance and simplicity,
accepts Messages (org.incava.attest.Message). Using Messages instead of Strings means that
`toString` is called only when necessary, i.e., when a matcher has failed.

Messages can be created with simple syntax by statically importing org.incava.test.Assertions.msg,
and `msg` contains either a string, or key/value pairs from which the string is generated.

```java
    assertThat(status, withContext(is(Status.CONNECTED), msg(userName)));
```

```java
    assertThat(status, withContext(is(Status.CONNECTED), msg("userName", userName, "password", password)));
```
