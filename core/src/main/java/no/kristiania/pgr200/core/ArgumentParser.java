package no.kristiania.pgr200.core;

import java.util.HashMap;

public class ArgumentParser {

    /**
     * Takes a String-array and parses their arguments.
     * An argument is defined as an -OPTION followed by a value
     * i.e. "-title", "This is my amazing title"
     *
     * @param args the arguments that should be parsed
     * @return map with argument-value, i.e. ("title", "My amazing title"
     * @throws IllegalArgumentException
     */
    public static HashMap<String, String> getArguments(String[] args) throws IllegalArgumentException {
        HashMap<String, String> arguments = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if(args[i].startsWith("-")){ //.equalsIgnoreCase(identifier)) {

                if(i >= args.length - 1 || args[i + 1].startsWith("-"))
                    throw new IllegalArgumentException("option (\"-option\") cannot be followed by another option");

                String identifier = args[i].substring(1);
                String value = "";
                while(i < args.length - 1 && !args[i + 1].startsWith("-")) {
                    value += args[i + 1] + " ";
                    i++;
                }
                // removing trailing " "
                value = value.substring(0, value.length() - 1);

                arguments.put(identifier, value);
            }
        }
        return arguments;
    }

}
