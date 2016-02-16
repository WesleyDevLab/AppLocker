package com.eeontheway.android.applocker.applock;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.eeontheway.android.applocker.R;

/**
 * 应用锁配置界面Activity
 *
 * @author lishutong
 * @version v1.0
 * @Time 2016-2-8
 */
public class AppLockSettingsActivity extends AppCompatActivity {
    private ActionBar actionBar;

    /**
     * 启动应用锁配置界面
     * @param context 上下文
     */
    public static void start (Context context) {
        Intent intent = new Intent(context, AppLockSettingsActivity.class);
        context.startActivity(intent);
    }

    /**
     * Activity的onCreate回调
     * @param savedInstanceState 之前保存的状态
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_locker_settings);

        setTitle(R.string.settings);
        initActionBar();
        initViews();
    }

    /**
     * 初始化各种View
     */
    private void initViews() {
        // 配置Fragment
        AppLockSettingsFragment fragment = new AppLockSettingsFragment();
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.fg_settings, fragment).commit();
    }

    /**
     * 配置ActionBar
     */
    private void initActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Activiy的onCreateOptionMenu回调
     * @param menu 创建的菜单
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 处理返回按钮按下的响应
     * @param item 被按下的项
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    /**
     * 等待初始密码的设置结果
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // 密码设置正确，保存密码
            AppLockSettingsManager asm = AppLockSettingsManager.getInstance(this);
            asm.savePassword(data.getStringExtra(AppLockPasswordSetActivity.RETURN_PARAM_PASS));
        }
    }
}