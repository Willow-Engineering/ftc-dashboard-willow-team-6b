package org.firstinspires.ftc.teamcode;

public class CMH_functions {
    /** converts a boolean to a integer
     * @param bool boolean
     **/
    public static int bool2int(boolean bool) {
        int n;
        if (bool) n = 1;
        else n = 0;
        return n;
    }
    public static double extRound(double x,double m) {return m * Math.round(x/m);}
    public static int extRound(int x,int m) {return m * Math.round(x/m);}
    /**
     * radially modulates x by k
     * @param number input
     * @param radius radial modulus
     */
    public static double arcmod(double number, double radius) {return radius-(radius-number)%2*radius;}

    public static double rangemod(double number, double min, double max) {return max-(max-number)%(max-min);}
}
