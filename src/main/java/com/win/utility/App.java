package com.win.utility;

import com.win.utility.model.Account;
import com.win.utility.model.User;
import com.win.utility.model.enumeration.AccountType;
import com.win.utility.model.enumeration.UserType;
import com.win.utility.service.AccountService;
import com.win.utility.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import org.apache.commons.cli.*;

/**
 * Program arguments: -s ID
 *
 * ID, OWNER, BALANCE
 */

public class App {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    private static final String ANSI_GRAY_BACKGROUND = "\u001B[47m";
    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    private static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    private static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private static final String formatSortOld = "| %1$-2s | %2$-15s | %3$-8s |\n";
    private static final String formatSortNew = "| %1$-24s | %2$-8s | %3$-12s |\n";

    private static AccountService serviceOld;
    private static UserService serviceNew;


    public static void main(String[] args) {
        Options options = new Options();

        Option sort = new Option("s", "sort", true, "sort records by field");
        sort.setRequired(false);
        options.addOption(sort);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            String sortByType = cmd.getOptionValue("sort");

            ////////////////////////////////////
            ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");

            serviceOld = ctx.getBean("accountServiceBean", AccountService.class);
            serviceNew = ctx.getBean("userServiceBean", UserService.class);

            printStatistic(AccountType.valueOf(sortByType));
            printStatistic(UserType.valueOf(sortByType));

            System.exit(0);
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
            return;
        }
    }


    public static void printStatistic(AccountType sortByType) {
        List<Account> accounts = serviceOld.getAllAccounts(sortByType);

        System.out.println("+----+-----------------+----------+");
        System.out.println("| ID |      OWNER      |  BALANCE |");
        System.out.println("+----+-----------------+----------+");
        switch (sortByType) {
            case OWNER:
                for (Account account: accounts)
                    System.out.format(formatSortOld, account.getId(), account.getOwner(), account.getBalance());
                break;
            case BALANCE:
                for (Account account: accounts)
                    System.out.format(formatSortOld, account.getId(), account.getOwner(), account.getBalance());
                break;
            default:
                for (Account account: accounts)
                    System.out.format(formatSortOld, account.getId(), account.getOwner(), account.getBalance());
                break;
        }
        System.out.println("+----+-----------------+----------+");
    }

    public static void printStatistic(UserType sortByType) {
        List<User> users = serviceNew.getAllUsers(); //List<User> users = service.getAllUsers(sortByType); // ???

        System.out.println("+----+---------------------+-------------------------+");
        System.out.println("| ID |         NAME        |         PASSWORD        |");
        System.out.println("+----+---------------------+-------------------------+");
        switch (sortByType) {
            case NAME:
                for (User user: users)
                    System.out.format(formatSortNew, user.getId(), user.getUsername(), user.getPassword());
                break;
            default:
                for (User user: users)
                    System.out.format(formatSortNew, user.getId(), user.getUsername(), user.getPassword());
                break;
        }
        System.out.println("+----+---------------------+-------------------------+");
    }
}
