package com.idcom4.compiler.parser.node_factories.directives;

import com.idcom4.compiler.lexer.tokens.directives.DirectiveToken;
import com.idcom4.compiler.lexer.tokens.symbols.AssignSymbolToken;
import com.idcom4.compiler.lexer.tokens.symbols.DirectivePrefixSymbolToken;
import com.idcom4.compiler.parser.nodes.directives.DirectiveNode;
import com.idcom4.exceptions.BadTokenException;
import com.idcom4.exceptions.CompilationException;
import com.idcom4.exceptions.DiscardTokenException;
import com.idcom4.exceptions.GrammarException;
import com.idcom4.lexer.tokens.Token;
import com.idcom4.parser.Context;
import com.idcom4.parser.nodes.NodeFactory;
import com.idcom4.utils.scanner.TokenScanner;

import java.util.List;

public class DirectiveNodeFactory extends NodeFactory<DirectiveNode> {

    private static final List<NodeFactory<? extends DirectiveNode>> subFactories = List.of(
        new AtDirectiveNodeFactory(),
        new RelativeDirectiveNodeFactory(),
        new InjectDirectiveNodeFactory()
    );

    @Override
    public DirectiveNode tryCreate(TokenScanner tokens, Context context) throws CompilationException, BadTokenException {

        for (NodeFactory<? extends DirectiveNode> subFactory : subFactories) {
            try {
                return subFactory.tryCreate(tokens, context);
            } catch (DiscardTokenException ignored) {}
        }

        throw new DiscardTokenException("not a directive");
    }

    public static void validateDirectiveSignatureUntilParam(TokenScanner tokens, Class<? extends DirectiveToken> directiveTokenClass) throws GrammarException, DiscardTokenException {
        Token token;

        if (!((token = tokens.peek()) instanceof DirectivePrefixSymbolToken))
            throw new DiscardTokenException("expected " + DirectivePrefixSymbolToken.SYMBOL + " at index " + token.getIndexStart() + ": " + token.getSlice());

        if (!(directiveTokenClass.isInstance((token = tokens.peek(1)))))
            throw new DiscardTokenException("expected " + directiveTokenClass.getSimpleName() + ", but got: " + token.getSlice());

        if (!((token = tokens.peek(2)) instanceof AssignSymbolToken))
            throw new GrammarException("expected '" + AssignSymbolToken.SYMBOL + "' at index " + token.getIndexStart() + ": " + token.getSlice());

        tokens.skip(3);
    }
}
