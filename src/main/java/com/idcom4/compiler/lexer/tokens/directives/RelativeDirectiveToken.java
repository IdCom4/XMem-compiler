package com.idcom4.compiler.lexer.tokens.directives;

public class RelativeDirectiveToken extends DirectiveToken {

    public static final String KEYWORD = "relative";

    public RelativeDirectiveToken(int indexEnd) {
        super(KEYWORD, indexEnd);
    }
}
