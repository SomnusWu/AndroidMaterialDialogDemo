package com.somnus.androidmaterialdialogdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button showMyLoadingDlg, showInfoDlgOne, showInfoDlgTwo, showInputDlg, showListDlg, showComplexInputDlg, btnShowDefaultDialog;
    DialogUtils dialogUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        dialogUtils = DialogUtils.getInstance();
        findId();
        init();
    }

    private void init() {
        showMyLoadingDlg.setOnClickListener(this);
        showInfoDlgTwo.setOnClickListener(this);
        showInfoDlgOne.setOnClickListener(this);
        showInputDlg.setOnClickListener(this);
        showComplexInputDlg.setOnClickListener(this);
        showListDlg.setOnClickListener(this);


        btnShowDefaultDialog.setOnClickListener(this);
    }

    private void findId() {
        showMyLoadingDlg = (Button) findViewById(R.id.showMyLoadingDlg);
        showInfoDlgTwo = (Button) findViewById(R.id.showInfoDlgTwo);
        showInfoDlgOne = (Button) findViewById(R.id.showInfoDlgOne);
        showInputDlg = (Button) findViewById(R.id.showInputDlg);
        showListDlg = (Button) findViewById(R.id.showListDlg);
        showComplexInputDlg = (Button) findViewById(R.id.showComplexInputDlg);
        btnShowDefaultDialog = (Button) findViewById(R.id.btnShowDefaultDialog);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showMyLoadingDlg:
                dialogUtils.showProgressDialogs(this, "", "测试用的很长的测试文本内容...");
                break;
            case R.id.showInfoDlgTwo:
                dialogUtils.showInfoDlg(this, "这是标题", "这是提示的内容", "确认", "取消");
                break;
            case R.id.showInfoDlgOne:
                dialogUtils.showInfoDlg(this, "这是标题", "这是提示的内容", "确认");
                break;
            case R.id.showInputDlg:
                dialogUtils.showInputDlg(this, "标题", 6, 6, "在此输入pin");
                break;
            case R.id.showListDlg:
                dialogUtils.showListDlg(this, "选择蓝牙设备", makeData(), "确认");
                break;
            case R.id.showComplexInputDlg:
                dialogUtils.showComplexInputDlg(this, "标题", "确认", 6, 6);
                break;
            case R.id.btnShowDefaultDialog:
                DialogUtils.getConfirmDialog(this, "这是日志日志日志日志", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("tag", "onClick    ");
                    }
                }).setTitle("发现新版本").show();
                break;
        }
    }

    /**
     * 仿造List数据源
     **/
    private ArrayList<BleInformation> makeData() {
        ArrayList<BleInformation> list = new ArrayList<>();
        for (int k = 0; k < 10; k++) {
            BleInformation bleInformation = new BleInformation();
            bleInformation.setMacAddress("我是 " + k + "个地址");
            bleInformation.setRSSI("我是 " + k + "个RSSI");
            bleInformation.setUUID("我是 " + k + "个UUID");
            list.add(bleInformation);
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
