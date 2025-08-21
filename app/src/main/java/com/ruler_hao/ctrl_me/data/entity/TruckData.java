package com.ruler_hao.ctrl_me.data.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TruckData {
    private int limit;
    private int offset;
    private int count;
    private List<StationData> results;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<StationData> getResults() {
        return results;
    }

    public void setResults(List<StationData> results) {
        this.results = results;
    }

    public static TruckData parseJsonToTruckData(String jsonResponse) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONObject resultObject = jsonObject.getJSONObject("result");

            TruckData truckData = new TruckData();
            truckData.setLimit(resultObject.getInt("limit"));
            truckData.setOffset(resultObject.getInt("offset"));
            truckData.setCount(resultObject.getInt("count"));

            // 解析 results (假設是 StationData 的陣列)
            JSONArray resultsArray = resultObject.getJSONArray("results");
            List<StationData> stationDataList = new ArrayList<>();

            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject stationObject = resultsArray.getJSONObject(i);
                StationData stationData = new StationData();

                stationData.setId(stationObject.getInt("_id"));
                stationData.setDistrict(stationObject.getString("行政區"));
                stationData.setVillage(stationObject.getString("里別"));
                stationData.setSquad(stationObject.getString("分隊"));
                stationData.setBureauCode(stationObject.getString("局編"));
                stationData.setVehicleNumber(stationObject.getString("車號"));
                stationData.setRoute(stationObject.getString("路線"));
                stationData.setVehicleTrip(stationObject.getString("車次"));
                stationData.setArrivalTime(stationObject.getString("抵達時間"));
                stationData.setDepartureTime(stationObject.getString("離開時間"));
                stationData.setLocation(stationObject.getString("地點"));
                stationData.setLongitude(stationObject.getString("經度"));
                stationData.setLatitude(stationObject.getString("緯度"));

                stationDataList.add(stationData);
            }

            truckData.setResults(stationDataList);
            return truckData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}