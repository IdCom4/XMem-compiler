package com.idcom4.compiler.parser.node_factories.directives;

import com.idcom4.parser.Context;
import com.idcom4.compiler.lexer.tokens.directives.RelativeDirectiveToken;
import com.idcom4.compiler.lexer.tokens.symbols.CloseArraySymbolToken;
import com.idcom4.compiler.lexer.tokens.symbols.OpenArraySymbolToken;
import com.idcom4.compiler.lexer.tokens.symbols.ValueSeparatorSymbolToken;
import com.idcom4.compiler.lexer.tokens.values.NumberValueToken;
import com.idcom4.compiler.parser.nodes.directives.RelativeDirectiveNode;
import com.idcom4.exceptions.CompilationException;
import com.idcom4.exceptions.GrammarException;
import com.idcom4.lexer.tokens.Token;
import com.idcom4.parser.nodes.NodeFactory;
import com.idcom4.utils.scanner.TokenScanner;

import java.util.ArrayList;
import java.util.List;

public class RelativeDirectiveNodeFactory extends NodeFactory<RelativeDirectiveNode> {

    @Override
    public RelativeDirectiveNode tryCreate(TokenScanner tokens, Context context) throws CompilationException {
        DirectiveNodeFactory.validateDirectiveSignatureUntilParam(tokens, RelativeDirectiveToken.class);

        List<Integer> addresses = new ArrayList<>();

        Token token;
        if (!((token = tokens.consume()) instanceof OpenArraySymbolToken))
            throw new GrammarException("expected " + OpenArraySymbolToken.SYMBOL + " at index " + token.getIndexStart() + ": " + token.getSlice());

        while (!tokens.isFullyConsumed() && !((token = tokens.consume()) instanceof CloseArraySymbolToken)) {

            if (!addresses.isEmpty()) {
                if (!(token instanceof ValueSeparatorSymbolToken))
                    throw new GrammarException("expected " + ValueSeparatorSymbolToken.SYMBOL + " at index " + token.getIndexStart() + ": " + token.getSlice());
                token = tokens.consume();
            }

            if (token instanceof NumberValueToken numberValueToken)
                addresses.add(numberValueToken.getValue());
            else throw new GrammarException("expected number at index " + token.getIndexStart() + ": " + token.getSlice());
        }

        if (!(token instanceof CloseArraySymbolToken))
            throw new GrammarException("expected " + CloseArraySymbolToken.SYMBOL + " at index " + token.getIndexStart() + ": " + token.getSlice());

        return new RelativeDirectiveNode(context, addresses);
    }
}
