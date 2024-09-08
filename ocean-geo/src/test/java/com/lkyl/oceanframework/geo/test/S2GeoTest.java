//package com.lkyl.oceanframework.geo.test;
//
//import com.google.common.base.Splitter;
//import com.google.common.collect.Lists;
//import com.google.common.geometry.*;
//import org.junit.jupiter.api.Test;
//
//import java.util.Collections;
//import java.util.List;
//
///**
// * @author nicholas
// * @date 2023/06/10 15:01
// */
//public class S2GeoTest {
//
//    // 计算地球上两个点之间的距离
//    public void test1() {
//        double lat = 22.629164;
//        double lgt = 114.025514;
//        S2LatLng s2LatLng = S2LatLng.fromDegrees(lat, lgt);
//        S2Point s2Point = s2LatLng.toPoint();
//        double earthDistance = s2LatLng.getEarthDistance(new S2LatLng(s2Point)); //单位为m
//        System.out.println(earthDistance); //输出距离为0
//        //s2LatLng.getDistance() //可以用于计算两点之间的弧度距离
//    }
//
//    // 计算地球上某个点是否在矩形区域内
//    public void test2() {
//        String[] split = "114.025914,22.629364".split(",");
//        String[] coord = "114.027745,22.623408".split(",");
//        S2LatLngRect rect = new S2LatLngRect(S2LatLng.fromDegrees(Double.valueOf(split[1]), Double.valueOf(split[0])),
//                S2LatLng.fromDegrees(Double.valueOf(coord[1]), Double.valueOf(coord[0])));
//        // S2RegionCoverer coverer = new S2RegionCoverer();
//        //设置cell
//        //        coverer.setMinLevel(8);
//        //        coverer.setMaxLevel(15);
//        //        coverer.setMaxCells(500);
//        //        S2CellUnion covering = coverer.getCovering(rect);
//
//        double lat = 22.629164;
//        double lgt = 114.025514;
//        S2LatLng s2LatLng = S2LatLng.fromDegrees(lat, lgt);
//        boolean contains = rect.contains(s2LatLng.toPoint());
//        System.out.println(contains);
//    }
//
//    // 计算点是否在在圆形区域内
//    public void test3() {
//        double lng = 112.030500;
//        double lat = 27.970271;
//        double capHeight = 600.5; //半径
//        S2LatLng s2LatLng = S2LatLng.fromDegrees(lat, lng);
//        S2Cap cap = S2Cap.fromAxisHeight(s2LatLng.toPoint(), capHeight);
//
//        double lat2 = 22.629164;
//        double lng2 = 114.025514;
//        S2LatLng s2LatLng2 = S2LatLng.fromDegrees(lat2, lng2);
//        boolean contains = cap.contains(s2LatLng2.toPoint());
//        System.out.println(contains);
//    }
//
//    // 计算点是否在在多边形内
//    public void test4() {
//        String str = "114.025914,22.629364;114.027745,22.623408;114.028944,22.619712;114.030112,22.617669;114.033967,22.612966;114.042343,22.602816;114.046114,22.603997;114.048353,22.604289;114.049606,22.604455;114.050796,22.604898;114.051728,22.605412;114.053186,22.606647;114.054171,22.608369";
//        double lat = 22.629164;
//        double lgt = 114.025514;
//        List<S2Point> vertices = Lists.newArrayList();
//        for (String token : Splitter.on(';').split(str)) {
//            int colon = token.indexOf(',');
//            if (colon == -1) {
//                throw new IllegalArgumentException(
//                        "Illegal string:" + token + ". Should look like '114.139312,22.551337;114.120260,22.535537'");
//            }
//            double lngY = Double.parseDouble(token.substring(0, colon));
//            double latX = Double.parseDouble(token.substring(colon + 1));
//            vertices.add(S2LatLng.fromDegrees(latX, lngY).toPoint());
//        }
//        S2Loop s2Loop = new S2Loop(vertices);
//        S2Polygon polygon = new S2Polygon(s2Loop); //创建多边形
//        S2Point s2Point = S2LatLng.fromDegrees(lat, lgt).toPoint();
//        boolean contains = polygon.contains(s2Point);
//        System.out.println(contains);
//    }
//
//    // 计算两个区域是否有交集
//    public void test5() {
//        //计算一个正方形和多边形之间是否有交集
//        String str = "114.025914,22.629364;114.027745,22.623408;114.028944,22.619712;114.030112,22.617669;114.033967,22.612966;114.042343,22.602816;114.046114,22.603997;114.048353,22.604289;114.049606,22.604455;114.050796,22.604898;114.051728,22.605412;114.053186,22.606647;114.054171,22.608369";
//        double lat = 22.629164;
//        double lgt = 114.025514;
//
//        List<S2Point> vertices = Lists.newArrayList();
//
//        for (String token : Splitter.on(';').split(str)) {
//            int colon = token.indexOf(',');
//            if (colon == -1) {
//                throw new IllegalArgumentException(
//                        "Illegal string:" + token + ". Should look like '114.139312,22.551337;114.120260,22.535537'");
//            }
//            double lngY = Double.parseDouble(token.substring(0, colon));
//            double latX = Double.parseDouble(token.substring(colon + 1));
//            vertices.add(S2LatLng.fromDegrees(latX, lngY).toPoint());
//        }
//
//        S2Loop s2Loop = new S2Loop(vertices);
//        S2Polygon polygon = new S2Polygon(s2Loop);
//        S2Point s2Point = S2LatLng.fromDegrees(lat, lgt).toPoint();
//        boolean contains = polygon.contains(s2Point);
//        System.out.println(contains);
//
//        String[] split = "114.025914,22.629364".split(",");
//        String[] coord = "114.027745,22.623408".split(",");
//        S2LatLngRect rect = new S2LatLngRect(S2LatLng.fromDegrees(Double.valueOf(split[1]), Double.valueOf(split[0])),
//                S2LatLng.fromDegrees(Double.valueOf(coord[1]), Double.valueOf(coord[0])));
//        S2RegionCoverer coverer = new S2RegionCoverer();
//        //设置cell
//        coverer.setMinLevel(8);
//        coverer.setMaxLevel(15);
//        coverer.setMaxCells(500);
//        S2CellUnion covering = coverer.getCovering(rect);
//        for (S2CellId s2CellId : covering.cellIds()) {
//            boolean b = polygon.mayIntersect(new S2Cell(s2CellId));
//            if (b) {
//                System.out.println("两个区域之间含有交集.....");
//            }
//        }
//    }
//}
