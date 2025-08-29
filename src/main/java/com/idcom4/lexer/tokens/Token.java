package com.idcom4.lexer.tokens;

public abstract class Token {

    protected final int indexStart;
    protected final int indexEnd;
    protected final String slice;

    public Token(String slice, int indexEnd) {
        this.slice = slice;
        this.indexStart = indexEnd - slice.length();
        this.indexEnd = indexEnd;
    }

    public int getIndexStart() {
        return this.indexStart;
    }

    public int getIndexEnd() {
        return this.indexEnd;
    }

    public String getSlice() {
        return this.slice;
    }

}
