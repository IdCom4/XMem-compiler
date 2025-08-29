package com.idcom4.compiler.parser.node_factories.properties;

import com.idcom4.compiler.lexer.tokens.properties.PropertyToken;
import com.idcom4.compiler.lexer.tokens.symbols.AssignSymbolToken;
import com.idcom4.compiler.lexer.tokens.symbols.PropertyPrefixSymbolToken;
import com.idcom4.compiler.lexer.tokens.values.NumberValueToken;
import com.idcom4.compiler.parser.nodes.properties.PropertyNode;
import com.idcom4.exceptions.BadTokenException;
import com.idcom4.exceptions.CompilationException;
import com.idcom4.exceptions.DiscardTokenException;
import com.idcom4.exceptions.GrammarException;
import com.idcom4.lexer.tokens.Token;
import com.idcom4.parser.Context;
import com.idcom4.parser.nodes.NodeFactory;
import com.idcom4.utils.scanner.TokenScanner;

import java.util.List;

public class PropertyNodeFactory extends NodeFactory<PropertyNode> {

    private static final List<NodeFactory<? extends PropertyNode>> subFactories = List.of(
        new ByteEncodingPropertyNodeFactory(),
        new TotalSizePropertyNodeFactory()
    );

    @Override
    public PropertyNode tryCreate(TokenScanner tokens, Context context) throws CompilationException, BadTokenException {

        for (NodeFactory<? extends PropertyNode> subFactory : subFactories) {
            try {
                return subFactory.tryCreate(tokens, context);
            } catch (DiscardTokenException ignored) {}
        }

        throw new DiscardTokenException("not a property");
    }

    public static int tryCreatePropertyNode(TokenScanner tokens, Class<? extends PropertyToken> propertyTokenClass) throws GrammarException, DiscardTokenException {
        Token token;

        if (!((token = tokens.peek()) instanceof PropertyPrefixSymbolToken))
            throw new DiscardTokenException("expected " + PropertyPrefixSymbolToken.SYMBOL + " at index " + token.getIndexStart() + ": " + token.getSlice());

        if (!(propertyTokenClass.isInstance((token = tokens.peek(1)))))
            throw new DiscardTokenException("expected " + propertyTokenClass.getSimpleName() + ", but got: " + token.getSlice());

        if (!((token = tokens.peek(2)) instanceof AssignSymbolToken))
            throw new GrammarException("expected '" + AssignSymbolToken.SYMBOL + "' at index " + token.getIndexStart() + ": " + token.getSlice());

        if ((token = tokens.peek(3)) instanceof NumberValueToken numberValueToken) {
            tokens.skip(4);

            return numberValueToken.getValue();
        } else {
            throw new GrammarException("expected a value at index " + token.getIndexStart() + ": " + token.getSlice());
        }
    }

}
