package no.kristiania.pgr200.client;

import java.util.Scanner;

/**
 * Runs the the main program, but through a more interactive
 * shell - An optional layer on top of the program.
 */
public  class Shell {

    public static boolean runShell(String[] args) {
        if(args[0].equals("shell")){
            System.out.println("Shell started. Type 'exit' to exit");
            Scanner scanner = new Scanner(System.in);
            String nextLine = "";
            boolean run = true;
            while(run){
                System.out.print("Enter command: ");
                nextLine = scanner.nextLine();
                if(nextLine.equals("exit")){
                    run = false;
                }else if(nextLine.equals("shell")){
                    printShell();
                    continue;
                }
                String[] rgs = nextLine.split(" ");
                Program.main(rgs);
            }
            return true;
        }
        return false;
    }

    /**
     * Tiny easter egg.
     */
    private static void printShell() {
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWWWWWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWNX00OOO0KXXNNNNNXXKKKKKXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWWWWWWNXKOxdddxxxkkkkkkkkkkxxxxxxOKNWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMWNK00OO00Okkxxddddddxkkkkkkkxxxxdddddxkk00KKKKKXNWMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMN0kxdddxxxxxxxxddddxxxkkkkxxxxxxxxxxddxxxkxkxxxxkk0XWMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMWNKOkdddddxxxxxddxxddxxxxxxkkkkxxxxxxxxxxxxxxxxxxdxxxxkKWMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMWNXK0kxxkxddxxxxxxxxdxxxxxxxxxxxkkkxddxxxxdxxxxxxxxxxxxxdxkO0XNWMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMNKOkkxxxxxxkxxxxkxxdxxddxxxddddoddxxxdddddddoddddddddxxxxxxkkkkkO0XNWWMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMWXOxdxxxxxxxxkkxxkkkddddooddddooooooddddoodddooododddoodddxkkkkkkkkkkOO0XWMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMWXkdddddxxxxxxkkxxkkxdooooloddddddooodddooodddddddoddoooooddxkkkxxxxxkxxxx0NMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMWKOxdoodxxxxxddxxxxxxdolllllooodxxddoodddooooddddddoddoooooddxxxxxxxxxxxdddx0WMMMMMMMMMM\n" +
                "MMMMMMMMMMN0kdddddddxxxxddddxddddoolllllloddxxxdooodddooodxddddddoooodddxxxxxxxkxxxdddddkKWMMMMMMMMM\n" +
                "MMMMMMMMWKkdoooooddxxxddddddddoooooollloooddddddoooddoooooddddddddooddddxxxxxxxxdxxxxdxxxk0NWMMMMMMM\n" +
                "MMMMMMMMNkoooooodddxdddooooooddoodddolloooddddoooooooololloooddddoooodddxxdxkxxddxkkxxddddxk0NWMMMMM\n" +
                "MMMMMMMMXxoooooodddddooolloooddoooooolclloodddoollllllllllooooodoooooddxxddxxxddxxxxxxddddddxOXMMMMM\n" +
                "MMMMMMMW0xdodddoodoooooollloooooooooolclloodxdolllllcccclodddooooooooodddddxxdddxkxddddddddddxKMMMMM\n" +
                "MMMMMMWKxdddddxxdoooooolllllooooloooolccloooooooolllccclloodddoooloooooooddddodxkxddddddddoodkKWMMMM\n" +
                "MMMMMMNkoododdxxdollollooolclloolodooocccloooooooooolllllllooddolooddxdoddoooodxxxdxxdddxxddxxONMMMM\n" +
                "MMMMMMNkoooodddodollllldkxocccldolddlllccloooodddoooooooddddddooooodxkxdkdllldOOxddxdddkkkxxxxkKWMMM\n" +
                "MMMMMMWOdooooooloddlcllldkxolccooloxdoollooddxkkxxddooddxkkkxdooooodkxoxkdccoxOkddxdddxxxxxddxxONMMM\n" +
                "MMMMMMWKxoddoooooodolloolxOkdl:cooooooddooddxkOkkxxdooodkkOOxdooddxkkdoxdccldkkddxdodxddddddddx0WMMM\n" +
                "MMMMMMMNkoddoooooooddoloooxOxolcooooxkkxdoodxkkkxxxdooodxkkkxdoodxk0kdxxlccokxddxdodxxddxdxxxxxKWMMM\n" +
                "MMMMMMMWKdoddoooooooxxolooodxdolodddxkkxdooddxxdddddoooodddddolodxkOxxkdlcoxxolooodxxdxxddxkxxxKMMMM\n" +
                "MMMMMMMMNOdddddodooooddolclloddooodddxxxdooooooodddoooloooddoooodxOkxxdlldxxoc:cloddddxddxxxxdONMMMM\n" +
                "MMMMMMMMMXkdddooooodooddolcoodkkdlooodkxooloooxxkkxxdodxkOOxdooddxkxddlldkkdlc:coolldddxxxkkkkKWMMMM\n" +
                "MMMMMMMMMWXkoooollooooooollloodxxollooxxxdooodkO00kxolox0K0kdodOOOkxxdooxkxolcclllclloddxkOkkKWMMMMM\n" +
                "MMMMMMMMMMMNOdolllllloolllllllloolcloooxOOdlclxO0OOxoloxO0OxookKK0kxxdodxxoclodolcccllodxkxkXWMMMMMM\n" +
                "MMMMMMMMMMMMWKxollllllllllodocclloolllldkOkocldxkkkxoooxkkkdooO00Oxoodxkxdooxkxoollllllodx0NWMMMMMMM\n" +
                "MMMMMMMMMMMMMMWKkdollclllooddoclldxdlccodxkdlodddxxxdooddxxdoxkkkxdlokOOxdodxddoollllooxOXWMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMWX0kdollooooolllodxkxolooodxdoodkOOkdlldkOkdoxOkxxdodkOxooddooodollldx0XWMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMWNKOdooooolooolodxdooodxkxlldO00OdllxO0koox00kdodddolokkdlodooodOKNWMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMWN0kdoooodxdlloddoloxkkdlokOOkxooxkkxloxOOdloxxdlcodoolllok0NWMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMWXOxoodddolodxxoldxxdoodddxdoodxxdoooddoldkxlllccooooxKNMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMWNKkdollllldkxlloodolodxxxlcokkoloddolooolloolloood0WMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWKxlloolclolcloxxocdkOkl:oOklcdkdlldolclloolloodx0NMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMW0doolc::ccc:cdkocldxkl:lxo::odlcddoolllolllooddx0NMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNOdolccccccc:cooccclolccllccolccllloollllcllllodxONMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMXdlllccccccc:clllclooclolloolllccclcccc:::cclooddOXMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNkccccccc::ccccol:clo::ccldlclolc::::::;::ccllooodONMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMXdcccc:::cclc:clc:cl:;c:clccllc:::;;:::::ccccloodkXMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWKdlcc:;:::ccc:cc::c:;::cccccc::;::::cc:ccclllood0WMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNOdlc::::;::cccccccclodddddddddxxxxxkkkOOO00KKXWMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWX00OOOOOO0KKK0O00KXNWWWWWWWWMMWWMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
    }
}
