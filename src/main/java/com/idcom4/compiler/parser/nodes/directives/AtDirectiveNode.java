package com.idcom4.compiler.parser.nodes.directives;

import com.idcom4.parser.Context;
import com.idcom4.compiler.lexer.tokens.directives.AtDirectiveToken;
import com.idcom4.compiler.parser.nodes.ProgramNode;
import com.idcom4.exceptions.CompilationException;
import com.idcom4.parser.nodes.Node;

public class AtDirectiveNode extends DirectiveNode {

    private final int paddingAmount;

    public AtDirectiveNode(Context context, int address) throws CompilationException {
        super(context);
        paddingAmount = address - context.getAddressSpace().getCurrentAddress();

        if (paddingAmount < 0)
            throw new CompilationException("invalid " + AtDirectiveToken.KEYWORD + " directive: current address is greater then" + address);

        context.getAddressSpace().incrementAddress(paddingAmount);
    }

    @Override
    public byte[] generate(ProgramNode programNode, Node parentNode) {
        return new byte[paddingAmount];
    }
}
