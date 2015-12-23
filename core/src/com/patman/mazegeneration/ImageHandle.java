package com.patman.mazegeneration;




import java.awt.image.BufferedImage;

import java.awt.*;

import static java.lang.Math.sqrt;


public class ImageHandle {
    public ImageHandle() {
    }

    public  String[][] getMapArray() {

        Maze2D maze;
        maze = new Maze2D(12, 12);
        maze.setIm(getCroppedImage(maze.getIm(), 0));


        BufferedImage image = maze.getIm();

        int rows = image.getWidth() / 8;
        int cols = image.getHeight() / 8;
        int chunks = rows * cols;

        int chunkWidth = 8;
        int chunkHeight = 8;
        int count = 0;
        BufferedImage imgs[] = new BufferedImage[chunks];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {

                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());


                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }
        int dim = (int) sqrt(imgs.length);
        String[][] map = new String[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (imgs[(j * dim) + i].getRGB(imgs[i].getWidth() / 2, imgs[i].getHeight() / 2) == -1)
                    map[i][j] = "p";
                else {
                    map[i][j] = "w";
                }
            }
        }
return map;
    }

    public  BufferedImage getCroppedImage(BufferedImage source, double tolerance) {

        int baseColor = source.getRGB(0, 0);

        int width = source.getWidth();
        int height = source.getHeight();

        int topY = Integer.MAX_VALUE, topX = Integer.MAX_VALUE;
        int bottomY = -1, bottomX = -1;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (colorWithinTolerance(baseColor, source.getRGB(x, y), tolerance)) {
                    if (x < topX) topX = x;
                    if (y < topY) topY = y;
                    if (x > bottomX) bottomX = x;
                    if (y > bottomY) bottomY = y;
                }
            }
        }

        BufferedImage destination = new BufferedImage((bottomX - topX + 1),
                (bottomY - topY + 1), BufferedImage.TYPE_INT_ARGB);

        destination.getGraphics().drawImage(source, 0, 0,
                destination.getWidth(), destination.getHeight(),
                topX, topY, bottomX, bottomY, null);

        return destination;
    }

    private  boolean colorWithinTolerance(int a, int b, double tolerance) {
        int aAlpha = (int) ((a & 0xFF000000) >>> 24);
        int aRed = (int) ((a & 0x00FF0000) >>> 16);
        int aGreen = (int) ((a & 0x0000FF00) >>> 8);
        int aBlue = (int) (a & 0x000000FF);

        int bAlpha = (int) ((b & 0xFF000000) >>> 24);
        int bRed = (int) ((b & 0x00FF0000) >>> 16);
        int bGreen = (int) ((b & 0x0000FF00) >>> 8);
        int bBlue = (int) (b & 0x000000FF);

        double distance = Math.sqrt((aAlpha - bAlpha) * (aAlpha - bAlpha) +
                (aRed - bRed) * (aRed - bRed) +
                (aGreen - bGreen) * (aGreen - bGreen) +
                (aBlue - bBlue) * (aBlue - bBlue));
        double percentAway = distance / 510.0d;

        return (percentAway > tolerance);
    }
}