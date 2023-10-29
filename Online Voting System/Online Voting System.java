import java.util.Scanner;
class main1 {
    public static void main(String[] args) {
        System.out.println("-------> Welcome to the online voting system >-------");
        System.out.println("");
        System.out.println("enter how many people are voting");
        LinkedList pairlist = new LinkedList();
        Scanner sc = new Scanner(System.in);
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        int people=sc.nextInt();
        for (int i = 1; i <= people; i++) {
            System.out.println("");
            System.out.println("Enter name of "+i+" voter");
            String name = sc.next();
            System.out.println("enter your vote");
            System.out.println("");
            System.out.println("1.BJP");
            System.out.println("2.INC");
            System.out.println("3.AAP");
            int votechoice = sc.nextInt();
            String vote = "";

            switch (votechoice) {
                case 1:
                    vote = "BJP";
                    v1++;
                    break;
                case 2:
                    vote = "INC";
                    v2++;
                    break;
                case 3:
                    vote = "AAP";
                    v3++;
                    break;
                default:
                    System.out.println("Invalid vote choice. Please vote again.");
                    i--; // Retry the vote
                    continue; // Continue to the next iteration of the loop
            }
            pairlist.insert(name, vote);
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("");
            System.out.println("\nChoose an option:");
            System.out.println("1. Print List");
            System.out.println("2. Show Winner");
            System.out.println("3. Search by Name");
            System.out.println("4. Exit");

            int choiceAfterVoting = sc.nextInt();

            switch (choiceAfterVoting) {
                case 1:
                    pairlist.printList();
                    break;

                case 2:
                    showWinner(v1, v2, v3);
                    break;

                case 3:
                    searchByName(pairlist);
                    break;

                case 4:
                    exit = true;
                    System.out.println("Thank you for using the online voting system.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void showWinner(int v1, int v2, int v3) {
        // Determine the winner
        String winner;
        int maxVotes;

        if (v1 > v2 && v1 > v3) {
            winner = "BJP";
            maxVotes = v1;
        } else if (v2 > v1 && v2 > v3) {
            winner = "INC";
            maxVotes = v2;
        } else if (v3 > v1 && v3 > v2) {
            winner = "AAP";
            maxVotes = v3;
        } else {
            winner = "No clear winner";
            maxVotes = 0;
        }

        System.out.println("Winner of the voting is " + winner +" votes.");
    }

    public static void searchByName(LinkedList list) {
        Scanner searchScanner = new Scanner(System.in);
        System.out.println("Enter the name you want to search for:");
        String searchName = searchScanner.next();
        String searchResult = list.searchByString(searchName);

        if (searchResult != null) {
            System.out.println(searchName + " voted for " + searchResult);
        } else {
            System.out.println(searchName + " not found in the list.");
        }
    }
}

class Node {
    String stringValue1;
    String stringValue2;
    Node next;

    public Node(String stringValue1, String stringValue2) {
        this.stringValue1 = stringValue1;
        this.stringValue2 = stringValue2;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    public LinkedList() {
        this.head = null;
    }

    public void insert(String stringValue1, String stringValue2) {
        Node newNode = new Node(stringValue1, stringValue2);

        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.println("(" + current.stringValue1 + "voted for" + current.stringValue2 + ")");
            current = current.next;
        }
    }

    public String searchByString(String searchString) {
        Node current = head;
        while (current != null) {
            if (current.stringValue1.equals(searchString)) {
                return current.stringValue2;
            }
            current = current.next;
        }
        return null; // Return null if the searchString is not found
    }
}