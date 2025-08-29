package com.idcom4.compiler.lexer.tokens;

import com.idcom4.lexer.tokens.Token;

public class CommentToken  extends Token {
    public CommentToken(String slice, int indexEnd) {
        super(slice, indexEnd);
    }
}
