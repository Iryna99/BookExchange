package bookshare.api.repositories;

import bookshare.api.entities.OrderEntity;

import java.sql.SQLException;
import java.util.List;

public interface OrderRepository {
    public List<OrderEntity> selectAll() throws SQLException;
    public OrderEntity insert(OrderEntity entity) throws SQLException;
    public OrderEntity findById(Integer id) throws SQLException;
    public int updateStatus(OrderEntity entity) throws SQLException;
}