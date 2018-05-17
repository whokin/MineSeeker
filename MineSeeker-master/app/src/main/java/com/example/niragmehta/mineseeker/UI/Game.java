package com.example.niragmehta.mineseeker.UI;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.niragmehta.mineseeker.R;
import com.example.niragmehta.mineseeker.model.Cell;
import com.example.niragmehta.mineseeker.model.ConfigOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game extends AppCompatActivity {

    int minesFound=0;
    int scansUsed=0;

    public static Intent makeIntent(Context context){
        return new Intent(context, Game.class);
    }

    Dialog congratulation_diag;

    private ConfigOptions configOptions=ConfigOptions.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get the number of rows and columns the table contains
        final int TABLE_ROW = configOptions.getRow();
        final int TABLE_COL = configOptions.getCol();

        final int totalNumberOfMines = configOptions.getMineCount();

        final Cell board[][] =new Cell[TABLE_ROW][TABLE_COL];
        for(int i=0;i<TABLE_ROW;i++)
            for(int j=0;j<TABLE_COL;j++)
                board[i][j]=new Cell();


        //Randomly plcae mines on board
        randomizeMinePositions(totalNumberOfMines,board,TABLE_ROW,TABLE_COL);

        final TextView txtMineFound=findViewById(R.id.txtMineFound);
        setMineCountText(txtMineFound,minesFound,totalNumberOfMines);
        final TextView txtScansUsed=findViewById(R.id.txtScansUsed);
        setScanUsedText(txtScansUsed,scansUsed);

        TableLayout table = findViewById(R.id.tblLayout);
        for(int i = 0; i < TABLE_ROW; i++){
            TableRow tableRow = new TableRow(this);
            TableRow.LayoutParams layoutParamsRow;
            layoutParamsRow = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT ,1);
            tableRow.setLayoutParams(layoutParamsRow);
            table.addView(tableRow);



            for(int j = 0; j < TABLE_COL; j++){
                Button imgBtn = new Button(this);
                TableRow.LayoutParams layoutParamsBtn;
                layoutParamsBtn=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,1);
                imgBtn.setLayoutParams(layoutParamsBtn);


                imgBtn.setBackgroundResource(R.drawable.grass);
                tableRow.addView(imgBtn);
                board[i][j].setImgButton(imgBtn);

                final int btnRowIndex=i;
                final int btnColIndex=j;

                imgBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        board[btnRowIndex][btnColIndex].incrementClickCount();

                        if(!board[btnRowIndex][btnColIndex].isMine() && board[btnRowIndex][btnColIndex].getClickCount()==1)
                        {
                            scansUsed++;
                            setScanUsedText(txtScansUsed,scansUsed);
                        }
                        if(board[btnRowIndex][btnColIndex].isMine() && board[btnRowIndex][btnColIndex].getClickCount()==2)
                        {
                            scansUsed++;
                            setScanUsedText(txtScansUsed,scansUsed);
                        }
                        if(board[btnRowIndex][btnColIndex].isMine() && board[btnRowIndex][btnColIndex].getClickCount()==1)
                        {
                            minesFound++;
                            setMineCountText(txtMineFound,minesFound,totalNumberOfMines);
                            if(minesFound==totalNumberOfMines)
                            {
                                //display welcome screen here
                                configOptions.setGamesPlayed(configOptions.getGamesPlayed()+1);
                                configOptions.getGameScore().add(100-2*scansUsed);
                                configOptions.updategamesPlayedAndScore();
                                FragmentManager manager = getSupportFragmentManager();
                                DialogueMessage dialog = new DialogueMessage();
                                dialog.show(manager, "Message Dialog");
                            }
                        }


                        board[btnRowIndex][btnColIndex].setVisible(true);
                        setScannedValue(board,TABLE_ROW,TABLE_COL);


                        updateDisplayOfScannedValueAllCells(board,TABLE_ROW,TABLE_COL,btnRowIndex,btnColIndex);

                        if(board[btnRowIndex][btnColIndex].isMine())
                            board[btnRowIndex][btnColIndex].updateUI();


                    }
                });

            }
        }

    }

    public void setMineCountText(TextView txtMineFound,int minesFound,int totalNumberOfMines)
    {
        txtMineFound.setText("Found "+minesFound+" of " + totalNumberOfMines + " mines");
    }
    public void setScanUsedText(TextView txtScansUsed, int scansUsed)
    {
        txtScansUsed.setText("Scans used: " + scansUsed);
    }

    public void randomizeMinePositions(int totalNumberOfMines, Cell board[][], final int rowSize, final int colSize)
    {
        int minesPlaced=0;
        List<Integer> rowShuffle=new ArrayList<>();
        List<Integer> colShuffle=new ArrayList<>();
        for(int i=0;i<rowSize;i++)
            rowShuffle.add(i);
        for(int i=0;i<colSize;i++)
            colShuffle.add(i);


        while (minesPlaced<totalNumberOfMines)
        {
            Collections.shuffle(rowShuffle);
            Collections.shuffle(colShuffle);

            final int row=rowShuffle.get(0);
            final int col=colShuffle.get(0);

            Log.i("row", Integer.toString(row));
            Log.i("col", Integer.toString(col));

            if(!board[row][col].isMine())
            {
                board[row][col].setMine(true);
                ++minesPlaced;
            }

        }
    }

    public void setScannedValue(Cell[][] board, final int rowSize, final int colSize)
    {
        for(int i=0;i<rowSize;i++)
        {
            for(int j=0;j<colSize;j++)
            {
                int invisibleMineCounter=0;

                for(int k=0;k<colSize;k++)
                {
                    if(k==j)
                        continue;
                    //increment when we have invisible mines
                    if(board[i][k].isMine() && !board[i][k].isVisible())
                    {
                        ++invisibleMineCounter;
                    }
                }
                for(int k=0;k<rowSize;k++)
                {
                    if(k==i)
                        continue;
                    //increment when we have invisible mines
                    if(board[k][j].isMine() && !board[k][j].isVisible())
                    {
                        ++invisibleMineCounter;
                    }
                }
                board[i][j].setScannedValue(invisibleMineCounter);
            }
        }
    }

    public void updateDisplayOfScannedValueAllCells(Cell[][] board, int rowSize, int colSize, int curRow, int curCol)
    {
        for(int i=0;i<rowSize;i++)
        {
            for(int j=0;j<colSize;j++)
            {
                if(i==curRow && j==curCol && !board[i][j].isMine())
                {
                    board[curRow][curCol].displayScannedValue();
                }
                else if(board[i][j].isVisible() && !board[i][j].isMine())
                    board[i][j].displayScannedValue();
                if(board[i][j].isMine() && board[i][j].getClickCount()>=2)
                {
                    board[i][j].displayScannedValue();
                }
            }
        }
    }

}


