package com.idcom4.compiler.lexer.parsers.symbols;

import com.idcom4.compiler.lexer.tokens.symbols.DirectivePrefixSymbolToken;
import com.idcom4.compiler.lexer.tokens.symbols.SymbolToken;

public class DirectivePrefixSymbolParser extends SymbolParser {

    public DirectivePrefixSymbolParser() {
        super(DirectivePrefixSymbolToken.SYMBOL);
    }

    @Override
    protected SymbolToken createToken(int indexEnd) {
        return new DirectivePrefixSymbolToken(indexEnd);
    }
}
