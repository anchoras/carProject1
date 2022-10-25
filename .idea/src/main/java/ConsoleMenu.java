import java.sql.SQLException;
import java.util.*;

import static java.lang.String.format;

public class ConsoleMenu {
    Scanner scanner = new Scanner(System.in);
    boolean isRunning = true;


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


    public void executeMainMenu() {
        MainMenu mainMenu = new MainMenu();
        while(this.isRunning) {
            System.out.println("Menu: \n" + MENU_COMMANDS.toString());
            switch (ConsoleMenuUtils.getMainMenuInput()) {
                case "1", "first" -> mainMenu.executeFirst();
                case "2", "second" -> mainMenu.executeSecond();
                case "3", "third" -> mainMenu.executeThird();
                case "pu", "powerunit", "power unit" -> mainMenu.executePowerUnitMenu();
                case "exit", "e", "q", "quit", "end", "fino", "done" -> mainMenu.executeExit();  //TODO make to use static final set?
                case "default" -> System.out.println("Default menu option...");
            }
        }
        System.out.println("Program is shutting down");
    }



    public class MainMenu {
        private void executeFirst() {
            boolean isReturnToMenu = false;
            while(!isReturnToMenu) {
                System.out.println("First menu option entered");
                System.out.println("Return to menu? : ");
                isReturnToMenu = ConsoleMenuUtils.getYesNoInput();
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
                isReturnToMenu = ConsoleMenuUtils.getYesNoInput();
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
                    punit = puDAO.findByID(ConsoleMenuUtils.getIntInput());
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    System.out.println(e.getStackTrace());
                }
                System.out.println(punit.toString());
                System.out.println("Return to menu? : ");
                isReturnToMenu = ConsoleMenuUtils.getYesNoInput();
            }
        }

        private void executePowerUnitMenu() {
            PowerUnitMenu puMenu = new PowerUnitMenu();
            PowerUnitDAO puDAO = new PowerUnitDAO();
            boolean isReturnToMenu = false;
            while(!isReturnToMenu) {
                System.out.println("Power unit menu option entered");
                /*
                PowerUnitDAO puDAO = new PowerUnitDAO();
                PowerUnit punit = new PowerUnit();
                PowerUnitCollection punits = new PowerUnitCollection();
                */
                System.out.println("Menu: \n" + PUMENU_CMDS.toString());
                try {
                    switch (ConsoleMenuUtils.getPowerUnitMenuInput()) {
                        case "add" -> puMenu.executePowerUnitAdd();
                        case "del", "delete" -> puMenu.executePowerUnitDeleteByID();
                        case "update", "change" -> puMenu.executePowerUnitUpdate();
                        case "show", "show all" -> puMenu.executePowerUnitShowAll();
                        case "find" -> puMenu.executePowerUnitFindById();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    System.out.println(e.getStackTrace());
                }
                System.out.println("Return to previous menu?..");
                isReturnToMenu = ConsoleMenuUtils.getYesNoInput();
            }
        }

        private void executeExit() {
            System.out.println("Preparing to shutting down");
            isRunning = false;
        }
    }


    public class PowerUnitMenu {
        public void executePowerUnitShowAll() throws SQLException {
            PowerUnitCollection punits = new PowerUnitCollection();
            punits.showAll();
        }

        public void executePowerUnitDeleteByID() throws  SQLException {
            PowerUnitCollection punits = new PowerUnitCollection();
            punits.showAll();
            System.out.println("Input id to delete ...");
            punits.delete(ConsoleMenuUtils.getIntInput());
        }

        public void executePowerUnitAdd() throws SQLException {
            PowerUnitCollection punits = new PowerUnitCollection();
            punits.add(createNewPunitByConsole());
        }

        public void executePowerUnitUpdate() throws SQLException {
            PowerUnitCollection punits = new PowerUnitCollection();
            PowerUnit newPunit = new PowerUnit();
            System.out.println("Input id for update ...");
            int id = ConsoleMenuUtils.getIntInput();
            newPunit = changePunitByConsole(punits.getById(id));
            punits.update(id, newPunit);
        }

        public void executePowerUnitFindById() throws SQLException {
            PowerUnitCollection punits = new PowerUnitCollection();
            punits.getById().toString();
        }

        private PowerUnit createNewPunitByConsole() {
            PowerUnit punit = new PowerUnit();
            System.out.println("Input type ...");
            punit.setType(ConsoleMenuUtils.getStringInput());
            System.out.println("Input name ...");
            punit.setName(ConsoleMenuUtils.getStringInput());
            System.out.println("Input maxcapacity ...");
            punit.setMaxcapacity(ConsoleMenuUtils.getDoubleInput());
            return punit;
        }

        private PowerUnit changePunitByConsole(PowerUnit oldPunit) {
            PowerUnit newPunit = new PowerUnit();
            System.out.println("Current type: " + oldPunit.getType());
            System.out.println("Input new type ...");
            newPunit.setType(ConsoleMenuUtils.getStringInput());
            System.out.println("Current name: " + oldPunit.getName());
            System.out.println("Input new name ...");
            newPunit.setName(ConsoleMenuUtils.getStringInput());
            System.out.println("Current maxcapacity: " + oldPunit.getMaxcapacity());
            System.out.println("Input new maxcapacity ...");
            newPunit.setMaxcapacity(ConsoleMenuUtils.getDoubleInput());
            return newPunit;
        }
    }


}
