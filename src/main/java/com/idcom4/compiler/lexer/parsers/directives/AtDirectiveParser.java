package com.idcom4.compiler.lexer.parsers.directives;

import com.idcom4.compiler.lexer.tokens.directives.AtDirectiveToken;

public class AtDirectiveParser extends DirectiveParser<AtDirectiveToken> {

    public AtDirectiveParser() {
        super(AtDirectiveToken.KEYWORD);
    }

    @Override
    protected AtDirectiveToken createToken(int indexEnd) {
        return new AtDirectiveToken(indexEnd);
    }
}
