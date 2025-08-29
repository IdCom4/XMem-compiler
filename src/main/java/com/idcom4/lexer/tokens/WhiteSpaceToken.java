package com.idcom4.lexer.tokens;

public class WhiteSpaceToken extends Token {
    public WhiteSpaceToken(int length, int indexEnd) {
        super(" ".repeat(length), indexEnd);
    }
}
