/**
 * Created by Andrew Nguyen; Professor Barbara Goldner's CSC 142, Fall 2015
 * Wednesday, October 14, 2015
 *
 * PROGRAM DESCRIPTION
 * ===================
 * This program comes in two parts. The first part, method part1(), draws a simple plus shaped figure out of
 * triangles and fills them in with 2 different colors. The second part consists of two methods, one which draws
 * individual circles, and another which draws a line of circles from left to right. In a situation where
 *
 */


import NguyenHW5.DrawingPanel;

import java.awt.Graphics;
import java.awt.Color;

public class NguyenHW2 {
    public static final int rings = 8;      //A class constant for determining the number of rings to draw in a circle.
    public static void main(String[] args) {
        part1(250,250);                     //Oh hello. It's just part 1.

        //Creation of the drawing panel used for part two with the circles.
        DrawingPanel panel = new DrawingPanel(850,700);
        panel.setBackground(Color.GRAY);
        Graphics g = panel.getGraphics();

        //Individual circles
        drawCircle(g, 50, 300, 30);
        drawCircle(g, 750, 550, 50);
        drawCircle(g, 320, 575, 90);

/*        Big Triangle
        drawCircleLine(g, 375, 375, 70, 3);
        drawCircleLine(g, 515, 235, 70, 2);
        drawCircleLine(g, 655, 95, 70, 1);

        Medium Triangle
        drawCircleLine(g, 75, 200, 25, 4);
        drawCircleLine(g, 125, 150, 25, 3);
        drawCircleLine(g, 175, 100, 25, 2);
        drawCircleLine(g, 225, 50, 25, 1);

        Small Triangle
        drawCircleLine(g, 25, 550, 20, 5);
        drawCircleLine(g, 65, 510, 20, 4);
        drawCircleLine(g, 105, 470, 20, 3);
        drawCircleLine(g, 145, 430, 20, 2);
        drawCircleLine(g, 185, 390, 20, 1);*/

        /**
         * I elected to write a method to loop the drawCircleLine method instead of giving all the coordinates and data
         * shown above. I figured this would be more similar to a real-world application where someone might need
         * to draw a triangle consisting of 5000 circles or another inane reason.
         */
        loopDrawing(g, 375, 375, 70, 3);
        loopDrawing(g, 75, 200, 25, 4);
        loopDrawing(g, 25, 550, 20, 5);

    }
    public static void part1(int x, int y) {
        DrawingPanel panel = new DrawingPanel(500,500);

        Graphics g = panel.getGraphics();
        int[] a = {x,x+100,x-100,x+100,x-100,x+100};
        int[] b = {y,y-200,y+200,y+200,y-200,y-200};
        g.drawPolygon(a,b,6);

        int[] j = {x,x+200,x+200,x+200,x-200,x-200};
        int[] k = {y,y-100,y+100,y+100,y-100,y+100};
        g.drawPolygon(j,k,6);


        g.setColor(Color.ORANGE);
        g.fillPolygon(j,k,6);
        g.setColor(Color.BLACK);
        g.fillPolygon(a,b,6);
    }

    /**
     * The drawCircle method is the core method of part 2 of the assignment. It takes the drawing panel (Graphics g)
     * as a parameter, from the main methojavad, and draws circles. That's it. x and y are the coordinates of the circle's
     * center, and the fact that Drawing Panel draws from the top left corner was accounted for.
     */
    public static void drawCircle(Graphics g,int x, int y, int radius) {
        g.setColor(Color.GREEN);                    //This draws the initial big green circle
        g.fillOval(x-radius,y-radius,radius*2,radius*2);

        for (int k = 1; k <= rings; k++) {          //A for loop to draw the concentric rings inside.
            int increment = 0;                      //increment is used to determine the radius of the next concentric circle
            increment += (radius/rings)*(k);
            /**
             * Originally, I did
             * int increment = 0
             * increment -= (radius/rings)*(k)
             * but that resulted in circles drawn from the outer edge inward, and the rings would look uneven.
             * Changed result gives circles drawn from inside to outer edge.
             */

            g.setColor(Color.BLACK);
            g.drawOval(x-increment, y-increment, increment*2, increment*2);

            int[] a = {x,x-radius,x,x+radius};      //This portion draws the square inside the circle independently.
            int[] b = {y+radius,y,y-radius,y};      //Drawn top-center, left-center, bottom-center, right-center,
            g.drawPolygon(a,b,4);                   //where (x,y) are the center coordinates of the circle.
        }
    }

    /**
     * This method utilizes the same parameters as the drawCircle method, and even calls the drawCircle method itself.
     * The only difference is the addition of the param. numCircles which determines how many times it loops, ie: how
     * many circles to draw in a straight line from left to right.
     */
    public static void drawCircleLine(Graphics g,int x, int y, int radius, int numCircles) {
        for (int i = 1; i <= numCircles; i++,x+=(radius*2)) {
            drawCircle(g, x, y, radius);
        }
    }

    /**
     * Again, params. are the same as drawCircleLine. loopDrawing simply loops the drawCircleLine method so that you
     * are only required to give the coordinates of the first circle, the radius and the number of circles in the
     * initial row. eg: if numCircles = 6, it would draw 6 circles, move up and to the right, draw 5, etc. until
     * a row with 1 circle is drawn. Then it would stop. Circles are drawn next to one another with outer edges touching.
     */
    public static void loopDrawing(Graphics g, int x, int y, int radius, int numCircles) {
        for(int i = numCircles; i>=0; i--,x+=(radius*2),y-=(radius*2)) {
            drawCircleLine(g,x,y,radius,i);
        }
    }
}