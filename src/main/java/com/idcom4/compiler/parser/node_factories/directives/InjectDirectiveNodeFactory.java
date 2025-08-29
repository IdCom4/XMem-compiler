package com.idcom4.compiler.parser.node_factories.directives;

import com.idcom4.compiler.lexer.tokens.directives.InjectDirectiveToken;
import com.idcom4.compiler.lexer.tokens.values.StringValueToken;
import com.idcom4.compiler.parser.nodes.directives.InjectDirectiveNode;
import com.idcom4.exceptions.BadTokenException;
import com.idcom4.exceptions.CompilationException;
import com.idcom4.exceptions.GrammarException;
import com.idcom4.lexer.tokens.Token;
import com.idcom4.parser.Context;
import com.idcom4.parser.nodes.NodeFactory;
import com.idcom4.utils.scanner.TokenScanner;

public class InjectDirectiveNodeFactory extends NodeFactory<InjectDirectiveNode> {

    @Override
    public InjectDirectiveNode tryCreate(TokenScanner tokens, Context context) throws CompilationException, BadTokenException {
        DirectiveNodeFactory.validateDirectiveSignatureUntilParam(tokens, InjectDirectiveToken.class);

        Token valueToken = tokens.peek();
        if (valueToken instanceof StringValueToken stringValue) {
            tokens.consume();
            return new InjectDirectiveNode(context, stringValue.getValue(), tokens);
        }

        throw new GrammarException("expected number at index " + valueToken.getIndexStart() + ": " + valueToken.getSlice());
    }
}
