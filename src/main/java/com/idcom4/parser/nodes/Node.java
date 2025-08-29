package com.idcom4.parser.nodes;

import com.idcom4.parser.Context;
import com.idcom4.compiler.parser.nodes.ProgramNode;
import com.idcom4.exceptions.CompilationException;

import java.util.List;

public abstract class Node {

    protected final Context context;

    public Node(Context context) {
        this.context = context;
    }

    public abstract List<Node> getChildren();

    public abstract byte[] generate(ProgramNode programNode, Node parentNode) throws CompilationException;
}
