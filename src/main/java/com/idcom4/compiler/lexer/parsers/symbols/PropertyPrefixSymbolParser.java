package com.idcom4.compiler.lexer.parsers.symbols;

import com.idcom4.compiler.lexer.tokens.symbols.PropertyPrefixSymbolToken;
import com.idcom4.compiler.lexer.tokens.symbols.SymbolToken;

public class PropertyPrefixSymbolParser extends SymbolParser {

    public PropertyPrefixSymbolParser() {
        super(PropertyPrefixSymbolToken.SYMBOL);
    }

    @Override
    protected SymbolToken createToken(int indexEnd) {
        return new PropertyPrefixSymbolToken(indexEnd);
    }
}
