package com.idcom4.compiler.parser.nodes.directives;

import com.idcom4.parser.Context;
import com.idcom4.parser.nodes.Node;

import java.util.List;

public abstract class DirectiveNode extends Node {


    public DirectiveNode(Context context) {
        super(context);
    }

    @Override
    public List<Node> getChildren() {
        return List.of();
    }
}
