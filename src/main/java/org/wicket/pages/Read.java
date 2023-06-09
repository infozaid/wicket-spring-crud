package org.wicket.pages;


import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicket.dao.CustomerDAO;
import org.wicket.dao.DAOException;
import org.wicket.entities.Customer;
import org.wicket.models.LoadableCustomerModel;
import org.wicket.template.TemplatePage;

public class Read extends TemplatePage {

	private Form<Customer> customerForm;
	private TextField<String> firstName;
	private TextField<String> lastName;
	private TextField<String> street;

	public Read(final PageParameters parameters) {
		super(parameters);
		System.out.println(parameters.get("id").toLong());
		firstName = new TextField<String>("firstName");
		lastName = new TextField<String>("lastName");
		street = new TextField<String>("street");
		customerForm = new Form<Customer>("customerForm", new CompoundPropertyModel<Customer>(new LoadableCustomerModel(parameters.get("id").toLong()))) {
			@Override
			public void onSubmit() {
				System.out.println(this.getModelObject());
				try {
					CustomerDAO customerDAO = new CustomerDAO();
					customerDAO.create(this.getModelObject());
					PageParameters params = new PageParameters();
					params.add("successMsg", "Success Message");
					setResponsePage(Index.class, params);
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}
		};
		customerForm.add(firstName);
		customerForm.add(lastName);
		customerForm.add(street);
		add(customerForm);
	}
}
