package org.wicket.pages;


import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.wicket.dao.CustomerDAO;
import org.wicket.dao.DAOException;
import org.wicket.entities.Customer;
import org.wicket.models.LoadableCustomerModel;
import org.wicket.template.TemplatePage;
import org.wicket.validation.RequiredBehavior;

public class Create extends TemplatePage {
	
	private Form<Customer> customerForm;
	private TextField<String> firstName;
	private TextField<String> lastName;
	private TextField<String> street;
	private TextField<String> email;
	public Create(PageParameters params) {
		super(params);
		firstName = new TextField<String>("firstName");
		firstName.setRequired(true);
		firstName.add(new RequiredBehavior());
		lastName = new TextField<String>("lastName");
		lastName.setRequired(true);
		lastName.add(new RequiredBehavior());
		street = new TextField<String>("street");
		street.setRequired(true);
		street.add(new RequiredBehavior());
		email = new TextField<String>("email");
		// Validators
		email.add(EmailAddressValidator.getInstance());
		email.setRequired(true);
		email.add(new RequiredBehavior());

		
		customerForm = new Form<Customer>("customerForm", new CompoundPropertyModel<Customer>(new LoadableCustomerModel()))
		{
			@Override
			public void onSubmit() {
				System.out.println(this.getModelObject());
				try {
					CustomerDAO customerDAO = new CustomerDAO();
					customerDAO.create(this.getModelObject());
					feedBackPanelMsg.success("Saved");
					PageParameters params = new PageParameters();
					params.add("successMsg", "Success Message");
					setResponsePage(Index.class, params);
					
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}
			
			
		};
	//	this.errorFeedBackPanel.error("Test TTTTTESST");
		customerForm.add(firstName);
		customerForm.add(lastName);
		customerForm.add(street);
		customerForm.add(email);
		add(customerForm);
		
		
		
	/*	AjaxLink<String> test = new AjaxLink<String>("testooo") {
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				successFeedBackPanel.success("success");
				target.add(successFeedBackPanel);
			}
		};
		
		add(test);*/
	}

}
