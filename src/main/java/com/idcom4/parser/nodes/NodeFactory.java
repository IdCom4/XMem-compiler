package com.idcom4.parser.nodes;

import com.idcom4.exceptions.BadTokenException;
import com.idcom4.parser.Context;
import com.idcom4.exceptions.CompilationException;
import com.idcom4.utils.scanner.TokenScanner;

public abstract class NodeFactory<T extends Node> {

    public abstract T tryCreate(TokenScanner tokens, Context context) throws CompilationException, BadTokenException;
}
