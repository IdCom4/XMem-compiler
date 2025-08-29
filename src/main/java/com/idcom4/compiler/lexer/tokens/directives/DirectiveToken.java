package com.idcom4.compiler.lexer.tokens.directives;

import com.idcom4.lexer.tokens.Token;

public abstract class DirectiveToken extends Token {
    public DirectiveToken(String slice, int indexEnd) {
        super(slice, indexEnd);
    }
}
