package org.wicket.provider;


import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicket.dao.DAOException;
import org.wicket.dao.PersonDao;
import org.wicket.entities.Person;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PersonDataProvider implements IDataProvider<Person> {

	@Override
	public void detach() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<? extends Person> iterator(long first, long count) {
		try {
			PersonDao personDAO = new PersonDao();
			List<Person> listUsers = personDAO.search(new HashMap<String, Object>() , (int)first, (int)count, "id", true);
			return listUsers.iterator(); 
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public long size() {
		long size = 0;
		try {
			PersonDao personDao = new PersonDao();
			size = personDao.size();
		} catch(DAOException e) {
			size = 0;
		}
		return size;
	}

	@Override
	public IModel<Person> model(Person object) {
		return new Model<Person>(object) ;
	}


}
