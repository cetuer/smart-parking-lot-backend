package com.cetuer.parking.app.mapper;

import com.cetuer.parking.app.domain.BeaconCoordinate;
import com.cetuer.parking.app.domain.BeaconRssi;
import com.cetuer.parking.app.domain.KNNBean;

import java.util.List;
import java.util.Map;

/**
* 信标RSSI值数据操作
* 
* @author zhangqb
* @date 2022/3/19 16:01
*/
public interface BeaconCoordinateMapper {

    /**
     * 插入数据
     * @param beaconCoordinate 数据
     */
    void insertBeaconCoordinate(BeaconCoordinate beaconCoordinate);

    /**
     * 根据坐标id查找所在坐标的信标mac和rssi
     * @param coordinateId 坐标id
     * @return mac和rssi
     */
    List<BeaconRssi> selectMacRssiByCoordinateId(Integer coordinateId);
}