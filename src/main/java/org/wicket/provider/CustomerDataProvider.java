package org.wicket.provider;


import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicket.dao.CustomerDAO;
import org.wicket.dao.DAOException;
import org.wicket.entities.Customer;

import java.util.Iterator;
import java.util.List;
public class CustomerDataProvider implements IDataProvider<Customer> {

	
	@Override
	public void detach() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<Customer> iterator(long first, long count) {
		System.out.println("--------first--------" + first);

		System.out.println("--------COUNT--------" + count);
		try {
			CustomerDAO customerDAO = new CustomerDAO();
			List<Customer> listCustomers = customerDAO.iterator((int) first, (int) count);
			System.out.println("List Customers = " + listCustomers);
			return listCustomers.iterator(); 
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public long size() {
		System.out.println("-------SIZE -----------");
		long size = 0;
		try {
			CustomerDAO customerDAO = new CustomerDAO();
			size = customerDAO.size();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return size;
	}

	@Override
	public IModel<Customer> model(Customer object) {
		// TODO Auto-generated method stub
		return new Model<Customer>(object) ;
	}

}
