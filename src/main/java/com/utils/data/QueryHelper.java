package com.utils.data;

import com.context.Context;
import com.utils.FileHelper;


import com.utils.Helper;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.ConcurrentModificationException;
import java.util.HashMap;

public class QueryHelper {
    private static final String SECRET = "82hj 36j !!!4 %$ suyseuweyb ^^8hscvb";
    private static final String MAIL_SECRET = "qdb82qjd!^&shaagsa ashjjsag &^(";
    private static final String STEP_ID = "stepId";
    private static final String SCENARIO_ID = "scenarioId";
    private static final String RUN_ID = "runId";
    private static final String CUBE_SECRET = "cubeSecret";
    private static final String SHMEST_SECRET = (Helper.isThing(System.getProperty(CUBE_SECRET)))
            ? System.getProperty(CUBE_SECRET) : "92jehdjasdg823jewgahuawaw wsau7a";
    private final static Logger LOGGER = Logger.getLogger(QueryHelper.class);



    public static JSONObject postData(final String url, final JSONObject object){

        return HttpClient.sendHttpsPost(object,url);
    }


    public static JSONObject getProfile(final String token){
        JSONObject result = getFailedObject();
        String url = FileHelper.getUrl("backend.url") + "name?token=" + token + "&scope=";
        try {
            result = HttpClient.sendGet(url,new HashMap<>()).getJSONObject("profile");
            result.remove("seed");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getProject(final String token){
        String result = "";
        String url = FileHelper.getUrl("backend.url") + "name?token=" + token;
        try {
            result = HttpClient.getString(url);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    public static String sendStep(final String status) {
        var object = new JSONObject();
        object.put(SCENARIO_ID, Context.getVars().getScenarioId());
        object.put(RUN_ID, Context.getVars().getRunId());
        object.put(STEP_ID, Context.getVars().getCurrentStepId());
   //     object.put(STEP_ID, Context.getVars().getS);
        object.put("secret", SHMEST_SECRET);
        object.put("status", status);

        String url = FileHelper.getUrl("backend.url") + "step";
        var stat = new JSONObject();
        if (url.startsWith("https")) {
            stat = HttpClient.sendHttpsPost(object, url);
        } else {
            stat = HttpClient.sendHttpPost(object, url);
        }
        return stat.toString();
    }


    public static String getIdByName(final String name){
        String result = "";
        String url = FileHelper.getUrl("user.url") + "user-id?name=" + name;
        try {
            result = HttpClient.sendGet(url,new HashMap<>()).getString("message");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static JSONObject getFailedObject() {
        var object = new JSONObject();
        object.put("status", "failed");
        return object;
    }

    public static JSONObject sendMail(String template,String email, String subject,String replace){
        JSONObject result = getFailedObject();
        JSONObject data = new JSONObject();
        data.put("template",template);
        data.put("email",email);
        data.put("subject",subject);
        data.put("subject",replace);
        String url = FileHelper.getUrl("mail.url");
        result = HttpClient.sendHttpsPost(data,url);
        return result;
    }

}
