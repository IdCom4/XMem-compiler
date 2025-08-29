package com.idcom4.compiler.parser.nodes;

import com.idcom4.parser.Context;
import com.idcom4.compiler.parser.nodes.directives.DirectiveNode;
import com.idcom4.compiler.parser.nodes.values.NumberValueNode;
import com.idcom4.exceptions.CompilationException;
import com.idcom4.parser.nodes.Node;

import java.util.ArrayList;
import java.util.List;

public class BodyNode extends Node {

    public sealed interface BodyChildNode permits ChildDirectiveNode, ChildNumberValueNode {}
    public record ChildDirectiveNode(DirectiveNode value) implements BodyChildNode {}
    public record ChildNumberValueNode(NumberValueNode value) implements BodyChildNode {}

    private final List<Node> childrenNodes = new ArrayList<>();

    public BodyNode(Context context, List<BodyChildNode> nodes) {

        super(context);

        for (BodyChildNode childNodeWrapper : nodes) {
            childrenNodes.add(switch (childNodeWrapper) {
                case ChildNumberValueNode _node -> _node.value;
                case ChildDirectiveNode _node -> _node.value;
            });
        }
    }

    @Override
    public List<Node> getChildren() {
        return childrenNodes;
    }

    @Override
    public byte[] generate(ProgramNode programNode, Node parentNode) throws CompilationException {
        List<byte[]> childrenBytes = new ArrayList<>();
        int byteAmount = 0;

        for (Node childNode : childrenNodes) {
            byte[] bytes = childNode.generate(programNode, this);
            childrenBytes.add(bytes);
            byteAmount += bytes.length;
        }

        byte[] bodyBytes = new byte[byteAmount];
        int byteIndex = 0;
        for (byte[] bytes : childrenBytes) {
            System.arraycopy(bytes, 0, bodyBytes, byteIndex, bytes.length);
            byteIndex += bytes.length;
        }

        return bodyBytes;
    }
}
