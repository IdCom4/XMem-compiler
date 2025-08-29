package com.idcom4.compiler.lexer.parsers.symbols;

import com.idcom4.compiler.lexer.tokens.symbols.CloseArraySymbolToken;
import com.idcom4.compiler.lexer.tokens.symbols.SymbolToken;

public class CloseArraySymbolParser extends SymbolParser {

    public CloseArraySymbolParser() {
        super(CloseArraySymbolToken.SYMBOL);
    }

    @Override
    protected SymbolToken createToken(int indexEnd) {
        return new CloseArraySymbolToken(indexEnd);
    }
}
