package com.azure.math;

/**
 * 功能说明：TODO
 *
 * @author azure
 * @date 2018年03月14日 下午5:02
 */
public class MoreMath {

    /**
     * 牛顿法求平方根
     * Xn+1 = (Xn + a / Xn) / 2
     * @param x a double
     * @return the square root of x
     */
    public double sqrt(double x) {
        double eps = 1e-12;
        double t = x;
        while (Math.abs(t - x/t) > eps * t) {
            t = (t + x / t) / 2.0;
        }
        return t;
    }
}
