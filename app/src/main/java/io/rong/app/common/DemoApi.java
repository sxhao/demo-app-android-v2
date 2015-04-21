package io.rong.app.common;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import io.rong.app.DemoContext;
import io.rong.app.model.Friends;
import io.rong.app.model.Groups;
import io.rong.app.model.Status;
import io.rong.app.model.User;
import io.rong.app.parser.GsonArrayParser;
import io.rong.app.parser.GsonParser;
import me.add1.network.AbstractHttpRequest;
import me.add1.network.ApiCallback;
import me.add1.network.ApiReqeust;
import me.add1.network.AuthType;
import me.add1.network.BaseApi;
import me.add1.network.NetworkManager;


public class DemoApi extends BaseApi {
//    private static String HOST = "http://119.254.110.241:80/";
    private static String   HOST = "http://webim.demo.rong.io/";

    private final static String DEMO_LOGIN_EMAIL = "email_login";
    private final static String DEMO_FRIENDS = "friends";
    private final static String DEMO_REQ = "reg";
    private final static String DEMO_UPDATE_PROFILE = "update_profile";

    private final static String DEMO_TOKEN = "token";
    private final static String DEMO_JOIN_GROUP = "join_group";
    private final static String DEMO_QUIT_GROUP = "quit_group";
    private final static String DEMO_GET_ALL_GROUP = "get_all_group";
    private final static String DEMO_GET_MY_GROUP = "get_my_group";

    private final static String DEMO_SEARCH_NAME = "seach_name";
    private final static String DEMO_GET_FRIEND = "get_friend";
    private final static String DEMO_REQUEST_FRIEND = "request_friend";
    private final static String DEMO_DELETE_FRIEND = "delete_friend";
    private final static String DEMO_PROCESS_REQUEST_FRIEND = "process_request_friend";


    public DemoApi(Context context) {
        super(NetworkManager.getInstance(), context);
//        HOST = "http://webim.demo.rong.io/";
    }


    /**
     * 登录 demo server
     *
     * @param email
     * @param password
     * @param callback
     * @return
     */
    public AbstractHttpRequest<User> login(String email, String password, ApiCallback<User> callback) {

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("email", email));
        nameValuePairs.add(new BasicNameValuePair("password", password));

