package com.idcom4.compiler.lexer.parsers;

import com.idcom4.compiler.lexer.tokens.CommentToken;
import com.idcom4.compiler.lexer.tokens.values.StringValueToken;
import com.idcom4.exceptions.EOFException;
import com.idcom4.utils.scanner.StringScanner;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentParser implements ITokenParser<CommentToken> {

    private final static Pattern commentPattern = Pattern.compile("^#[^\\r\\n]*(\\R|$)");

    @Override
    public Optional<CommentToken> tryParse(StringScanner<EOFException> sourceCode) {
        if (sourceCode.isFullyConsumed()) return Optional.empty();

        Matcher matcher = sourceCode.match(commentPattern);
        if (!matcher.find()) return Optional.empty();

        sourceCode.skip(matcher.group(0).length());

        return Optional.of(new CommentToken(matcher.group(0), sourceCode.getCursor()));
    }
}
