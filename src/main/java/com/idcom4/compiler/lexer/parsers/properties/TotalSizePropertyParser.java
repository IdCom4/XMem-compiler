package com.idcom4.compiler.lexer.parsers.properties;

import com.idcom4.compiler.lexer.tokens.properties.TotalSizePropertyToken;

public class TotalSizePropertyParser extends PropertyParser<TotalSizePropertyToken> {

    public final static String NAME = "total_size";

    public TotalSizePropertyParser() {
        super(NAME);
    }

    @Override
    protected TotalSizePropertyToken createToken(int indexEnd) {
        return new TotalSizePropertyToken(NAME, indexEnd);
    }
}
