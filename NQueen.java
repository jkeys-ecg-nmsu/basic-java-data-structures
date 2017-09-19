/**
 * Created by jkeys on 9/28/2015.
 */

import java.lang.Math;
import java.util.*;

public class NQueen {
 /*   private static boolean checkConflict(Position QPos, LinkStack<Position> QCols) {
        Integer currCol;

        for(int i = 0; i < QCols.size(); i++) {
            currCol = QCols.pop();
            if(QPos.colid == currCol)
                return false;
        }

        return true;
    }*/

    private static boolean checkConflict(Position QPos, LinkStack<Position> S)
    {
        if(S.size() == 0)
            return false;

        //printStack(S);

        LinkStack<Position> temp = new LinkStack<Position>();
        Position tempPos;
        boolean conflict = false;

        //copy stuff from S to temp
        while((tempPos = S.pop()) != null) {
            temp.push(tempPos);


            if(     (QPos.colid == tempPos.colid) || (QPos.rowid == tempPos.rowid) ||
                    (Math.abs(QPos.colid - tempPos.colid) == Math.abs(QPos.rowid - tempPos.rowid)) ) {
                //System.out.println("conflict = true,   qPos.colid  == " + QPos.colid + " and QPos.rowid == " + QPos.rowid);
                //System.out.println("..............., tempPos.colid == " + tempPos.colid + " and tempPos.rowid == " + tempPos.rowid);
                //System.out.println((Math.abs(QPos.colid - tempPos.colid) == Math.abs(QPos.rowid - tempPos.rowid)));
                conflict = true;
                break;
            }
        }

/*        while(!temp.isEmpty()) {
            Position top = temp.pop();
            //pop out the top
            //push top to temp

            //if QPos and top conflict, break

        }*/

        while((tempPos = temp.pop()) != null) {
            S.push(tempPos);
        }
        //put everything back in original stack

        //abs(qpos.rowid - top.rowid) == abs(qpos.colid - top.colid);

        //return conflict/not conflict

        //printStack(S);

        return conflict;
    }

    private static void printStack(StackInterface<Position> S) {
        Position tempPos;
        LinkStack<Position> temp = new LinkStack<Position>();

        while((tempPos = S.pop()) != null) {
            System.out.println(tempPos);
            temp.push(tempPos);
        }

        while((tempPos = temp.pop()) != null) {
            S.push(tempPos);
        }
    }

    public static void main(String[] args) {
        //NQueen p1 = new NQueen();
        Position QPos = new Position(1, 1);

        LinkStack<Position> S = new LinkStack<Position>();

        boolean solutionExists = true;
        //S.push(QPos);

        System.out.println("Please enter single integer for size of board (N rows, N cols, N queens): ");

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        while(S.size() < N) {
            System.out.println("S.size() == " + S.size());
            System.out.println("In main loop, QPos.colid == " + QPos.colid + " and QPos.rowid == " + QPos.rowid);
            boolean conflict = checkConflict(QPos, S);

            if(!conflict) {
                System.out.println("No conflict, pushing QPos: " + QPos);
                S.push(QPos);
                printStack(S);
                QPos = new Position(1, S.size() + 1);
            }
            else if(conflict && QPos.colid < N) {
                QPos.colid++;
            } else if(conflict && QPos.colid == N) {
                System.out.println("Conflict exists and no room to move rightward, QPos.colid == " + QPos.colid + ", S.size() == " + S.size());
                System.out.println("S.top() == " + S.top());
                printStack(S);
                while(S.top() != null && S.top().colid == N) {
                    //System.out.println("In the while loop");
                    S.pop();
                }

                if(S.size() != 0)
                    S.top().colid++;
                System.out.println("Stack after while loop: ");
                printStack(S);
            }

            if(QPos.colid > N) {
                solutionExists = false;
                break;
            }
        }

        if(!solutionExists) {
            System.out.println("No solution exists with N = " + N);
        } else {
            System.out.println("A solution has been found: ");
            printStack(S);
        }

    }

/*
    public static void main(String[] args) {
        LinkStack<Integer> data = new LinkStack<Integer>();

        Position QPos = new Position(1, 1);
    }*/
}
