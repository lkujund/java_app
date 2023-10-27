package hr.algebra.dal;

import java.util.List;
import java.util.Optional;


public interface Repository<T> {
    int createOne(T item) throws Exception;
    void createMany(List<T> items) throws Exception;
    void updateOne(int id, T item) throws Exception;
    void deleteOne(int id) throws Exception;
    Optional<T> selectOne(int id) throws Exception;
    List<T> selectMany() throws Exception;
    
}
