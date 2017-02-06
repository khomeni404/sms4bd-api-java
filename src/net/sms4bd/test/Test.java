package net.sms4bd.test;


import org.apache.commons.lang.StringUtils;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 24/01/16
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 24/01/16
 * Version : 1.0
 */

public class Test {
    public static boolean isValidBDCellNo(String cell) {
        if (cell.length() == 13 && cell.startsWith("88")) {
            cell = cell.substring(2, cell.length());
        } else if (cell.length() == 14 && cell.startsWith("+88")) {
            cell = cell.substring(3, cell.length());
        }
        return cell.length() == 11 && StringUtils.startsWithAny(cell, new String[]{"011", "015", "016", "017", "018", "018"});
    }

    public static String formatBDCellNo(String cell) {
        if (cell.length() == 13 && cell.startsWith("88")) {
            cell = cell.substring(2, cell.length());
        } else if (cell.length() == 14 && cell.startsWith("+88")) {
            cell = cell.substring(3, cell.length());
        }
        if (cell.length() == 11 && StringUtils.startsWithAny(cell, new String[]{"011", "015", "016", "017", "018", "018"})) {
            return "88" + cell;
        } else {
            return "Not a valid cell no";
        }
    }

    public static void main(String[] args) {
        String cell = "+8801717659287";
        System.out.println();
        System.out.println(isValidBDCellNo(cell));
        System.out.println(formatBDCellNo(cell));
    }

}
