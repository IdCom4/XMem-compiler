package com.idcom4.compiler.parser.node_factories;

import com.idcom4.exceptions.BadTokenException;
import com.idcom4.parser.Context;
import com.idcom4.compiler.parser.node_factories.directives.DirectiveNodeFactory;
import com.idcom4.compiler.parser.node_factories.values.NumberValueNodeFactory;
import com.idcom4.compiler.parser.nodes.BodyNode;
import com.idcom4.compiler.parser.nodes.directives.DirectiveNode;
import com.idcom4.compiler.parser.nodes.values.NumberValueNode;
import com.idcom4.exceptions.CompilationException;
import com.idcom4.exceptions.DiscardTokenException;
import com.idcom4.exceptions.GrammarException;
import com.idcom4.lexer.tokens.EOFToken;
import com.idcom4.lexer.tokens.Token;
import com.idcom4.parser.nodes.NodeFactory;
import com.idcom4.utils.scanner.TokenScanner;

import java.util.ArrayList;
import java.util.List;

public class BodyNodeFactory extends NodeFactory<BodyNode> {

    private final static List<DirectiveNodeFactory> directiveFactories = List.of(
            new DirectiveNodeFactory()
    );
    private final static List<NumberValueNodeFactory> valueFactories = List.of(
            new NumberValueNodeFactory()
    );

    @Override
    public BodyNode tryCreate(TokenScanner tokens, Context context) throws CompilationException, BadTokenException {

        List<BodyNode.BodyChildNode> bodyChildrenNodes = new ArrayList<>();

        while (!tokens.isFullyConsumed() && !(tokens.peek() instanceof EOFToken)) {
            bodyChildrenNodes.add(tryCreateBodyChildNode(tokens, context));
        }

        return new BodyNode(context, bodyChildrenNodes);
    }

    private BodyNode.BodyChildNode tryCreateBodyChildNode(TokenScanner tokens, Context context) throws CompilationException, BadTokenException {
        DirectiveNode directiveNode = null;
        for (DirectiveNodeFactory directiveFactory : directiveFactories) {
            try {
                directiveNode = directiveFactory.tryCreate(tokens, context);
                break;
            } catch (DiscardTokenException ignored) {}
        }

        if (directiveNode != null) {
            return new BodyNode.ChildDirectiveNode(directiveNode);
        }

        NumberValueNode valueNode = null;
        for (NumberValueNodeFactory valueFactory : valueFactories) {
            try {
                valueNode = valueFactory.tryCreate(tokens, context);
                break;
            } catch (DiscardTokenException ignored) {}
        }

        if (valueNode != null) {
            return new BodyNode.ChildNumberValueNode(valueNode);
        }

        Token token = tokens.peek();
        throw new GrammarException("unexpected token at index " + token.getIndexStart() + ": " + token.getSlice());
    }
}
