package com.inetum.documentos.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.inetum.documentos.dao.FormatoDao;
import com.inetum.documentos.entity.Formato;
import com.inetum.documentos.util.ControllerUtil;

import lombok.Getter;
import lombok.Setter;
/*import com.inetum.documentos.jsf.util.JsfUtil;
import com.inetum.documentos.jsf.util.JsfUtil.PersistAction;
*/

//@Named("formatoController")
@ManagedBean(name = "formatoController")
@SessionScoped
public class FormatoController implements Serializable {

	@EJB
	private FormatoDao ejbFacade;

	private FormatoDao dao;

	private List<Formato> items = null;
	@Getter
	@Setter
	private Formato selected;

	public FormatoController() {
	}

	public List<Formato> getItems() {
		if (items == null) {
			// items = getFacade().findAll();
			items = getDao().findAll();
		}
		return items;
	}

	public Formato prepareCreate() {
		selected = new Formato();
		return selected;
	}

	public void create() {
		// persist(PersistAction.CREATE,
		// ResourceBundle.getBundle("/Bundle").getString("FormatoCreated"));
		try {
			if (selected != null) {				
				// if (!ControllerUtil.isValidationFailed())
				selected.setFechahoracreacion(new Date());
				getDao().save(selected);
				items = null;				
				ControllerUtil.addSuccessMessage("Creado!");
			}			

		} catch (Exception e) {
			System.out.println("error al crear " + e.getMessage());
			ControllerUtil.addErrorMessage("error al Crear!");
		}
	}

	public void update() {
		try {
			if (selected != null) {
				//if (!ControllerUtil.isValidationFailed())
				selected.setFechahoramodificacion(new Date());
				getDao().update(selected);
				items = null;
				ControllerUtil.addSuccessMessage("Actualizado!");
			}
			

		} catch (Exception e) {
			System.out.println("error al actualizar " + e.getMessage());
			ControllerUtil.addErrorMessage("error al Actualizar!");
		}
	}

	public void destroy() {        
        try {
        	if (!ControllerUtil.isValidationFailed()) {
        		if (selected != null) {        			
    				getDao().remove(selected);
    				items = null;    				
    				selected = null;
    				ControllerUtil.addSuccessMessage("Eliminado!");
    			}                
            }        	

		} catch (Exception e) {
			System.out.println("error al eliminar " + e.getMessage());
			ControllerUtil.addErrorMessage("error al Eliminar!");
		}        
        

	}

	private FormatoDao getDao() {
		if (this.dao == null) {
			this.dao = new FormatoDao();
		}
		return this.dao;
	}

	// private FormatoFacade getFacade() {
	private FormatoDao getFacade() {
		return ejbFacade;
	}

	/*
	 * public Formato getSelected() { return selected; }
	 * 
	 * public void setSelected(Formato selected) { this.selected = selected; }
	 * 
	 * protected void setEmbeddableKeys() { }
	 * 
	 * protected void initializeEmbeddableKey() { }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * private void persist(PersistAction persistAction, String successMessage) { if
	 * (selected != null) { setEmbeddableKeys(); try { if (persistAction !=
	 * PersistAction.DELETE) { getFacade().edit(selected); } else {
	 * getFacade().remove(selected); } JsfUtil.addSuccessMessage(successMessage); }
	 * catch (EJBException ex) { String msg = ""; Throwable cause = ex.getCause();
	 * if (cause != null) { msg = cause.getLocalizedMessage(); } if (msg.length() >
	 * 0) { JsfUtil.addErrorMessage(msg); } else { JsfUtil.addErrorMessage(ex,
	 * ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured")); }
	 * } catch (Exception ex) {
	 * Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
	 * JsfUtil.addErrorMessage(ex,
	 * ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured")); }
	 * } }
	 * 
	 * public Formato getFormato(java.lang.Integer id) { return
	 * getFacade().find(id); }
	 * 
	 * public List<Formato> getItemsAvailableSelectMany() { return
	 * getFacade().findAll(); }
	 * 
	 * public List<Formato> getItemsAvailableSelectOne() { return
	 * getFacade().findAll(); }
	 * 
	 * @FacesConverter(forClass = Formato.class) public static class
	 * FormatoControllerConverter implements Converter {
	 * 
	 * @Override public Object getAsObject(FacesContext facesContext, UIComponent
	 * component, String value) { if (value == null || value.length() == 0) { return
	 * null; } FormatoController controller = (FormatoController)
	 * facesContext.getApplication().getELResolver().
	 * getValue(facesContext.getELContext(), null, "formatoController"); return
	 * controller.getFormato(getKey(value)); }
	 * 
	 * java.lang.Integer getKey(String value) { java.lang.Integer key; key =
	 * Integer.valueOf(value); return key; }
	 * 
	 * String getStringKey(java.lang.Integer value) { StringBuilder sb = new
	 * StringBuilder(); sb.append(value); return sb.toString(); }
	 * 
	 * @Override public String getAsString(FacesContext facesContext, UIComponent
	 * component, Object object) { if (object == null) { return null; } if (object
	 * instanceof Formato) { Formato o = (Formato) object; return
	 * getStringKey(o.getId()); } else {
	 * Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
	 * "object {0} is of type {1}; expected type: {2}", new Object[]{object,
	 * object.getClass().getName(), Formato.class.getName()}); return null; } }
	 * 
	 * }
	 */

}
