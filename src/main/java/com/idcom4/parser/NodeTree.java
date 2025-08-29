package com.idcom4.parser;

import com.idcom4.parser.nodes.Node;

import java.util.List;

public class NodeTree<T extends Node> {

    private Node node;
    private List<NodeTree<T>> children;
}
