package com.idcom4.compiler.lexer.parsers.properties;

import com.idcom4.compiler.lexer.tokens.properties.ByteEncodingPropertyToken;

public class ByteEncodingPropertyParser extends PropertyParser<ByteEncodingPropertyToken> {

    public final static String NAME = "byte_encoding";

    public ByteEncodingPropertyParser() {
        super(NAME);
    }

    @Override
    protected ByteEncodingPropertyToken createToken(int indexEnd) {
        return new ByteEncodingPropertyToken(NAME, indexEnd);
    }
}
