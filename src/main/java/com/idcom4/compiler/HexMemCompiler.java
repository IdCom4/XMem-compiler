package com.idcom4.compiler;

import com.idcom4.compiler.parser.nodes.ProgramNode;
import com.idcom4.exceptions.CompilationException;
import com.idcom4.exceptions.TokenParsingException;
import com.idcom4.lexer.tokens.Token;
import com.idcom4.parser.Context;

import java.nio.ByteBuffer;
import java.util.List;

public class HexMemCompiler {

    public record Result(byte[] bytes, int byteEncoding) {}

    public Result Compile(String fileContent, Context context) throws CompilationException, TokenParsingException {
            HexMemLexer lexer = new HexMemLexer(fileContent);
            List<Token> tokens = lexer.processOrThrow();

            ProgramNode programNode = new HexMemParser(tokens, context).parse();

            byte[] bytes = programNode.generateRoot();

            return new Result(FormatBytes(bytes, context.getTotalSize()), context.getTotalSize());
    }

    private static byte[] FormatBytes(byte[] bytes, int wantedTotalLength) {

        if (wantedTotalLength <= bytes.length) return bytes;

        ByteBuffer buffer = ByteBuffer.allocate(wantedTotalLength);
        buffer.put(bytes);

        return buffer.array();
    }
}
