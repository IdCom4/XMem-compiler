package com.idcom4.compiler.parser.node_factories.properties;

import com.idcom4.parser.Context;
import com.idcom4.compiler.lexer.tokens.properties.TotalSizePropertyToken;
import com.idcom4.compiler.parser.nodes.properties.TotalSizePropertyNode;
import com.idcom4.exceptions.DiscardTokenException;
import com.idcom4.exceptions.GrammarException;
import com.idcom4.parser.nodes.NodeFactory;
import com.idcom4.utils.scanner.TokenScanner;

public class TotalSizePropertyNodeFactory extends NodeFactory<TotalSizePropertyNode> {

    @Override
    public TotalSizePropertyNode tryCreate(TokenScanner tokens, Context context) throws GrammarException, DiscardTokenException {
        int value = PropertyNodeFactory.tryCreatePropertyNode(tokens, TotalSizePropertyToken.class);

        return new TotalSizePropertyNode(context, value);
    }
}
