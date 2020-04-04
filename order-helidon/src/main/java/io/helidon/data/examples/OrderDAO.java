package io.helidon.data.examples;

import java.sql.SQLException;
import java.util.Objects;
import io.helidon.config.Config;
import oracle.jdbc.OracleConnection;
import oracle.soda.OracleCollection;
import oracle.soda.OracleDatabase;
import oracle.soda.OracleDocument;
import oracle.soda.OracleException;
import oracle.soda.rdbms.OracleRDBMSClient;

public class OrderDAO {
    private static final String QUEUE_SENDER_NAME = "ORDERS_SENDER";
    private static final String QUEUE_SENDER_ADDRESS = "ORDERS_SENDER_ADDRESS";
    private final String collectionName = "orderscollection";


    public Order get(OracleConnection conn, String id) throws OracleException {
        OracleDatabase soda = new OracleRDBMSClient().getDatabase(conn);
        OracleCollection col = soda.openCollection(collectionName);
        OracleDocument doc = col.find().key(id).getOne();
        if (Objects.nonNull(doc)) {
            return JsonUtils.read(doc.getContentAsString(), Order.class);
        } else {
            return null;
        }
    }

    public Order create(OracleConnection conn, Order order) throws OracleException {
        OracleDatabase soda = new OracleRDBMSClient().getDatabase(conn);
        OracleCollection col = soda.openCollection(collectionName);
        OracleDocument doc = soda.createDocumentFromString(order.getOrderid(), JsonUtils.writeValueAsString(order));
        col.insert(doc);
        System.out.println("Created order:" + order);
        return order;
    }

    public void update(OracleConnection conn, Order order) throws OracleException {
        OracleDatabase soda = new OracleRDBMSClient().getDatabase(conn);
        OracleCollection col = soda.openCollection(collectionName);
        OracleDocument doc = soda.createDocumentFromString(order.getOrderid(), JsonUtils.writeValueAsString(order));
        col.find().key(order.getOrderid()).replaceOne(doc);
        System.out.println("Updated order:" + order);
    }

    public int delete(OracleConnection conn, String id) throws OracleException {
//        OracleDatabase soda = database.getSoda().getDatabase(conn);
        OracleDatabase soda = new OracleRDBMSClient().getDatabase(conn);
        OracleCollection col = soda.openCollection(collectionName);
        return col.find().key(id).remove();
    }

//    public String pushToQueue(OracleConnection conn, Order order) throws SQLException {
//        return QueueUtils.sendMessage(conn, new OrderMessage(order), queue, QUEUE_SENDER_NAME, QUEUE_SENDER_ADDRESS);
//    }
}