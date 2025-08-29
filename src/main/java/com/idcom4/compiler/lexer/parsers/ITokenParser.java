package com.idcom4.compiler.lexer.parsers;

import com.idcom4.exceptions.EOFException;
import com.idcom4.utils.scanner.StringScanner;
import com.idcom4.lexer.tokens.Token;

import java.util.Optional;

public interface ITokenParser<T extends Token> {

    Optional<T> tryParse(StringScanner<EOFException> sourceCode);

}
