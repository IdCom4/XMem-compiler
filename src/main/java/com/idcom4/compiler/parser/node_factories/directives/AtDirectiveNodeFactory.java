package com.idcom4.compiler.parser.node_factories.directives;

import com.idcom4.parser.Context;
import com.idcom4.compiler.lexer.tokens.directives.AtDirectiveToken;
import com.idcom4.compiler.lexer.tokens.values.NumberValueToken;
import com.idcom4.compiler.parser.nodes.directives.AtDirectiveNode;
import com.idcom4.exceptions.CompilationException;

import com.idcom4.exceptions.GrammarException;
import com.idcom4.lexer.tokens.Token;
import com.idcom4.parser.nodes.NodeFactory;
import com.idcom4.utils.scanner.TokenScanner;

public class AtDirectiveNodeFactory extends NodeFactory<AtDirectiveNode> {

    @Override
    public AtDirectiveNode tryCreate(TokenScanner tokens, Context context) throws CompilationException {
        DirectiveNodeFactory.validateDirectiveSignatureUntilParam(tokens, AtDirectiveToken.class);

        Token valueToken = tokens.peek();
        if (valueToken instanceof NumberValueToken numberValueToken) {
            tokens.consume();
            return new AtDirectiveNode(context, numberValueToken.getValue());
        }

        throw new GrammarException("expected number at index " + valueToken.getIndexStart() + ": " + valueToken.getSlice());
    }
}
