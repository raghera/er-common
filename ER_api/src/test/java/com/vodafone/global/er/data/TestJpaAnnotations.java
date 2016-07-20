package com.vodafone.global.er.data;

import static org.junit.Assert.*;

import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Ignore;
import org.junit.Test;

import com.vizzavi.ecommerce.business.selfcare.BasicAccount;
import com.vizzavi.ecommerce.business.selfcare.ModifyTxn;
import com.vizzavi.ecommerce.business.selfcare.PaymentTxn;
import com.vizzavi.ecommerce.business.selfcare.RefundTxn;
import com.vizzavi.ecommerce.business.selfcare.Subscription;
import com.vizzavi.ecommerce.business.selfcare.Transaction;
import com.vizzavi.ecommerce.business.selfcare.UserGroup;



/**
 * tests that the JPA annotations on the Transaction class are ok
 * @author matt
 *
 */
public class TestJpaAnnotations {


	@Ignore
	@Test
	public void testIt()	{
		// Get the entity manager for the tests.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("core");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trx = null;
		try {
		   //Get a new transaction
		   trx = em.getTransaction();

		   //Start the transaction
		   trx.begin();
//		   //retrieve from DB
//		  PaymentTxn t=em.find(PaymentTxn.class, new Long(2801));
//		  assertEquals("foo", t.getExternalField1());
//		  RefundTxn rt = em.find(RefundTxn.class, new Long(1237));
//		  PaymentTxn t2 = rt.getPaymentTransaction();
//		  assertNotNull(t2);
//		  assertEquals(t2.getTransactionIdLong(), 1236l);
//		  ModifyTxn m = em.find(ModifyTxn.class, new Long(2709));
//		  assertNotNull(m.getSubscription());
//		  assertEquals(1480l, m.getSubscription().getSubscriptionIdLong());
//		  Subscription s=em.find(Subscription.class, new Long(1274));
//		  assertNotNull(s.getPaymentTransactions());
//		  assertEquals(1, s.getPaymentTransactions().size());
//		  assertEquals(2, s.getPaymentTransactions().get(0).getRefundTransactions().size());
////		  assertEquals(4, s.getTransactions().size());
//		  BasicAccount acc = em.find(BasicAccount.class, new Long(22));
//		  assertNotNull(acc);
//		  assertNotNull(s);
//		  assertNotNull(t);
//		  System.out.println(t);
////		  System.out.println(s);
////		  System.out.println(acc);
		  
		  BasicAccount acc2 = new BasicAccount(System.currentTimeMillis()+"test", "ban", "name", Locale.UK, 401);
		  acc2.setCountry("GB");
		  em.persist(acc2);
		  
		  UserGroup ug = em.find(UserGroup.class, 2);
		  assertNotNull(ug);
		  assertEquals("director", ug.getName());
		   //Commit and end the transaction
		   trx.commit();
		} catch (RuntimeException e) {
		   if (trx != null && trx.isActive()) {
		      trx.rollback();
		   }
		   throw e;
		} finally {
		   //Close the manager
		   em.close();
		   emf.close();
		}

	}
	
	
}
