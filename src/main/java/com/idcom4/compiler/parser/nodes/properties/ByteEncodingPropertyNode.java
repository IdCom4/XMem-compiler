package com.idcom4.compiler.parser.nodes.properties;

import com.idcom4.parser.Context;
import com.idcom4.compiler.parser.nodes.ProgramNode;
import com.idcom4.exceptions.CompilationException;
import com.idcom4.exceptions.IDException;
import com.idcom4.parser.nodes.Node;

public class ByteEncodingPropertyNode extends PropertyNode {

    public ByteEncodingPropertyNode(Context context, int value) throws CompilationException {
        super(context);
        try {
            context.setByteEncoding(value);
        } catch (IDException e) {
            throw new CompilationException(e);
        }
    }

    @Override
    public byte[] generate(ProgramNode programNode, Node parentNode) {
        return new byte[0];
    }
}
