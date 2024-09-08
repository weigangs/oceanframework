# ocean-geo

## 1. 模块简介(module introduce)
    该模块使用了geotools来实现两点（经纬度）之间距离的计算，正在持续开发中。
    this module use spring-security archive login, authentication and so on.
## 2. 具体实现类(concrete implementation class)
    com.lkyl.oceanframework.geo.util.Gcj02DistanceUtils.java
    com.lkyl.oceanframework.geo.util.CoordinateTransformUtils.java
    当然还有一些配置类，具体实现可看代码，变量名称以及类名称可以直观了解意图

### 2.1 Gcj02DistanceUtils.java
    calculateDistance 可计算出两点间的距离，单位：米
### 2.2 CoordinateTransformUtils.java
    gcjToWgs 将gcj02坐标转换成WGS84坐标
## 3. 无配置项