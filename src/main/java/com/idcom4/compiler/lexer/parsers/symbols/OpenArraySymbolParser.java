package com.idcom4.compiler.lexer.parsers.symbols;

import com.idcom4.compiler.lexer.tokens.symbols.OpenArraySymbolToken;
import com.idcom4.compiler.lexer.tokens.symbols.SymbolToken;

public class OpenArraySymbolParser extends SymbolParser {

    public OpenArraySymbolParser() {
        super(OpenArraySymbolToken.SYMBOL);
    }

    @Override
    protected SymbolToken createToken(int indexEnd) {
        return new OpenArraySymbolToken(indexEnd);
    }
}
