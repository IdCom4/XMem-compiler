package com.idcom4.compiler.parser.nodes.properties;

import com.idcom4.parser.Context;
import com.idcom4.parser.nodes.Node;

import java.util.List;

public abstract class PropertyNode extends Node {

    public PropertyNode(Context context) {
        super(context);
    }

    @Override
    public List<Node> getChildren() {
        return List.of();
    }
}