        ApiReqeust<User> apiReqeust = new DefaultApiReqeust<User>(ApiReqeust.POST_METHOD, URI.create(HOST + DEMO_LOGIN_EMAIL), nameValuePairs, callback);
        AbstractHttpRequest<User> httpRequest = apiReqeust.obtainRequest(new GsonParser<User>(User.class), null, null);
        NetworkManager.getInstance().requestAsync(httpRequest);
        return httpRequest;
    }

    /**
     * demo server 注册新用户
     *
     * @param email
     * @param username
     * @param password
     * @param callback
     * @return
     */
    public AbstractHttpRequest<Status> register(String email, String username, String mobile, String password, ApiCallback<Status> callback) {

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("email", email));
        nameValuePairs.add(new BasicNameValuePair("username", username));
        nameValuePairs.add(new BasicNameValuePair("password", password));
        nameValuePairs.add(new BasicNameValuePair("mobile", mobile));

        ApiReqeust<Status> apiReqeust = new DefaultApiReqeust<Status>(ApiReqeust.POST_METHOD, URI.create(HOST + DEMO_REQ), nameValuePairs, callback);
        AbstractHttpRequest<Status> httpRequest = apiReqeust.obtainRequest(new GsonParser<Status>(Status.class), null, null);
        NetworkManager.getInstance().requestAsync(httpRequest);

        return httpRequest;

    }

    /**
     * demo server 注册新用户
     *
     * @param username
     * @param callback
     * @return
     */
    public AbstractHttpRequest<Status> updateProfile(String username, ApiCallback<Status> callback) {

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("username", username));

        ApiReqeust<Status> apiReqeust = new DefaultApiReqeust<Status>(ApiReqeust.POST_METHOD, URI.create(HOST + DEMO_UPDATE_PROFILE), nameValuePairs, callback);
        AbstractHttpRequest<Status> httpRequest = apiReqeust.obtainRequest(new GsonParser<Status>(Status.class), null, null);
        NetworkManager.getInstance().requestAsync(httpRequest);

        return httpRequest;

    }

    /**
     * 登陆成功后获得token
     *
     * @param callback
     * @return
     */
    public AbstractHttpRequest<User> getToken(ApiCallback<User> callback) {
        ApiReqeust<User> apiReqeust = new DefaultApiReqeust<User>(ApiReqeust.GET_METHOD, URI.create(HOST + DEMO_TOKEN), callback);
        AbstractHttpRequest<User> httpRequest = apiReqeust.obtainRequest(new GsonParser<User>(User.class), mAuthType);
        NetworkManager.getInstance().requestAsync(httpRequest);

        return httpRequest;

    }


    /**
     * demo server 获取好友
     *
     * @param callback
     * @return
     */

    public AbstractHttpRequest<Friends> getFriends(ApiCallback<Friends> callback) {

        ApiReqeust<Friends> apiReqeust = new DefaultApiReqeust<Friends>(ApiReqeust.GET_METHOD, URI.create(HOST + DEMO_FRIENDS), callback);
        AbstractHttpRequest<Friends> httpRequest = apiReqeust.obtainRequest(new GsonParser<Friends>(Friends.class), mAuthType);
        NetworkManager.getInstance().requestAsync(httpRequest);

        return httpRequest;

    }

    /**
     * demo server 加入群组
     *
     * @param callback
     * @return
     */

    public AbstractHttpRequest<Status> joinGroup(String username, ApiCallback<Status> callback) {

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("id", username + ""));

        ApiReqeust<Status> apiReqeust = new DefaultApiReqeust<Status>(ApiReqeust.GET_METHOD, URI.create(HOST + DEMO_JOIN_GROUP), nameValuePairs, callback);
        AbstractHttpRequest<Status> httpRequest = apiReqeust.obtainRequest(new GsonParser<Status>(Status.class), null, null);
        NetworkManager.getInstance().requestAsync(httpRequest);

        return httpRequest;

    }

    /**
     * demo server 退出群组
     *
     * @param callback
     * @return
     */

    public AbstractHttpRequest<Status> quitGroup(String username, ApiCallback<Status> callback) {

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("id", username + ""));

        ApiReqeust<Status> apiReqeust = new DefaultApiReqeust<Status>(ApiReqeust.GET_METHOD, URI.create(HOST + DEMO_QUIT_GROUP), nameValuePairs, callback);
        AbstractHttpRequest<Status> httpRequest = apiReqeust.obtainRequest(new GsonParser<Status>(Status.class), null, null);
        NetworkManager.getInstance().requestAsync(httpRequest);

        return httpRequest;

    }


    /**
     * demo server 获取所有群组列表
     *
     * @param callback
     * @return
     */
    public AbstractHttpRequest<Groups> getAllGroups(ApiCallback<Groups> callback) {

        ApiReqeust<Groups> apiReqeust = new DefaultApiReqeust<Groups>(ApiReqeust.GET_METHOD, URI.create(HOST + DEMO_GET_ALL_GROUP), callback);
        AbstractHttpRequest<Groups> httpRequest = apiReqeust.obtainRequest(new GsonParser<Groups>(Groups.class), mAuthType);
        NetworkManager.getInstance().requestAsync(httpRequest);

        return httpRequest;

    }

    /**
     * demo server 获取我的群组列表
     *
     * @param callback
     * @return
     */
    public AbstractHttpRequest<Groups> getMyGroups(ApiCallback<Groups> callback) {

        ApiReqeust<Groups> apiReqeust = new DefaultApiReqeust<Groups>(ApiReqeust.GET_METHOD, URI.create(HOST + DEMO_GET_MY_GROUP), callback);
        AbstractHttpRequest<Groups> httpRequest = apiReqeust.obtainRequest(new GsonParser<Groups>(Groups.class), mAuthType);
        NetworkManager.getInstance().requestAsync(httpRequest);

        return httpRequest;

    }

    /**
     * 通过用户名搜索用户
     *
     * @param callback
     * @return
     */

    public AbstractHttpRequest<Friends> searchUserByUserName(String username, ApiCallback<Friends> callback) {

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("username", username));

        ApiReqeust<Friends> apiReqeust = new DefaultApiReqeust<Friends>(ApiReqeust.GET_METHOD, URI.create(HOST + DEMO_SEARCH_NAME), nameValuePairs, callback);
        AbstractHttpRequest<Friends> httpRequest = apiReqeust.obtainRequest(new GsonParser<Friends>(Friends.class), null, null);
        NetworkManager.getInstance().requestAsync(httpRequest);

        return httpRequest;

    }

    /**
     * 获取好友列表
     *
     * @param callback
     * @return
     */

    public AbstractHttpRequest<Friends> getNewFriendlist(ApiCallback<Friends> callback) {

        ApiReqeust<Friends> apiReqeust = new DefaultApiReqeust<Friends>(ApiReqeust.GET_METHOD, URI.create(HOST + DEMO_GET_FRIEND), callback);
        AbstractHttpRequest<Friends> httpRequest = apiReqeust.obtainRequest(new GsonParser<Friends>(Friends.class), null, null);
        NetworkManager.getInstance().requestAsync(httpRequest);

        return httpRequest;

    }


    /**
     * 发好友邀请
     *
     * @param callback
     * @return
     */

    public AbstractHttpRequest<User> sendFriendInvite(String userid, String message, ApiCallback<User> callback) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("id", userid + ""));
        nameValuePairs.add(new BasicNameValuePair("message", message));

        ApiReqeust<User> apiReqeust = new DefaultApiReqeust<User>(ApiReqeust.POST_METHOD, URI.create(HOST + DEMO_REQUEST_FRIEND), nameValuePairs, callback);
        AbstractHttpRequest<User> httpRequest = apiReqeust.obtainRequest(new GsonParser<>(User.class), null, null);
        NetworkManager.getInstance().requestAsync(httpRequest);

        return httpRequest;

    }

    /**
     * 发好友邀请
     *
     * @param callback
     * @return
     */

    public AbstractHttpRequest<ArrayList<User>> sendFriesdfndInvite(String userid, ApiCallback<ArrayList<User>> callback) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("id", userid + ""));

        ApiReqeust<ArrayList<User>> apiReqeust = new DefaultApiReqeust<ArrayList<User>>(ApiReqeust.GET_METHOD, URI.create(HOST + DEMO_REQUEST_FRIEND), nameValuePairs, callback);
        AbstractHttpRequest<ArrayList<User>> httpRequest = apiReqeust.obtainRequest(new GsonArrayParser<>(new TypeToken<ArrayList<User>>() {
        }), null, null);
        NetworkManager.getInstance().requestAsync(httpRequest);

        return httpRequest;

    }

    /**
     * demo server 删除好友
     *
     * @param callback
     * @return
     */

    public AbstractHttpRequest<Status> deletefriends(String id, ApiCallback<Status> callback) {

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("id", id + ""));

        ApiReqeust<Status> apiReqeust = new DefaultApiReqeust<Status>(ApiReqeust.POST_METHOD, URI.create(HOST + DEMO_DELETE_FRIEND), nameValuePairs, callback);
        AbstractHttpRequest<Status> httpRequest = apiReqeust.obtainRequest(new GsonParser<Status>(Status.class), null, null);
        NetworkManager.getInstance().requestAsync(httpRequest);

        return httpRequest;

    }

    /**
     * demo server 处理好友请求好友
     *
     * @param callback
     * @return
     */

    public AbstractHttpRequest<Status> processRequestFriend(String id, String isaccess, ApiCallback<Status> callback) {

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("id", id + ""));
        nameValuePairs.add(new BasicNameValuePair("is_access", isaccess));

        ApiReqeust<Status> apiReqeust = new DefaultApiReqeust<Status>(ApiReqeust.POST_METHOD, URI.create(HOST + DEMO_PROCESS_REQUEST_FRIEND), nameValuePairs, callback);
        AbstractHttpRequest<Status> httpRequest = apiReqeust.obtainRequest(new GsonParser<Status>(Status.class), null, null);
        NetworkManager.getInstance().requestAsync(httpRequest);

        return httpRequest;

    }


    AuthType mAuthType = new AuthType() {

        @Override
        public void signRequest(HttpRequest httpRequest, List<NameValuePair> nameValuePairs) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
            if (DemoContext.getInstance().getSharedPreferences().getString("DEMO_COOKIE", null) != null) {
                httpRequest.addHeader("cookie", DemoContext.getInstance().getSharedPreferences().getString("DEMO_COOKIE", null));
            }

        }
    };


}
