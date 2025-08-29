package com.idcom4.compiler.parser.nodes.values;

import com.idcom4.parser.Context;
import com.idcom4.compiler.parser.nodes.ProgramNode;
import com.idcom4.parser.nodes.Node;

import java.util.List;

public class NumberValueNode extends Node {

    private int value;

    public NumberValueNode(Context context, int value) {
        super(context);

       context.getAddressSpace().incrementAddress(1);
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public List<Node> getChildren() {
        return List.of();
    }

    @Override
    public byte[] generate(ProgramNode programNode, Node parentNode) {
        int byteLength = context.getByteEncoding();
        byte[] bytes = new byte[byteLength];

        for (int i = 0; i < byteLength; i++) {
            bytes[byteLength - i - 1] = (byte)(this.value >>> (8 * i));
        }

        return bytes;
    }
}
