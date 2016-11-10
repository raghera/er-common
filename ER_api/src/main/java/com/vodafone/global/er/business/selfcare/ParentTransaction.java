package com.vodafone.global.er.business.selfcare;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import com.vizzavi.ecommerce.business.selfcare.Transaction;

/** @Added: 13-09-05 
 *@Added for ER8 phase2 
 *@Added by : VFE - PS team
 *@Purpose: Usage of credits from multiple packages
 *@SelfCare Module: Parent transaction 
 *@Description: Parent transaction used to group together transactions for multiple packages
 */
public class ParentTransaction implements Serializable
{

	private static final long serialVersionUID = -1935445721502044811L;

	Collection<Transaction> mTransaction; // Collection containing the set of transactions related to a specific parentId


	/**@Purpose: Class Constructor
	 *@Description: take a collection of transactions, checks that the collection items
	 *              are all of type Transaction Object, then sets that transaction List value
	 *              to the local mTransaction variable
	 *@Throws Ecommerce Exception
	 */
	public ParentTransaction (Collection<Transaction> transList)
	{
		if ((transList != null)&&(!transList.isEmpty()))
		{
//			Iterator<Transaction> listIt = transList.iterator();
//			while ((listIt.hasNext()))
//			{
//				Transaction trans = listIt.next();
//				if (!(trans instanceof Transaction))
//					throw new EcommerceException("ParentTransaction:getParentTransaction mTransaction not	instance of ParentTransaction");
//			}
			mTransaction = transList;
		}
	}

	/**@Purpose: Return list of Transactions
	 */
	public Collection<Transaction> getTransactions ()
	{
		return this.mTransaction;
	}

	/**@Purpose: Return first Transaction object in the transaction List
	 *@Description: check transaction list is not null and returns first Transaction object
	 */
	public Transaction getFirstTransaction()
	{
		Transaction firstTrans = null;
		if ((mTransaction != null)&&(!mTransaction.isEmpty()))
		{
			if (mTransaction.iterator().hasNext())
			{
				firstTrans = (mTransaction.iterator().next());
			}
		}
		return firstTrans;
	}

	/**@Purpose: getting the used service Id
	 *@Description: Returns the service ID from the contenctName of the first Transaction in the list
	 *              as all transactions in the list have the same service id
	 */
	public String getServiceId ()
	{
		String serviceId=null;
		Transaction firstTrans = getFirstTransaction();
		if (firstTrans != null)
			serviceId = firstTrans.getServiceId();
		return serviceId;
	}

	/**@Purpose: getting the used service Id
	 *@Description: Returns the Asset ID of the first Transaction in the list
	 *              as all transactions in the list have the same AssetId
	 */
	public String getAssetID ()
	{
		String assetId=null;
		Transaction firstTrans = getFirstTransaction();
		if (firstTrans != null)
			assetId = firstTrans.getAssetId();
		return assetId;
	}

	/**@Purpose: getting the used ResourceId
	 *@Description: Returns the Resource ID of the first Transaction in the list
	 *              as all transactions in the list have the same ResourceId
	 */
	public int getResourceId()
	{
		int resourceId=0;
		Transaction transaction1 = getFirstTransaction();
		//MQC 5974 - also check for null for purchase currency
		//WAS
		//if (transaction1 != null)
		if (transaction1 != null && transaction1.getPurchaseCurrency() != null)
			resourceId = transaction1.getPurchaseCurrency().getCode();
		return resourceId;
	} 

	/**@Purpose: Calculate the sum of all mTransactions rate
	 *@Description: Returns the total amount of the calculated sum
	 */
	public double getTotalAmount()
	{
		double TotalAmount = 0;
		if ((mTransaction != null)&&(!mTransaction.isEmpty()))
		{
			Iterator<Transaction> it = mTransaction.iterator(); 
			while (it.hasNext())
			{
				Transaction transaction = (it.next());
				TotalAmount += transaction.getPurchaseRate();
			}
		}
		return TotalAmount;
	}
}
