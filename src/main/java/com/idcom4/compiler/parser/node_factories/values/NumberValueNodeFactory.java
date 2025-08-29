package com.idcom4.compiler.parser.node_factories.values;

import com.idcom4.parser.Context;
import com.idcom4.compiler.lexer.tokens.values.NumberValueToken;
import com.idcom4.compiler.parser.nodes.values.NumberValueNode;
import com.idcom4.exceptions.DiscardTokenException;
import com.idcom4.exceptions.EOFException;
import com.idcom4.lexer.tokens.Token;
import com.idcom4.parser.nodes.NodeFactory;
import com.idcom4.utils.scanner.TokenScanner;

public class NumberValueNodeFactory extends NodeFactory<NumberValueNode> {

    @Override
    public NumberValueNode tryCreate(TokenScanner tokens, Context context) throws DiscardTokenException, EOFException {
        Token token = tokens.peek();

        if (token instanceof NumberValueToken numberValueToken) {
            tokens.consume();
            return new NumberValueNode(context, numberValueToken.getValue());
        }
        else {
            throw new DiscardTokenException("number expected, got " + token.getSlice());
        }
    }
}
