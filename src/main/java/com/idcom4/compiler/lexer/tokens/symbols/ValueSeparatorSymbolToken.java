package com.idcom4.compiler.lexer.tokens.symbols;

public class ValueSeparatorSymbolToken extends SymbolToken {

    public static final String SYMBOL = ",";

    public ValueSeparatorSymbolToken(int indexEnd) {
        super(SYMBOL, indexEnd);
    }
}
