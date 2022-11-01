package models;

public class Point2D extends Pair<Integer,Integer>{

    public Point2D(int x, int y) {
        super(x, y);
    }
    
    /*
     * this is what we tecnically call a static constructor (since they don't tecnically exist in Java)
     * This is used especially for aspect such the movement of entites
     */
    public static Point2D sum(Point2D point1, Point2D point2) {
        return new Point2D(point1.getX() + point2.getX(), point1.getY() + point2.getY());
    }

}
