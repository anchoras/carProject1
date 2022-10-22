import java.sql.SQLException;
import java.util.*;

import static java.lang.String.format;

public class ConsoleMenu {
    Scanner scanner = new Scanner(System.in);
    boolean isRunning = true;

    private static final Set<String> EXIT_COMMANDS;
    private static final Set<String> HELP_COMMANDS;
    private static final Set<String> MENU_COMMANDS;
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
        final SortedSet<String> mcmds = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        mcmds.addAll(Arrays.asList("first", "1", "second", "2", "third", "3"));
        MENU_COMMANDS = Collections.unmodifiableSortedSet(mcmds);
        final SortedSet<String> yeswords = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        yeswords.addAll(Arrays.asList("yes", "y", "confirm"));
        YES_WORDS = Collections.unmodifiableSortedSet(yeswords);
        final SortedSet<String> nowords = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        nowords.addAll(Arrays.asList("no", "n", "deny"));
        NO_WORDS = Collections.unmodifiableSortedSet(nowords);
        HELP_COMMANDS = Collections.unmodifiableSet(hcmds);
        HELP_MESSAGE = format("Please enter some data or enter one of the following commands to exit %s", EXIT_COMMANDS);
    }

    public void executeMenu() {
        while(this.isRunning) {
            //System.out.println("Menu: \n1. \n2. \n0. \n...\n\n");
            System.out.println("Menu: \n" + MENU_COMMANDS.toString());
            switch (getMainMenuInput()) {
                case "1", "first" -> executeFirst();
                case "2", "second" -> executeSecond();
                case "3", "third" -> executeThird();
                case "default" -> System.out.println("Default menu option...");
            }
        }
        System.out.println("Program is shutting down");
    }

    private String getMainMenuInput() {
        String choosenMenu = "default";
        while (!MENU_COMMANDS.contains(choosenMenu)) {
            System.out.println("Input desirable menu name ...");
            if (!scanner.hasNextLine()) {
                System.out.println("Please, input String");
                continue;
            }
            choosenMenu = scanner.nextLine();
            if (EXIT_COMMANDS.contains(choosenMenu)) {
                executeExit();
                break;
            } else if (MENU_COMMANDS.contains(choosenMenu)) {
                return choosenMenu;
            } else {
                System.out.println("your input is: " + choosenMenu);
                System.out.println("try again or input some of this commands for exit: " + EXIT_COMMANDS.toString());
            }
        }
        return choosenMenu;
    }


    private int getIntInput() {
        int choosenState = 0;
        while (!scanner.hasNextInt()) {
            System.out.println("input Integer ...");
            scanner.nextInt();
        };
        choosenState = scanner.nextInt();
        return choosenState;
    }

    private boolean getYesNoInput() {
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

    private void executeFirst() {
        boolean isReturnToMenu = false;
        while(!isReturnToMenu) {
            System.out.println("First menu option entered");
            System.out.println("Return to menu? : ");
            isReturnToMenu = getYesNoInput();
        }
    }

    private void executeSecond() {
        boolean isReturnToMenu = false;
        while(!isReturnToMenu) {
            System.out.println("Second menu option entered");
            PowerUnitDAO puDAO = new PowerUnitDAO();
            PowerUnitCollection punits = new PowerUnitCollection();
            try {
                punits = new PowerUnitCollection(puDAO.findAll());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
            System.out.println(punits.toString());
            System.out.println("Return to menu? : ");
            isReturnToMenu = getYesNoInput();
        }
    }

    private void executeThird() {
        boolean isReturnToMenu = false;
        while(!isReturnToMenu) {
            System.out.println("Third menu option entered");
            PowerUnitDAO puDAO = new PowerUnitDAO();
            PowerUnit punit = new PowerUnit();
            try {
                System.out.println("input id number...");
                punit = puDAO.findByID(getIntInput());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
            System.out.println(punit.toString());
            System.out.println("Return to menu? : ");
            isReturnToMenu = getYesNoInput();
        }
    }

    private void executeExit() {
        System.out.println("Preparing to shutting down");
        this.isRunning = false;
    }
}
