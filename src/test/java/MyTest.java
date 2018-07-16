import com.zust.acm.apms.manager.FaceManager;
import com.zust.acm.apms.manager.FaceManagerImp;
import com.zust.acm.apms.utils.Base64Util;
import com.zust.acm.apms.utils.FileUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import javax.management.Query;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MyTest {
    @Test
    public void yfsd() {
        FaceManager faceManager = new FaceManagerImp();
        String filePath = "D:\\IDMDownload\\Compress\\Face_Access-dev\\Face_Access\\web\\static\\images\\666.jpg";
        byte[] imgData = new byte[0];
        try {
            imgData = FileUtil.readFileByBytes(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imgStr = Base64Util.encode(imgData);

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        org.hibernate.query.Query query = session.createQuery("select userId from UserEntity ");
        List list = query.list();
        session.getTransaction().commit();
        for (Object o : list) {
            System.out.println("o = " + o);
            faceManager.addface(String.valueOf(o), imgStr);
        }
    }
}

