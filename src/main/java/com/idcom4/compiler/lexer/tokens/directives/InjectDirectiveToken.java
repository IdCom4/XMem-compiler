package com.idcom4.compiler.lexer.tokens.directives;

public class InjectDirectiveToken extends DirectiveToken {

    public static final String KEYWORD = "inject";

    public InjectDirectiveToken(int indexEnd) {
        super(KEYWORD, indexEnd);
    }
}
