import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

class Grid{
  private int numRows;
  private int numColumns;
  private int numBombs;
  private boolean bombGrid[][];
  private int countGrid[][];

  public Grid(){
    numRows = 10;
    numColumns = 10;
    numBombs = 25;
    bombGrid = new boolean[numRows][numColumns];
    countGrid = new int[numRows][numColumns];
    createBombGrid();
    createCountGrid();
  }

  public Grid(int rows, int columns){
    numRows = rows;
    numColumns = columns;
    numBombs = 25;
    bombGrid = new boolean[numRows][numColumns];
    countGrid = new int[numRows][numColumns];
    createBombGrid();
    createCountGrid();
  }

  public Grid(int rows, int columns , int bombs){
    numRows = rows;
    numColumns = columns;
    numBombs = bombs;
    bombGrid = new boolean[numRows][numColumns];
    countGrid = new int[numRows][numColumns];
    createBombGrid();
    createCountGrid();
  }

  void createBombGrid(){
    

    for(int i=0;i<numBombs;i++){
      int x =(int) (Math.random()*numBombs)%numRows;
      int y =(int) (Math.random()*numBombs)%numColumns;

while(true){

	if(bombGrid[x][y] ==true) {
if((x-1)>=0) x=x-1;
if((y+1)<numColumns) y=y+1;
continue;
}
else bombGrid[x][y] =true;
break;
}

    }
  }


boolean isBomb(int row, int column){
    if(row >=0 && column >= 0 && row < numRows && column< numColumns){
       return bombGrid[row][column] == true;
    }

    return false;
  }

void createCountGrid(){
    for(int i=0;i<numRows;i++){
      for(int j=0;j<numColumns;j++){
        int b = 0;
if(bombGrid[i][j]) b=1;

        if(isBomb(i,j-1)){
          b += 1;
        }
 if(isBomb(i-1,j)){
          b += 1;
        }
       
 if(isBomb(i-1,j-1)){
          b += 1;
        }
       
        if(isBomb(i-1,j+1)){
          b += 1;
        }
        if(isBomb(i,j+1)){
          b += 1;
        }
        if(isBomb(i+1,j)){
          b += 1;
        }

        if(isBomb(i+1,j+1)){
          b += 1;
        }
        if(isBomb(i+1,j-1)){
          b += 1;
        }
        countGrid[i][j] = b;
      }
    }
  }


// setter are used to set the values and getter are use to get 
	public int getNumRows() {
		return  numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	public int getNumBombs() {
		return numBombs;
	}



	public boolean[][]getBombGrid(){

boolean [][]bombG=new boolean[bombGrid.length][bombGrid[0].length];

for(int i=0;i<bombGrid.length;i++)
{
for(int j=0;j<bombGrid[0].length;j++)
{
bombG[i][j]=bombGrid[i][j];
}
}
return bombG;
}


	public int [][] getCountGrid() {
		int [][]count= new int[countGrid.length][countGrid[0].length];

for(int i=0;i<bombGrid.length;i++)
{
for(int j=0;j<bombGrid[0].length;j++)
{
count[i][j]=countGrid[i][j];
}
}

return count;
}


	public boolean isBombAtLocation(int row, int column) {
	return bombGrid[row][column];
	}

	public int getCountAtLocation(int row, int column) {

		return countGrid[row][column];
	}



public void startGame()
{
JFrame f=new JFrame();

int numberOfButtons=numRows*numColumns;
JButton b[]=new JButton[numberOfButtons];
int x,y;
x=1;
y=1;
int val=0;
for(int i=1;i<=numberOfButtons;i++) {
b[i-1]=new JButton(".");


b[i-1].addActionListener(new ActionListener(){
public int count=0;
public void actionPerformed(ActionEvent e)
{

count++;
for(int i=0;i<100;i++) {

// 23432423 

if(e.getSource()==b[i])
{
// i=76
// 76/10=7 and 76%10=6

if(bombGrid[i/10][i%10]) {
int a = JOptionPane.showConfirmDialog(f,"You lose!! Play Again?");
if(a==JOptionPane.YES_OPTION)
{
f.dispose();
startGame();
}
else System.exit(0); 
}
else if(count>=numberOfButtons-numBombs) {
int a = JOptionPane.showConfirmDialog(f,"You lose!! Play Again?");
if(a==JOptionPane.YES_OPTION)
{
f.dispose();
startGame();
}
else System.exit(0);
}

else b[i].setText(""+countGrid[i/10][i%10]);
}
}

}
});

b[i-1].setBounds((x)+val,y,50,50);
val+=50;

f.add(b[i-1]);
if(i%10==0) {
val=0;
y+=50;
x=1;
}
}


f.setSize(520,540);
f.setLayout(null);
f.setVisible(true);
}


}




class Minesweeper
{
public static void main(String args[]) {

Grid minesweep = new Grid();

// here is the new updation
minesweep.startGame();

int rows = minesweep.getNumRows();

int columns = minesweep.getNumColumns();

boolean[][] bombgrid = minesweep.getBombGrid();

for (int i = 0; i < rows; i++) {

for (int j = 0; j < columns; j++) {

	System.out.print(bombgrid[i][j] ? "T" : "F");

}

System.out.println("");

}

System.out.println("");

int[][] countgrid = minesweep.getCountGrid();

for (int i = 0; i < rows; i++) {

for (int j = 0; j < columns; j++) {

	System.out.print(countgrid[i][j] + "");

}

System.out.println("");
}
}


}