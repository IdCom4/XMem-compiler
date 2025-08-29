package com.idcom4.parser;

import com.idcom4.exceptions.BadTokenException;
import com.idcom4.exceptions.CompilationException;
import com.idcom4.parser.nodes.Node;
import com.idcom4.parser.nodes.NodeFactory;
import com.idcom4.utils.scanner.TokenScanner;


public class Parser<T extends Node> {

    protected final TokenScanner tokens;
    protected final Context context;
    protected final NodeFactory<T> mainNodeFactory;

    public Parser(TokenScanner tokens, Context context, NodeFactory<T> mainNodeFactory) {
        this.tokens = tokens;
        this.context = context;
        this.mainNodeFactory = mainNodeFactory;
    }

    public T parse() throws CompilationException, BadTokenException {
        return this.mainNodeFactory.tryCreate(tokens, context);
    }
}
