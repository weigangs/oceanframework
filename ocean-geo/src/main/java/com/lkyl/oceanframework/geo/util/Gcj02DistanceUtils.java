package com.lkyl.oceanframework.geo.util;

import com.lkyl.oceanframework.geo.entity.SimplePoint;
import org.geotools.referencing.CRS;
import org.geotools.referencing.GeodeticCalculator;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public class Gcj02DistanceUtils {
    /**
     * In the context of GeoTools and geographic information systems (GIS), "EPSG:4326" refers to a specific spatial reference system, which is the WGS 84 (World Geodetic System 1984) coordinate system.
     * <p>
     * Here’s what it means:
     * <p>
     * EPSG: This is a code registry maintained by the European Petroleum Survey Group (EPSG) that defines various coordinate reference systems (CRS). Each CRS has a unique code.
     * 4326: This is the specific code for the WGS 84 geographic coordinate system, which is commonly used for GPS data.
     */
    public static final String SPECIAL_SPATIAL_REFER_SYSTEM_CODE = "EPSG:4326";
    public static double calculateDistance(SimplePoint pointA, SimplePoint pointB) throws FactoryException {

        CoordinateReferenceSystem crs = CRS.decode(SPECIAL_SPATIAL_REFER_SYSTEM_CODE);
        GeodeticCalculator calculator = new GeodeticCalculator(crs);
        calculator.setStartingGeographicPoint(pointA.getLon(), pointA.getLat());
        calculator.setDestinationGeographicPoint(pointB.getLon(), pointB.getLat());
        return calculator.getOrthodromicDistance(); // 距离，单位：米
    }
}
