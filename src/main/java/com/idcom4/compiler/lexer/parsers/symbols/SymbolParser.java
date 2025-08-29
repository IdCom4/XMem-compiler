package com.idcom4.compiler.lexer.parsers.symbols;

import com.idcom4.compiler.lexer.parsers.StaticValueParser;
import com.idcom4.compiler.lexer.tokens.symbols.SymbolToken;


public abstract class SymbolParser extends StaticValueParser<SymbolToken> {
    public SymbolParser(String staticValue) {
        super(staticValue);
    }
}
