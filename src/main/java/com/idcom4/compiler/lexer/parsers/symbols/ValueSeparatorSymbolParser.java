package com.idcom4.compiler.lexer.parsers.symbols;

import com.idcom4.compiler.lexer.tokens.symbols.ValueSeparatorSymbolToken;
import com.idcom4.compiler.lexer.tokens.symbols.SymbolToken;

public class ValueSeparatorSymbolParser extends SymbolParser {

    public ValueSeparatorSymbolParser() {
        super(ValueSeparatorSymbolToken.SYMBOL);
    }

    @Override
    protected SymbolToken createToken(int indexEnd) {
        return new ValueSeparatorSymbolToken(indexEnd);
    }
}
