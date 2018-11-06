package no.kristiania.pgr200.core;

import java.util.HashMap;

public class ArgumentParser {


    public static HashMap<String, String> getArguments(String[] strings) throws IllegalArgumentException {
        HashMap<String, String> arguments = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            if(strings[i].startsWith("-")){ //.equalsIgnoreCase(identifier)) {

                if(i >= strings.length - 1 || strings[i + 1].startsWith("-"))
                    throw new IllegalArgumentException("option (\"-option\") cannot be followed by another option");

                String identifier = strings[i].substring(1);
                String value = "";
                while(i < strings.length - 1 && !strings[i + 1].startsWith("-")) {
                    value += strings[i + 1] + " ";
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
