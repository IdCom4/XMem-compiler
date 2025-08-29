package com.idcom4.utils.scanner;

import com.idcom4.exceptions.EOFException;
import com.idcom4.lexer.tokens.Token;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenScanner extends Scanner<Token, EOFException> {

    protected final List<Token> tokens;

    public TokenScanner(List<Token> tokens) {
        super(new IScannable<>() {
            @Override
            public Token getValueAt(int index) {
                return tokens.get(index);
            }

            @Override
            public int getLength() {
                return tokens.size();
            }
        }, EOFException::new);

        this.tokens = tokens;
    }

    public void insertTokensAt(int index, List<Token> newTokens) {
        tokens.addAll(index, newTokens);
        this.length = tokens.size();
    }

    public void insertTokenAt(int index, Token newToken) {
        tokens.add(index, newToken);
        this.length = tokens.size();
    }


}
