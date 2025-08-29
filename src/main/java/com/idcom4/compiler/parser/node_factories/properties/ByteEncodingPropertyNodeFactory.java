package com.idcom4.compiler.parser.node_factories.properties;

import com.idcom4.parser.Context;
import com.idcom4.compiler.lexer.tokens.properties.ByteEncodingPropertyToken;
import com.idcom4.compiler.parser.nodes.properties.ByteEncodingPropertyNode;
import com.idcom4.exceptions.CompilationException;
import com.idcom4.parser.nodes.NodeFactory;
import com.idcom4.utils.scanner.TokenScanner;

public class ByteEncodingPropertyNodeFactory extends NodeFactory<ByteEncodingPropertyNode> {

    @Override
    public ByteEncodingPropertyNode tryCreate(TokenScanner tokens, Context context) throws CompilationException {
        int value = PropertyNodeFactory.tryCreatePropertyNode(tokens, ByteEncodingPropertyToken.class);

        return new ByteEncodingPropertyNode(context, value);
    }
}
