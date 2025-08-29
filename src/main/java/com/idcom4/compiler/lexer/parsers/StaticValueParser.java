package com.idcom4.compiler.lexer.parsers;

import com.idcom4.exceptions.EOFException;
import com.idcom4.lexer.tokens.Token;
import com.idcom4.utils.scanner.StringScanner;

import java.util.Objects;
import java.util.Optional;

public abstract class StaticValueParser<T extends Token> implements ITokenParser<T> {

    private final String staticValue;

    public StaticValueParser(String staticValue) {
        this.staticValue = staticValue;
    }

    @Override
    public Optional<T> tryParse(StringScanner<EOFException> sourceCode) {
        if (sourceCode.isFullyConsumed()) return Optional.empty();

        if (!sourceCode.startsWith(staticValue)) return Optional.empty();
        sourceCode.skip(staticValue.length());

        return Optional.of(this.createToken(sourceCode.getCursor()));
    }

    protected abstract T createToken(int indexEnd);
}
