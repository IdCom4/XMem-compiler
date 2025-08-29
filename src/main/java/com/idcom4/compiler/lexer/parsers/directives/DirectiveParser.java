package com.idcom4.compiler.lexer.parsers.directives;

import com.idcom4.compiler.lexer.parsers.StaticValueParser;
import com.idcom4.compiler.lexer.tokens.directives.DirectiveToken;

public abstract class DirectiveParser<T extends DirectiveToken> extends StaticValueParser<T> {

    public DirectiveParser(String staticValue) {
        super(staticValue);
    }
}
