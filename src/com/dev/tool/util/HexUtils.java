package com.dev.tool.util;


/**
 * Created by gk on 4/18/14.
 */
public class HexUtils {


    public static void main(String[] args) {

        int D1=13172753;
        String H1 = Integer.toHexString(D1);
        System.out.println("10進位:" + D1 + "  轉16進位=" + H1);

        String H2 = "c90011";
        int D2 = Integer.parseInt(H2,16);
        System.out.println("16進位:" + H2 + "  轉10進位=" + D2);

    }
}
