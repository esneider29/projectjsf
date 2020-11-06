package com.inetum.documentos.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.Part;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import com.inetum.documentos.dao.DocumentoDao;
import com.inetum.documentos.dao.FormatoDao;
import com.inetum.documentos.entity.Documento;
import com.inetum.documentos.entity.Formato;
import com.inetum.documentos.util.ControllerUtil;

import lombok.Getter;
import lombok.Setter;

@ManagedBean(name = "documentoController")
@SessionScoped
public class DocumentoController implements Serializable {

	private DocumentoDao dao;
	private FormatoDao formatoDao;
	@Getter @Setter
	private Formato formatoSelected;
	@Getter @Setter
	private Integer idFormato;
	
	@Getter
	@Setter
	//private UploadedFile file;
	private Part file;
	
	private List<Documento> items = null;
	@Getter
	@Setter
	private Documento selected;

	
	private static String documentFolder = "/tmp/";
	private InputStream inputStreamFile = null;
	private String fileName = null;
	
	private static StreamedContent defaultFileContent;
	
	private StreamedContent documentoImg;
	
	@Getter
	@Setter
	private CroppedImage croppedImage;
	
	@Getter
	@Setter	
	private String newImageName;
	
	
	
	public DocumentoController() {
	}

	public List<Documento> getItems() {
		if (items == null) {
			// items = getFacade().findAll();
			items = getDao().findAll();
		}
		return items;
	}

	public Documento prepareCreate() {
		selected = new Documento();
		formatoSelected = new Formato();
		return selected;
	}
	
	public List<Formato> getFormatos() {
        return getFormatoDao().findAll();
    }	
	

	public void create() {
		try {
			
			
			if ((selected != null)) {
					//&&(formatoSelected !=null)) {				
				// if (!ControllerUtil.isValidationFailed())

				formatoSelected = formatoDao.findById(idFormato).get(); 
				selected.setFormato(formatoSelected);				
				selected.setFechahoracreacion(new Date());
						
				
				
	            
	            selected.setRuta(copyFile(file));
	            				
				getDao().save(selected);
				items = null;				
				ControllerUtil.addSuccessMessage("Creado!");
			}			

		} catch (Exception e) {
			System.out.println("error al crear " + e.getMessage());
			ControllerUtil.addErrorMessage("error al Crear!");
		}
	}
	
	

//	public void update() {
//		try {
//			if (selected != null) {
//				//if (!ControllerUtil.isValidationFailed())
//				selected.setFechahoramodificacion(new Date());
//				getDao().update(selected);
//				items = null;
//				ControllerUtil.addSuccessMessage("Actualizado!");
//			}
//			
//
//		} catch (Exception e) {
//			System.out.println("error al actualizar " + e.getMessage());
//			ControllerUtil.addErrorMessage("error al Actualizar!");
//		}
//	}

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

	private DocumentoDao getDao() {
		if (dao == null) {
			dao = new DocumentoDao();
		}
		return dao;
	}
	
	private FormatoDao getFormatoDao() {
		if (formatoDao == null) {
			formatoDao = new FormatoDao();
		}
		return formatoDao;
	}

	
	
	public StreamedContent getDocumentoImg() throws IOException{
		
		
		  //ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
		
		  //String imagenPath = externalContext.getRequestParameterMap().get("pathImagen");
		String imagenPath = context.getExternalContext().getRequestParameterMap().get("pathImagen");
		
		  if(imagenPath  == null || imagenPath.equals(""))
		  {
		   documentoImg = defaultFileContent;
		  }
		  else
		  {		   
		   ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		   InputStream inputStream = contextClassLoader.getResourceAsStream(imagenPath);
		   documentoImg = new DefaultStreamedContent(inputStream, "image/bmp");
		   
		  }

		  return documentoImg;

	}

 public String copyFile(Part file) {
        
        	//System.out.println("paso1:" + documentFolder + fileName);
            // write the inputStream to a FileOutputStream
            //OutputStream out = new FileOutputStream(new File(documentFolder + fileName));


            try (InputStream input = file.getInputStream()) {
    			String fileName = file.getSubmittedFileName();
    			File fileTemp = new File(documentFolder, fileName);
    	        Files.copy(input, fileTemp.toPath());
    	        return fileTemp.getAbsolutePath(); 
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
      return null;
    }	

 
 
	
	

}
