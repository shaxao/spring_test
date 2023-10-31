package game;


import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BookMange {


}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Book{
    private String bookName;
    private String zuozhe;
    private DateTime chuban;
    private int flag;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Users{
    private String name;
    private String phone;
    private String password;
    //已借阅书籍
    private String[] myBook;
    //剩余使用时间
    private DateTime daiHuanTime;
    private int balance;
}
