//package com.win.utility;
//
//import java.util.List;
//
//import com.win.utility.model.enumeration.UserType;
//import com.win.utility.service.UserService;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//
//import com.win.utility.model.User;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.apache.commons.cli.*;
//
//
//public class AppNew {
//
//    private static final String ANSI_RESET = "\u001B[0m";
//    private static final String ANSI_BLACK = "\u001B[30m";
//    private static final String ANSI_RED = "\u001B[31m";
//    private static final String ANSI_GREEN = "\u001B[32m";
//    private static final String ANSI_YELLOW = "\u001B[33m";
//    private static final String ANSI_BLUE = "\u001B[34m";
//    private static final String ANSI_PURPLE = "\u001B[35m";
//    private static final String ANSI_CYAN = "\u001B[36m";
//    private static final String ANSI_WHITE = "\u001B[37m";
//    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
//    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
//    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
//    private static final String ANSI_GRAY_BACKGROUND = "\u001B[47m";
//    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
//    private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
//    private static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
//    private static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
//    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
//
//    private static final String formatSort = "| %1$-24s | %2$-8s | %3$-12s |\n";
////    private static final String formatSortById = "|" + ANSI_BLUE_BACKGROUND + ANSI_YELLOW + " %1$-24s " + ANSI_RESET + "| %2$-8s | %3$-12s |\n";
////    private static final String formatSortByName = "| %1$-24s |" + ANSI_BLUE_BACKGROUND + ANSI_YELLOW + " %2$-8s " + ANSI_RESET + "| %3$-12s |\n";
//
//    private static UserService service;
//
//    public static void main(String[] args) {
//        Options options = new Options();
//
//        Option sort = new Option("s", "sort", true, "sort records by field");
//        sort.setRequired(false);
//        options.addOption(sort);
//
//        CommandLineParser parser = new DefaultParser();
//        HelpFormatter formatter = new HelpFormatter();
//        CommandLine cmd;
//
//        try {
//            cmd = parser.parse(options, args);
//            String sortByType = cmd.getOptionValue("sort");
//
//            ////////////////////////////////////
//            ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
//
//            service = ctx.getBean("userServiceBean", UserService.class);
//
//            myPrint(UserType.valueOf(sortByType));
//
//            System.exit(0);
//            return;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            formatter.printHelp("utility-name", options);
//            System.exit(1);
//            return;
//        }
//    }
//
//    public static void myPrint(UserType sortByType) {
//        List<User> users = service.getAllUsers(); //List<User> users = service.getAllUsers(sortByType); // ???
//
//        System.out.println("+----+---------------------+-------------------------+");
//        System.out.println("| ID |         NAME        |         PASSWORD        |");
//        System.out.println("+----+---------------------+-------------------------+");
//        switch (sortByType) {
//            case NAME:
//                for (User user: users)
//                    System.out.format(formatSort, user.getId(), user.getUsername(), user.getPassword());
////                    System.out.format(formatSortByName, user.getId(), user.getUsername(), user.getPassword());
//                break;
//            default:
//                for (User user: users)
//                    System.out.format(formatSort, user.getId(), user.getUsername(), user.getPassword());
////                    System.out.format(formatSortById, user.getId(), user.getUsername(), user.getPassword());
//                break;
//        }
//        System.out.println("+----+---------------------+-------------------------+");
//    }
//
//	public static void test() {
//        /////////////////////////
//        service.createUser("mkyong", "password123");
//        System.out.println("1. user");
//
//        /////////////////////////
//        User findUser = service.getUserByName("mkyong");
//        System.out.println("2. found - savedUser : " + findUser);
//
//        /////////////////////////
//        service.updatePassword("mkyong", "password", "new password");
//        System.out.println("3. updatedUser");
//
//        /////////////////////////
////        service.deleteUser("mkyong");
//
//        List<User> users = service.getAllUsers();
//        System.out.println("4. Number of user = " + users.size());
//	}
//
//}
