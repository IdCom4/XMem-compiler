package com.idcom4.compiler.lexer.parsers.properties;

import com.idcom4.compiler.lexer.parsers.StaticValueParser;
import com.idcom4.compiler.lexer.tokens.properties.PropertyToken;

public abstract class PropertyParser<T extends PropertyToken> extends StaticValueParser<T> {

    public PropertyParser(String staticValue) {
        super(staticValue);
    }
}
