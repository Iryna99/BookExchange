package bookshare.api.repositories.impl;

import bookshare.api.ConnectionManager;
import bookshare.api.entities.OrderEntity;
import bookshare.api.repositories.OrderRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository

public class OrderRepositoryImpl implements OrderRepository {

    private static final String SELECT_ALL = "SELECT " +
            "\"user_id\"," +
            "\"announce_id\"," +
            "\"comment\"," +
            "\"is_active\"" +
            " FROM public.order o";


    private static final String CREATE =
            "INSERT INTO public.order (\"user_id\", \"announce_id\",\"comment\",\"is_active\") VALUES(?,?,?,?) RETURNING \"user_id\"";

    private static final String FIND_BY_CLIENT_ID = SELECT_ALL + " WHERE  o.user_id=? ";

    private static final String UPDATE_STATUS = "UPDATE public.order  SET \"is_active\" =? WHERE announce_id = ?";

    @Override
    public List<OrderEntity> selectAll() throws SQLException {
        List<OrderEntity> entities = new ArrayList<>();
        try (
                Connection connection = ConnectionManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL)
        ) {
            while (resultSet.next()) {
                entities.add(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }


    @Override
    public OrderEntity insert(OrderEntity entity) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE)) {
            setPreparedStatementData(statement, entity);
            try (ResultSet generatedKeys = statement.executeQuery()) {
                if (generatedKeys.next()) {
                    entity.setUserId(generatedKeys.getInt(1));

                }
            }
        }
        return entity;
    }

    @Override
    public OrderEntity findById(Integer id) throws SQLException {
        return null;
    }

    public OrderEntity findByClientId(Integer _id) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_CLIENT_ID)) {
            statement.setInt(1, _id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return parseResultSet(resultSet);
            }

        }
        return null;

    }

    public int updateStatus(OrderEntity entity) throws SQLException {  //TODO
        int updatesNumber = 0;
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS)) {
            statement.setBoolean(1,entity.getActive());
            statement.setInt(2,entity.getAnnounceId());
            updatesNumber = statement.executeUpdate();
        }
        return updatesNumber;

    }


    public int update(OrderEntity entity) throws SQLException {
        return 0;
    }


    public int delete(OrderEntity entity) throws SQLException {
        return 0;
    }

    private OrderEntity parseResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("user_id");
        Integer announceId = resultSet.getInt("announce_id");
        String comment = resultSet.getString("comment");
        Boolean isActive = resultSet.getBoolean("is_active");
        return new OrderEntity(id, announceId, comment, isActive);
    }

    private void setPreparedStatementData(PreparedStatement statement, OrderEntity entity) throws SQLException {
        statement.setInt(1,entity.getUserId());
        statement.setInt(2, entity.getAnnounceId());
        statement.setString(3, entity.getComment());
        statement.setBoolean(4, entity.getActive());
    }

    public static void main(String[] args) throws SQLException {

        new OrderRepositoryImpl().selectAll().forEach(System.out::println);

        new OrderRepositoryImpl().updateStatus(new OrderEntity(1,1,"sdsd",true));
        new OrderRepositoryImpl().selectAll().forEach(System.out::println);

    }
}