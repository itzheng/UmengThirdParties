package org.itzheng.thirdparties;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.R;

import org.itzheng.umeng.bean.BaseAuthInfo;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final boolean isauth = UMShareAPI.get(this).isAuthorize(this, SHARE_MEDIA.WEIXIN);
        Log.w(TAG, "isauth: " + isauth);
        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
//                delete();
//                share();
            }
        });

    }

    private void share() {
        ShareAction shareAction = new ShareAction(this).withText("ceshi");
        shareAction.setPlatform(SHARE_MEDIA.QQ);
        UMShareAPI.get(this).doShare(this, shareAction, new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.w(TAG, "onStart: ");
            }

            @Override
            public void onResult(SHARE_MEDIA share_media) {
                Log.w(TAG, "onResult: ");
            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                Log.w(TAG, "onError: ");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                Log.w(TAG, "onCancel: ");
            }
        });
    }

    private void delete() {
        UMShareAPI.get(this).deleteOauth(this, SHARE_MEDIA.WEIXIN, umAuthListener);
    }

    private static final String TAG = "MainActivity";

    private void login() {
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(this).setShareConfig(config);
        try {
            UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    UMAuthListener umAuthListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Log.w(TAG, "onStart: " + platform);
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Log.w(TAG, "成功了");
            BaseAuthInfo baseAuthInfo = BaseAuthInfo.mapToObject(data, BaseAuthInfo.class);
            String iconurl = baseAuthInfo.iconurl;
            Log.d(TAG, "onComplete: " + data.toString());
            Toast.makeText(getApplicationContext(), "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Log.w(TAG, "失败：" + t.getMessage());
            Toast.makeText(getApplicationContext(), "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Log.w(TAG, "取消了");
            Toast.makeText(getApplicationContext(), "取消了", Toast.LENGTH_LONG).show();
        }
    };

}
