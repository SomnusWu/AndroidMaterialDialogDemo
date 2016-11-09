package com.somnus.androidmaterialdialogdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

/**
 * @date： 2016/9/29.
 * @FileName: com.somnus.androidmaterialdialogdemo.DialogUtils.java
 * @author: Somnus
 * @Description:
 */

public class DialogUtils {
    private static DialogUtils instance;

    public static DialogUtils getInstance() {
        if (instance == null) {
            instance = new DialogUtils();
        }
        return instance;
    }

    private DialogUtils() {
    }

    MaterialEditText newPinInput;
    MaterialEditText oldPinInput;
    View positiveAction;
    boolean oldPinFlag, newPinFlag = false;

    /**
     * 加载状态的Dialog
     * context 上下文对象
     * title 标题文本
     * content 提示文本
     **/
    public void showProgressDialogs(Context context, String title, String content) {
        new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .progress(true, 0)
                .canceledOnTouchOutside(false)
                .show();
    }

    /**
     * 附带双按钮的Dialog
     * context 上下文对象
     * title 标题文本
     * content 提示文本
     * agreeText 确认按钮
     * disagreeText 取消按钮
     **/
    public void showInfoDlg(final Context context, String title, String content, String agreeText, String disagreeText) {
        new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .canceledOnTouchOutside(false)
                .positiveText(agreeText)
                .positiveColor(context.getResources().getColor(R.color.colorPrimary))
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(context, "点击了确认", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .negativeText(disagreeText)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(context, "点击了取消", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * 附带单按钮的Dialog
     * context 上下文对象
     * title 标题文本
     * content 提示文本
     * agreeText 确认按钮
     **/
    public void showInfoDlg(final Context context, String title, String content, String agreeText) {
        new MaterialDialog.Builder(context)
                .title(title)
                .canceledOnTouchOutside(false)
                .content(content)
                .positiveText(agreeText)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(context, "点击了确认", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * 附带单输入框的Dialog
     * context 上下文对象
     * title 标题文本
     * max 最大字数
     * min 最少字数
     * hint 半透明提示语
     **/
    public void showInputDlg(final Context context, String title, final int max, final int min, String hint) {
        new MaterialDialog.Builder(context)
                .title(title)
                .canceledOnTouchOutside(false)
                .inputRangeRes(max, min, R.color.darkRed)
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .input(hint, null, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        if (input.toString().length() > max && input.toString().length() < min) {
                            dialog.getActionButton(DialogAction.POSITIVE).setEnabled(false);
                        } else {
                            dialog.getActionButton(DialogAction.POSITIVE).setEnabled(true);
                            Toast.makeText(context, "输入了" + input.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }).show();
    }

    /**
     * 附带ListView的Dialog
     * context 上下文对象
     * title 标题文本
     * ArrayList<BleInformation> 数据源
     * cancel 按钮文本
     **/
    public void showListDlg(final Context context, String title, ArrayList<BleInformation> list, String cancel) {
        new MaterialDialog.Builder(context)
                .title(title)
                .canceledOnTouchOutside(false)
                .adapter(new BleEquipment(context, list),
                        new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                Toast.makeText(context, "Clicked item " + which, Toast.LENGTH_SHORT).show();
                            }
                        })
                .positiveText(cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(context, "点击了取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    /**
     * 附带2个输入框的Dialog
     * context 上下文对象
     * title 标题文本
     * positiveText 按钮文本
     * max 最大字数
     * min 最少字数
     **/
    public void showComplexInputDlg(final Context context, String title, String positiveText, final int max, final int min) {

        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title(title)
                .canceledOnTouchOutside(false)
                .customView(R.layout.change_dlg_layout, true)
                .positiveText(positiveText)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(context, " newPinInput= " + newPinInput.getText() + " oldPinInput= " + oldPinInput.getText(), Toast.LENGTH_SHORT).show();
                    }
                }).build();


        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        positiveAction.setEnabled(false);
        newPinInput = (MaterialEditText) dialog.getCustomView().findViewById(R.id.newPinInput);
        oldPinInput = (MaterialEditText) dialog.getCustomView().findViewById(R.id.oldPinInput);
        newPinInput.setMaxCharacters(max);
        newPinInput.setMinCharacters(min);
        oldPinInput.setMaxCharacters(max);
        oldPinInput.setMinCharacters(min);
        newPinInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() >= min && s.toString().trim().length() <= max) {

                    newPinFlag = true;

                } else {
                    newPinFlag = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > max) {
                    newPinInput.setText(s.subSequence(0, 6));
                }

                if (oldPinFlag && newPinFlag) {
                    positiveAction.setEnabled(true);
                } else {
                    positiveAction.setEnabled(false);
                }

            }
        });

        oldPinInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() >= min && s.toString().trim().length() <= max) {
                    oldPinFlag = true;
                } else {
                    oldPinFlag = false;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > max) {
                    oldPinInput.setText(s.subSequence(0, 6));
                }

                if (oldPinFlag && newPinFlag) {
                    positiveAction.setEnabled(true);
                } else {
                    positiveAction.setEnabled(false);
                }
            }
        });
        dialog.show();
    }


    /***
     *  DefaultDialog
     *
     */
    /***
     * 获取一个dialog
     * @param context
     * @return
     */
    public static AlertDialog.Builder getDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder;
    }
    public static AlertDialog.Builder getConfirmDialog(Context context, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setMessage(message);
        builder.setPositiveButton("确定", onClickListener);
        builder.setNegativeButton("取消", null);
        return builder;
    }




}
