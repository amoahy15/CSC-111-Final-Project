package javaProject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class jeopardy extends JFrame implements ActionListener{


    private static final long serialVersionUID = 1L;

    //Declare variables
    public static final int WIDTH = 1100; //Dimensions of Jeopardy game board
    public static final int HEIGHT = 1000;
    private JPanel board; //Name of board(panel)
    private JPanel info;
    private JButton [] myJeopardy; //Array for buttons
    private JButton reset;
    private JLabel player1Wins; //Number of wins per player
    private JLabel player2Wins;
    private static String player1; //Variable to store player 1 name
    private static String player2; //Variable to store player 2 name
    private String answer;
    private static int p1Score; //Variable to keep track of player 1 score
    private static int p2Score; //Variable to keep track to player 2 score
    private int score;
    private int turn;

    /*************************************************************************/
    public jeopardy(Color theColor){
        super("Wake Forest Jeopardy"); //Name that appears at the top
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5,5));

        board = new JPanel(new GridLayout(5, 3,5,5));
        getContentPane().setBackground(Color.BLACK);

        myJeopardy = new JButton[15];// Array of 15 buttons
        for (int i = 0; i < myJeopardy.length; i++){ //Loop to construct each button
            myJeopardy[i] = new JButton(""+i);// construct new button


            //Make only question buttons clickable
            if(i >= 0){
                myJeopardy[i].addActionListener(this); // To make it clickable
            }//End if

            myJeopardy[i].setSize(10,10);
            categories(i); //Call method to set button background colors
            myJeopardy[i].setForeground(Color.BLACK);
            board.add(myJeopardy[i]);
        }// End for

        add(board, BorderLayout.CENTER);
        info = new JPanel(new FlowLayout());// layout of another panel at bottom of game
        reset = new JButton("RESET"); //Reset button
        reset.addActionListener(this); //Make reset button clickable
        player1Wins = new JLabel(player1 + "'s Score: ");
        player2Wins = new JLabel(player2 + "'s Score: ");
        info.add(player1Wins); //Add buttons to board
        info.add(reset);
        info.add(player2Wins);
        add(info, BorderLayout.SOUTH);
    }//End Jeopardy

    /*************************************************************************/

    public static void main(String[] args) {

        int input = 0; //Get user input

        do{ //Menu to display rules, enter player names, play game and exit menu
            input = Integer.parseInt(JOptionPane.showInputDialog("1. View the rules of the game \n"
                    + "2. Enter Player One and Player Two names \n"
                    + "3. Play the game! \n"
                    + "4. Exit Menu"));

            if (input == 1){ //Rules of the game
                JOptionPane.showMessageDialog(null, "1. This is a two player game. \n"
                                + "2. Must pick a category and a point value to play \n"
                                + "3. Click on the chosen box for the question. \n"
                                + "4. Correct responses must satisfy the demands of both the clue and the category. \n"
                                + "5. Responses must be spelled correctly \n"
                                + "6. Click the “Reset” button to start all over.\n"
                                + "7. Player with highest dollar amount at end of game wins", "Rules of Jeopardy",
                        JOptionPane.INFORMATION_MESSAGE);

            }//End if
            else if (input == 2){ //Enter player 1 and player 2 names
                player1 = JOptionPane.showInputDialog("Enter Player One's name: ");
                player2 = JOptionPane.showInputDialog("Enter Player Two's name: ");
            }//End else
            else if (input == 3){
                while (player1 == null || player2 == null){ //While loop validation if no entry for names
                    JOptionPane.showMessageDialog(null, "You didn't enter a name for Player One or Player Two. \n"
                            + "Please enter names for Player One or Player Two: ");
                    player1 = JOptionPane.showInputDialog("Enter Player One's name: ");
                    player2 = JOptionPane.showInputDialog("Enter Player Two's name: ");
                }//End while

                //Start Game
                jeopardy w = new jeopardy (Color.RED);
                w.setVisible(true);
            }//End else if
        }
        while (input > 0 && input < 4);

    }//End main
    /************************************************************************/
    public void actionPerformed(ActionEvent e) {
        System.out.println(e); //What prints out in console
        String actionCommand = e.getActionCommand();
        System.out.println("this is the item : " + actionCommand); //Prints out index location of button in array

        if ( actionCommand.equals("RESET")){
            resetBoard(); //Call to reset game
        }//End if
        else{
            int pos = Integer.parseInt(actionCommand);// convert string into int
            System.out.println(pos);
            if (turn % 2 == 0){
                questions(pos, score); //Call method to display questions for each button
                p1Score += score; //Running total score
                player1Wins.setText(player1 + "'s Score: $" + p1Score); //Display scores and player names
                turn++;
            }//if
            else if (turn % 2 == 1){
                questions(pos, score);
                p2Score += score;
                player2Wins.setText(player2 + "'s Score: $" + p2Score); //Display scores and player names
                turn++;
            }//End else if
        }//End else
        winner(); //Call to display winner
    }//End actionPerformed
    /************************************************************************/
    public void winner(){
        //If every button is disabled, the game ends and displays the winner's name
        if (myJeopardy[0].isEnabled() == false && myJeopardy[1].isEnabled() == false &&
                myJeopardy[2].isEnabled() == false && myJeopardy[3].isEnabled() == false &&
                myJeopardy[4].isEnabled() == false && myJeopardy[5].isEnabled() == false &&
                myJeopardy[6].isEnabled() == false && myJeopardy[7].isEnabled() == false &&
                myJeopardy[8].isEnabled() == false && myJeopardy[9].isEnabled() == false &&
                myJeopardy[10].isEnabled() == false && myJeopardy[11].isEnabled() == false &&
                myJeopardy[12].isEnabled() == false && myJeopardy[13].isEnabled() == false
                && myJeopardy[14].isEnabled() == false){
            if (p1Score > p2Score || p1Score >= 4500){
                JOptionPane.showMessageDialog(null, player1 + " is the WINNER!!!","Winner",JOptionPane.INFORMATION_MESSAGE);
                disableButtons(); //Call to disable buttons
            }
            else if (p1Score < p2Score || p2Score >= 4500){
                JOptionPane.showMessageDialog(null, player2 + " is the WINNER!!!","Winner",JOptionPane.INFORMATION_MESSAGE);
                disableButtons(); //Call to disable buttons
            }
        }
    }//End winner

    /************************************************************************/
    public int questions(int pos, int score){

        switch (pos){
            //Academics Questions
            case 0: myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Academics for $100 \nHow many majors does Wake offer? \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("45") || answer.equals("forty five") || answer.equals("Forty five")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: 45");
                    score = moneyWrong(pos);
                }
                break;
            /************/
            case 3:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Academics for $200 \nHow many minors does Wake offer? \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("60") || answer.equals("sixty") || answer.equals("Sixty")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: 60");
                    score = moneyWrong(pos);
                }
                break;
            /****************/
            case 6: myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Academics for $300 \nWhat hall is the office of academic advising in? \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("Reynolda") || answer.equals("reynolda") || answer.equals("Reynolda hall")
                || answer.equals("reynolda hall")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: answer");
                    score = moneyWrong(pos);
                }
                break;
            /****************/
            case 9:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Academics for $400 \nWhat is Wake's 2021 national rank? \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("28") || answer.equals("twenty eight") || answer.equals("Twenty eight")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: 28");
                    score = moneyWrong(pos);
                }
                break;
            /************/
            case 12:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("question");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Academics for $500" +
                        " \nWhat is the total number of instructional employees at Wake? \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("836")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: 836");
                    score = moneyWrong(pos);
                }
                break;

            /******************************************************************************************************/
            //Sports
            case 1:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Sports for $100 \nHow many division 1 sports does Wake compete in? \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("18") || answer.equals("eighteen") || answer.equals("Eighteen")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: 18");
                    score = moneyWrong(pos);
                }
                break;
            /*************/
            case 4:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Sports for $200 \nWhat conference does Wake compete within? \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("ACC") || answer.equals("acc") || answer.equals("Atlantic Coast Conference") ||
                        answer.equals("atlantic coast conference") || answer.equals("Atlantic Coast") || answer.equals("atlantic coast")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Atlantic Coast Conference");
                    score = moneyWrong(pos);
                }
                break;
            /************/
            case 7:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Sports for $300 " +
                        "\nHow many bowl games has Wake football played in the last 20 years? \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("9") || answer.equals("nine") || answer.equals("Nine")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: 9");
                    score = moneyWrong(pos);
                }
                break;
            /************/
            case 10:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Sports for $400 " +
                        "\nHow many ACC championships has Wake won, across all sports, in the last 20 years? \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("16") || answer.equals("sixteen") || answer.equals("Sixteen")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: 16");
                    score = moneyWrong(pos);
                }
                break;
            /************/
            case 13:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Sports for $500 \nWhat percentage of Wake student-athletes graduate? \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("95") || answer.equals("95%") || answer.equals("ninety five")
                || answer.equals("Ninety five")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: 95%");
                    score = moneyWrong(pos);
                }
                break;

            /******************************************************************************************************/
            //Campus Life
            case 2:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                //myJeopardy[pos].setText("How many freshman dorms are on south campus?");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Campus Life for $100 \nHow many freshman dorms are on south campus? \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("7") || answer.equals("seven") || answer.equals("Seven")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: 7");
                    score = moneyWrong(pos);
                }
                break;
            /*************/
            case 5:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Campus Life for $200 \nWho is the current dean of students? \nWho is: ");
                answer = answer.toLowerCase();

                if (answer.equals("Dr. Adam Goldstein") || answer.equals("Adam Goldstein")
                || answer.equals("dr. adam goldstein") || answer.equals("adam goldstein")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: Dr. Adam Goldstein");
                    score = moneyWrong(pos);
                }
                break;
            /************/
            case 8:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Campus Life for $300 \nHow many social fraternities and sororities are at Wake? \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("24") || answer.equals("twenty four") || answer.equals("Twenty four")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: 24");
                    score = moneyWrong(pos);
                }
                break;
            /************/
            case 11:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Campus Life for $400 \nWhat does the 'Z' in ZSR stand for? \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("Zachary") || answer.equals("zachary")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Zachary");
                    score = moneyWrong(pos);
                }
                break;
            /************/
            case 14:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Campus Life for $500 \nHow many acres is Wake's campus? \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("340") || answer.equals("340 acres")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: 340 acres");
                    score = moneyWrong(pos);
                }
                break;
        }//End switch
        return score;
    }
    /************************************************************************/
    public int money(int pos){
        //Method to set what each button is worth score wise
        if (pos == 0 || pos == 1 || pos == 2){
            score = 100;
        }
        else if (pos == 3 || pos == 4 || pos == 5){
            score = 200;
        }
        else if (pos == 6 || pos == 7 || pos == 8){
            score = 300;
        }
        else if (pos == 9 || pos == 10 || pos == 11){
            score = 400;
        }
        else if (pos == 12 || pos == 13 || pos == 14){
            score = 500;
        }
        return score;
    }//End money

    /************************************************************************/
    public int moneyWrong(int pos){
        //Method to display a score of ) if answer is incorrect
        if (pos == 0 || pos == 1 || pos == 2 || pos == 3 || pos == 4 || pos == 5 ||
                pos == 6 || pos == 7 || pos == 8 || pos == 9 || pos == 10 || pos == 11 ||
                pos == 12 || pos == 13 || pos == 14){
            score = 0;
        }//End if

        return score;
    }//End money

    /************************************************************************/
    public void categories(int i){
        if(i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5){
            myJeopardy[i].setBackground(Color.WHITE);
        }else
        if(i == 8 || i == 14 || i == 20 || i == 26 || i == 32 || i == 38){
            myJeopardy[i].setBackground(Color.WHITE);
        }//End if

    }//End categories

    public void resetBoard(){
        for (int i = 0; i < myJeopardy.length; i++){
            myJeopardy[i].setEnabled(true);
            myJeopardy[i].setText("" + i);
            categories(i);
            myJeopardy[i].setForeground(Color.BLACK);
            p1Score = 0;
            p2Score = 0;
            player1Wins.setText(player1 + "'s Score: " + p1Score);
            player2Wins.setText(player2 + "'s Score: " + p2Score);
        }
    }//End resetBoard
    /*******************************************************************/
    public void disableButtons(){
        for (int i = 0; i < myJeopardy.length; i++){
            myJeopardy[i].setEnabled(false);
            myJeopardy[i].setText("" + i);
            categories(i);
            myJeopardy[i].setForeground(Color.BLACK);
        }
    }
}