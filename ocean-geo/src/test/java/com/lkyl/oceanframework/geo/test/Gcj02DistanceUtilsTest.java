package com.lkyl.oceanframework.geo.test;

import com.lkyl.oceanframework.geo.entity.SimplePoint;
import com.lkyl.oceanframework.geo.util.CoordinateTransformUtils;
import com.lkyl.oceanframework.geo.util.Gcj02DistanceUtils;
import org.junit.Assert;
import org.junit.Test;

public class Gcj02DistanceUtilsTest {


    @Test
    public  void test() throws Exception {
        // GCJ-02 坐标 (如需要计算北京天安门到上海人民广场的距离)
        double lat1Gcj = 39.908823;
        double lon1Gcj = 116.397470;
        double lat2Gcj = 31.230416;
        double lon2Gcj = 121.473701;

        // 将 GCJ-02 坐标转换为 WGS84 坐标
        double[] wgs84Coord1 = CoordinateTransformUtils.gcjToWgs(lat1Gcj, lon1Gcj);
        double[] wgs84Coord2 = CoordinateTransformUtils.gcjToWgs(lat2Gcj, lon2Gcj);

        // 计算距离
        double distance = Gcj02DistanceUtils.calculateDistance(new SimplePoint(wgs84Coord1[0], wgs84Coord1[1]), new SimplePoint(wgs84Coord2[0], wgs84Coord2[1]));
        Assert.assertEquals(distance, 1066419.7648947989, 0.1);
    }
}
