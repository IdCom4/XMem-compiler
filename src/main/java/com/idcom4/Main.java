package com.idcom4;

import com.idcom4.compiler.HexMemCompiler;
import com.idcom4.infra.options.Options;
import com.idcom4.infra.options.OptionsManager;
import com.idcom4.infra.options.OptionsPrinter;
import com.idcom4.parser.Context;
import com.idcom4.utils.FileUtils;


public class Main {
    public static void main(String[] args) {

        Options options = OptionsManager.GetOptions(args);
        if (options.GetHelp()) {

            OptionsPrinter.PrintAvailableOptions();
            return ;
        }
        if (options.getHexMemFile() == null) {
            System.err.println("Hex Mem File not specified.");
            return ;
        }

        String hexMemFile = FileUtils.ReadFile(options.getHexMemFile());

        HexMemCompiler compiler = new HexMemCompiler();

        try {
            Context context = new Context(new Context.Address(0));

            HexMemCompiler.Result result = compiler.Compile(hexMemFile, context);
            FileUtils.WriteFileBytes(options.GetOutputFile() + ".mem", result.bytes(), result.byteEncoding());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}