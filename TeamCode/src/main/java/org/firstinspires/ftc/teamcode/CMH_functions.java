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
    /**
     * radially modulates x by k
     * @param number input
     * @param modulus radial modulus
     */
    public static double arcmod(double number, double modulus) {return modulus-(modulus-number)%2*modulus;}

    public static double rangemod(double number, double min, double max) {return max-(max-number)%(max-min);}
}
