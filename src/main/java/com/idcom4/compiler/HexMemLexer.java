package com.idcom4.compiler;

import com.idcom4.compiler.lexer.parsers.CommentParser;
import com.idcom4.compiler.lexer.parsers.ITokenParser;
import com.idcom4.compiler.lexer.parsers.directives.AtDirectiveParser;
import com.idcom4.compiler.lexer.parsers.directives.InjectDirectiveParser;
import com.idcom4.compiler.lexer.parsers.directives.RelativeDirectiveParser;
import com.idcom4.compiler.lexer.parsers.properties.ByteEncodingPropertyParser;
import com.idcom4.compiler.lexer.parsers.properties.TotalSizePropertyParser;
import com.idcom4.compiler.lexer.parsers.symbols.*;
import com.idcom4.compiler.lexer.parsers.values.NumberValueParser;
import com.idcom4.compiler.lexer.parsers.values.StringValueParser;
import com.idcom4.compiler.lexer.tokens.CommentToken;
import com.idcom4.compiler.lexer.tokens.symbols.SymbolToken;
import com.idcom4.exceptions.BadTokenException;
import com.idcom4.exceptions.EOFException;
import com.idcom4.lexer.tokens.BadToken;
import com.idcom4.lexer.tokens.Token;
import com.idcom4.lexer.Lexer;
import com.idcom4.lexer.tokens.WhiteSpaceToken;

import java.util.List;

public class HexMemLexer extends Lexer {

    private final static List<ITokenParser<? extends Token>> parsers = List.of(
            // properties
            new ByteEncodingPropertyParser(),
            new TotalSizePropertyParser(),

            // directives
            new AtDirectiveParser(),
            new RelativeDirectiveParser(),
            new InjectDirectiveParser(),

            // values
            new NumberValueParser(),
            new StringValueParser(),

            // symbols
            new AssignSymbolParser(),
            new PropertyPrefixSymbolParser(),
            new DirectivePrefixSymbolParser(),
            new OpenArraySymbolParser(),
            new CloseArraySymbolParser(),
            new ValueSeparatorSymbolParser(),

            // comments
            new CommentParser()
    );

    public HexMemLexer(String sourceCode) {
        super(sourceCode, parsers);
    }

    public List<Token> processOrThrow() throws EOFException, BadTokenException {
        List<Token> tokens = super.process(
                List.of(SymbolToken.class, WhiteSpaceToken.class),
                List.of(WhiteSpaceToken.class, CommentToken.class)
        );

        BadToken firstBadToken = null;

        for (Token token : tokens) {
            if (token instanceof BadToken badToken) {
                if (firstBadToken == null) firstBadToken = badToken;
                System.out.println("bad token at index " + badToken.getIndexStart() + ": " + badToken.getSlice());
            }
        }

        if (firstBadToken != null)
            throw new BadTokenException(firstBadToken);

        return tokens;
    }
}
