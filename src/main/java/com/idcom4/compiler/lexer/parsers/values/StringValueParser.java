package com.idcom4.compiler.lexer.parsers.values;

import com.idcom4.compiler.lexer.parsers.ITokenParser;
import com.idcom4.compiler.lexer.tokens.values.StringValueToken;
import com.idcom4.exceptions.EOFException;
import com.idcom4.utils.scanner.StringScanner;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValueParser implements ITokenParser<StringValueToken> {

    private final static Pattern stringPattern = Pattern.compile("^\"((?:[^\"\\\\]|\\\\[\"\\\\/bfnrt])*)\"");

    @Override
    public Optional<StringValueToken> tryParse(StringScanner<EOFException> sourceCode) {
        if (sourceCode.isFullyConsumed()) return Optional.empty();

        Matcher matcher = sourceCode.match(stringPattern);
        if (!matcher.find()) return Optional.empty();

        sourceCode.skip(matcher.group(0).length());

        return Optional.of(new StringValueToken(matcher.group(1), matcher.group(0), sourceCode.getCursor()));
    }
}
