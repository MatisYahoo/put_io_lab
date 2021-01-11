package put.io.testing.mocks;

import put.io.students.fancylibrary.database.IFancyDatabase;

import java.util.Collections;
import java.util.List;

public class MyDatabase implements IFancyDatabase {

    public List queryAll(){ return Collections.emptyList(); }
    @Override
    public void connect() { }
    @Override
    public <T> void persist(T t) { }
    @Override
    public void close() { }
}
