package com.idcom4.infra.options;


public class Options {

    public enum EOptions {
        OUTPUT("--output", "-o"),
        HELP("--help", "-h");

        public final String name;
        public final String shortName;

        EOptions(String name, String shortName) {
            this.name = name;
            this.shortName = shortName;
        }
    }

    public interface IArgValueParser {
        void parse(Options options, String value);
    }

    public record SupportedOption(String name, String shortName, String description, IArgValueParser parser) {}

    public static final SupportedOption[] supportedOptions = new SupportedOption[]{
        new SupportedOption(
                EOptions.OUTPUT.name, EOptions.OUTPUT.shortName,
                "The name of the output file.",
                Options::SetOutputFile
        ),
        new SupportedOption(
                EOptions.HELP.name, EOptions.HELP.shortName,
                "Prints available options.",
                Options::SetHelp
        )
    };

    public static final SupportedOption defaultAnonymousOption = new SupportedOption(
            "<hex_mem_file>", "",
            "The path of the xmem file",
            Options::SetHexMemFile
    );

    private String hexMemFile = null;
    private String outputFile = "out";
    private boolean help = false;

    public Options() {}

    private void SetHexMemFile(String hexMemFile) {
        this.hexMemFile = hexMemFile;
    }

    public String getHexMemFile() {
        return this.hexMemFile;
    }

    private void SetOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public String GetOutputFile() {
        return this.outputFile;
    }

    private void SetHelp(String _unused) {
        this.help = true;
    }

    public boolean GetHelp() {
        return this.help;
    }

    public static void UnknownOption(String unknownOption) {
        System.err.println("[ERR] Unknown option: " + unknownOption);
        System.err.println("use " + EOptions.HELP.name + " to see the available options.");
    }
}
