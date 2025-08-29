package com.idcom4.lexer;

import com.idcom4.compiler.lexer.parsers.ITokenParser;
import com.idcom4.exceptions.EOFException;
import com.idcom4.lexer.tokens.BadToken;
import com.idcom4.lexer.tokens.EOFToken;
import com.idcom4.lexer.tokens.Token;
import com.idcom4.lexer.tokens.WhiteSpaceToken;
import com.idcom4.utils.scanner.StringScanner;

import java.util.*;

public abstract class Lexer {

    protected final String originalSourceCode;
    protected final StringScanner<EOFException> sourceCodeScanner;
    protected final List<ITokenParser<? extends Token>> parsers;

    private final List<Token> tokens = new LinkedList<>();

    public Lexer(String sourceCode, List<ITokenParser<? extends Token>> parsers) {
        this.originalSourceCode = sourceCode;
        this.sourceCodeScanner = new StringScanner<>(sourceCode, EOFException::new);
        this.parsers = parsers;
    }

    public Token getNextToken() throws EOFException {
        if (sourceCodeScanner.isFullyConsumed())
            return new EOFToken(sourceCodeScanner.getLength());

        int consumedWhiteSpaces = sourceCodeScanner.consumeWhiteSpaces();
        if (consumedWhiteSpaces > 0)
            return new WhiteSpaceToken(consumedWhiteSpaces, sourceCodeScanner.getCursor());

        for (ITokenParser<? extends Token> parser : parsers) {
            Token token = parser.tryParse(sourceCodeScanner).orElse(null);
            if (token != null) return token;
        }

        return new BadToken(Objects.requireNonNull(sourceCodeScanner.consume()).toString(), sourceCodeScanner.getCursor());
    }

    public List<Token> process(List<Class<? extends Token>> badTokenUntil, List<Class<? extends Token>> ignoreTokens) throws EOFException {
        Token token;

        boolean inBadToken = false;
        int badTokenEndIndex = -1;
        StringBuilder badTokenSliceBuilder = new StringBuilder();
        do {
            token = this.getNextToken();


            if (token instanceof BadToken) inBadToken = true;

            if (!inBadToken) {
                if (!this.mustBeIgnored(token, ignoreTokens))
                    tokens.add(token);
                continue;
            }

            if (!isEndOfBadToken(token, badTokenUntil)) {
                badTokenEndIndex = token.getIndexEnd();
                badTokenSliceBuilder.append(token.getSlice());
            }
            else {
                inBadToken = false;
                tokens.add(new BadToken(badTokenSliceBuilder.toString(), badTokenEndIndex));
                badTokenSliceBuilder.setLength(0);
            }

        } while (!(token instanceof EOFToken));

        return tokens;
    }

    private boolean mustBeIgnored(Token token, List<Class<? extends Token>> ignoreTokens) {
        return ignoreTokens.stream().filter((_c) -> _c.isInstance(token)).findFirst().orElse(null) != null;
    }

    private boolean isEndOfBadToken(Token token, List<Class<? extends Token>> badTokenUntil) {
        return (badTokenUntil.isEmpty() && !(token instanceof BadToken)) || badTokenUntil.stream().filter((_t) -> _t.isInstance(token)).findFirst().orElse(null) != null;
    }

}
