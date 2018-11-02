package no.kristiania.pgr200.program;

public class ArgumentParser {

    /**
     * Returns value of given argument identifier
     * @param identifier of argument (i.e. "-title")
     * @return value if found or default value if not
     * @throws IllegalArgumentException if identifier does not have corresponding value
     */
    public static String getArgument(String identifier, String[] strings, String defaultValue) throws IllegalArgumentException {

        for (int i = 0; i < strings.length; i++) {
            if(strings[i].equalsIgnoreCase(identifier)) {
                /*
                 * Two conditions lead to option missing argument value:
                 * 1: The argument option was last in array (no one could follow
                 * 2: The next element starts with "-", meaning there is no value in between
                 * */
                if(i >= strings.length - 1 || strings[i + 1].startsWith("-"))
                    throw new IllegalArgumentException("option (\"-option\") cannot be followed by another option");

                String value = "";
                while(i < strings.length - 1 && !strings[i + 1].startsWith("-")) {
                    value += strings[i + 1] + " ";
                    i++;
                }
                // removing trailing " "
                value = value.substring(0, value.length() - 1);

                //String value = strings[i + 1];
                return value;
            }
        }
        return defaultValue;
    }

}
