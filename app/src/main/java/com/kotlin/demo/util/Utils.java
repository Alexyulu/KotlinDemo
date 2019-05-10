package com.kotlin.demo.util;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/19
 * Desc :
 */

public class Utils {

    /**
     * 获取EditText中的值，若EditText为null则返回空
     * @param editText EditText
     * @return String
     */
    public static String getText(EditText editText) {
        return editText == null ? "" : TextUtils.isEmpty(editText.getText()) ? "" : editText.getText().toString().trim();
    }

    /**
     * view获取焦点
     * @param view View
     */
    public static void requestFocus(View view) {
        requestFocus(view, -1);
    }

    private static void requestFocus(final View view, long delay) {
        if (view == null) {
            return;
        }
        delay = delay <= -1 ? 0 : delay;
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setFocusableInTouchMode(true);
                view.setFocusable(true);
                view.requestFocus();
            }
        }, delay);
    }

    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     *
     * @param str 需要处理的字符串
     * @return 处理完之后的字符串
     */
    public static String addComma(String str) {
        DecimalFormat decimalFormat = new DecimalFormat(",###");
        return decimalFormat.format(Double.parseDouble(str));
    }

    public static boolean checkAmount(String string) {
        boolean a = string.matches("[01]+");
        if (a) {
            try {
                long l = Long.parseLong(string);
                return l % 10 == 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public static Calendar subtractYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar;
    }

    // 校验Tag Alias 只能是数字,英文字母和中文
    public static boolean isValidTagAndAlias(String s) {
        Pattern p = Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_!@#$&*+=.|]+$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * 方法描述：判断某一应用是否正在运行
     * @param context     上下文
     * @param packageName 应用的包名
     * @return true 表示正在运行，false 表示没有运行
     */
    public static boolean isAppRunning(Context context, String packageName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        if (list.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.baseActivity.getPackageName().equals(packageName)) {
                return true;
            }
        }
        return false;
    }


}
