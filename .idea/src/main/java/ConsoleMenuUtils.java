import java.util.*;

import static java.lang.String.format;


public class ConsoleMenuUtils {
    static Scanner scanner = new Scanner(System.in);


    private static final Set<String> EXIT_COMMANDS;
    private static final Set<String> HELP_COMMANDS;
    private static final Set<String> MENU_COMMANDS;
    private static final Set<String> PUMENU_CMDS;
    private static final Set<String> YES_WORDS;
    private static final Set<String> NO_WORDS;
    private static final String HELP_MESSAGE;

    static
    {
        final SortedSet<String> ecmds = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        ecmds.addAll(Arrays.asList("exit", "done", "quit", "end", "fino", "e", "q"));
        EXIT_COMMANDS = Collections.unmodifiableSortedSet(ecmds);
        final SortedSet<String> hcmds = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        hcmds.addAll(Arrays.asList("help", "helpi", "?"));
        HELP_COMMANDS = Collections.unmodifiableSortedSet(hcmds);
        final SortedSet<String> mcmds = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        mcmds.addAll(Arrays.asList("first", "1", "second", "2", "third", "3", "pu", "powerunit", "power unit"));
        MENU_COMMANDS = Collections.unmodifiableSortedSet(mcmds);
        final SortedSet<String> pumenucmds = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        pumenucmds.addAll(Arrays.asList("delete", "del", "add", "change", "update", "show", "show all", "find"));
        PUMENU_CMDS = Collections.unmodifiableSortedSet(pumenucmds);
        final SortedSet<String> yeswords = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        yeswords.addAll(Arrays.asList("yes", "y", "confirm"));
        YES_WORDS = Collections.unmodifiableSortedSet(yeswords);
        final SortedSet<String> nowords = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        nowords.addAll(Arrays.asList("no", "n", "deny"));
        NO_WORDS = Collections.unmodifiableSortedSet(nowords);
        HELP_MESSAGE = format("Please enter some data or enter one of the following commands to exit %s", EXIT_COMMANDS);
    }


    public static int getIntInput() {
        int choosenState = 0;
        while (!scanner.hasNextInt()) {
            System.out.println("input Integer ...");
            scanner.nextInt();
        };
        choosenState = scanner.nextInt();
        return choosenState;
    }

    public static String getStringInput() {
        String choosenState = "";
        while (!scanner.hasNextLine()) {
            System.out.println("input String ...");
            scanner.nextLine();
        };
        choosenState = scanner.nextLine();
        return choosenState;
    }

    public static Double getDoubleInput() {
        Double choosenState = 0.0;
        while (!scanner.hasNextDouble()) {
            System.out.println("input String ...");
            scanner.nextDouble();
        };
        choosenState = scanner.nextDouble();
        return choosenState;
    }

    public static boolean getYesNoInput() {
        boolean yesNoAnswer = false;
        String currentAnswer = "default";
        while (!(YES_WORDS.contains(currentAnswer) || NO_WORDS.contains(currentAnswer))) {
            currentAnswer = scanner.nextLine();
            if (YES_WORDS.contains(currentAnswer)) {
                yesNoAnswer = true;
                break;
            } else if (NO_WORDS.contains(currentAnswer)) {
                yesNoAnswer = false;
            } else { continue; }
        }
        return yesNoAnswer;
    }

    public static String getMainMenuInput() {
        String choosenMenu = "default";
        while (!MENU_COMMANDS.contains(choosenMenu)) {
            System.out.println("Input desirable menu name ...");
            if (!scanner.hasNextLine()) {
                System.out.println("Please, input String");
                continue;
            }
            choosenMenu = scanner.nextLine();
            if (MENU_COMMANDS.contains(choosenMenu) || EXIT_COMMANDS.contains(choosenMenu)) {
                System.out.println("returning input: " + choosenMenu);
                return choosenMenu;
            } else {
                System.out.println("your input is: " + choosenMenu);
                System.out.println("try again or input some of this commands for exit: " + EXIT_COMMANDS.toString());
            }
        }
        return choosenMenu;
    }

    public static String getPowerUnitMenuInput() {
        String choosenMenu = "default";
        while (!PUMENU_CMDS.contains(choosenMenu)) {
            System.out.println("Input desirable power menu name ...");
            if (!scanner.hasNextLine()) {
                System.out.println("Please, input String");
                continue;
            }
            choosenMenu = scanner.nextLine();
            if (PUMENU_CMDS.contains(choosenMenu) || EXIT_COMMANDS.contains(choosenMenu)) {
                System.out.println("returning input pu: " + choosenMenu);
                return choosenMenu;
            } else {
                System.out.println("your input is: " + choosenMenu);
                System.out.println("try again or input some of this commands for exit: " + EXIT_COMMANDS.toString());
            }
        }
        return choosenMenu;
    }
}
