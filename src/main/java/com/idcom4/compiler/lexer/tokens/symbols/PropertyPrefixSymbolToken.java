package com.idcom4.compiler.lexer.tokens.symbols;

public class PropertyPrefixSymbolToken extends SymbolToken {

    public static final String SYMBOL = "$";

    public PropertyPrefixSymbolToken(int indexEnd) {
        super(SYMBOL, indexEnd);
    }
}
