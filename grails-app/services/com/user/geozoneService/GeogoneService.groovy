package com.user.geozoneService

import Entity.Geozone
import grails.transaction.Transactional

@Transactional
class GeogoneService {





    public static boolean isInside(double rad, double lat2, double lon2, double lat1, double lon1) {

        double R = 6371e3; // metres
        double a1 = Math.toRadians(lat1);
        double a2 = Math.toRadians(lat2);
        double a = Math.toRadians((lat2 - lat1));
        double b = Math.toRadians((lon2 - lon1));

        double c = Math.sin(a / 2) * Math.sin(a / 2) +
                Math.cos(a1) * Math.cos(a2) *
                Math.sin(b / 2) * Math.sin(b / 2);
        double d = 2 * Math.atan2(Math.sqrt(c), Math.sqrt(1 - c));

        double e = R * d;
        System.out.println("------------------------------------------------------------------");
        System.out.println("distance=" + e);
        if (e <= rad) {
            System.out.println("inside the circle");
            return true;
        } else {
            System.out.println("outside the circle");
            return false;
        }


    }





}