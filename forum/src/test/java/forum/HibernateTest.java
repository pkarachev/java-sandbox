package forum;

import forum.data.Message;
import forum.data.Topic;
import forum.data.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HibernateTest {
    private static SessionFactory sessionFactory;
    private static Logger log = LoggerFactory.getLogger(HibernateTest.class);
    private static final long MINUTE = 1000 * 60 * 60;

    @BeforeClass
    public void oneTimeSetUp() {
        // A SessionFactory is set up once for an application
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
    }

    @AfterClass
    public void oneTimeTearDown() {
        sessionFactory.close();
    }

    @Test
    public void test() {
        // register a user
        User user = new User();
        user.setName("MrWriter");

        // create a topic
        long timestamp = System.currentTimeMillis();
        Topic topic = new Topic();
        topic.setAuthor(user);
        topic.setName("My first topic in this forum");
        topic.setTimestampValue(new Date(timestamp));

        // write a couple of messages
        List<Message> messages = new ArrayList<Message>();
        for (int i = 0; i < 3; i++) {
            timestamp += MINUTE;

            Message msg = new Message();
            msg.setAuthor(user);
            msg.setTimestampValue(new Date(timestamp));
            msg.setText("(" + i + ") pam param pam pam");
            messages.add(msg);
        }
        topic.setMessages(messages);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.save(topic);
        for (Message msg : messages) {
            session.save(msg);
        }
        session.getTransaction().commit();
        session.close();

        readAndPrint(User.class);
        readAndPrint(Topic.class);
        readAndPrint(Message.class);
    }

    private <T> void readAndPrint(Class<T> entityClass) {
        String tableName = entityClass.getSimpleName();
        log.info("==================  " + tableName + "  ================================");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<T> result = session.createQuery("from " + tableName).list();
        for (T entity : result) {
            log.info(entity.toString());
        }
        session.getTransaction().commit();
        session.close();
        log.info("=======================================================================");
    }
}
