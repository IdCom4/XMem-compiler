package com.idcom4.compiler.lexer.parsers.values;

import com.idcom4.compiler.lexer.parsers.ITokenParser;
import com.idcom4.compiler.lexer.tokens.values.NumberValueToken;
import com.idcom4.exceptions.EOFException;
import com.idcom4.utils.scanner.StringScanner;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberValueParser implements ITokenParser<NumberValueToken> {
    private final static Pattern hexadecimalPattern = Pattern.compile("^([0-9a-fA-F]+)");

    @Override
    public Optional<NumberValueToken> tryParse(StringScanner<EOFException> sourceCode) {
        if (sourceCode.isFullyConsumed()) return Optional.empty();

        Matcher matcher = sourceCode.match(hexadecimalPattern);
        if (!matcher.find()) return Optional.empty();

        sourceCode.skip(matcher.group(0).length());

        return Optional.of(new NumberValueToken(Integer.valueOf(matcher.group(0), 16), matcher.group(0), sourceCode.getCursor()));
    }
}
