package com.idcom4.compiler.lexer.tokens.directives;

public class AtDirectiveToken extends DirectiveToken {

    public static final String KEYWORD = "at";

    public AtDirectiveToken(int indexEnd) {
        super(KEYWORD, indexEnd);
    }
}
