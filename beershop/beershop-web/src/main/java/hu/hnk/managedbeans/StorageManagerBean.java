package hu.hnk.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import hu.hnk.beershop.exception.NegativeQuantityNumber;
import hu.hnk.beershop.model.StorageItem;
import hu.hnk.beershop.service.interfaces.StorageService;

/**
 * @author Nandi
 *
 */
@ManagedBean(name = "storageManager")
@ViewScoped
public class StorageManagerBean implements Serializable {

	/**
	 * Az oszt�ly loggere.
	 */
	public static final Logger logger = Logger.getLogger(StorageManagerBean.class);

	/**
	 * A rakt�rt kezel� szolg�ltat�s.
	 */
	@EJB
	private StorageService storageService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<StorageItem> storage;

	@PostConstruct
	public void init() {
		storage = storageService.findAll();
	}

	public List<StorageItem> getStorage() {
		return storage;
	}

	public void setStorage(List<StorageItem> storage) {
		this.storage = storage;
	}

	public void saveChanges() {
		FacesMessage msg;

		try {
			storageService.saveAllChanges(storage);
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "M�dos�t�sok sikeresen mentve!",
					"M�dos�t�sok sikeresen mentve!");
			logger.info("Storage database updated succesfully.");
		} catch (NegativeQuantityNumber e) {
			logger.warn(e.getMessage(), e);
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Negat�v �rt�k nem t�rolhat�!",
					"Negat�v �rt�k nem t�rolhat�!");
			storage = storageService.findAll();
		}

		FacesContext.getCurrentInstance()
				.addMessage(null, msg);
	}

}
