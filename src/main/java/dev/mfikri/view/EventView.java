package dev.mfikri.view;

import dev.mfikri.service.EventService;

import java.util.Scanner;

public class EventView {
    public Scanner scanner = new Scanner(System.in);
    private final EventService eventService;

    public EventView(EventService eventService) {
        this.eventService = eventService;
    }

    public void showEventCommand () {
        while (true) {
            System.out.println("----------------------------------------------------");
            System.out.println("Type the username to find the public activity of that user in github:");
            System.out.println("<username>");
            System.out.println("example: abdullahfikri");
            System.out.println("----------------------------------------------------");
            System.out.println("type exit to close the program");
            System.out.println();

            System.out.print("Username: ");
            String userInput = scanner.nextLine();
            String[] commands = userInput.split(" ");

            if (commands[0].equals("exit") && commands.length == 1) {
                System.out.println("Bye!");
                break;
            } else if ( commands.length == 1) {
                eventService.getAllEvents(commands[0]);
            } else {
                System.out.println("Unrecognized Command");
            }
        }
    }
}
