package com.idcom4.compiler.parser.node_factories;

import com.idcom4.exceptions.BadTokenException;
import com.idcom4.parser.Context;
import com.idcom4.compiler.parser.node_factories.properties.PropertyNodeFactory;
import com.idcom4.compiler.parser.nodes.*;
import com.idcom4.compiler.parser.nodes.properties.PropertyNode;
import com.idcom4.exceptions.CompilationException;
import com.idcom4.exceptions.DiscardTokenException;
import com.idcom4.parser.nodes.NodeFactory;
import com.idcom4.utils.scanner.TokenScanner;

import java.util.ArrayList;
import java.util.List;

public class ProgramNodeFactory extends NodeFactory<ProgramNode> {

    private final static PropertyNodeFactory propertyFactory = new PropertyNodeFactory();
    private final static BodyNodeFactory bodyFactory = new BodyNodeFactory();

    @Override
    public ProgramNode tryCreate(TokenScanner tokens, Context context) throws CompilationException, BadTokenException {

        List<PropertyNode> propertyNodes = new ArrayList<>();

        while (!tokens.isFullyConsumed()) {
            try {
                propertyNodes.add(propertyFactory.tryCreate(tokens, context));
            } catch (DiscardTokenException e) {
                break ;
            }
        }

        BodyNode bodyNode = bodyFactory.tryCreate(tokens, context);

        return new ProgramNode(context, propertyNodes, bodyNode);
    }
}
