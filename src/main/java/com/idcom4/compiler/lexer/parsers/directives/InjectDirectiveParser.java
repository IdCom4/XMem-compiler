package com.idcom4.compiler.lexer.parsers.directives;

import com.idcom4.compiler.lexer.tokens.directives.InjectDirectiveToken;

public class InjectDirectiveParser extends DirectiveParser<InjectDirectiveToken> {

    public InjectDirectiveParser() {
        super(InjectDirectiveToken.KEYWORD);
    }

    @Override
    protected InjectDirectiveToken createToken(int indexEnd) {
        return new InjectDirectiveToken(indexEnd);
    }
}
