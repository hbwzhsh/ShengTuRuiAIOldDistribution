package com.init;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.model.Floor;
import com.model.IntendParams;
import com.model.ResponseFormat;
import com.model.User;
import com.netty.NettyClient;
import com.netty.model.Device;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

public class HttpServiceClient {

    public static String ip="http://"+Config.serverIpConnectSocket+":"+Config.httpPort+"/ShengTuRuiService/";
    public static String baseUrlRoomList = ip+"/client/getFloorAndRoomList";
    public static String baseUrlEmail = ip+"/client/getServerByUserEmail";
    public static String baseUrlDeviceList = ip+"/client/queryDevices";

    public User realIp(String email) {
        User user = null;
        try {
            Content reponseMsg = Request.Post(baseUrlEmail).bodyForm(Form.form().add("email", email).build()).execute().returnContent();
            String content = reponseMsg.asString();
            System.out.println(content);
            ResponseFormat responseFormat = JSONObject.parseObject(content, ResponseFormat.class);
            if (responseFormat.getRetCode().equals("success")) {
                user = JSONObject.parseObject(responseFormat.getContent().toString(), User.class);
                ip = user.getDomain();
                System.out.println(ip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public static void updateDeviceListRoomData(String userId,String token,List<Device> deviceList) {
        long beginTime = System.currentTimeMillis();
        try {
            List<Future<String>> futureList = new ArrayList<>();
            AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
            Future<String> futureRoom = asyncHttpClient.preparePost(baseUrlRoomList).addFormParam("userId", userId+"").addFormParam("token", token)
                    .execute(  new AsyncCompletionHandler<String>() {
                        @Override
                        public String onCompleted(Response response) throws Exception {
                            // Do something with the Response
                            return response.getResponseBody();
                        }
                        @Override
                        public void onThrowable(Throwable t) {
                            // Something wrong happened.
                        }
                    });


            String futureRoomcontent = futureRoom.get();
            ResponseFormat futureRoomcontentresponseFormat = JSONObject.parseObject(futureRoomcontent, ResponseFormat.class);
            System.out.println("futureRoomcontentresponseFormat:"+futureRoomcontentresponseFormat);
            List<Floor> floorList = JSONArray.parseArray(futureRoomcontentresponseFormat.getContent().toString(), Floor.class);

            floorList.forEach(item->item.getRoomlist().forEach(roomItem->{
                deviceList.forEach(deviceItem ->{
                    if(StringUtils.isNotBlank(deviceItem.getRoomId())){
                        String roomIds[] = deviceItem.getRoomId().split(",");
                        Arrays.stream(roomIds).filter(roomId->roomId.equalsIgnoreCase(roomItem.getRoomId()+"")).forEach(finalItem->{
                            deviceItem.setRoomName(roomItem.getRoomName());
                        });
                    }
                } );
            }));
            Constants.logicDeviceList.put(userId , deviceList);
            System.out.println(Constants.logicDeviceList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - beginTime);
    }

    public static void updateDataFromServer(IntendParams params) {
        long beginTime = System.currentTimeMillis();
        System.out.println("params:"+params.getUserId()+"-->"+params.getToken());
        try {
            List<Future<String>> futureList = new ArrayList<>();
            AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
            Future<String> futureRoom = asyncHttpClient.preparePost(baseUrlRoomList).addFormParam("userId", params.getUserId()+"").addFormParam("token", params.getToken())
                    .execute(  new AsyncCompletionHandler<String>() {
                                @Override
                                public String onCompleted(Response response) throws Exception {
                                    // Do something with the Response
                                    return response.getResponseBody();
                                }
                                @Override
                                public void onThrowable(Throwable t) {
                                    // Something wrong happened.
                                }
                            });
            Future<String> futuredevice = asyncHttpClient.preparePost(baseUrlDeviceList).addFormParam("userId", params.getUserId()+"").addFormParam("token", params.getToken())
                    .execute(
                            new AsyncCompletionHandler<String>() {
                                @Override
                                public String onCompleted(Response response) throws Exception {
                                    // Do something with the Response
                                    return response.getResponseBody();
                                }
                                @Override
                                public void onThrowable(Throwable t) {
                                    // Something wrong happened.
                                }
                            });

            String futureRoomcontent = futureRoom.get();
            ResponseFormat futureRoomcontentresponseFormat = JSONObject.parseObject(futureRoomcontent, ResponseFormat.class);
            System.out.println("futureRoomcontentresponseFormat:"+futureRoomcontentresponseFormat);
            List<Floor> floorList = JSONArray.parseArray(futureRoomcontentresponseFormat.getContent().toString(), Floor.class);
            String futuredevicecontent = futuredevice.get();
            ResponseFormat futuredevicecontentresponseFormat = JSONObject.parseObject(futuredevicecontent, ResponseFormat.class);
            System.out.println("futuredevicecontentresponseFormat:"+futuredevicecontentresponseFormat);
            List<Device> deviceList = JSONArray.parseArray(futuredevicecontentresponseFormat.getContent().toString(), Device.class);
            floorList.forEach(item->item.getRoomlist().forEach(roomItem->{
                deviceList.forEach(deviceItem ->{
                    if(StringUtils.isNotBlank(deviceItem.getRoomId())){
                        String roomIds[] = deviceItem.getRoomId().split(",");
                        Arrays.stream(roomIds).filter(roomId->roomId.equalsIgnoreCase(roomItem.getRoomId()+"")).forEach(finalItem->{
                            deviceItem.setRoomName(roomItem.getRoomName());
                        });
                    }
                } );
            }));

            Constants.logicDeviceList.put(params.getUserId()+"", deviceList);
            System.out.println(Constants.logicDeviceList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - beginTime);
    }

  public static void main(String[] args) throws InterruptedException {
        IntendParams params = new IntendParams();
        params.setUserId("10154");
        params.setToken("123");
        params.setWhere("living room");
        params.setDeviceName("curtains");
        params.setIntentName(Constants.OPENCURTAINS);

        new NettyClient();

       Thread.sleep(5000);

        List<Device> deviceList =(ArrayList) Constants.logicDeviceList.get(params.getUserId());

        System.out.println("new DeviceService().getAllLightsFromDevices(params).size():"+new DeviceService().getAllLightsFromDevices(params).size());


  }
}
