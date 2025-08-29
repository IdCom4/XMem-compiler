package com.idcom4.utils.scanner;

import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringScanner<X extends Throwable> extends Scanner<Character, X> {

    protected final String str;

    public StringScanner(String str, Supplier<X> outOfBoundException) {
        super(new IScannable<>() {
            @Override
            public Character getValueAt(int index) {
                return str.charAt(index);
            }

            @Override
            public int getLength() {
                return str.length();
            }
        }, outOfBoundException);

        this.str = str;
    }

    public int consumeWhiteSpaces() throws X {
        int consumed = 0;

        Character c;
        while (!this.isFullyConsumed() && (c = this.peek()) != null && Character.isWhitespace(c)) {
            this.skip();
            consumed++;
        }

        return consumed;
    }

    public int consumeUntilNextLine() throws X {
        int consumed = 0;

        Character c;
        while (!this.isFullyConsumed() && (c = this.peek()) != null && c != '\n') {
            this.skip();
            consumed++;
        }

        return consumed;
    }

    public Matcher match(Pattern regexPattern) {
        return regexPattern.matcher(this.str.substring(this.cursor));
    }

    public boolean startsWith(String other) {
        return this.str.substring(this.cursor).startsWith(other);
    }
}
