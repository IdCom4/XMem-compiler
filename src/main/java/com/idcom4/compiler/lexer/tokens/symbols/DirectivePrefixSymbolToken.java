package com.idcom4.compiler.lexer.tokens.symbols;

public class DirectivePrefixSymbolToken extends SymbolToken {
    public static final String SYMBOL = "@";

    public DirectivePrefixSymbolToken(int indexEnd) {
        super(SYMBOL, indexEnd);
    }
}
