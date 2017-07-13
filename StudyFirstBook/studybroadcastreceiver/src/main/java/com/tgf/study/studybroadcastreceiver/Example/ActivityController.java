package com.tgf.study.studybroadcastreceiver.Example;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tugaofeng on 17/3/20.
 */
public class ActivityController {

    private static List<Activity> activities = new ArrayList<Activity>();
    public static Activity currentActivity;

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void clearAll(){
        for (Activity activity:activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }
}
