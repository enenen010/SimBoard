package MysqlTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.bit.model.NBoardDao;
import com.bit.model.NBoardDto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NBoardTest {
	  static NBoardDto target;
	   NBoardDao dao = new NBoardDao();
	   
	   @BeforeClass
	   public static void beforeClass() {
	      System.out.println("beforeClass");
	      target = new NBoardDto();
	      target.setNqid("nq11111");
	      target.setContent("새로운 내용1");
	      target.setImg("/");
	      target.setSub("새로운글1");
	      target.setId("user01");
	      target.setwDate(new Date());
	   }
	   
//	   public void before() {
//	      System.out.println("before");
//	      dao = new NBoardDao();
//	   }

	   @Test
	   public void test5DeleteOne() {
	      assertSame(1, dao.DeleteOne(target.getNqid()));
	   }

	   @Test
	   public void test4UpdateOne() {
	      target.setContent("바뀐내용1");
	      target.setSub("바뀐제목");
	      assertSame(1, dao.UpdateOne(target.getNqid(), target.getContent(), target.getImg(), target.getSub(), target.getId()));
	   }

	   @Test
	   public void test2InsertOne() {
		   
	      assertSame(1, dao.InsertOne(target.getNqid(), target.getContent(), target.getImg(), target.getSub(), target.getId()));
	   }

	   @Test
	   public void test3SelectOne() {
	      assertEquals(target.getNqid(), dao.SelectOne(target.getNqid()).getNqid());
	   }

	   @Test
	   public void test1SelectAll() {
	      assertTrue(dao.SelectList("","",0,10).size()>0);
	   }
}
