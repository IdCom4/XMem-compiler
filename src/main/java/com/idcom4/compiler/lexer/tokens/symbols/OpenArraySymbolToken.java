package com.idcom4.compiler.lexer.tokens.symbols;

public class OpenArraySymbolToken extends SymbolToken {

    public static final String SYMBOL = "[";

    public OpenArraySymbolToken(int indexEnd) {
        super(SYMBOL, indexEnd);
    }
}
