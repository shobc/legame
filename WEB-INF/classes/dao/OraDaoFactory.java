package dao;

public class OraDaoFactory extends AbstractDaoFactory{
    public OraPropertyDao getPropertyDao(){
        return new OraPropertyDao();
    }
}