package javaProject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
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

        board = new JPanel(new GridLayout(5, 3,5,5)); // Grid layout width = 6, height = 7
        getContentPane().setBackground(Color.BLACK);

        myJeopardy = new JButton[42];// Array of 42 buttons
        for (int i = 0; i < myJeopardy.length; i++){ //Loop to construct each button
            myJeopardy[i] = new JButton(""+i);// construct new button


            //Make only question buttons clickable
            if(i >5){
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
        if (myJeopardy[6].isEnabled() == false && myJeopardy[7].isEnabled() == false && myJeopardy[8].isEnabled() == false &&
                myJeopardy[9].isEnabled() == false && myJeopardy[10].isEnabled() == false && myJeopardy[11].isEnabled() == false &&
                myJeopardy[12].isEnabled() == false && myJeopardy[13].isEnabled() == false && myJeopardy[14].isEnabled() == false &&
                myJeopardy[15].isEnabled() == false && myJeopardy[16].isEnabled() == false && myJeopardy[17].isEnabled() == false &&
                myJeopardy[18].isEnabled() == false && myJeopardy[19].isEnabled() == false && myJeopardy[20].isEnabled() == false &&
                myJeopardy[21].isEnabled() == false && myJeopardy[22].isEnabled() == false && myJeopardy[23].isEnabled() == false &&
                myJeopardy[24].isEnabled() == false && myJeopardy[30].isEnabled() == false && myJeopardy[36].isEnabled() == false &&
                myJeopardy[25].isEnabled() == false && myJeopardy[31].isEnabled() == false && myJeopardy[37].isEnabled() == false &&
                myJeopardy[26].isEnabled() == false && myJeopardy[32].isEnabled() == false && myJeopardy[38].isEnabled() == false &&
                myJeopardy[27].isEnabled() == false && myJeopardy[33].isEnabled() == false && myJeopardy[39].isEnabled() == false &&
                myJeopardy[28].isEnabled() == false && myJeopardy[34].isEnabled() == false && myJeopardy[40].isEnabled() == false &&
                myJeopardy[29].isEnabled() == false && myJeopardy[35].isEnabled() == false && myJeopardy[41].isEnabled() == false ){
            if (p1Score > p2Score || p1Score >= 6300){
                JOptionPane.showMessageDialog(null, player1 + " is the WINNER!!!","Winner",JOptionPane.INFORMATION_MESSAGE);
                disableButtons(); //Call to disable buttons
            }
            else if (p1Score < p2Score || p2Score >= 6300){
                JOptionPane.showMessageDialog(null, player2 + " is the WINNER!!!","Winner",JOptionPane.INFORMATION_MESSAGE);
                disableButtons(); //Call to disable buttons
            }
        }
    }//End winner

    /************************************************************************/
    public int questions(int pos, int score){

        switch (pos){
            //Sports Questions
            case 1: myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("Question");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Sports for $100 \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("answer")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!");
                    score = moneyWrong(pos);
                }
                break;
            /************/
            case 4:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("question");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Sports for $200 \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("answer")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: answer");
                    score = moneyWrong(pos);
                }
                break;
            /****************/
            case 7: myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>What does Harry eat to manage to breathe "
                        + "underwater during the second task"
                        + "of the Triwizard Tournament?</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Harry Potter for $300 \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("gillyweed") || answer.equals("gilly weed")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Gillyweed");
                    score = moneyWrong(pos);
                }
                break;
            case 10:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>What does the spell \"Obliviate\" do? <br>"
                        + "a. Destroy objects <br>"
                        + "b. Make objects invisible <br>"
                        + "c. Remove parts of someone's memory </html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Harry Potter for $400 \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("c")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: C");
                    score = moneyWrong(pos);
                }
                break;
            /************/
            case 13:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>What is the killing curse?<br>"
                        + "(Please spell correctly)");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Harry Potter for $500 \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("avada kedavra")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Avada Kedavra");
                    score = moneyWrong(pos);
                }
                break;

            /******************************************************************************************************/
            //Academics
            case 2:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>Who directed Rogue One: A Star Wars Story? </html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Star Wars for $100 \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("gareth edwards")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Gareth Edwards");
                    score = moneyWrong(pos);
                }
                break;
            /*************/
            case 5:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>Who provided the voice of Yoda? </html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Star Wars for $200 \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("frank ozr")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Frank Oz");
                    score = moneyWrong(pos);
                }
                break;
            /************/
            case 8:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>Who commissioned the Grand Army of the Republic?</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Star Wars for $300 \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("jedi master sifo-dyas") || answer.equals("sifo dyas") || answer.equals("sifo-dyas")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Sifo Dyas");
                    score = moneyWrong(pos);
                }
                break;
            /************/
            case 11:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>How many known Jedi possess a purple-bladed lightsaber?</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Star Wars for $400 \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("one") || answer.equals("1")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: One");
                    score = moneyWrong(pos);
                }
                break;
            /************/
            case 14:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>Qui-Gon Jinn dies during a duel on which planet?</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Star Wars for $500 \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("naboo")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Naboo");
                    score = moneyWrong(pos);
                }
                break;

            /******************************************************************************************************/
            //Campus Life
            case 3:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>In \"The Lion King,\" what side of Scar's face is his scar on? Left or right?</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Disney for $100 \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("left")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: left");
                    score = moneyWrong(pos);
                }
                break;
            /*************/
            case 6:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>In \"Aladdin,\" what does Aladdin, and a reluctant Abu, give to the poor children to eat?</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Disney for $200 \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("bread") || answer.equals("loaf of bread")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: bread");
                    score = moneyWrong(pos);
                }
                break;
            /************/
            case 9:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>In \"Hercules,\" Hades promised not to harm Megara if Hercules gave up his strength for how long?</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Disney for $300 \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("24 hours") || answer.equals("24") || answer.equals("twenty four")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: 24");
                    score = moneyWrong(pos);
                }
                break;
            /************/
            case 12:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html> In \"The Little Mermaid,\" what alias does Ursula use when she becomes human?</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Disney for $400 \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("venessa") || answer.equals("vennessa")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Venessa");
                    score = moneyWrong(pos);
                }
                break;
            /************/
            case 15:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
                myJeopardy[pos].setText("<html>Who is the first and original disney princess?</html>");
                myJeopardy[pos].setEnabled(false);
                myJeopardy[pos].setBackground(Color.BLACK);
                myJeopardy[pos].setIcon(null);

                answer = JOptionPane.showInputDialog("Disney for $500 \nWhat is: ");
                answer = answer.toLowerCase();

                if (answer.equals("snow white")){
                    JOptionPane.showMessageDialog(null, "CORRECT!");
                    score = money(pos);
                    System.out.println(score);
                }else{
                    JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Snow White");
                    score = moneyWrong(pos);
                }
                break;
        }//End switch
        return score;
    }
    /************************************************************************/
    public int money(int pos){
        //Method to set what each button is worth score wise
        if (pos == 6 || pos == 7 || pos == 8 || pos == 9 || pos == 10 || pos == 11){
            score = 100;
        }
        else if (pos == 12 || pos == 13 || pos == 14 || pos == 15 || pos == 16 || pos == 17){
            score = 200;
        }
        else if (pos == 18 || pos == 19 || pos == 20 || pos == 21 || pos == 22 || pos == 23){
            score = 300;
        }
        else if (pos == 24 || pos == 25 || pos == 26 || pos == 27 || pos == 28 || pos == 29){
            score = 400;
        }
        else if (pos == 30 || pos == 31 || pos == 32 || pos == 33 || pos == 34 || pos == 35){
            score = 500;
        }
        else if (pos == 36 || pos == 37 || pos == 38 || pos == 39 || pos == 40 || pos == 41){
            score = 600;
        }
        return score;
    }//End money

    /************************************************************************/
    public int moneyWrong(int pos){
        //Method to display a score of ) if answer is incorrect
        if (pos == 6 || pos == 7 || pos == 8 || pos == 9 || pos == 10 || pos == 11 ||
                pos == 12 || pos == 13 || pos == 14 || pos == 15 || pos == 16 || pos == 17 ||
                pos == 18 || pos == 19 || pos == 20 || pos == 21 || pos == 22 || pos == 23 ||
                pos == 24 || pos == 25 || pos == 26 || pos == 27 || pos == 28 || pos == 29 ||
                pos == 30 || pos == 31 || pos == 32 || pos == 33 || pos == 34 || pos == 35 ||
                pos == 36 || pos == 37 || pos == 38 || pos == 39 || pos == 40 || pos == 41){
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