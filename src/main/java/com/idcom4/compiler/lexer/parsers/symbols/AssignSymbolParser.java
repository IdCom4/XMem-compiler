package com.idcom4.compiler.lexer.parsers.symbols;

import com.idcom4.compiler.lexer.tokens.symbols.AssignSymbolToken;
import com.idcom4.compiler.lexer.tokens.symbols.SymbolToken;

public class AssignSymbolParser extends SymbolParser {

    public AssignSymbolParser() {
        super(AssignSymbolToken.SYMBOL);
    }

    @Override
    protected SymbolToken createToken(int indexEnd) {
        return new AssignSymbolToken(indexEnd);
    }
}
