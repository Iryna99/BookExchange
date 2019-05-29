package bookshare.api.repositories.impl;


import bookshare.api.ConnectionManager;
import bookshare.api.entities.BookEntity;
import bookshare.api.entities.OrderEntity;
import bookshare.api.repositories.OrderRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private static final String SELECT_ALL = "SELECT " +
            "\"client_id\"," +
            "\"announce_id\"," +
            "\"comment\"," +
            "\"is_active\"" +
            " FROM public.order o";


    private static final String CREATE =
            "INSERT INTO public.order (\"client_id\", \"announce_id\",\"comment\",\"is_active\") VALUES(?,?,?,?)";

    private static final String FIND_BY_CLIENT_ID = SELECT_ALL + " WHERE  o.client_id=? ";

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
                    entity.setClientId(generatedKeys.getInt(1));

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

    @Override
    public int update(OrderEntity entity) throws SQLException {
        return 0;
    }

    @Override
    public int delete(OrderEntity entity) throws SQLException {
        return 0;
    }

    private OrderEntity parseResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("client_id");
        Integer announceId = resultSet.getInt("announce_id");
        String comment = resultSet.getString("comment");
        Boolean isActive = resultSet.getBoolean("is_active");
        return new OrderEntity(id, announceId, comment, isActive);
    }

    private void setPreparedStatementData(PreparedStatement statement, OrderEntity entity) throws SQLException {
        statement.setInt(1,entity.getClientId());
        statement.setInt(2, entity.getAnnounceId());
        statement.setString(3, entity.getComment());
        statement.setBoolean(4, entity.getActive());
    }

    public static void main(String[] args) throws SQLException {

        new OrderRepositoryImpl().selectAll().forEach(System.out::println);

        new OrderRepositoryImpl().updateStatus(new OrderEntity(null,2,null,false));
        new OrderRepositoryImpl().selectAll().forEach(System.out::println);

    }
}