package com.idcom4.compiler.parser.nodes;

import com.idcom4.parser.Context;
import com.idcom4.compiler.parser.nodes.properties.PropertyNode;
import com.idcom4.exceptions.CompilationException;
import com.idcom4.parser.nodes.Node;

import java.util.ArrayList;
import java.util.List;

public class ProgramNode extends Node {

    private final List<PropertyNode> properties;
    private final BodyNode body;

    public ProgramNode(Context context, List<PropertyNode> properties, BodyNode body) throws CompilationException {

        super(context);

        this.assertNoDuplicateProperty(properties);

        this.properties = properties;
        this.body = body;
    }

    private void assertNoDuplicateProperty(List<PropertyNode> properties) throws CompilationException {
        for (PropertyNode node : properties) {
            PropertyNode duplicate = properties.stream()
                    .filter((_n) -> _n.getClass().equals(node.getClass()) && _n != node)
                    .findFirst().orElse(null);

            if (duplicate != null)
                throw new CompilationException("Duplicate property: " + duplicate.getClass().getSimpleName());
        }
    }

    @Override
    public List<Node> getChildren() {
        List<Node> children = new ArrayList<>(properties);
        children.add(body);

        return children;
    }

    public byte[] generateRoot() throws CompilationException {
        List<byte[]> propertiesBytes = new ArrayList<>();
        int byteAmount = 0;

        for (PropertyNode propertyNode : properties) {
            byte[] bytes = propertyNode.generate(this, this);
            propertiesBytes.add(bytes);
            byteAmount += bytes.length;
        }

        byte[] bodyBytes = body.generate(this, this);
        byteAmount += bodyBytes.length;

        byte[] programBytes = new byte[byteAmount];
        int byteIndex = 0;

        for (byte[] bytes : propertiesBytes) {
            System.arraycopy(bytes, 0, programBytes, byteIndex, bytes.length);
            byteIndex += bytes.length;
        }

        System.arraycopy(bodyBytes, 0, programBytes, byteIndex, bodyBytes.length);

        return programBytes;
    }

    @Override
    public byte[] generate(ProgramNode programNode, Node parentNode) throws CompilationException {
        return generateRoot();
    }
}
