package com.idcom4.compiler.parser.nodes.directives;

import com.idcom4.compiler.HexMemLexer;
import com.idcom4.compiler.parser.node_factories.properties.PropertyNodeFactory;
import com.idcom4.exceptions.BadTokenException;
import com.idcom4.exceptions.DiscardTokenException;
import com.idcom4.lexer.tokens.Token;
import com.idcom4.parser.Context;
import com.idcom4.compiler.parser.nodes.ProgramNode;
import com.idcom4.exceptions.CompilationException;
import com.idcom4.parser.nodes.Node;
import com.idcom4.utils.FileUtils;
import com.idcom4.utils.scanner.TokenScanner;

import java.util.List;

public class InjectDirectiveNode extends DirectiveNode {

    public InjectDirectiveNode(Context context, String filePath, TokenScanner tokens) throws CompilationException, BadTokenException {
        super(context);
        String fileContent = FileUtils.ReadFile(filePath);

        HexMemLexer lexer = new HexMemLexer(fileContent);
        List<Token> injectedTokens = lexer.processOrThrow();

        // consume tokens until they are no longer property related
        TokenScanner injectedScanner = new TokenScanner(injectedTokens);
        try {
            PropertyNodeFactory propertyNodeFactory = new PropertyNodeFactory();
            Context dummyContext = new Context(new Context.Address(context.getAddressSpace().getCurrentAddress()));

            while (!injectedScanner.isFullyConsumed()) {
                propertyNodeFactory.tryCreate(injectedScanner, dummyContext);
            }
        } catch (DiscardTokenException ignored) {}

        // then gather the remaining tokens, and account for the EOF token at the end, thus the - 1
        List<Token> propertyLessTokens = injectedTokens.subList(injectedScanner.getCursor(), injectedTokens.size() - 1);
        tokens.insertTokensAt(tokens.getCursor(), propertyLessTokens);
    }

    @Override
    public byte[] generate(ProgramNode programNode, Node parentNode) {
        return new byte[0];
    }
}
