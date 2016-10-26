package com.fosung.usedemo.amodule.demo_db;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fosung.frame.utils.ToastUtil;
import com.fosung.gui.base.BaseSecondLevelActivity;
import com.fosung.usedemo.R;
import com.fosung.usedemo.db.daobiz.EmployeeDaoBiz;
import com.fosung.usedemo.greendao.dao.EmployeeDao;
import com.fosung.usedemo.greendao.entity.Employee;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DBDemo
 */
public class DbDemoActivity extends BaseSecondLevelActivity implements View.OnClickListener {
    private LinearLayout llContent;
    private TextView     textView;
    private ArrayList<Button> listButton      = new ArrayList<>();
    private int               currentSortType = 0;//当前排序方式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_db);

        setToolbarTitle("DBDemo");
        init();
        queryObject();
    }

    private void init() {
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
    public Employee insertObject() {
        Employee employee = getEmployee();
        EmployeeDaoBiz employeeDaoBiz = new EmployeeDaoBiz();
        boolean b = employeeDaoBiz.insertObject(employee);
        ToastUtil.toastShort(b ? "插入成功" : "插入失败-主键重复");
        return employee;
    }

    /**
     * 有则替换，无则插入
     */
    public Employee insertOrReplaceObject() {
        Employee employee = getEmployee();
        EmployeeDaoBiz employeeDaoBiz = new EmployeeDaoBiz();
        boolean b = employeeDaoBiz.insertOrReplaceObject(employee);
        ToastUtil.toastShort(b ? "插入成功" : "插入失败-主键重复");
        return employee;
    }

    /**
     * 查询所有数据的数据列表
     */
    public void queryObject() {
        EmployeeDaoBiz biz = new EmployeeDaoBiz();
        List<Employee> list = biz.queryAll(Employee.class);
        setText(list);
    }

    /**
     * 查询group为“部门一”的人员,从第二条开始限制为3个,按照日期降序
     * <p>
     */
    public void queryObjectWithCondition() {
        EmployeeDaoBiz biz = new EmployeeDaoBiz();
        QueryBuilder queryBuilder = biz.getQueryBuilder(Employee.class);
        queryBuilder.where(EmployeeDao.Properties.Group.eq("部门二"));
        queryBuilder.offset(1);
        queryBuilder.limit(3);
        if (currentSortType == 0) {
            currentSortType = 1;
            queryBuilder.orderDesc(EmployeeDao.Properties.Date);
        } else {
            currentSortType = 0;
            queryBuilder.orderAsc(EmployeeDao.Properties.Date);
        }
        List<Employee> list = biz.queryAll(Employee.class);
        setText(list);
    }

    /**
     * 删除所有数据
     */
    public void deleteAllObject(){
        EmployeeDaoBiz biz = new EmployeeDaoBiz();
        boolean b = biz.deleteAll(Employee.class);
    }

    private Employee getEmployee() {
        Employee employee = new Employee();
        Date date = new Date();
        long t = date.getTime();

        employee.setId(t % 20);
        employee.setCompany("fosung");
        employee.setName("张" + t % 15);
        employee.setGroup(t % 2 == 0 ? "部门一" : "部门二");
        employee.setDate(date);
        return employee;
    }

    private void setText(List<Employee> list) {
        if (list != null && list.size() > 0) {
            StringBuilder builder = new StringBuilder();
            for (Employee o : list) {
                builder.append("id:")
                       .append(o.getId())
                       .append("  name:")
                       .append(o.getName())
                       .append("  group:")
                       .append(o.getGroup())
                       .append("\n");
            }
            textView.setText(builder);
        }else{
            textView.setText(null);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == listButton.get(0)) {
            insertObject();
            queryObject();
        } else if (v == listButton.get(1)) {
            insertOrReplaceObject();
            queryObject();
        } else if (v == listButton.get(2)) {
            queryObject();
        } else if (v == listButton.get(3)) {
            queryObjectWithCondition();
        }else if (v == listButton.get(4)) {
            deleteAllObject();
            queryObject();
        }
    }
}
