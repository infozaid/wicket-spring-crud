package org.wicket.panels;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadProgressBar;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.wicket.feedback.FeedbackPanelMsg;
import org.wicket.services.ServiceImport;

import java.io.IOException;

public abstract class  UploadForm extends Panel {
	
	private Form<String> form;
	
	private FeedbackPanelMsg feedback;
	
	// Champ pour faire l'upload
	private	FileUploadField fileUploadField;
	
	public UploadForm(String id) {
		super(id);
		System.out.println("----Submit----button");

		/**
		 * Feedback Panel
		 */
		feedback = new FeedbackPanelMsg("feedback");
		feedback.setOutputMarkupId(true);
		add(feedback);
		
			
		/**
		 * Formulaire pour faire l'upload
		 */
		// Champ pour faire l'upload
		fileUploadField = new FileUploadField("fileUpload");
		fileUploadField.setRequired(true);		
		form = new Form<String>("form") {
			
			@Override
			public void onSubmit() {
				
			}
		};
		form.setMultiPart(true);
			// Progress bar

		UploadProgressBar progressbar = new UploadProgressBar("uploadProgressbar", form, fileUploadField);
		form.add(progressbar);
		form.add(fileUploadField);
		// I�mport progress bar
		final WebMarkupContainer importProgressBar = new WebMarkupContainer("importProgressBar");
		importProgressBar.setVisible(false);
		importProgressBar.setOutputMarkupId(true);
		form.add(importProgressBar);
		/**
		 * Bouton d'upload
		 */
		form.add(new AjaxButton("btnUload", form) {
			

			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
				FileUpload fileuploaded = fileUploadField.getFileUpload();
				try {
					target.add(importProgressBar);
					importProgressBar.setVisible(true);
					ServiceImport.importExcel(fileuploaded.getInputStream(), feedback);

					callbackMethod(target);
					importProgressBar.setVisible(false);

					// ModalWindow.closeCurrent(target);
					
				} catch (IOException e) {
					error("impossible de lire le fichier "+ fileuploaded.getClientFileName() + " Error : "+e.getMessage());
				}
			}
			

			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
			}
		});
		
		
		add(form);
		
	}
	
	public abstract void callbackMethod(AjaxRequestTarget target);
}
