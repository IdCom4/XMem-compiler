package com.idcom4.compiler.parser.nodes.directives;

import com.idcom4.parser.Context;
import com.idcom4.compiler.lexer.tokens.directives.RelativeDirectiveToken;
import com.idcom4.compiler.parser.nodes.ProgramNode;
import com.idcom4.compiler.parser.nodes.values.NumberValueNode;
import com.idcom4.exceptions.CompilationException;
import com.idcom4.parser.nodes.Node;

import java.util.ArrayList;
import java.util.List;

public class RelativeDirectiveNode extends DirectiveNode {

    private final List<Integer> addressesToShift;
    private final int address;

    public RelativeDirectiveNode(Context context, List<Integer> addressesToShift) {
        super(context);

        this.address = context.getAddressSpace().getCurrentAddress();
        this.addressesToShift = addressesToShift.stream().sorted().toList();
    }

    @Override
    public byte[] generate(ProgramNode programNode, Node parentNode) throws CompilationException {
        if (addressesToShift.isEmpty()) return new byte[0];

        List<NumberValueNode> valueFollowingSiblings = new ArrayList<>();

        for (Node sibling : parentNode.getChildren()) {
            if (sibling instanceof NumberValueNode valueNode) {
                valueFollowingSiblings.add(valueNode);
            }
        }

        if (valueFollowingSiblings.size() <= addressesToShift.getLast())
            throw new CompilationException("invalid " + RelativeDirectiveToken.KEYWORD + " addresses, more addresses to shift than there actually is");

        for (int i : addressesToShift) {
            int currentValue = valueFollowingSiblings.get(i).getValue();
            valueFollowingSiblings.get(i).setValue(currentValue + address);
        }

        return new byte[0];
    }
}
