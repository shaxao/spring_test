package test;

import cn.hutool.extra.tokenizer.engine.mmseg.MmsegEngine;
import test.pojo.Users;

import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;

public class MyTest10 {
    public static void main(String[] args) {
        Users mumu = new Users(1, "mumu");
        Users mumu1 = new Users(2, "mumu1");
        Users mumu2 = new Users(3, "mum2");
        Users mumu3 = new Users(4, "mum3");
        List<Users> list = new ArrayList<>();
        list.add(mumu);
        list.add(mumu1);
        list.add(mumu2);
        list.add(mumu3);

//        long begin = System.currentTimeMillis();

//        list.forEach(a -> ids.add(a.getId()));
//        long end = System.currentTimeMillis();
//        System.out.println("stream流耗费时间"+(end - begin));
        long begin = System.currentTimeMillis();
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ids.add(list.get(i).getId());
        }
        long end = System.currentTimeMillis();
        System.out.println("stream流耗费时间"+(end - begin));
    }
}
