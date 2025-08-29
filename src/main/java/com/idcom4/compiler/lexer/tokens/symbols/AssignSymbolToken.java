package com.idcom4.compiler.lexer.tokens.symbols;

public class AssignSymbolToken extends SymbolToken {

    public static final String SYMBOL = "=";

    public AssignSymbolToken(int indexEnd) {
        super(SYMBOL, indexEnd);
    }
}
