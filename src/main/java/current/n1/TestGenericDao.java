package current.n1;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestGenericDao {
    public static void main(String[] args) {
        GenericDao dao = new GenericDaoCached();
        System.out.println("============> 查询");
        String sql = "select * from emp where empno = ?";
        int empno = 7369;
        Emp emp = dao.queryOne(Emp.class, sql, empno);
        System.out.println(emp);
        emp = dao.queryOne(Emp.class, sql, empno);
        System.out.println(emp);
        emp = dao.queryOne(Emp.class, sql, empno);
        System.out.println(emp);

        System.out.println("============> 更新");
        dao.update("update emp set sal = ? where empno = ?", 800, empno);
        emp = dao.queryOne(Emp.class, sql, empno);
        System.out.println(emp);
    }
}

//缓存数据
class GenericDaoCached extends GenericDao {
    private GenericDao dao = new GenericDao();
    private Map<SqlPair,Object> map = new HashMap<>();
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();


    @Override
    public <T> List<T> queryList(Class<T> beanClass, String sql, Object... args) {
        return super.queryList(beanClass, sql, args);
    }

    @Override
    public <T> T queryOne(Class<T> beanClass, String sql, Object... args) {
        //先从缓存中找，找到直接返回
        SqlPair key = new SqlPair(sql,args);
        rw.readLock().lock();
        try {
            T value = (T) map.get(key);
            if(value == null){
                dao.queryOne(beanClass,sql,args);
            }
        } finally {
            rw.readLock().unlock();
        }
        rw.writeLock().lock();
        try {
            //多个线程
            T value = (T) map.get(key);
            //缓存中没有，从数据库找
            if(value == null){
                value = dao.queryOne(beanClass,sql,args);
                map.put(key,value);
            }
            return value;
        } finally {
        rw.writeLock().lock();
        }
    }

    @Override
    public int update(String sql, Object... args) {
        rw.writeLock().lock();
        try {
            //先更新数据库
            int update = dao.update(sql, args);
            //再删除缓存(否则可能导致读取数据位缓存数据，与数据库不一致)
            map.clear();
            return update;
        } finally {
            rw.writeLock().unlock();
        }
    }

    class SqlPair {
        private String Sql;
        private Object[] args;

        public SqlPair(String sql, Object[] args) {
            Sql = sql;
            this.args = args;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SqlPair sqlPair = (SqlPair) o;
            return Objects.equals(Sql, sqlPair.Sql) && Arrays.equals(args, sqlPair.args);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(Sql);
            result = 31 * result + Arrays.hashCode(args);
            return result;
        }
    }
}
