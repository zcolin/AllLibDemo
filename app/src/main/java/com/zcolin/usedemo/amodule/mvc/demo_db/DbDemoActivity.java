/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-5-4 下午5:25
 * ********************************************************
 */

package com.zcolin.usedemo.amodule.mvc.demo_db;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zcolin.usedemo.R;
import com.zcolin.usedemo.amodule.base.BaseActivity;
import com.zcolin.usedemo.biz.DaoMgr;
import com.zcolin.usedemo.biz.TestDataMgr;
import com.zcolin.usedemo.db.entity.Employee;

import java.util.ArrayList;
import java.util.List;

import static com.zcolin.frame.util.ToastUtil.toastShort;


/**
 * DBDemo
 */
public class DbDemoActivity extends BaseActivity implements View.OnClickListener {
    private ArrayList<Button> listButton = new ArrayList<>();
    private LinearLayout llContent;
    private TextView     textView;
    private int currentSortType = 0;//当前排序方式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_db);
        setToolbarTitle("DBDemo");

        initView();
    }

    protected void initView() {
        llContent = getView(R.id.ll_content);
        textView = getView(R.id.textview);
        textView.setMovementMethod(new ScrollingMovementMethod());
        listButton.add(addButton("插入数据"));
        listButton.add(addButton("替换或插入数据"));
        listButton.add(addButton("获取数据列表"));
        listButton.add(addButton("条件查询降序"));
        listButton.add(addButton("清空数据库"));

        for (Button btn : listButton) {
            btn.setOnClickListener(this);
        }
    }


    private Button addButton(String text) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Button button = new Button(mActivity);
        button.setText(text);
        button.setGravity(Gravity.CENTER);
        button.setAllCaps(false);
        llContent.addView(button, params);
        return button;
    }


    /**
     * 插入对象
     */
    public void insertObject() {
        List<Employee> list = new ArrayList<>();
        boolean b = DaoMgr.insertObject(TestDataMgr.getTestEmployee());
        if (b) {
            list = DaoMgr.queryAllObject();
        }
        showResult(list);
        toastShort(b ? "插入成功" : "插入失败-主键重复");
    }

    /**
     * 有则替换，无则插入
     */
    public void insertOrReplaceObject() {
        List<Employee> list = new ArrayList<>();
        boolean b = DaoMgr.insertOrReplaceObject(TestDataMgr.getTestEmployee());
        if (b) {
            list = DaoMgr.queryAllObject();
        }
        showResult(list);
        toastShort(b ? "插入成功" : "插入失败-主键重复");
    }

    /**
     * 查询所有数据的数据列表
     */
    public void queryAllObject() {
        List<Employee> list = DaoMgr.queryAllObject();
        showResult(list);
    }

    /**
     * 查询group为“部门一”的人员,从第二条开始限制为3个,按照日期降序/升序
     * <p>
     */
    public void queryObjectWithCondition() {
        currentSortType = currentSortType == 0 ? 1 : 0;
        List<Employee> list = DaoMgr.queryObjectWithCondition(currentSortType);
        showResult(list);
    }

    /**
     * 删除所有数据
     */
    public void deleteAllObject() {
        List<Employee> list = new ArrayList<>();
        boolean b = DaoMgr.deleteAllObject();
        if (b) {
            list = DaoMgr.queryAllObject();
        }
        showResult(list);
    }

    public void showResult(List<Employee> list) {
        if (list != null && list.size() > 0) {
            StringBuilder builder = new StringBuilder();
            for (Employee o : list) {
                builder.append("id:")
                       .append(o.getId())
                       .append("  name:")
                       .append(o.getName())
                       .append("  group:")
                       .append(o.getGroup())
                       .append("  time")
                       .append(o.getDate())
                       .append("\n");
            }
            textView.setText(builder.toString());
        } else {
            textView.setText(null);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == listButton.get(0)) {
            insertObject();
        } else if (v == listButton.get(1)) {
            insertOrReplaceObject();
        } else if (v == listButton.get(2)) {
            queryAllObject();
        } else if (v == listButton.get(3)) {
            queryObjectWithCondition();
        } else if (v == listButton.get(4)) {
            deleteAllObject();
        }
    }
}
