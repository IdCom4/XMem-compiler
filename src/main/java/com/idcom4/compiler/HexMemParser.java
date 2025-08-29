package com.idcom4.compiler;

import com.idcom4.compiler.parser.node_factories.ProgramNodeFactory;
import com.idcom4.compiler.parser.nodes.ProgramNode;
import com.idcom4.lexer.tokens.Token;
import com.idcom4.parser.Context;
import com.idcom4.parser.Parser;
import com.idcom4.utils.scanner.TokenScanner;


import java.util.List;

public class HexMemParser extends Parser<ProgramNode> {

    public HexMemParser(List<Token> tokens, Context context) {
        super(new TokenScanner(tokens), context, new ProgramNodeFactory());
    }
}
