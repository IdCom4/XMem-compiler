package com.idcom4.compiler.parser.nodes.properties;

import com.idcom4.parser.Context;
import com.idcom4.compiler.parser.nodes.ProgramNode;
import com.idcom4.parser.nodes.Node;

public class TotalSizePropertyNode extends PropertyNode {

    public TotalSizePropertyNode(Context context, int value) {
        super(context);
        context.setTotalSize(value);
    }

    @Override
    public byte[] generate(ProgramNode programNode, Node parentNode) {
        return new byte[0];
    }
}
