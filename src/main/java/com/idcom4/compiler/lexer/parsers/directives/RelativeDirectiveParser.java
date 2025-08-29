package com.idcom4.compiler.lexer.parsers.directives;

import com.idcom4.compiler.lexer.tokens.directives.RelativeDirectiveToken;

public class RelativeDirectiveParser extends DirectiveParser<RelativeDirectiveToken> {

    public RelativeDirectiveParser() {
        super(RelativeDirectiveToken.KEYWORD);
    }

    @Override
    protected RelativeDirectiveToken createToken(int indexEnd) {
        return new RelativeDirectiveToken(indexEnd);
    }
}
