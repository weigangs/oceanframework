package com.lkyl.oceanframework.geo.util;
 
public class LongitudeLatitudeConvertUtil {
	/**圆周率*/
	public static double pi = 3.14159265358979324;
	public static double x_pi = pi * 3000 / 180;
	/**地球长半轴距离，米*/
	public static double a = 6378245;
	/**地球扁率*/
	public static double ee = 0.00669342162296594323;
 
	/**
	 * 百度转高德
	 * @param lat	经度
	 * @param lon	纬度
	 * @return double[]	转换后的[lat, lon]
	 */
	public static double[] bd09ToGcj02(double lat, double lon) {
		double x = lon - 0.0065, y = lat - 0.006;
		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
		double tempLon = z * Math.cos(theta);
		double tempLat = z * Math.sin(theta);
		return new double[]{ tempLat, tempLon };
	}
 
	/**
	 * 高德转百度
	 * @param lat	经度
	 * @param lon	纬度
	 * @return double[]	转换后的[lat, lon]
	 */
	public static double[] gcj02ToBd09(double lat, double lon) {
		double x = lon, y = lat;
		double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
		double tempLon = z * Math.cos(theta) + 0.0065;
		double tempLat = z * Math.sin(theta) + 0.006;
		return new double[]{ tempLat, tempLon };
	}
 
	/**
	 * 84转高德
	 * @param lat	经度
	 * @param lon	纬度
	 * @return double[]	转换后的[lat, lon]
	 */
	public static double[] wgs84ToGcj20(double lat, double lon) {
		if (outOfChina(lat, lon)) {
			return new double[] { lat, lon };
		}
		double dLat = transformLat(lon - 105.0, lat - 35.0);
		double dLon = transformLon(lon - 105.0, lat - 35.0);
		double radLat = lat / 180.0 * pi;
		double magic = Math.sin(radLat);
		magic = 1 - ee * magic * magic;
		double sqrtMagic = Math.sqrt(magic);
		dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
		dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
		double mgLat = lat + dLat;
		double mgLon = lon + dLon;
		return new double[] { mgLat, mgLon };
	}
 
	/**
	 * 高德转84
	 * @param lat	经度
	 * @param lon	纬度
	 * @return double[]	转换后的[lat, lon]
	 */
	public static double[] gcj20ToWgs84(double lat, double lon) {
		double[] gps = transform(lat, lon);
		double lontitude = lon * 2 - gps[1];
		double latitude = lat * 2 - gps[0];
		return new double[] { latitude, lontitude };
	}
 
	public static double transformLat(double x, double y) {
		double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
		ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
		return ret;
	}
 
	public static double transformLon(double x, double y) {
		double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
		ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;
		return ret;
	}
 
	public static double[] transform(double lat, double lon) {
		if (outOfChina(lat, lon)) {
			return new double[] { lat, lon };
		}
		double dLat = transformLat(lon - 105.0, lat - 35.0);
		double dLon = transformLon(lon - 105.0, lat - 35.0);
		double radLat = lat / 180.0 * pi;
		double magic = Math.sin(radLat);
		magic = 1 - ee * magic * magic;
		double sqrtMagic = Math.sqrt(magic);
		dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
		dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
		double mgLat = lat + dLat;
		double mgLon = lon + dLon;
		return new double[] { mgLat, mgLon };
	}
 
	/**
	 * 判断经纬度是否在中国
	 * 
	 * @param lat	经度
	 * @param lon	纬度
	 * @return
	 */
	public static boolean outOfChina(double lon, double lat) {
		if (lon < 72.004 || lon > 137.8347)
			return true;
		if (lat < 0.8293 || lat > 55.8271)
			return true;
		return false;
	}
 
}